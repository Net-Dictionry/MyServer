/**
 * Created by apple on 16/11/27.
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * Created by apple on 16/11/27.
 */
public class MyServer extends JFrame {

    //new MyClient();
    private JTextArea jta = new JTextArea();
    public static void main(String[] args) {
        new MyServer();
    }

    public MyServer(){                      //new a server
    	setLayout(new BorderLayout());
        add(new JScrollPane(jta),BorderLayout.CENTER);
        setTitle("MyServer");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    	ServerSocket serverSocket = null;
    	Socket request=null;
    	Thread receiveThread=null;
    	try{
    		serverSocket=new ServerSocket(8088);
    		jta.append("Server start at: "+ new Date()+'\n');
    		while (true){
    			request=serverSocket.accept();
    			receiveThread=new serverThread(request,jta);
    			receiveThread.start();
    		}
    	} catch(IOException EX) {
            System.err.println(EX);
        }
    }
    
    /*public MyServer(){
        setLayout(new BorderLayout());
        add(new JScrollPane(jta),BorderLayout.CENTER);
        setTitle("MyServer");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        try{

            ServerSocket serverSocket = new ServerSocket(8088);
            jta.append("Server start at: "+ new Date()+'\n');
            Socket socket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
            while(true){

                String forClient = inputStream.readUTF();
                String explain="";
                
                String ClientSeg[]=SegmentClient(forClient);
                //System.out.print(ClientSeg[0]);
                //System.out.print(ClientSeg[1]);
                if (ClientSeg[0].equals("10")){                 //search
                	String forClient_youdao =ClientSeg[1];
                	String forClient_jinshan = ClientSeg[1];
                	String forClient_bing = ClientSeg[1];

	                NetSerch_youdao Youdaox =new NetSerch_youdao();
	                Youdaox.get_word(forClient_youdao);
	                Youdaox.NetSerch();
	                forClient_youdao = Youdaox.get_explain();
	                Carve_youdao Youdaoy =new Carve_youdao();
	                Youdaoy.get_explain(forClient_youdao);
	                Youdaoy.Carve_youdao();
	                GetYoudaoUP Youdaoz=new GetYoudaoUP();
	                Youdaoz.get_word(ClientSeg[1]);
	                Youdaoz.GetYoudaoUP();
	                explain += Youdaoy.get_carve()+"@"+Youdaoz.get_up();
	                //System.out.println(explain);
	                //-----------------------------
	                NetSearch_jinshan Jinshanx =new NetSearch_jinshan();
	                Jinshanx.get_word(forClient_jinshan);
	                Jinshanx.NetSerch();
                	forClient_jinshan = Jinshanx.get_explain();
                	Carve_jinshan Jinshany =new Carve_jinshan();
                	Jinshany.get_explain(forClient_jinshan);
                	Jinshany.Carve_jinshan();
                	GetJinshanUP Jinshanz=new GetJinshanUP();
                	Jinshanz.get_word(ClientSeg[1]);
                	Jinshanz.GetJinshanUP();
                	explain+="@"+Jinshany.get_carve()+"@"+Jinshanz.get_up();
                	//System.out.println(explain);
                	//System.out.print(forClient_jinshan);

	                //--------------------------------
	                NetSearch_other Bingx = new NetSearch_other();
	                Bingx.get_word(forClient_bing);
	                Bingx.NetSerch();
	                forClient_bing = Bingx.get_explain();
	                Carve_bing Bingy =new Carve_bing();
	                Bingy.get_explain(forClient_bing);
	                Bingy.Carve_bing();
	                GetBingUP Bingz=new GetBingUP();
	                Bingz.get_word(ClientSeg[1]);
	                Bingz.GetBingUP();
                	explain+="@"+Bingy.get_carve()+"@"+Bingz.get_up();
                	//System.out.println(explain);
	                //System.out.print(forClient_other);
	                //------------------------------
	                //System.out.print("222");
	                //outStream.writeUTF(explain);
                }
                else if (ClientSeg[0].equals("11")){         //up youdao
                	ThumbsUp thumbsup=new ThumbsUp();
                	thumbsup.get_word(ClientSeg[1], 1);
                	boolean judge=thumbsup.ThumbsUp();
                	if (judge)
                		explain="true";
                	else
                		explain="false";
                }
                else if (ClientSeg[0].equals("12")){         //up jinshan
                	ThumbsUp thumbsup=new ThumbsUp();
                	thumbsup.get_word(ClientSeg[1], 2);
                	boolean judge=thumbsup.ThumbsUp();
                	if (judge)
                		explain="true";
                	else
                		explain="false";
                }
                else if (ClientSeg[0].equals("13")){          //up bing
                	ThumbsUp thumbsup=new ThumbsUp();
                	thumbsup.get_word(ClientSeg[1], 3);
                	boolean judge=thumbsup.ThumbsUp();
                	if (judge)
                		explain="true";
                	else
                		explain="false";
                }
                else if (ClientSeg[0].equals("20")){          //User Register
                	UserRegister Register=new UserRegister();
                	Register.get_account(ClientSeg[1]);
                	boolean judge=Register.UserRegister();
                	if (judge)
                		explain="true";
                	else
                		explain="false";
                }
                else if (ClientSeg[0].equals("21")){          //User Login
                	UserLogin Login=new UserLogin();
                	Login.get_account(ClientSeg[1]);
                	boolean judge=Login.UserLogin();
                	if (judge)
                		explain="true";
                	else
                		explain="false";
                }
                outStream.writeUTF(explain);
            }
        }
        catch(IOException EX) {
            System.err.println(EX);
        }
    }*/

}

class serverThread extends Thread{        //build a new thread
	Socket clientRequest; 
    JTextArea jta;
    DataInputStream inputStream;
    DataOutputStream outStream;
    
    public serverThread(Socket s,JTextArea j) {
    	this.clientRequest=s;
    	this.jta=j;
        try{
        	inputStream = new DataInputStream(clientRequest.getInputStream());
            outStream = new DataOutputStream(clientRequest.getOutputStream());
        } catch(IOException EX) {
            System.err.println(EX);
        }
        
        jta.append("Now is: "+ new Date()+" Port:"+clientRequest.getLocalPort()+'\n');
    }
    
    public void run() {
    	String forClient=null;
    	
    	while (true){
    		try {  
    			forClient = inputStream.readUTF();
    			String explain="";
    			String ClientSeg[]=SegmentClient(forClient);

            	if (ClientSeg[0].equals("10")){                 //search
            		String forClient_youdao =ClientSeg[1];
            		String forClient_jinshan = ClientSeg[1];
            		String forClient_bing = ClientSeg[1];

                	NetSerch_youdao Youdaox =new NetSerch_youdao();    //有道查词
                	Youdaox.get_word(forClient_youdao);
                	Youdaox.NetSerch();
                	forClient_youdao = Youdaox.get_explain();
                	Carve_youdao Youdaoy =new Carve_youdao();
                	Youdaoy.get_explain(forClient_youdao);
                	Youdaoy.Carve_youdao();
                	GetYoudaoUP Youdaoz=new GetYoudaoUP();           //有道点赞
                	Youdaoz.get_word(ClientSeg[1]);
                	Youdaoz.GetYoudaoUP();
                	explain += Youdaoy.get_carve()+"@"+Youdaoz.get_up();

                	//-----------------------------
                	NetSearch_jinshan Jinshanx =new NetSearch_jinshan();    //金山查词
                	Jinshanx.get_word(forClient_jinshan);
                	Jinshanx.NetSerch();
            		forClient_jinshan = Jinshanx.get_explain();
            		Carve_jinshan Jinshany =new Carve_jinshan();
            		Jinshany.get_explain(forClient_jinshan);
            		Jinshany.Carve_jinshan();
            		GetJinshanUP Jinshanz=new GetJinshanUP();           //金山点赞
            		Jinshanz.get_word(ClientSeg[1]);
            		Jinshanz.GetJinshanUP();
            		explain+="@"+Jinshany.get_carve()+"@"+Jinshanz.get_up();
            	
                	//--------------------------------
                	NetSearch_other Bingx = new NetSearch_other();   //必应查词
                	Bingx.get_word(forClient_bing);
                	Bingx.NetSerch();
                	forClient_bing = Bingx.get_explain();
                	Carve_bing Bingy =new Carve_bing();
                	Bingy.get_explain(forClient_bing);
                	Bingy.Carve_bing();
                	GetBingUP Bingz=new GetBingUP();                  //必应点暂
                	Bingz.get_word(ClientSeg[1]);
                	Bingz.GetBingUP();
            		explain+="@"+Bingy.get_carve()+"@"+Bingz.get_up();
            	}
            	else if (ClientSeg[0].equals("11")){         //up youdao
            		ThumbsUp thumbsup=new ThumbsUp();
            		thumbsup.get_word(ClientSeg[1], 1);
            		boolean judge=thumbsup.ThumbsUp();
            		if (judge)
            			explain="true";
            		else
            			explain="false";
            	}
            	else if (ClientSeg[0].equals("12")){         //up jinshan
            		ThumbsUp thumbsup=new ThumbsUp();
            		thumbsup.get_word(ClientSeg[1], 2);
            		boolean judge=thumbsup.ThumbsUp();
            		if (judge)
            			explain="true";
            		else
            			explain="false";
            	}
            	else if (ClientSeg[0].equals("13")){          //up bing
            		ThumbsUp thumbsup=new ThumbsUp();
            		thumbsup.get_word(ClientSeg[1], 3);
            		boolean judge=thumbsup.ThumbsUp();
            		if (judge)
            			explain="true";
            		else
            			explain="false";
            	}
            	else if (ClientSeg[0].equals("20")){          //User Register
            		UserRegister Register=new UserRegister();
            		Register.get_account(ClientSeg[1]);
            		boolean judge=Register.UserRegister();
            		if (judge)
            			explain="true";
            		else
            			explain="false";
            	}
            	else if (ClientSeg[0].equals("21")){          //User Login
            		UserLogin Login=new UserLogin();
            		Login.get_account(ClientSeg[1]);
            		boolean judge=Login.UserLogin();
            		if (judge)
            			explain="true";
            		else
            			explain="false";
            	}
            	else if (ClientSeg[0].equals("22")){          //User Logout
            		UserLogout Logout=new UserLogout();
            		Logout.get_name(ClientSeg[1]);
            		boolean judge=Logout.UserLogout();
            		if (judge)
            			explain="true";
            		else
            			explain="false";
            	}
            	else if (ClientSeg[0].equals("23")){          //User Online
            		GetOnlineUser onlineuser=new GetOnlineUser();
            		explain+=onlineuser.GetOnlineUser();
            	}
            	outStream.writeUTF(explain);
    		} catch(IOException EX) {
    			try {
					clientRequest.close();
					break;
				} catch (IOException e) {
					System.err.println(e);
				}
        		System.err.println(EX);
        	}
    	}
    }
	
	public static String[] SegmentClient(String str){       //拆分操作码和内容
    	String[] res = new String[2];
    	res[0]=str.substring(0, 2);
    	res[1]=str.substring(2, str.length());
    	return res;
    }
}
