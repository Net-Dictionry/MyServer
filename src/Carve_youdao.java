/**
 * Created by apple on 16/11/28.
 */
public class Carve_youdao {

    private String explain;
    private String carve_explain;

    public void get_explain(String line){
      explain = line;
    }

    public String get_carve(){
        return carve_explain;
    }

    public void Carve_youdao(){

        carve_explain = explain;

    }

}
