package it.polito.tdp.lab3.model;

import java.util.*;

import it.polito.tdp.lab3.db.CorsoDAO;
import it.polito.tdp.lab3.db.StudenteDAO;

public class SegreteriaModel {
	
	private List<Corso> elencoCorsi;
	
	public SegreteriaModel(){
		elencoCorsi = new ArrayList<Corso>();
	}
	
	//Popola la comboBox con i corsi del DB
	
	public List<Corso> getCorsi(){
		CorsoDAO dao = new CorsoDAO();
		elencoCorsi = dao.getCorsiFromDB();
		return elencoCorsi;
	}
	
	//Data una matricola fornisce lo studente
	
	public Studente getStudente(String matricola){
		StudenteDAO dao = new StudenteDAO();
		Studente stemp = dao.getStudenteFromDB(matricola);
		//Gestione matricola inesistente o nulla
		if(stemp == null){
			return new Studente(null, null, null, null);
		}
		else return stemp;
	}
	
	//Dato un corso, fornisce la lista di studenti che lo seguono
	
	public String segueCorso(Corso corso){
		List<Studente> studentiCorso = new ArrayList<Studente>();
		StudenteDAO dao = new StudenteDAO();
		List<String> matricoleCorso = dao.getMatricoleIscrittiCorsiFromDB(corso);
		//Popolo la lista di studenti per scriverli in un'unica stringa
		for(String matricola : matricoleCorso){
			Studente stemp = this.getStudente(matricola);
			studentiCorso.add(stemp);
		}
		String result = "";
		for(Studente stemp : studentiCorso){
			result += stemp.toString()+"\n";
		}
		return result;
		
	}
}
