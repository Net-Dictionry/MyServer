import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetOnlineUser {
	public String GetOnlineUser(){
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		 String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=TransData";
	 	 String userName="sa";		
		 String userPwd="220093";
		 String users="";
		 
		 try {
			 Class.forName(driverName);
			 Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
			 System.out.println("连接数据库成功");
			 Statement stmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
			    	ResultSet.CONCUR_READ_ONLY);
			 ResultSet rs = stmt.executeQuery("select * from users where sta=1;");
			 while (rs.next()){
				 users+=(rs.getString("uname").trim()+'\n');
			 }
			 
		 } catch(Exception e) {
			    e.printStackTrace();
			    System.out.print("连接失败");
		 }
		 return users;
	}
}
