package interfaceDirGen;



import Agence.Agence;
import Banque.Banque;
import Personnes.DirecteurGeneral;
import Personnes.Employe;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class InterfaceDirGenController implements Initializable {

    @FXML
    private AnchorPane directeur;

    @FXML
    private Pane detailsPane;

    @FXML
    private TextField numeroAgence;

    @FXML
    private TextField nameAgence;

    @FXML
    private TextArea adresseAgence;

    @FXML
    private Button buttonAjout;

    @FXML
    private Button modifierAgence;

    @FXML
    private TableView<Agence> tableAgence;

    @FXML
    private TableColumn<Agence, Integer> numAgence;

    @FXML
    private TableColumn<Agence, String> nomAgence;

    @FXML
    private TableColumn<Agence, String> adresseA;

    @FXML
    private TableColumn<Agence, Integer> nombreEmp;

    @FXML
    private TextField barreRecherche;

    @FXML
    private Button supprimerA;
    
    
    
    
   ObservableList<Agence> exemples;
   
   public int indexAgence=-1;
    
    ArrayList<Agence> agences =new  ArrayList<>();
    
    
    
    public boolean agenceExistante()
    {
        boolean test=false;
        
        for(Agence agn:agences)
        {
            if(agn.getNumero()==Integer.parseInt(numeroAgence.getText()))
            {
                test=true;
            }
            
        }
        return test;
    }
    
    
    
    
    
    
    
    @FXML
    void ajouterAgence(ActionEvent event)
    {
        try{
           if(!agenceExistante() && !( adresseAgence.getText().equals("") || adresseAgence.getText().equals("")))
        {
            int numeroAgn=Integer.parseInt(numeroAgence.getText());
            String nomAgn=nameAgence.getText();
            String adresseAgn=adresseAgence.getText();
            Agence agn= new Agence(adresseAgn, nomAgn, numeroAgn, new ArrayList<>());
            exemples.add(agn);
            agences.add(agn);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agence Ajoutée");
            alert.setHeaderText(null);
            alert.setContentText("agence ajoutée avec succes...");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText("Agence existante");
            alert.setContentText("Le numero de l'agence utilisé existe déja");
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

    @FXML
    void getSelectedAgence(MouseEvent event)
    {
       
        indexAgence=tableAgence.getSelectionModel().getSelectedIndex();
        if(indexAgence==-1)
        {
            return;
        }
        
        numeroAgence.setText(numAgence.getCellData(indexAgence).toString());
        nameAgence.setText(nomAgence.getCellData(indexAgence));
        
        adresseAgence.setText(adresseA.getCellData(indexAgence));
        
    }

    @FXML
    void modifierAgence(ActionEvent event)
    {
        
     try{
           if(!( adresseAgence.getText().equals("") || adresseAgence.getText().equals(""))){
            int numeroAgn=Integer.parseInt(numeroAgence.getText());

            Agence agn;

             String nomAgn=nameAgence.getText();

             String adresseAgn=adresseAgence.getText();

             ArrayList<Employe> employes=new ArrayList<>();


            agn =new Agence(adresseAgn, nomAgn, numeroAgn, exemples.get(indexAgence).getEmployes());

            exemples.set(indexAgence, agn);
            agences.set(indexAgence, agn);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Agence Modifieé");
            alert.setHeaderText(null);
            alert.setContentText("agence modifiée avec succes...");
            alert.showAndWait();
           }
           else
           {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText("erreur de valeurs");
                alert.setContentText("veuillez respecter les formats requises...");
                alert.showAndWait();
           }
     }catch(Exception e)
     {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur de modification");
         alert.setHeaderText("erreur de valeurs");
         alert.setContentText("veuillez respecter les formats requises...");
         alert.showAndWait();
     }
       
    }
    
    @FXML
    void rechercherAgence(KeyEvent event)
     {
         barreRecherche.textProperty().addListener((Observable observable) -> {
             if(barreRecherche.textProperty().get().isEmpty())
             {
                tableAgence.setItems(exemples);
                return;
             }
             
             ObservableList<Agence> elemetsRecherchés=FXCollections.observableArrayList();
             ObservableList<TableColumn<Agence,?>> colonne=tableAgence.getColumns();
             for(int i=0;i<exemples.size();i++)
             {
                 for(int j=0;j<colonne.size();j++)
                 {
                     TableColumn colParcorrue =colonne.get(j);
                     String valeur=colParcorrue.getCellData(exemples.get(i)).toString();
                     valeur=valeur.toLowerCase();
                     if(valeur.contains(barreRecherche.getText().toLowerCase()) && valeur.startsWith(barreRecherche.getText().toLowerCase()))
                     {
                        elemetsRecherchés.add((Agence) exemples.get(i));
                        break;
                     }
                     
                 }
             }
             tableAgence.setItems(elemetsRecherchés);
         });
     }
    
    @FXML
    void supprimerAgence(ActionEvent event)
    {
        if(indexAgence==-1)
        {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erreur de suppression");
         alert.setHeaderText("erreur de suppression");
         alert.setContentText("veuillez choisir l'agence à supprimée...");
         alert.showAndWait();
            
        }
        exemples.remove(indexAgence);
        agences.remove(indexAgence);
        indexAgence=-1;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Agence Supprimée");
        alert.setHeaderText(null);
        alert.setContentText("agence supprimée avec succes...");
        alert.showAndWait();
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
        Stage stage1 = (Stage) directeur.getScene().getWindow();
        stage1.close();
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        numAgence.setCellValueFactory(new PropertyValueFactory<>("numero"));
        nomAgence.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nombreEmp.setCellValueFactory(new PropertyValueFactory<>("nbEmp"));
        
        adresseA.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        Employe emp1= new Employe("errasily","yassin", "bk000",new Date(2011-1900,5,11) , null); //duplicated mais ca marche
        Employe emp2= new Employe("said","hassan", "bk1451241",new Date(2012-1900,7,4), null);
	Employe emp3= new Employe("belhaj","ilyas","bk241221",new Date(2015-1900,5,11) , null); //duplicated mais ca marche
	Employe emp4= new Employe("belhad","hassna", "bk21241541",new Date(2017-1900,7,4), null);
		
	ArrayList<Employe> employes1 = new ArrayList<>();
	ArrayList<Employe> employes2 = new ArrayList<>();
	employes1.add(emp1);
	employes1.add(emp2);
	employes2.add(emp3);
	employes2.add(emp4);
		
		
		
	Agence agence1=new Agence("GHANDI ", "ghandi", 1, employes1);
	Agence agence2=new Agence("OULFA", "oulfa Agence", 2, employes2);	
	agences.add(agence1);
	agences.add(agence2);
		
		
		
	DirecteurGeneral dir=new DirecteurGeneral("Qasbaji", "Zakaria","bk999", 50000, null);
        Banque maBanque=new Banque("Ain sebae", 1500000, "IslamicBank", dir, agences);	
	dir.setBanque(maBanque);

       exemples = FXCollections.observableArrayList(agence1,agence2);
       
       tableAgence.setItems(exemples);
    }    
    
}
