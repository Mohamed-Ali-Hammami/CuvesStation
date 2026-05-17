package shell.sijoumi.etatcuve;

public class MainExeScan {
	
	 
		public static void main(String[] args) {
			try {
			Runtime.getRuntime().exec("tonProg.exe");
			}
			catch (Exception e) { e.toString(); }
			}
	

}
