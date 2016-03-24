package it.polito.tdp.lab3.model;

import java.util.*;

import it.polito.tdp.lab3.db.CorsoDAO;

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

}
