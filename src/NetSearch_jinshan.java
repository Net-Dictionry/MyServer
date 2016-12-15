/**
 * Created by apple on 16/12/15.
 */

import java.net.*;
import  java.io.*;

class NetSearch_jinshan  extends Thread{

        private String word;
        private String explain;
        private String _string;

    public void get_word(String translation){
            word = translation;
        }

        public String get_explain(){
            return explain;
        }

        public void NetSerch(){
            try {


                URL url = new URL("http://dict-co.iciba.com/api/dictionary.php?w="+word+"&key=5A3C4473D549126F7E440B44C1E9BCB8");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.addRequestProperty("encoding", "UTF-8");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                connection.setRequestMethod("POST");

                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);


                //bw.write(word+"&key=5A3C4473D549126F7E440B44C1E9BCB8");
                bw.flush();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"UTF-8");
                BufferedReader br = new BufferedReader(isr);

               /* _string = br.readLine();
                while(!_string.isEmpty()){
                    explain = explain + _string;
                    _string = br.readLine();
                }*/

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

