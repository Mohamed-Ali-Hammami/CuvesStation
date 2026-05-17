package shell.sijoumi.etatcuve;
 

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TransactionTableModel extends AbstractTableModel {
	
	String[] columnNames  = new String[] {"Date","Heure","Produit","Pompe","Pistole","Qte","Prix","Montant"};
     List<Transaction> listtTr;

    List<Transaction> data = new ArrayList<Transaction>();

    public TransactionTableModel( List<Transaction> listtTr) {
       //(Proper way to use a setter method to set the Person)
       data.addAll(listtTr); 
    }

    //Abstract method implementation
    public int getRowCount() {
       return data.size();
    }

    //Abstract method implementation
    public int getColumnCount() {
       return columnNames.length;
    }

    //Abstract method implementation
    public Object getValueAt(int row, int colum) {
      Transaction personObj = data.get(row);

      switch(colum){
        case 0: return personObj.getDates();
        case 1: return personObj.getHeure();
        case 2: return personObj.getArticle();
        case 3: return personObj.getPompe();
        case 4: return personObj.getPistolet();
        case 5: return personObj.getQte();
        case 6: return personObj.getPrix();
        case 7: return personObj.getMontant();
        default : return null;
      }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addData(Transaction person){
        data.add(person);
    }
}