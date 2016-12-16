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
        MyServer x = new MyServer();
    }

    public MyServer(){
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
                System.out.print(ClientSeg[0]);
                System.out.print(ClientSeg[1]);
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
	                explain += Youdaoy.get_carve();
	                //System.out.println(explain);
	                //-----------------------------
	                NetSearch_jinshan Jinshanx =new NetSearch_jinshan();
	                Jinshanx.get_word(forClient_jinshan);
	                Jinshanx.NetSerch();
                	forClient_jinshan = Jinshanx.get_explain();
                	Carve_jinshan Jinshany =new Carve_jinshan();
                	Jinshany.get_explain(forClient_jinshan);
                	Jinshany.Carve_jinshan();
                	explain+="@"+Jinshany.get_carve();
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
                	explain+="@"+Bingy.get_carve();
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
                outStream.writeUTF(explain);
            }
        }
        catch(IOException EX) {
            System.err.println(EX);
        }
    }

    public static String[] SegmentClient(String str){
    	String[] res = new String[2];
    	res[0]=str.substring(0, 2);
    	res[1]=str.substring(2, str.length());
    	return res;
    }
}
