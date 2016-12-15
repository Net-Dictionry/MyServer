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

        carve_explain = "";
		int index1=explain.indexOf('[');
		int index2=explain.indexOf(']');
		carve_explain+=explain.substring(index1+2, index2-1);
		if (!isChinese(carve_explain)){
			carve_explain = "This word can not be found in youdao!";
			return;
		}
		carve_explain+="\n";
		int index3=explain.indexOf('[',index1+1);
		int index4=explain.indexOf(']',index2+1);
		String temp=explain.substring(index3+1, index4);
		int index5=-1;
		int index6=-1;
		while(index6<temp.length()-1){
			index5=temp.indexOf('"',index6+1);
			index6=temp.indexOf('"',index5+1);
			carve_explain=carve_explain+temp.substring(index5+1, index6)+'\n';
		}
    }
    
    public static boolean isChinese(char c) {
        return c >= 0x4E00 &&  c <= 0x9FA5;
  }

  public static boolean isChinese(String str) {
      if (str == null) return false;
      for (char c : str.toCharArray()) {
          if (isChinese(c)) return true;
      }
      return false;
  }

}
