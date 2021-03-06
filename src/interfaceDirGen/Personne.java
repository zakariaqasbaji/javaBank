package interfaceDirGen;

import interfaceEmp.*;

public abstract class Personne {
	
	protected String nom;
	protected String prenom;
	protected String cin;
	
	

	
	public Personne(String nom, String prenom,String cin) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin=cin;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	
}
