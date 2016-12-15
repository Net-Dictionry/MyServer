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
                String explain;

                String forClient_jinshan = forClient;

                NetSerch_youdao x =new NetSerch_youdao();
                x.get_word(forClient);
                x.NetSerch();
                forClient = x.get_explain();

                Carve_youdao y =new Carve_youdao();
                y.get_explain(forClient);
                y.Carve_youdao();
                explain = y.get_carve();
                //-----------------------------
                NetSearch_jinshan z =new NetSearch_jinshan();
                z.get_word(forClient_jinshan);
                z.NetSerch();
                forClient_jinshan = z.get_explain();
                System.out.print(forClient_jinshan);

                //------------------------------
                //System.out.print(explain);
                outStream.writeUTF(explain);

            }
        }
        catch(IOException EX) {
            System.err.println(EX);
        }
    }


}
