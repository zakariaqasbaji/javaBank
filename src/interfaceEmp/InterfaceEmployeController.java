package interfaceEmp;
import Comptes.*;
import Personnes.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;  
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;  
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;  
import com.itextpdf.text.Paragraph;  
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;  
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;




public class InterfaceEmployeController implements Initializable {

    @FXML
    private AnchorPane employe;   
    @FXML
    private ComboBox type;
    @FXML
    private TextField taux;

    @FXML
    public TextField solde;

    @FXML
    private TextField numrCompte;

    @FXML
    private Label labelTaux;

    @FXML
    private TextField numeroCompte;

    @FXML
    private TextField nameClient;

    @FXML
    private TextField firstnameClient;

    @FXML
    private TextArea adresseClient;

    @FXML
    private TextField cinClient;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, Integer> numCompte;
    @FXML
    private TableColumn<Client, String> nomClient;
    @FXML
    private TableColumn<Client,String>prenomClient;
    @FXML
    private TableColumn<Client, String> cin;
    @FXML
    private TableColumn<Client, String> adresseC;
    
    @FXML
    private TableView<Compte> tableCompte;

    @FXML
    private TableColumn<Compte, Integer> nmcpt;

    @FXML
    private TableColumn<Compte, Double> soldecmptt;

    @FXML
    private TableColumn<Compte, String> typeC;

    @FXML
    private TableColumn<Compte, Double> tauxInteret;


    
   ObservableList<Client> exemples;
   ArrayList<Client> clients= new ArrayList<>();
   public int indexClient=-1;
   ObservableList<Compte> exemplesCompte;
   ArrayList<Compte> comptes= new ArrayList<>();
   public int indexCompte=-1; 
   public String pathClients="clients.csv";
   public String pathComptesCourant="comptesCourant.csv";
   public String pathComptesRenumere="comptesRenumere.csv";
   private Scanner sc;

   
    @FXML
    private TextField rechercherCompte;
    @FXML
    private AnchorPane employePane;
    @FXML
    private Pane detailsPane;
    @FXML
    private Button buttonAjout;
    @FXML
    private Button modifierClient;
    @FXML
    private Button retraitButton;
    @FXML
    private Button verserButton; 
    @FXML
    private Button supprimerC;
    @FXML
    private Pane MesComptes;
    @FXML
    private Pane detailsMesCompes;
    @FXML
    private Pane MesClients;
    @FXML
    private Pane detailsMesClients;
    @FXML
    private TextField barreRecherche;
    @FXML
    private TextField recherche;
    
    public boolean ClientExistant()
    {
        boolean test=false;
        
        for(Client clt:clients)
        {

                if(clt.getNumCompte()==Integer.parseInt(numeroCompte.getText()) || clt.getCin().equals(cinClient.getText()) )
                {
                    test=true;
                    
                }
  
        }
        return test;
    }
    
    public boolean CompteExistant()
    {
        boolean test=false;
        
        for(Compte cmp:comptes)
        {
            
            
                if(cmp.getNumero()==Integer.parseInt(numrCompte.getText()))
                {
                    test=true;
                    
                }
  
        }
        return test;
    }
       
    
    public void comboAct(ActionEvent event)
    {
        if(type.getValue().equals("rénuméré"))
        {
            taux.setVisible(true);
            labelTaux.setVisible(true);
        }
        if(type.getValue().equals("courant"))
        {
            taux.setVisible(false);
            labelTaux.setVisible(false);
        }
    }
    
  
    
    public void getSelectedClient()
    {
        
        
        Client cliet = null;
        
        indexClient=tableClient.getSelectionModel().getSelectedIndex();
        if(indexClient==-1)
        {
            return;
        }
        numeroCompte.setText(numCompte.getCellData(indexClient).toString());
        nameClient.setText(nomClient.getCellData(indexClient));
        firstnameClient.setText(prenomClient.getCellData(indexClient));
        adresseClient.setText(adresseC.getCellData(indexClient));
        cinClient.setText(cin.getCellData(indexClient));
     
    }
    
    public void getSelectedCompte()
    {

        indexCompte=tableCompte.getSelectionModel().getSelectedIndex();
        if(indexCompte==-1)
        {
            return;
        }  
        numrCompte.setText(nmcpt.getCellData(indexCompte).toString());
        solde.setText(Double.toString(soldecmptt.getCellData(indexCompte)));
        type.getSelectionModel().select(typeC.getCellData(indexCompte));
        if(typeC.getCellData(indexCompte).equals("rénuméré"))
            taux.setText(Double.toString(tauxInteret.getCellData(indexCompte)));
              
    }
    
    public void rechercherClient()
     {
         barreRecherche.textProperty().addListener((Observable observable) -> {
             if(barreRecherche.textProperty().get().isEmpty())
             {
                 tableClient.setItems(exemples);
                 return;
             }
             
             ObservableList<Client> elemetsRecherchés=FXCollections.observableArrayList();
             ObservableList<TableColumn<Client,?>> colonne=tableClient.getColumns();
             for(int i=0;i<exemples.size();i++)
             {
                 for(int j=0;j<colonne.size();j++)
                 {
                    TableColumn colParcorrue =colonne.get(j); 
                    String valeur=colParcorrue.getCellData(exemples.get(i)).toString();
                    valeur=valeur.toLowerCase();
                    if(valeur.contains(barreRecherche.getText().toLowerCase()) && valeur.startsWith(barreRecherche.getText().toLowerCase()))
                     {
                        elemetsRecherchés.add((Client) exemples.get(i));
                        break;
                     }                     
                 }
             }
             tableClient.setItems(elemetsRecherchés);
         });
     }
    
    
    public void rechercherCompte()
     {
         recherche.textProperty().addListener((Observable observable) -> {
             if(recherche.textProperty().get().isEmpty())
             {
                 tableCompte.setItems(exemplesCompte);
                 return;
             }
             
             ObservableList<Compte> elemetsRecherchés=FXCollections.observableArrayList();
             ObservableList<TableColumn<Compte, ?>> colonne=tableCompte.getColumns();
             for(int i=0;i<exemplesCompte.size();i++)
             {
                 for(int j=1;j<3;j=j+2)
                 {
                    String valeur;
                    TableColumn colParcorrue =colonne.get(j);
                    valeur=Double.toString((double) colParcorrue.getCellData(exemplesCompte.get(i)));
                    valeur=valeur.toLowerCase();
                    if(valeur.contains(recherche.getText().toLowerCase()) && valeur.startsWith(recherche.getText().toLowerCase()))
                     {
                        elemetsRecherchés.add((Compte) exemplesCompte.get(i));
                        break;
                     }
                 }
                 for(int j=0;j<3;j=j+2)
                 {
                    String valeur;
                    TableColumn colParcorrue =colonne.get(j);
                    valeur=colParcorrue.getCellData(exemplesCompte.get(i)).toString();
                    valeur=valeur.toLowerCase();
                    if(valeur.contains(recherche.getText().toLowerCase()) && valeur.startsWith(recherche.getText().toLowerCase()))
                     {
                        elemetsRecherchés.add((Compte) exemplesCompte.get(i));
                        break;
                     }
                 }
             }
             tableCompte.setItems(elemetsRecherchés);
         });
     }

    public void ajouterCompte()
    {
  
        try{
            if((!CompteExistant())&& !(solde.getText().equals("")||numrCompte.getText().equals("")))
                {
                    int numeroCmpt=Integer.parseInt(numrCompte.getText());
                    double soldeC=Double.parseDouble(solde.getText());
                    
                    if(type.getValue().equals("rénuméré"))
                    {
                        double tauxI=Double.parseDouble(taux.getText());
                        CompteRenumere cmp=new CompteRenumere(numeroCmpt,soldeC,tauxI);
                        comptes.add(cmp);
                        exemplesCompte.add(cmp);
                        saveComptes(comptes,pathComptesCourant,pathComptesRenumere);
                    }
                    else
                    {
                        CompteCourant cmp =new CompteCourant(numeroCmpt,soldeC);
                        comptes.add(cmp);
                        exemplesCompte.add(cmp);
                        saveComptes(comptes,pathComptesCourant,pathComptesRenumere);
                    }
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Compte ajouté");
                    alert.setHeaderText(null);
                    alert.setContentText("Compte ajouté avec succes...");
                    alert.showAndWait();
                    }
            else if(!CompteExistant())
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur d'ajout");
                    alert.setHeaderText("erreur de valeurs");
                    alert.setContentText("veuillez respecter les formats requises...");
                    alert.showAndWait();
                }
                else
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur d'ajout");
                    alert.setHeaderText("Compte existant");
                    alert.setContentText("Le numero du compte existe déja...");
                    alert.showAndWait();
                }
            
            }
            catch(Exception e)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur d'ajout");
                alert.setHeaderText("erreur de valeurs");
                alert.setContentText("veuillez respecter les formats requises...");
                alert.showAndWait();
            }
  
        }
    public void ajouterClient()
    {
  
        try{
            if((!ClientExistant())&& !(adresseClient.getText().equals("")||cinClient.getText().equals("") || nameClient.getText().equals("") ||firstnameClient.getText().equals("") ))
                {
                    int numeroCmpt=Integer.parseInt(numeroCompte.getText());
                    String nomClt=nameClient.getText();
                    String prenomClt=firstnameClient.getText();
                    String cinClt=cinClient.getText();
                    String adresseClt=adresseClient.getText();    
                    Client clt= new Client(nomClt, prenomClt, cinClt, adresseClt, numeroCmpt);
                    exemples.add(clt);
                    clients.add(clt);
                    saveClients(clients,pathClients);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("client ajouté");
                    alert.setHeaderText(null);
                    alert.setContentText("client ajouté avec succes...");
                    alert.showAndWait();
                    }
            else if(!ClientExistant())
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur d'ajout");
                    alert.setHeaderText("erreur de valeurs");
                    alert.setContentText("veuillez respecter les formats requises...");
                    alert.showAndWait();
                }
                else
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur d'ajout");
                    alert.setHeaderText("Client existant");
                    alert.setContentText("CIN utilisé existe déja ou Le numero du compte correspond à un autre client");
                    alert.showAndWait();
                }
            
        }
            catch(Exception e)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur d'ajout");
                alert.setHeaderText("erreur de valeurs");
                alert.setContentText("veuillez respecter les formats requises...");
                alert.showAndWait();
            }
  
        }
       
    
    public void modifierClient()
    {
        try {
            if(!(adresseClient.getText().equals("")||cinClient.getText().equals("") || nameClient.getText().equals("") ||firstnameClient.getText().equals("") )){
                int numeroCmpt=Integer.parseInt(numeroCompte.getText());
                Client clt;
                String nomClt=nameClient.getText();
                String prenomClt=firstnameClient.getText();
                String cinClt=cinClient.getText();
                String adresseClt=adresseClient.getText();
                clt= new Client(nomClt, prenomClt, cinClt, adresseClt,numeroCmpt);
                exemples.set(indexClient, clt);
                clients.set(indexClient,clt);
                saveClients(clients,pathClients);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("client modifié");
                alert.setHeaderText(null);
                alert.setContentText("client modifié avec succes...");
                alert.showAndWait();
                
            
                
            }else
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText("erreur de valeurs");
                alert.setContentText("veuillez respecter les formats requises...");
                alert.showAndWait();
            }
            
            
            
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("erreur de valeurs");
            alert.setContentText("Ce client n'existe plus...");
            alert.showAndWait();
            
        }
        
    }
    public void modifierCompte()
    {
        try{
            if( !(solde.getText().equals("")||numrCompte.getText().equals("")))
                {
                    int numeroCmpt=Integer.parseInt(numrCompte.getText());
                    double soldeC=Double.parseDouble(solde.getText());
                    
                    if(type.getValue().equals("rénuméré"))
                    {
                        double tauxI=Double.parseDouble(taux.getText());
                        CompteRenumere cmp=new CompteRenumere(numeroCmpt,soldeC,tauxI);
                        comptes.set(indexCompte, cmp);
                        exemplesCompte.set(indexCompte, cmp);
                        saveComptes(comptes,pathComptesCourant,pathComptesRenumere);
                    }
                    else
                    {
                        CompteCourant cmp =new CompteCourant(numeroCmpt,soldeC);
                        comptes.set(indexCompte, cmp);
                        exemplesCompte.set(indexCompte, cmp);
                        saveComptes(comptes,pathComptesCourant,pathComptesRenumere);
                    }
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Compte modifié");
                    alert.setHeaderText(null);
                    alert.setContentText("Compte modifié avec succes...");
                    alert.showAndWait();
                    }
            else if(!CompteExistant())
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur de modification");
                    alert.setHeaderText("erreur de valeurs");
                    alert.setContentText("veuillez respecter les formats requises...");
                    alert.showAndWait();
                }
            }
            catch(Exception e)
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText("erreur de valeurs");
                alert.setContentText("veuillez respecter les formats requises...");
                alert.showAndWait();
            }
  
        
    }
    
    
    public void supprimerCompte()
    {
        if(indexCompte==-1)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText("aucun compte n'est choisi");
            alert.setContentText("choisir un compte et reéssayer...");            
            alert.showAndWait();
            
        }
        try
        {
            comptes.remove(indexCompte);
            exemplesCompte.remove(indexCompte);
            saveComptes(comptes,pathComptesCourant,pathComptesRenumere);
            indexCompte=-1;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Compte Supprimée");
            alert.setHeaderText(null);
            alert.setContentText("Compte supprimé avec succes...");
            alert.showAndWait();
            numrCompte.clear();
            solde.clear();
            taux.clear();
            
        }catch(Exception e)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText("aucun compte n'est choisi");
            alert.setContentText("choisir un compte et reéssayer...");
            alert.showAndWait();
            
        }
        
    }
    
    public void supprimerClient()
    {
        if(indexClient==-1)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText("aucun compte n'est choisi");
            alert.setContentText("choisir un compte et reéssayer...");
            
            alert.showAndWait();
            
        }
        try
        {
            clients.remove(indexClient);
            exemples.remove(indexClient);
            saveClients(clients,pathClients);
            indexClient=-1;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Client Supprimée");
            alert.setHeaderText(null);
            alert.setContentText("Client supprimé avec succes...");
            alert.showAndWait();
            numeroCompte.clear();
            nameClient.clear();
            firstnameClient.clear();
            cinClient.clear();
            adresseClient.clear();
        }catch(Exception e)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText("aucun compte n'est choisi");
            alert.setContentText("choisir un compte et reéssayer...");
            alert.showAndWait();
            
        }
        
    }
    
    public void retraitPane() throws IOException
    {
          
         if (indexCompte<=-1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("erreur de retrait");
            alert.setHeaderText("Vous devez choisir un compte");
            alert.setContentText("choisir un compte et reessayer...");
            alert.showAndWait();
            
        }
         else
        {
         java.net.URL url = new File("src\\interfaceEmp\\retrait.fxml").toURI().toURL();
	 FXMLLoader loader = new FXMLLoader(url);
         Parent root = loader.load();
         RetaitController controller = loader.getController();
         controller.setIndex(indexCompte);
         controller.setComptes(comptes);
         controller.setComptesO(exemplesCompte);
         controller.setNumero(nmcpt.getCellData(indexCompte));
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.setTitle("Retrait");
         stage.show();
        }
        
    }
    public void setComptesO(ObservableList<Compte> exCompte)
    {
        exemplesCompte=exCompte;
    }
    
   
    
    
    public void verser() throws IOException
    {
        if (indexCompte<=-1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("erreur de versement");
            alert.setHeaderText("Vous devez choisir un compte");
            alert.setContentText("choisir un compte et reessayer...");
            alert.showAndWait();
        }else
        {
         java.net.URL url = new File("src\\interfaceEmp\\verser.fxml").toURI().toURL();
	 FXMLLoader loader = new FXMLLoader(url);
         Parent root= loader.load();
         VerserController controller = loader.getController();
         controller.setIndex(indexCompte);
         controller.setComptes(comptes);
         controller.setComptesO(exemplesCompte);
         controller.setNumero(nmcpt.getCellData(indexCompte));
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.setTitle("Ajout d'argent");
         stage.show();
        }
        
    }
    public void deconnecter() throws MalformedURLException, IOException
    {
        java.net.URL url = new File("src\\application\\Acueil.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
		//	Parent root = FXMLLoader.load(getClass().getResource("/applicaion/Acueil.fxml"));
			Scene scene = new Scene(root,400,450);
                        Stage stage=new Stage();
                        stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
        Stage stage1 = (Stage) employePane.getScene().getWindow();
        stage1.close();
    }
    
    
    public void setClients(ArrayList<Client> cls)
    {
        clients=cls;
    }
    
    public void saveClients(ArrayList<Client> clients, String path)
    {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            PrintWriter pw = new PrintWriter(fos);
            for(Client cl:clients)
            {
                String s=cl.getNom()+";"+cl.getPrenom()+";"+cl.getAdresse()+";"+cl.getNumCompte()+";"+cl.getCin();
                s=s.replaceAll("\n", "");
                pw.print(s+"\n");
                
            }
            
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InterfaceEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public void saveComptes (ArrayList<Compte> comptes, String pathC,String pathR)
    {
        try {
            FileOutputStream fos1 = new FileOutputStream(pathC);
            FileOutputStream fos2 = new FileOutputStream(pathR);
            
            try (PrintWriter pw = new PrintWriter(fos1)) {
                comptes.forEach((compte) -> {
                    if(compte.getType().equals("courant"))
                    {
                        pw.println(compte.getNumero()+";"+compte.getSolde());
                    }
                    
                });
            }
            try (PrintWriter pw = new PrintWriter(fos2)) {
                comptes.forEach((compte) -> {
                    if(compte.getType().equals("rénuméré"))
                    {
                        
                        CompteRenumere cmp=(CompteRenumere) compte;
                        pw.println(cmp.getNumero()+";"+cmp.getSolde()+";"+cmp.getTauxInteret());
                    }
                    
                });
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InterfaceEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    
    public ArrayList<Client> readClients()
    {
        ArrayList<Client> cls=new ArrayList<>();
        try {
            sc=new Scanner(new File(pathClients));
            sc.useDelimiter("[;\n]");
            
            while(sc.hasNext())
                {
                    int numm;
                    String nom;
                    String prenom;
                    String cinn;
                    String ad;
                    nom =sc.next();
                    prenom=sc.next();
                    ad=sc.next();
                    numm=Integer.parseInt(sc.next());
                    cinn=sc.next();
                    Client cl=new Client(nom, prenom, cinn, ad, numm); 
                    cls.add(cl);
                }
     
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InterfaceEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc.close();
      return cls;
    }
    
    public ArrayList<Compte> readComptes()
    {
    ArrayList<Compte> cmps=new ArrayList<>();
    try {
    Scanner sc1=new Scanner(new File(pathComptesCourant));
    Scanner sc2=new Scanner(new File(pathComptesRenumere));
    sc1.useDelimiter("[;\n]");
    while(sc1.hasNext())
        {
            int n;
            double s;
            n =Integer.parseInt(sc1.next());
            s=Double.parseDouble(sc1.next());
            CompteCourant cmp=new CompteCourant(n,s);
            cmps.add(cmp);
        }
        sc1.close();
        
        sc2.useDelimiter("[;\n]");
        while(sc2.hasNext())
        {
            int n;
            double s,t;
            n =Integer.parseInt(sc2.next());
            s=Double.parseDouble(sc2.next());
            t=Double.parseDouble(sc2.next());
            CompteRenumere cmp=new CompteRenumere(n,s,t);
            cmps.add(cmp);
        }
        sc2.close();
    
            } catch (FileNotFoundException ex) {
            Logger.getLogger(InterfaceEmployeController.class.getName()).log(Level.SEVERE, null, ex);
            }
      return cmps;
    }
    

    public void clear()
        {
            numeroCompte.clear();
            nameClient.clear();
            firstnameClient.clear();
            cinClient.clear();
            adresseClient.clear();
        }
    public void clearCompte()
        {
        numrCompte.clear();
        solde.clear();
        taux.clear();
        taux.setVisible(false);
        labelTaux.setVisible(false);
        }
    
    @FXML
    void afficherInterfaceClient(ActionEvent event) {
        MesComptes.setVisible(false);
        detailsMesCompes.setVisible(false);
        detailsMesClients.setVisible(true);
        MesClients.setVisible(true);
    }

    @FXML
    void afficherInterfaceCompte(ActionEvent event) {
        detailsMesClients.setVisible(false);
        MesClients.setVisible(false);
        MesComptes.setVisible(true);
        detailsMesCompes.setVisible(true);
    }
    
    public void creerRecuRetrait(String num,String mnt) throws FileNotFoundException, DocumentException, BadElementException, IOException
    {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("recuderetrait"+System.currentTimeMillis()+".pdf"));
        document.open();
        Image image2 = Image.getInstance("ensam.png");
        image2.scaleAbsolute(80f,30f);
        document.add(image2);
        document.add(new Paragraph("\n\n\n\n"));
        Paragraph paragraph=new Paragraph("Retrait:\n\n",FontFactory.getFont(FontFactory.HELVETICA,18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
        document.add(paragraph);
        PdfPTable t = new PdfPTable(3);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);
        PdfPCell c1 = new PdfPCell(new Phrase("Numero du compte"));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Montant"));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("Date"));
        t.addCell(c3);
        t.addCell(""+num);
        t.addCell(""+mnt);
        t.addCell(""+java.util.Calendar.getInstance().getTime());
        document.add(t);
        Paragraph paragraph2=new Paragraph("\n\n-Signature Client:                                                                                          -Signature Agent:");
        document.add(paragraph2);
        document.close();  
    }
    public void creerRecuVersement(String num,String mnt) throws FileNotFoundException, DocumentException, BadElementException, IOException
    {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("recudeversement"+System.currentTimeMillis()+".pdf"));
        document.open();
        Image image2 = Image.getInstance("ensam.png");
        image2.scaleAbsolute(80f,30f);
        document.add(image2);
        document.add(new Paragraph("\n\n\n\n"));
        Paragraph paragraph=new Paragraph("Versement:\n\n",FontFactory.getFont(FontFactory.HELVETICA,18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
        document.add(paragraph);
        PdfPTable t = new PdfPTable(3);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);
        PdfPCell c1 = new PdfPCell(new Phrase("Numero du compte"));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Montant"));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("Date"));
        t.addCell(c3);
        t.addCell(""+num);
        t.addCell(""+mnt);
        t.addCell(""+java.util.Calendar.getInstance().getTime());
        document.add(t);
        Paragraph paragraph2=new Paragraph("\n\n-Signature Client:                                                                                          -Signature Agent:");
        document.add(paragraph2);
        document.close();  
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        ObservableList listType = FXCollections.observableArrayList("courant","rénuméré");
        type.setItems(listType);
        
        CompteCourant cmp1 = new CompteCourant(154684,50054);
        CompteCourant cmp2 = new CompteCourant(543452,12334);
        CompteCourant cmp3 = new CompteCourant(34152,600);
        CompteRenumere cmp4=new CompteRenumere(424554, 5424512, 0.2);
        CompteRenumere cmp5=new CompteRenumere(54545, 5124212, 0.2);
        Client cl1= new Client("alaoui", "ahmed", "FS455", "maarif",cmp1.getNumero());
        Client cl2= new Client("qasbaji", "zakaria", "BK4531", "hayhassani",cmp2.getNumero());
        Client cl3= new Client("mounir", "yassine", "TY4554", "beausejour", cmp3.getNumero());
        Client cl4= new Client("moladi", "soufi", "MK5745", "ghandi",cmp4.getNumero());
        Client cl5= new Client("allai", "ali", "JH4455", "el oulfa", cmp5.getNumero());
        
        

       numCompte.setCellValueFactory(new PropertyValueFactory<>("numCompte"));
       nomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
       adresseC.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       nmcpt.setCellValueFactory(new PropertyValueFactory<>("numero"));
       soldecmptt.setCellValueFactory(new PropertyValueFactory<>("solde"));
       typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
       tauxInteret.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
       comptes=readComptes();
       exemplesCompte = FXCollections.observableArrayList(comptes);
       tableCompte.setItems(exemplesCompte);
       clients=readClients();  
       //clients.add(cl1);clients.add(cl2);clients.add(cl3);clients.add(cl4);clients.add(cl5);
       exemples=FXCollections.observableArrayList(clients);
       tableClient.setItems(exemples);
       saveClients(clients,pathClients);
       saveComptes(comptes,pathComptesCourant,pathComptesRenumere);
       
       
    }
}