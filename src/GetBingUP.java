import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetBingUP {
	private String word;
    private String up;

    public void get_word(String s){
      word = s;
    }

    public String get_up(){
        return up;
    }
    
    public void GetBingUP(){
    	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=TransData";
		String userName="sa";		
		String userPwd="220093";
		try {
			Class.forName(driverName);
			Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		    //System.out.println("连接数据库成功");
		    Statement stmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,   
		    		ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs = stmt.executeQuery("select bingup from words where word='"+word+"';");
		    if (!rs.next())
		    	up="0";
		    else{
		    	up=rs.getString(1).trim();
		    }
		} catch(Exception e) {
		    e.printStackTrace();
		    System.out.print("连接失败");
		}
    }
}
