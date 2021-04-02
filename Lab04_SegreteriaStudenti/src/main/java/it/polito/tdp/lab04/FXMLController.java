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
boolean studentePresente;

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
    private Button btnCerca;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doCerca(ActionEvent event) {
    	
    txtRisultato.clear();
    String matricolaStringa = matricola.getText();
	Integer matricola = Integer.parseInt(matricolaStringa);
	
    Studente s = model.getStudenteByMatricola(matricola);
    Corso c = Corsi.getSelectionModel().getSelectedItem();
   if( model.studenteIscrittoAlCorso(s, c)==true) {
	   txtRisultato.setText("Lo studente è già iscritto a questo corso");
   }
   else {
	   txtRisultato.setText("Lo studente NON è iscritto al corso");
   }
    	
    	
    	
    	
    	
    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) {
    List <Corso> corsi= new LinkedList <Corso>();
    this.doCompletare(event);	
    if(studentePresente==true) { //se studente è presente visualizzo i corsi
    	String matricolaStringa = matricola.getText();
    	Integer matricola = Integer.parseInt(matricolaStringa);
    	corsi.addAll(model.getCorsiByStudente(matricola));
    	if(corsi.size()==0) { //se lughezza è 0 --> lo studente non è iscritto a nessun corso
    		txtRisultato.setText("Lo studente non è iscritto a nessun corso!");
    	}
    	else {
    		
    			
    			txtRisultato.setStyle("-fx-font-family: monospace");
    	    	StringBuilder sb = new StringBuilder(); 
    	    	for (Corso c : corsi) {
    	    		sb.append(String.format("%-8s ", c.getCodins()));
    	        	sb.append(String.format("%-4d ", c.getCrediti()));
    	    		sb.append(String.format("%-50s ", c.getNome()));
    	    		sb.append(String.format("%-4d\n", c.getPd()));
    	    	}
    	    	txtRisultato.appendText(sb.toString());	
    			
    			
    		
    	}
   
    }
 
   	
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
	    txtRisultato.setStyle("-fx-font-family: monospace");
     	StringBuilder sb = new StringBuilder(); 
	  for (Studente s : studenti) {
  		sb.append(String.format("%-6d ", s.getMatricola()));
      	sb.append(String.format("%-20s ", s.getCognome()));
  		sb.append(String.format("%-20s ", s.getNome()));
  		sb.append(String.format("%-6s \n", s.getCds()));
  	}
  	txtRisultato.appendText(sb.toString());	
		
    
  } 	
    	
    }
    	
    	
    	
    }

    @FXML
    void doCompletare(ActionEvent event) {

    if(this.inputIsValid(matricola.getText()) == true ) { //cioè se passa il controllo dell'input
	 Studente s =  model.getStudenteByMatricola(Integer.parseInt(matricola.getText()));
	 if(s!=null) {
	 txtRisultato.clear();
	 studentePresente= true;
	 Nome.setText(s.getNome());
	  Cognome.setText(s.getCognome());
  }
	 else {
		 studentePresente=false;
		 txtRisultato.setText("ERRORE: Lo studente non è presente!");
	 }
	 
    }
    
    	
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    txtRisultato.clear();
    matricola.clear();
    Nome.clear();
    Cognome.clear();
    Corsi.getSelectionModel().clearSelection();
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
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
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
