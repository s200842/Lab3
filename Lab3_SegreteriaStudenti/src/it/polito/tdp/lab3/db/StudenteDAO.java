package it.polito.tdp.lab3.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import it.polito.tdp.lab3.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteFromDB(String matricola){
		//Connessione al DB
		try{
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
			Connection c = DriverManager.getConnection(jdbcURL);
			Statement st = c.createStatement();
			//Gestione matricola vuota
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
				return stemp;
			}
			else{
				return null;
			}
		} 
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
		
	}

}
