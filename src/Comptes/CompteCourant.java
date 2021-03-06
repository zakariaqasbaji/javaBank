package Comptes;


public class CompteCourant extends Compte {
    
    
    
	
	public void verserVirment(CompteCourant cmpt,double montant)
	{
		if(montant<this.solde)
		{
			this.solde-=montant;
			cmpt.ajouterMontant(montant);
		}
		
	}
	public void afficher() {
		
		System.out.println("Num de compte:"+this.numero);
		System.out.println("Solde : "+this.solde);
		System.out.println("type: Courant");
		
	}

	public CompteCourant(int numero, double solde) {
		super(numero, solde);
                this.type="courant";
		
	}

   
	
	
}
