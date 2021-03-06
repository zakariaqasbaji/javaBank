package Personnes;

import java.util.ArrayList;
import java.util.Date;

public class Employe extends Personne {
	
	
	private double evaluation;
	private Date dateEmbauche;
	private ArrayList<Client> clients = new ArrayList<>();
	
	
	boolean mutation()
	{
            return (this.evaluation>=70);
		
	}
	
	public void afficher() {
		System.out.println("Nom: "+this.nom);
		System.out.println("Prenom: "+this.prenom);
		System.out.println("Evaluation: "+this.evaluation);
		System.out.println("Date d'embauche: "+this.dateEmbauche);
		
		}


	public Employe(String nom, String prenom,String cin ,double evaluation, Date dateEmbauche,
			ArrayList<Client> clients) {
		super(nom, prenom,cin);
		this.evaluation = evaluation;
		this.dateEmbauche = dateEmbauche;
		this.clients = clients;
	}
        public Employe(String nom, String prenom,String cin , Date dateEmbauche,
			ArrayList<Client> clients) {
		super(nom, prenom,cin);
		this.evaluation = 70;
		this.dateEmbauche = dateEmbauche;
		this.clients = clients;
	}


	public double getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(double evaluation) {
		this.evaluation = evaluation;
	}


	public Date getDateEmbauche() {
		return dateEmbauche;
	}


	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}



	public ArrayList<Client> getClients() {
		return clients;
	}


	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	

}
