package it.polito.tdp.lab3.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Connection;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {
	
	//Cerca nel DB lo studente con la matricola data
	
	public Studente getStudenteFromDB(String matricola){
		//Connessione al DB
		try{
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
			Connection c = DriverManager.getConnection(jdbcURL);
			Statement st = c.createStatement();
			//Gestione matricola vuota o non numerica
			if(matricola.compareTo("")==0){
				return null;
			}
			String sql = "SELECT nome, cognome, cds FROM studente WHERE matricola = "+matricola+";";
			ResultSet res = st.executeQuery(sql);
			//Gestione dei dati
			if(res.next()){
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				String cds = res.getString("cds");
				Studente stemp = new Studente(matricola, nome, cognome, cds);
				res.close();
				c.close();
				return stemp;
			}
			else{
				res.close();
				c.close();
				return null;
			}
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	//Cerca nel DB gli studenti che seguono il corso specificato
	
	public List<String> getMatricoleIscrittiCorsiFromDB(Corso corso){
		//Connessione al DB
		try{
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
			Connection c = DriverManager.getConnection(jdbcURL);
			Statement st = c.createStatement();
			String sql = "SELECT matricola FROM iscrizione WHERE codins = '"+corso.getCodIns()+"';";
			ResultSet res = st.executeQuery(sql);
			//Gestione dei dati
			List<String> elencoMatricoleIscritti = new ArrayList<String>();
			while(res.next()){
				String matricola = res.getString("matricola");
				elencoMatricoleIscritti.add(matricola);
			}
			res.close();
			c.close();
			return elencoMatricoleIscritti;			
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
		
	}

}
