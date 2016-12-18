import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserLogout {
	private String name;
	
	 public void get_name(String a){
		 this.name=a;
	 }
	 
	 public boolean UserLogout(){
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
			 int judge=stmt.executeUpdate("update users set sta=0 where uname='"+name+"'");
			 if (judge==1)
				 return true;
			 else
				 return false;
			 
		 } catch(Exception e) {
			    e.printStackTrace();
			    System.out.print("连接失败");
		 }
		 return false;
	 }
}
