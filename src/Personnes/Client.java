package Personnes;



public class Client extends Personne{
	
	private String adresse;
        private int numCompte;
       
 
		

	
	public void afficher() {
		
		System.out.println("Nom: "+this.nom);
		System.out.println("Prenom: "+this.prenom);
		System.out.println("Adresse: "+this.adresse);
		
		
	}


	


	public Client(String nom, String prenom, String cin, String adresse,int num) {
		super(nom, prenom, cin);
		this.adresse = adresse;
		this.numCompte =num;
                
                }
               
	





	public String getAdresse() {
		return adresse;
	}
        
        
        public int getNumCompte() {
		return numCompte;
	}
        public void setNumCompte(int num) {
		this.numCompte=num;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	}
