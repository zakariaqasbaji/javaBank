package Comptes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public abstract class Compte {
	
	
	protected int numero;
	protected double solde;
        protected String type;
        
	
	
	public Compte(int numero, double solde) {
		super();
		this.numero = numero;
		this.solde = solde;
	}
	public void ajouterMontant(double mnt) {
		this.solde+=mnt;
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Montant ajouté");
                alert.setHeaderText(null);
                alert.setContentText("Montant ajouté avec succes...");
                alert.showAndWait();
	}
	public void retirerMontant(double mnt) {
		if(mnt<this.solde)
		{
                    this.solde-=mnt;
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Retrait effectué");
                    alert.setHeaderText(null);
                    alert.setContentText("Retrait effectué avec succes...");
                    alert.showAndWait();
		}
		else
		{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("erreur de retrait");
                    alert.setHeaderText("Montant plus grand que le solde");
                    alert.setContentText("choisir un autre montant et reessayer...");
                    alert.showAndWait();
		}
	}
	
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
         public String getType() {
        return type;
         }
	
	
	
}
