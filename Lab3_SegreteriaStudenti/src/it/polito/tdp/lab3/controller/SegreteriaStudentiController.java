package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.model.SegreteriaModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	@FXML
    private ComboBox<String> boxCorsi;
	
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
    	 List<String> elencoCorsi = model.getCorsi();
         boxCorsi.getItems().addAll(elencoCorsi);
    }

    @FXML
    void doAutoComplete(ActionEvent event) {
    	String matricola = txtInput.getText();
    	String nomeStudente = model.getStudente(matricola).getNomeStudente();
    	String cognomeStudente = model.getStudente(matricola).getCognomeStudente();
    	//Gestione matricola inesistente
    	if(nomeStudente == null || cognomeStudente == null){
    		txtResult.setText("La matricola selezionata non è presente nel DataBase");
    	}
    	else{
    		txtNome.setText(nomeStudente);
        	txtCognome.setText(cognomeStudente);
    	}
    	btnAutoComplete.setDisable(true);
    }

    @FXML
    void doCerca(ActionEvent event) {
    	//Tutti gli studenti iscritti ad un corso -> Selezionato solo il corso dal menu a tendina
    	if((boxCorsi.getValue() != "") && (txtInput.getText().compareTo("")==0)){
    		
    	}

    }

    @FXML
    void doInserisci(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtInput.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	btnAutoComplete.setDisable(false);
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
