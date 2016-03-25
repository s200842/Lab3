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
    	try {
    		int matricola = Integer.parseInt(txtInput.getText());
    		String nomeStudente = model.getStudente(matricola).getNomeStudente();
        	String cognomeStudente = model.getStudente(matricola).getCognomeStudente();
        	//La matricola è un numero ma non esiste nel DB
        	if(nomeStudente == null || cognomeStudente == null){ 
        		txtResult.setText("La matricola selezionata non è presente nel DataBase");
        		return;
        	}
        	else{
        		txtNome.setText(nomeStudente);
            	txtCognome.setText(cognomeStudente);
        	}
        	btnAutoComplete.setDisable(true);
        	txtResult.clear();
    	}
    	catch(NumberFormatException e){
    		//La matricola è vuota
    		if(txtInput.getText().compareTo("") == 0){
    			txtResult.setText("Inserire una matricola per l'autocompletamento.");
    			return;
    		}
    		else{ //La matricola non è un numero
    			txtResult.setText("Formato matricola errato.");
    			return;
    		}
    	}
    }

    @FXML
    void doCerca(ActionEvent event) {
    	txtResult.clear();
    	btnAutoComplete.setDisable(true);
    	txtNome.setDisable(true);
    	txtCognome.setDisable(true);
    	
    	try {
    		Corso ctemp = boxCorsi.getValue();
    		Studente stemp = model.getStudente(Integer.parseInt(txtInput.getText()));
    		
    		//CONTROLLO CORSO
        	if(ctemp == null){
        		txtResult.setText("Selezionare un corso, oppure lo spazio vuoto se non si desidera specificare alcun corso");
        		return;
        	}
        	
        	//Matricola valida. Se il corso è vuoto, mostro i corsi a cui è iscritto lo studente
        	if(ctemp.toString().compareTo("") == 0){
        		txtResult.setText(model.corsoStudente(stemp));	
        	}
        	//Matricola valida. Se è specificato un corso, controllo se lo studente segue quel corso
        	else if(ctemp.toString() != ""){
        		if(model.corsoHaStudente(ctemp, stemp)){
					txtResult.setText(String.format("%s %s (%s) è iscritto al corso \"%s\"", stemp.getNomeStudente(), stemp.getCognomeStudente(), stemp.getMatricola(), ctemp.getNomeCorso()));
				}
				else{
					//Se lo studente esiste ne stampo nome e cognome, in caso contrario specifico che non esiste
					if(stemp.getMatricola() == -1){
						txtResult.setText("La matricola selezionata non è presente nel DataBase.");
						return;
					}
					txtResult.setText(String.format("%s %s (%s) non è iscritto al corso \"%s\"", stemp.getNomeStudente(), stemp.getCognomeStudente(), stemp.getMatricola(), ctemp.getNomeCorso()));
				}
        	}
    	}
    	catch(NumberFormatException e){
    		//La matricola è un campo vuoto
    		if(txtInput.getText().compareTo("") == 0){ //Se ho selezionato un corso, mostra tutti gli studenti ad esso iscritti
    			if(boxCorsi.getValue().toString() != ""){
    				txtResult.setText(model.segueCorso(boxCorsi.getValue()));
    			}
    			else {
        			txtResult.setText("Matricola non specificata oppure nessun corso selezionato.");
        			return;
        		}
    		}
    		//La matricola non è numerica
    		else{
    			txtResult.setText("Formato matricola errato");
    			return;
    		}
    	}
    }

    @FXML
    void doInserisci(ActionEvent event) {
    	txtResult.clear();
    	btnAutoComplete.setDisable(true);
    	txtNome.setDisable(true);
    	txtCognome.setDisable(true);
    	
    	Studente stemp = model.getStudente(Integer.parseInt(txtInput.getText()));
    	Corso ctemp = boxCorsi.getValue();
    	
    	//CONTROLLI PRELIMINARI : Controllo selezione corso
    	if(ctemp == null || ctemp.getNomeCorso().compareTo("") == 0){
    		txtResult.setText("Selezionare un corso a cui si desidera iscrivere lo studente.");
    		return;
    	}
    	//CONTROLLI PRELIMINARI : Controllo matricola inesistente
    	if(stemp.getMatricola() == 0){
    		txtResult.setText("La matricola selezionata non è scritta nel formato corretto o non è stata inserita.");
    		return;
    	}
    	//Controlla se lo studente è già iscritto al corso specificato
    	//CONTROLLI PRELIMINARI : Controllo matricola non numerica
    	try{
    		Integer.parseInt(txtInput.getText());
    	} 
    	catch(NumberFormatException e){
    		txtResult.setText("La matricola inserita non è un dato numerico.");
    		return;
    	}
    	
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
