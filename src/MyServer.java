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
                String forClient = inputStream.readUTF();//?????????????????????????
                outStream.writeChars(forClient);
            }
        }
        catch(IOException EX) {
            System.err.println(EX);
        }
    }


}
