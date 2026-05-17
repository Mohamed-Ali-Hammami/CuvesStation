package shell.sijoumi.etatcuve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileInverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Geauge> getgeauge2(){
		 List<Geauge> listGeauge = new ArrayList<Geauge>();
		 BufferedReader IN2;
		
		try { 
			IN2 = new BufferedReader(new FileReader("D:/Stok.txt"));		
			System.out.println("D:/Stok.txt");
			String ligne;
			String heure="";
			String l2;			 
			String motf = "";		 
			String[] indexouvrtures;		
			int counter=0;
			try {
				while ((ligne = IN2.readLine()) != null) {
					 counter++;		 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0;i<counter;i++) {
				ligne = IN2.readLine();
			}
			try {
				while ((ligne = IN2.readLine()) != null) {
					if(ligne.equals("I20100"))						 
                   heure= IN2.readLine();
					System.out.println("\n\nheure"+heure);
					if (ligne.contains("RESE PRODUIT")) {
						while ((l2 = IN2.readLine()) != null && !l2.contains("exit")) {
							motf = motf + l2 + ";";						 
						}
					}					 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			indexouvrtures = motf.split(";");
			//System.out.println("\n\nsize table"+indexouvrtures.length);
			int i = 0;
			while (i < indexouvrtures.length) {
			 
				//System.out.println(indexouvrtures[i]);
				Geauge g1 = new Geauge();
				g1.setCuve( indexouvrtures[i] .substring(6,16));				 
				 
				g1.setHeure("");
				 g1.setEau(Double.parseDouble(indexouvrtures[i]
						.substring(68,70)));
				g1.setLevel(Double.parseDouble(indexouvrtures[i]
						.substring(28,33)));
				g1.setTemperature(Double.parseDouble(indexouvrtures[i]
						.substring(73,78)));
				g1.setCreux(Double.parseDouble(indexouvrtures[i]
						.substring(47,53)));
				
				g1.setHeure(heure);
				//System.out.println(g1);
				listGeauge.add(g1);
			 
				i=i+1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return listGeauge;
	 }
	
	
}
