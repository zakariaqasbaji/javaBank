package interfaceEmp;

import Comptes.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class VerserController implements Initializable {

    @FXML
    private Label num;
    @FXML
    private TextField montant;
    ObservableList<Compte> exemplesCompte;
    ArrayList<Compte> comptes;    
    int indexCompte;
    

    public void annuler()
    {
        Stage stage = (Stage) num.getScene().getWindow();
        stage.close();
    }
    public void validerAjout() throws MalformedURLException, IOException
    {

        java.net.URL url = new File("src\\interfaceEmp\\interfaceEmploye.fxml").toURI().toURL(); 
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        InterfaceEmployeController controller = loader.getController();
        
        try{
            comptes.get(indexCompte).ajouterMontant(Float.parseFloat(montant.getText()));
            controller.saveComptes(comptes,controller.pathComptesCourant,controller.pathComptesRenumere);
            exemplesCompte.set(indexCompte,comptes.get(indexCompte));
            controller.setComptesO(exemplesCompte);
            controller.creerRecuVersement(num.getText(),montant.getText());
  
        }catch(Exception e)
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("erreur de versement");
                    alert.setHeaderText("Respectez les formats requises");
                    alert.setContentText("valeur inattendue...");
                    alert.showAndWait();
                }

        
        
        
         
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setNumero(int nm)
      {
          this.num.setText(""+nm);
      }

    void setIndex(int index) {
         indexCompte=index;
    }
    
    public void setComptes(ArrayList<Compte> cmps)
    {
        comptes=cmps;
    }
    public void setComptesO(ObservableList<Compte> exCompte)
    {
        exemplesCompte=exCompte;
    }
   
}


