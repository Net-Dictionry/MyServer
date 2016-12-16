
public class Carve_bing {
	private String explain;
    private String carve_explain;

    public void get_explain(String line){
      explain = line;
    }

    public String get_carve(){
        return carve_explain;
    }

    public void Carve_bing(){

        carve_explain = "";
        int index1=explain.indexOf("pos");
		int index2=explain.indexOf("def",index1+1);
		int index3=explain.indexOf('}', index2+1);
		if (index1==-1){
			carve_explain+="This word can not be found in bing!";
			return;
		}
		while (index1!=-1&&index2!=-1&&index3!=-1){
			carve_explain+=explain.substring(index1+6, index2-3)+'\t';
			carve_explain+=explain.substring(index2+6, index3-1)+'\n';
			index1=explain.indexOf("pos",index3+1);
			index2=explain.indexOf("def",index1+1);
			index3=explain.indexOf('}', index2+1);
		}
    }
}
