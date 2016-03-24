package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.SegreteriaModel;
import it.polito.tdp.lab3.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	@FXML
    private ComboBox<Corso> boxCorsi;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnAutoComplete;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnInserisci;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    private SegreteriaModel model;
    
    public void setModel(SegreteriaModel model){
    	this.model = model;
    	 List<Corso> elencoCorsi = model.getCorsi();
         boxCorsi.getItems().addAll(elencoCorsi);
         boxCorsi.setValue(elencoCorsi.get(0));
    }

    @FXML
    void doAutoComplete(ActionEvent event) {
    	String matricola = txtInput.getText();
    	String nomeStudente = model.getStudente(matricola).getNomeStudente();
    	String cognomeStudente = model.getStudente(matricola).getCognomeStudente();
    	//Gestione matricola inesistente
    	if(nomeStudente == null || cognomeStudente == null){ //aggiungere controllo numerico
    		txtResult.setText("La matricola selezionata non � presente nel DataBase");
    	}
    	else{
    		txtNome.setText(nomeStudente);
        	txtCognome.setText(cognomeStudente);
    	}
    	btnAutoComplete.setDisable(true);
    }

    @FXML
    void doCerca(ActionEvent event) {
    	txtResult.clear();
    	btnAutoComplete.setDisable(true);
    	txtNome.setDisable(true);
    	txtCognome.setDisable(true);
    	Studente stemp = model.getStudente(txtInput.getText());
    	Corso ctemp = boxCorsi.getValue();
    	//CONTROLLI PRELIMINARI : Controllo selezione corso
    	if(ctemp == null){
    		txtResult.setText("Selezionare un corso, oppure lo spazio vuoto se non si desidera specificare alcun corso");
    		return;
    	}
    	
    	//Tutti gli studenti iscritti ad un corso -> Selezionato solo il corso dal menu a tendina
    	
    	if((boxCorsi.getValue().toString() != "") && (txtInput.getText().compareTo("")==0)){
    		Corso corso = boxCorsi.getValue();
    		txtResult.setText(model.segueCorso(corso));
    	}
    	
    	//Tutti i corsi a cui � iscritto uno studente -> Nessun corso selezionato, solo matricola
    	
    	else if((boxCorsi.getValue().toString().compareTo("") == 0)&&(txtInput.getText().compareTo("") != 0)){
    		//Controllo matricola inesistente o errata
        	if(stemp.getMatricola() == null ){
        		txtResult.setText("La matricola selezionata non � presente nel DataBase");
        		return;
        	}
    		txtResult.setText(model.corsoStudente(stemp));
    	}
    	
    	//Ricerca singolo studente iscritto ad un singolo corso -> matricola E corso selezionati
    	
    	else if((boxCorsi.getValue().toString() != "") && (txtInput.getText() != "")){
    		//Controllo matricola inesistente o errata
        	if(stemp.getMatricola() == null ){
        		txtResult.setText("La matricola selezionata non � presente nel DataBase");
        		return;
        	}
        	else if(model.corsoHaStudente(ctemp, stemp)){
        		txtResult.setText(String.format("%s %s (%s) � iscritto al corso \"%s\"", stemp.getNomeStudente(), stemp.getCognomeStudente(), stemp.getMatricola(), ctemp.getNomeCorso()));
        	}
        	else{
        		txtResult.setText(String.format("%s %s (%s) non � iscritto al corso \"%s\"", stemp.getNomeStudente(), stemp.getCognomeStudente(), stemp.getMatricola(), ctemp.getNomeCorso()));
        	}
    		
    	}

    }

    @FXML
    void doInserisci(ActionEvent event) {
    	txtResult.clear();
    	btnAutoComplete.setDisable(true);
    	txtNome.setDisable(true);
    	txtCognome.setDisable(true);
    	Studente stemp = model.getStudente(txtInput.getText());
    	Corso ctemp = boxCorsi.getValue();
    	//CONTROLLI PRELIMINARI : Controllo selezione corso
    	if(ctemp == null || ctemp.getNomeCorso().compareTo("") == 0){
    		txtResult.setText("Selezionare un corso a cui si desidera iscrivere lo studente.");
    		return;
    	}
    	//CONTROLLI PRELIMINARI : Controllo matricola inesistente o errata
    	if((stemp.getMatricola().compareTo("") == 0) || (stemp.getMatricola().matches("[0-9]") == false)){
    		txtResult.setText("La matricola selezionata non � scritta nel formato corretto o non � stata inserita.");
    		return;
    	}
    	
    	//Controlla se lo studente � gi� iscritto al corso specificato
    	
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtInput.clear();
    	txtNome.clear();
    	txtNome.setDisable(false);
    	txtCognome.setDisable(false);
    	txtCognome.clear();
    	txtResult.clear();
    	btnAutoComplete.setDisable(false);
    	boxCorsi.setValue(boxCorsi.getItems().get(0));
    }

    @FXML
    void initialize() {
    	assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnAutoComplete != null : "fx:id=\"btnAutoComplete\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
    }
}
