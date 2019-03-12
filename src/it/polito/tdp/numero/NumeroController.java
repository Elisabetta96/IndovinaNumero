package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.numero.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private NumeroModel model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox boxControllopartita;

	@FXML
	private TextField txtRimasti;
	// numero di tentativi rimasti ancora da provare

	@FXML
	private HBox boxControlloTentativi;

	@FXML
	private TextField txtTentativo;
	// tentativo inserito dall'utente

	@FXML
	private TextArea txtMessaggi;

	@FXML
	void handleNuovaPartita(ActionEvent event) {
		// Gestisce l'inizio di una nuova partita

		// Logica del gioco
		// non c è più, va fatta nel model
		
		// Gestione dell'interfaccia 
		//RIMANE OVVIAMENTE !! 
		boxControllopartita.setDisable(true);
		boxControlloTentativi.setDisable(false);
		txtMessaggi.clear();
		this.txtTentativo.clear();
		txtRimasti.setText(Integer.toString(model.getTMAX()));
		
		//non cominciamo più noi la partita, lo devo dire al modello!
		model.newGame();

	}

	@FXML
	void handleProvaTentativo(ActionEvent event) {

		// Leggi il valore del tentativo 
		//Si, perchè è un valore che leggo dall'interfaccia 
		String ts = txtTentativo.getText();

		// Controlla se è valido. è un controllo di tipo. Rimane qua,perchè il modello richiede un parametro di tipo giusto.

		int tentativo ;
		try {
			tentativo = Integer.parseInt(ts);
		} catch (NumberFormatException e) {
			// la stringa inserita non è un numero valido
			txtMessaggi.appendText("Non è un numero valido\n");
			return ;
		}
		// potrei comunque per sicurezza controllare anche se il numero inserito è corretto 
		// non lo controlla già il metodo che richiamerò??? si ! è un controllo aggiuntivo 
		// quello che cambia è che qui posso fare un append text aggiuntivo visto che lavoro sull' interfaccia 
		
		if(!model.tentativoValido(tentativo)) {
			txtMessaggi.appendText("Range non valido \n");
			return;
		}
		
		int risultato= model.tentativo(tentativo);
		if(risultato==0){
			txtMessaggi.appendText("Complimenti, hai indovinato in "+model.getTentativiFatti()+" tentativi\n");
			boxControllopartita.setDisable(false);
			boxControlloTentativi.setDisable(true);
			
			// non ci serve perchè il modello gia lo fa 
			//model.endGame();
			
		}
		else if (risultato<0) {
			txtMessaggi.appendText("Tentativo troppo BASSO\n");
			
		}else {
			txtMessaggi.appendText("Tentativo troppo ALTO\n");
			}
	

		// Aggiornare interfaccia con n. tentativi rimasti
		
       this.txtRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti())); 
       
       //devo anche vedere se ho esauito le variabili
       //chiedo lo stato della partita al controller
       
       if(!model.isInGioco()) {
    	   //la partita è finita, due casi : ho indovinato ( abbiamo gia il messaggio ) 
    	   // 2 in caso in cui ho finito i tentativi,ho bisogno di una stamopa 
    	   if(risultato!=0) {
    		   this.txtMessaggi.appendText("Hai perso!");
    		   this.txtMessaggi.appendText(String.format("\n il numero segreto era : %d",model.getSegreto()));
    		   boxControllopartita.setDisable(false);
   			boxControlloTentativi.setDisable(true);
    	   }
       }
       
       
	}
        
	

	@FXML
	void initialize() {
		assert boxControllopartita != null : "fx:id=\"boxControllopartita\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
		assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

	}
	
	public void setModel(NumeroModel model) {
		this.model = model;
	}

}
