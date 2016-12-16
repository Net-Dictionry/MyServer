
public class Carve_jinshan {
	private String explain;
    private String carve_explain;

    public void get_explain(String line){
      explain = line;
    }

    public String get_carve(){
        return carve_explain;
    }

    public void Carve_jinshan(){

        carve_explain = "";
        int index1=explain.indexOf("pos");
		int index2=explain.indexOf("pos",index1+1);
		if (index1==-1){
			carve_explain+="This word can not be found in jinshan!";
			return;
		}
		while (index1!=-1){
			carve_explain+=explain.substring(index1+4, index2-2)+'\t';
			index1=explain.indexOf("acceptation",index2+1);
			index2=explain.indexOf("acceptation",index1+1);
			carve_explain+=explain.substring(index1+12, index2-2)+'\n';
			index1=explain.indexOf("pos",index2+1);
			index2=explain.indexOf("pos",index1+1);
		}
    }
}
