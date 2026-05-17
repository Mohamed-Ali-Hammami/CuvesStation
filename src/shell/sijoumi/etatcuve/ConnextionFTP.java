package shell.sijoumi.etatcuve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
public class ConnextionFTP {
 

	public boolean verifPompe(String pome, List<String> listpompes) {
		int i = 0;

		while (i <= listpompes.size())
			if (listpompes.get(i).equals(pome))
				return true;
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public List<Geauge> getgeauge(){
		 List<Geauge> listGeauge = new ArrayList<Geauge>();
		 BufferedReader IN2;
		/* try {
			Runtime. getRuntime(). exec("cmd /c start \"\" D:/test1.wsf");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try {
			 

			IN2 = new BufferedReader(new FileReader("D:/Stok.txt"));
		
			System.out.println("D:/Stok.txt");
			String ligne;
			String heure="";
			String l2;			 
			String motf = "";		 
			String[] indexouvrtures;			 
			try {
				while ((ligne = IN2.readLine()) != null) {
					if(ligne.equals("I20100"))						 
                     heure= IN2.readLine();
					System.out.println("\n\nheure"+heure);
					if (ligne.contains("RESE PRODUIT")) {
						while ((l2 = IN2.readLine()) != null && !l2.contains("exit")) {
							motf = motf + l2 + ";";
							//System.out.println("\n\nline"+l2);
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
				/*System.out.println("article"+ indexouvrtures[i]
						.substring(6,16));
				System.out.println("stock"+Double.parseDouble(indexouvrtures[i]
						.substring(29,33)));
				
				System.out.println("creux"+Double.parseDouble(indexouvrtures[i]
						.substring(47,53)));
				
				System.out.println("eau"+Double.parseDouble(indexouvrtures[i]
						.substring(68,70)));
				
				System.out.println("Tem"+Double.parseDouble(indexouvrtures[i]
						.substring(73,78)));*/
				
				//Double.parseDouble(indexouvrtures[i] .substring(29,3)))
				i=i+1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return listGeauge;
	 }
/*
	public List<Geauge> selectGeauge() throws ClassNotFoundException {//

//	try {

		String sql = "SELECT Timestamp,LevelQuantity ,Temperature FROM TankStatus limit 6";
	 
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Geauge> listGeauge = new ArrayList<Geauge>();
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/tankstatusFiles/TankStatus.db3");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet != null)
				System.out.println("");
			 

			int i = 1;
			while (resultSet.next()) {
				 
				 
				if(i==1){
				Geauge g = new Geauge();
				g.setCuve("GASOIL 1");				 
				double LevelQuantity = Double.parseDouble(resultSet.getString("LevelQuantity")) / 100;
				double temperature = Double.parseDouble(resultSet.getString("Temperature")) / 100;
				g.setHeure(resultSet.getString("Timestamp"));
				 
				g.setLevel(LevelQuantity);
				g.setTemperature(temperature);
				listGeauge.add(g);
				//System.out.println("g " + g);
				}
				
				if(i==2){
					Geauge g = new Geauge();
					g.setCuve("GASOIL 2");
					 
					double LevelQuantity = Double.parseDouble(resultSet.getString("LevelQuantity")) / 100;
					double temperature = Double.parseDouble(resultSet.getString("Temperature")) / 100;
					 
					g.setLevel(LevelQuantity);
					g.setTemperature(temperature);
					listGeauge.add(g);
					//System.out.println("g " + g);
					}
				if(i==3){
					Geauge g = new Geauge();
					g.setCuve("SSP");
					 
					double LevelQuantity = Double.parseDouble(resultSet.getString("LevelQuantity")) / 100;
					double temperature = Double.parseDouble(resultSet.getString("Temperature")) / 100;
					
					g.setLevel(LevelQuantity);
					g.setTemperature(temperature);
					listGeauge.add(g);
					//System.out.println("g " + g);
					}
				
				if(i==4){
					Geauge g = new Geauge();
					g.setCuve("PETROLE");
				 
					double LevelQuantity = Double.parseDouble(resultSet.getString("LevelQuantity")) / 100;
					double temperature = Double.parseDouble(resultSet.getString("Temperature")) / 100;
					 
					g.setLevel(LevelQuantity);
					g.setTemperature(temperature);
					listGeauge.add(g);
					//System.out.println("g " + g);
					}
				
				if(i==5){
					Geauge g = new Geauge();
					g.setCuve("GASOIL 50");					 
					double LevelQuantity = Double.parseDouble(resultSet.getString("LevelQuantity")) / 100;
					double temperature = Double.parseDouble(resultSet.getString("Temperature")) / 100;				
					g.setLevel(LevelQuantity);
					g.setTemperature(temperature);
					listGeauge.add(g);
					//System.out.println("g " + g);
					}
				
				if(i==6){
					Geauge g = new Geauge();
					g.setCuve("SSP 2");					 
					double LevelQuantity = Double.parseDouble(resultSet.getString("LevelQuantity")) / 100;
					double temperature = Double.parseDouble(resultSet.getString("Temperature")) / 100;				 
					g.setLevel(LevelQuantity);
					g.setTemperature(temperature);
					listGeauge.add(g);
					//System.out.println("g " + g);
					}
				
				
				i=i+1;
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			//System.out.println("  connexion erreur de connexion" + sqlex.getErrorCode());
		}
		
		return listGeauge;

	}*/
	 
	 /*
	public     List<Transaction> selectAlljournal(Date date,String txtHhmm,String txt_h_fin,List<String>listpompes) throws ClassNotFoundException {//
		listjournal = new ArrayList<Transaction>();

		
		try {

			String sql = "SELECT Time, Journaltext FROM JournalMaster  where Selection ='DISP HANDLER' and Time BETWEEN  '"+txtHhmm+"' and '"+txt_h_fin+"'";
			String dates="";

			SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");  				 
			dates = f.format(date);			     

			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				Class.forName("org.sqlite.JDBC");
				//connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/"+dates+".db3"); 
				connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/"+dates+".db3"); 

				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);

				//  System.out.println("  connexion");
				SimpleDateFormat f2 = new SimpleDateFormat("dd-MM-yyyy");

				int i=1;
				while (resultSet.next()) {
					String[] requetes;
				 Transaction tr= new Transaction();    
					//tr.setId2(i);
					String journal = resultSet.getString("Journaltext");
					//System.out.println("\n\n\njournal"+journal);
					//tr.setRequete(journal);
					String time = resultSet.getString("Time");
					requetes = journal.split(" ");    			 
					DecimalFormat df = new DecimalFormat("0.000");
					tr.setHeure(time);
					tr.setDates(f.format(new Date()));
				
					//tr.setDates(new Date());

					if(requetes.length>=3)
						if (requetes[2].contains("PMP") && listpompes.contains(requetes[2]) ) {    					
							//-0 PAYABLE PMP:6 CMD:2 NZL:4 FUEL:3 VOL:17.57000 AMT:26.00400 ID:527 FLW:40 PRC:1.480000 PID:20 STRT:0227.000709
							tr.setPompe( requetes[2]);
							tr.setPistolet( requetes[4] );
							tr.setArticle(getArticle(Integer.parseInt(requetes[5].substring(5))));
							tr.setQte(Double.parseDouble(requetes[6].substring(4)));
							tr.setMontant(Double.parseDouble(requetes[7].substring(4)));
							double p = Double.parseDouble(requetes[10].substring(4));
							tr.setPrix(df.format(p));
                            //  tr.setId(i);
							listjournal.add(tr);
							i=i+1;
						}



				}

			}
			catch(SQLException sqlex){
				sqlex.printStackTrace();
				//System.out.println("  connexion erreur de connexion"+sqlex.getErrorCode());
			}

		} catch(Exception e) {
			System.out.println("erreur");
		}

		return listjournal; 
	}
	*/
	private     String getArticle(Integer num) {
		if (num == 2)
			return "SSP";
		else if (num == 3)
			return "GASOIL";
		else if (num == 4)
			return "SSP-V-POWER";
		else if (num == 9)
			return "PETROLE";
		else
			return "GASOIL 50";
	}
	 
 

}
