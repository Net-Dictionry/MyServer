import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by apple on 16/12/15.
 */
public class NetSearch_other extends Thread{

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


                URL url = new URL("http://xtk.azurewebsites.net/BingDictService.aspx?Word=");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.addRequestProperty("encoding", "UTF-8");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                connection.setRequestMethod("POST");

                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);


                bw.write(word);
                bw.flush();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"UTF-8");
                BufferedReader br = new BufferedReader(isr);

               // explain = br.readLine();
                String line = null;
                while((line = br.readLine()) != null) {
                    explain += line;
                }


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

