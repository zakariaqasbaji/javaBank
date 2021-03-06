package Personnes;
import Banque.Banque;

public class DirecteurGeneral extends Personne {
	
	
	private double revenu;
	private Banque banque;
	
	
	
	

	public void afficher() {
		System.out.println("Nom: "+this.nom);
		System.out.println("Prenom: "+this.prenom);
		System.out.println("Revenu: "+this.revenu);
		System.out.println("Banque: "+this.banque.getNom());
		
		}

	public double getRevenu() {
		return revenu;
	}

	public void setRevenu(double revenu) {
		this.revenu = revenu;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public DirecteurGeneral(String nom, String prenom, String cin, double revenu, Banque banque) {
		super(nom, prenom, cin);
		this.revenu = revenu;
		this.banque = banque;
	}

	
	
	
}
