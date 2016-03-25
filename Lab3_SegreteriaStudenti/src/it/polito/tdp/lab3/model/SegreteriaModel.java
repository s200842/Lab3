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
	
	public Studente getStudente(int matricola){
		StudenteDAO dao = new StudenteDAO();
		Studente stemp = dao.getStudenteFromDB(matricola);
		//Gestione matricola inesistente o nulla
		if(stemp == null){
			return new Studente(-1, null, null, null);
		}
		else return stemp;
	}
	
	//Dato un corso, fornisce la lista di studenti che lo seguono
	
	public String segueCorso(Corso corso){
		List<Studente> studentiCorso = new ArrayList<Studente>();
		StudenteDAO dao = new StudenteDAO();
		List<Integer> matricoleCorso = dao.getMatricoleIscrittiCorsiFromDB(corso);
		//Popolo la lista di studenti per scriverli in un'unica stringa
		for(int matricola : matricoleCorso){
			Studente stemp = this.getStudente(matricola);
			studentiCorso.add(stemp);
		}
		String result = "";
		for(Studente stemp : studentiCorso){
			result += stemp.toString()+"\n";
		}
		return result;
	}
	
	//Dato uno studente, fornisce i corsi seguiti
	
	public String corsoStudente(Studente s){
		CorsoDAO dao = new CorsoDAO();
		List<Corso> corsiStudente = dao.getCorsoFromDB(s);
		//Gestione studente senza corsi
		if(corsiStudente == null){
			if(s.getMatricola() == -1){
				return "Lo studente selezionato non è presente nel database.";
			}
			else{
				return String.format("Lo studente %s %s non è iscritto ad alcun corso.", s.getNomeStudente(), s.getCognomeStudente());
			}
		}
		else{
			String result = "";
			for(Corso c : corsiStudente){
				result += c.toStringCompleto()+"\n";
			}
			return result;
		}	
	}
	
	//Dati un corso ed uno studente, verifica se lo studente è iscritto a quel corso
	
	public boolean corsoHaStudente(Corso c, Studente s){
		StudenteDAO dao = new StudenteDAO();
		List<Integer> matricoleCorso = dao.getMatricoleIscrittiCorsiFromDB(c);
		if(matricoleCorso.contains(s.getMatricola())){
			return true;
		}
		else{
			return false;
		}
	}
}
