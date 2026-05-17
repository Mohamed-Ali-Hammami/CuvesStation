package shell.sijoumi.etatcuve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Paramettre   extends JFrame implements ActionListener{
	private Integer qteG1;
	private Integer qteG2;
	private Integer qteSSP1;
	private Integer qtePET;
	private Integer qteG50;
	private Integer qteSSp2;
	public Paramettre() {
	 JFrame frame =new JFrame();
     JPanel panel =new JPanel();      
     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
     int width = gd.getDisplayMode().getWidth();
     int height = gd.getDisplayMode().getHeight();
     Color  white   = new Color(135,206,235);
     panel.setBackground(white);        
     panel.setForeground(Color.BLACK);  
     try {
			BufferedReader IN = new BufferedReader(new FileReader("C:\\sqlite\\Shell_App\\alerte.txt"));
			try {
				int i =0;
				String row = null;
				while ((row = IN.readLine()) != null) {
					
					if (  i <= 5) {
					//	System.out.println("\n\n\n row"+Integer.parseInt(row));
						switch(i) {
						case(0):setQteG1(Integer.parseInt(row));
						case(1):setQteG2(Integer.parseInt(row));
						case(2):setQteSSP1(Integer.parseInt(row));
						case(3):setQtePET(Integer.parseInt(row));
						case(4):setQteG50(Integer.parseInt(row));
						case(5):setQteSSp2(Integer.parseInt(row));
						 
						}
					}
					i=i+1;
				}//fin while
			}catch(Exception e) {
				
			}
			 
			IN.close();
		/*	File fichier2 = new File("M:/FTP_E00/num.txt"); // définir l'arborescence
			fichier2.createNewFile();
			FileWriter ffw = new FileWriter(fichier2);
			s = s + 1;
			String ch = s + "";
			ffw.write(ch);
			ffw.close();*/

		} catch (IOException ex) {
			System.out.println("erreur de lecture du fichier");
			ex.printStackTrace();

		}

     Object[][] donnees = {
             {"GASOIL1", getQteG1(),30322},
             {"GASOIL2", getQteG2(),7893},
             {"SSP1", getQteSSP1(),7250},
             {"PETROLE", getQtePET(),20446 },
             {"GASOIL50", getQteG50(),7250},
             {"SSP2", getQteSSp2(), 7893},
            
     };

     String[] entetes = {"CUVE", "QTE MIN","CAPACITE"};

     JTable tableau = new JTable(donnees, entetes);
     

     getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
     
     frame.setSize(1000, 540);        
     frame.setTitle("Paramétrage");
     //frame.setDefaultCloseOperation(JFrame,Exit_ON_CLOSE);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //image background
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setSize(1000, 540);;
		ImageIcon i = new ImageIcon("background.jpg");
		Image image2 = i.getImage(); // transform it 
		Image newimg = image2.getScaledInstance(1920, 1080,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		i = new ImageIcon(newimg); 
	    lblNewLabel.setIcon(i);
		frame.getContentPane().add(lblNewLabel);
     ImageIcon image = new ImageIcon("shell.png");
      frame.add(panel);       
     frame.setIconImage(image.getImage());
    // frame.setVisible(true);
	}

	public Integer getQteG1() {
		return qteG1;
	}

	public void setQteG1(Integer qteG1) {
		this.qteG1 = qteG1;
	}

	public Integer getQteG2() {
		return qteG2;
	}

	public void setQteG2(Integer qteG2) {
		this.qteG2 = qteG2;
	}

	public Integer getQteSSP1() {
		return qteSSP1;
	}

	public void setQteSSP1(Integer qteSSP1) {
		this.qteSSP1 = qteSSP1;
	}

	public Integer getQtePET() {
		return qtePET;
	}

	public void setQtePET(Integer qtePET) {
		this.qtePET = qtePET;
	}

	public Integer getQteG50() {
		return qteG50;
	}

	public void setQteG50(Integer qteG50) {
		this.qteG50 = qteG50;
	}

	public Integer getQteSSp2() {
		return qteSSp2;
	}

	public void setQteSSp2(Integer qteSSp2) {
		this.qteSSp2 = qteSSp2;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
