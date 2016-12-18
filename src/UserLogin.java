import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserLogin {
	 private String account;
		
	 public void get_account(String a){
		 this.account=a;
	 }
	 
	 public boolean UserLogin(){
		 String[] temp=SegAccount(account);
		 String name=temp[0];
		 String password=temp[1];
		 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		 String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=TransData";
	 	 String userName="sa";		
		 String userPwd="220093";
		 
		 try {
			 Class.forName(driverName);
			 Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			 System.out.println("连接数据库成功");
			 Statement stmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
			    	ResultSet.CONCUR_READ_ONLY);
			 ResultSet rs = stmt.executeQuery("select * from users;");
			 while (rs.next()){
				 if (rs.getString("uname").trim().equals(name)){
					 if (rs.getInt("sta")==1)
						 return false;
					 if (rs.getString("upassword").trim().equals(password)){
						 stmt.executeUpdate("update users set sta=1 where uname='"+name+"'");
						 return true;
					 }
				 }
			 }
			 
		 } catch(Exception e) {
			    e.printStackTrace();
			    System.out.print("连接失败");
		 }
		 return false;
	 }
	 
	 public String[] SegAccount(String str){
		 String[] temp=new String[2];
		 int index=str.indexOf('\t');
		 temp[0]=str.substring(0, index);
		 temp[1]=str.substring(index+1,str.length());
		 return temp;
	 }
}
