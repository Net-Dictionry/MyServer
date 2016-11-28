/**
 * Created by apple on 16/11/28.
 */
import java.net.*;
import  java.io.*;

class NetSerch_youdao extends Thread{

    private String word;
    private String explain;

    public void get_word(String translation){
        word = translation;
    }

    public String get_explain(){
        return explain;
    }

    public void NetSerch(){
        try {


            URL url = new URL("http://fanyi.youdao.com/openapi.do");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("encoding", "UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);


            bw.write("keyfrom=Unxizhe&key=1391679309&type=data&doctype=json&version=1.1&q="+word);
            bw.flush();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            explain = br.readLine();

            bw.close();
            osw.close();
            os.close();
            br.close();
            isr.close();
            is.close();

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}