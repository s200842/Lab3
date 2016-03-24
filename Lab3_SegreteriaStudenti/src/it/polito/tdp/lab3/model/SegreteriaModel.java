package it.polito.tdp.lab3.model;

import java.util.*;

import it.polito.tdp.lab3.db.CorsoDAO;
import it.polito.tdp.lab3.db.StudenteDAO;

public class SegreteriaModel {
	
	private List<String> elencoCorsi;
	
	public SegreteriaModel(){
		elencoCorsi = new ArrayList<String>();
	}
	
	public List<String> getCorsi(){
		CorsoDAO dao = new CorsoDAO();
		elencoCorsi = dao.getCorsiFromDB();
		return elencoCorsi;
	}
	
	public Studente getStudente(String matricola){
		StudenteDAO dao = new StudenteDAO();
		Studente stemp = dao.getStudenteFromDB(matricola);
		//Gestione matricola inesistente o nulla
		if(stemp == null){
			return new Studente(null, null, null, null);
		}
		else return stemp;
	}

}
