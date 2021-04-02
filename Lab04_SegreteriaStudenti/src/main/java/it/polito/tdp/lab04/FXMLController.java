package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
private Model model;
Corso corsoSelezionato;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Corso> Corsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField matricola;

    @FXML
    private Button btnCheck;

    @FXML
    private TextField Nome;

    @FXML
    private TextField Cognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    corsoSelezionato = Corsi.getSelectionModel().getSelectedItem();
    if(corsoSelezionato== null) {
    	txtRisultato.setText("ERRORE: Selezionare un corso!");
    }
   else { //se l'utente ha selezionato un corso...
  txtRisultato.clear(); 	
  List <Studente> studenti = new LinkedList <>();
  studenti.addAll(model.getStudentiIscrittiAlCorso(corsoSelezionato));
  if(studenti.size()==0) {
	  txtRisultato.setText("Il corso non ha iscritti!");
  }
  else {
  for (Studente s: studenti) {
	txtRisultato.appendText(s+"\n");  
  }
    
  } 	
    	
    }
    	
    	
    	
    }

    @FXML
    void doCompletare(ActionEvent event) {

    if(this.inputIsValid(matricola.getText()) == true ) { //cioè se passa il controllo dell'input
	 Studente s =  model.getStudenteByMatricola(Integer.parseInt(matricola.getText()));
	 if(s!=null) {
		 txtRisultato.clear();
	 Nome.setText(s.getNome());
	  Cognome.setText(s.getCognome());
  }
	 else {
		 
		 txtRisultato.setText("ERRORE: Lo studente non è presente!");
	 }
	 
    }
    
    	
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Corsi != null : "fx:id=\"Corsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert matricola != null : "fx:id=\"matricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Nome != null : "fx:id=\"Nome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert Cognome != null : "fx:id=\"Cognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
   
    
    public boolean inputIsValid (String input) {
    	txtRisultato.clear();
    	String matricolaStringa = matricola.getText();
    	Integer matricola;
    	if(matricolaStringa.length()<6 || matricolaStringa.length()>6) {
    		txtRisultato.setText("ERRORE: La matricola è composta da 6 numeri!");
    		return false;
    	}
    	try {
    		matricola = Integer.parseInt(matricolaStringa);
    	}
    	catch(NumberFormatException ne) {
    		txtRisultato.setText("ERRORE: La matricola è un numero!");
    		return false;
    	}
    	catch(NullPointerException npe) {
    		txtRisultato.setText("ERRORE: Devi inserire una matricola!");
    		return false;
    	}
    	
    	
    	
    	return true;
    	
    }
    
    
    public void setModel (Model model) {
    	this.model= model;
    	Corsi.getItems().addAll(model.getCorsi());
    	
    	
    	
    }
    
    
    
}
