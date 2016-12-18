import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ThumbsUp {
	private String word;
	private int up;

    public void get_word(String a,int b){
      word = a;
      up=b;
    }
    
	public boolean ThumbsUp(){
		if (word.equals(""))
			return false;
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
		    ResultSet rs = stmt.executeQuery("select * from words where word='"+word+"';");
		    if (!rs.next()){
		    	if (up==1)
		    		stmt.executeUpdate("INSERT INTO words VALUES ('"+word+"',1,0,0);");
		    	else if (up==2)
		    		stmt.executeUpdate("INSERT INTO words VALUES ('"+word+"',0,1,0);");
		    	else
		    		stmt.executeUpdate("INSERT INTO words VALUES ('"+word+"',0,0,1);");
		    }
		    else{
		    	if (up==1)
		    		stmt.executeUpdate("update words set youdaoup=youdaoup+1 where word='"+word+"';");
		    	else if (up==2)
		    		stmt.executeUpdate("update words set jinshanup=jinshanup+1 where word='"+word+"';");
		    	else
		    		stmt.executeUpdate("update words set bingup=bingup+1 where word='"+word+"';");
		    }
		} catch(Exception e) {
		    e.printStackTrace();
		    System.out.print("连接失败");
		}
		
		return true;
	}
}
