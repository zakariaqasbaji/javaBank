package Comptes;

public class CompteRenumere extends Compte {
	
	private double tauxInteret;
        
	
	private double calculerInteret()
	{
		return this.tauxInteret*this.solde;
	}
	
	void verserInteret()
	{
		this.solde+=this.calculerInteret();
	}
	public void afficher() {
		System.out.println("Num de compte:"+this.numero);
		System.out.println("Solde : "+this.solde);
		System.out.println("Taux d'interet: "+this.tauxInteret);
		System.out.println("Type: Rénuméré");
		
		
	}


	public CompteRenumere(int numero, double solde, double tauxInteret) {
		super(numero, solde);
		this.tauxInteret = tauxInteret;
                this.type="rénuméré";
	}

	public double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

    

        

}
