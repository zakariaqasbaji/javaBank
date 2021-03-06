package Agence;
import Personnes.Employe;

import java.util.ArrayList;

public class Agence {
	private String adresse;
	private String nom;
	private int numero;
	private ArrayList<Employe> employes = new ArrayList<>();
        private int nbEmp;
        
        
	
	void afficher()
	{
		System.out.println("Agence: "+this.nom+" Num "+this.numero);
		System.out.println("Adresse: "+this.adresse);
		System.out.println("Nombre d'employ�s: "+this.employes.size());
	}

	public Agence(String adresse, String nom, int numero, ArrayList<Employe> employes) {
		super();
		this.adresse = adresse;
		this.nom = nom;
		this.numero = numero;
		this.employes = employes;
                this.nbEmp=employes.size();
                
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ArrayList<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(ArrayList<Employe> employes) {
		this.employes = employes;
	}

    public int getNbEmp() {
        return nbEmp;
    }
	
	

}
