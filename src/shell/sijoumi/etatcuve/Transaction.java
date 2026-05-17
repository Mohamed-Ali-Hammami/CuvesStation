package shell.sijoumi.etatcuve;


import java.util.Date;

public class Transaction {

	private Integer id;
	private String heure;
	private String article;
	private String pompe;
	private double qte;
	private double montant;
	private String pistolet;
	private String prix;
	private String dates;


	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}




	@Override
	public String toString() {
		return "Transaction [id=" + id + ", pompe=" + pompe + "]";
	}

	public String getPompe() {
		return pompe;
	}
	public void setPompe(String pompe) {
		this.pompe = pompe;
	}
	public double getQte() {
		return qte;
	}
	public void setQte(double qte) {
		this.qte = qte;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getPistolet() {
		return pistolet;
	}
	public void setPistolet(String pistolet) {
		this.pistolet = pistolet;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}






}
