package Banque;
import Personnes.DirecteurGeneral;
import Agence.Agence;

import java.util.ArrayList;

public class Banque {
	
	private String adresse;
	private double capital;
	private String nom;
	private DirecteurGeneral dir;
	private ArrayList<Agence> agences = new ArrayList<>();
	
	public void afficher()
	{
		System.out.println("L'adresse de la banque est : "+this.adresse);
		System.out.println("Le capital de la banque est : "+this.capital);
		System.out.println("Le nombre des agences: "+this.agences.size());
		System.out.println("Le nom de la banque est : "+this.nom);
		System.out.println("Nom du directeur : "+this.dir.getNom());
		
	}

	public Banque(String adresse, double capital, String nom, DirecteurGeneral dir, ArrayList<Agence> agences) {
		super();
		this.adresse = adresse;
		this.capital = capital;
		this.nom = nom;
		this.dir = dir;
		this.agences = agences;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public DirecteurGeneral getDir() {
		return dir;
	}

	public void setDir(DirecteurGeneral dir) {
		this.dir = dir;
	}

	public ArrayList<Agence> getAgences() {
		return agences;
	}

	public void setAgences(ArrayList<Agence> agences) {
		this.agences = agences;
	}

	

}
