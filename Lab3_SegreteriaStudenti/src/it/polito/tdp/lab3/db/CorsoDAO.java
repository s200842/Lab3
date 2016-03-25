package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;

//Classe per l'accesso al DB

public class CorsoDAO {
	
	public List<Corso> getCorsiFromDB(){
		//Apertura connessione con database
		try {
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
			Connection c = DriverManager.getConnection(jdbcURL);
			Statement st = c.createStatement();
			String sql = "SELECT * FROM corso";
			ResultSet res = st.executeQuery(sql);
			//Elaborazione risultati
			List<Corso> elencoCorsiDB = new ArrayList<Corso>();
			elencoCorsiDB.add(new Corso("", 0, "", 0));
			while(res.next()){
				String codIns = res.getString("codins");
				int crediti = res.getInt("crediti");
				String nomeCorso = res.getString("nome");
				int pd = res.getInt("pd");
				Corso ctemp = new Corso(codIns, crediti, nomeCorso, pd);
				elencoCorsiDB.add(ctemp);
			}
			res.close();
			c.close();
			return elencoCorsiDB;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Corso> getCorsoFromDB(Studente s){
		//Apertura connessione con database
		try {
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
			Connection c = DriverManager.getConnection(jdbcURL);
			Statement st = c.createStatement();
			String sql = "SELECT codins FROM iscrizione WHERE matricola = '"+s.getMatricola()+"';";
			ResultSet res = st.executeQuery(sql);
			//Elaborazione risultati; Qui ho una lista di codici insegnamento relativi ai corsi seguiti dallo studente s
			List<String> elencoCodCorsiStudente = new ArrayList<String>();
			while(res.next()){
				String codIns = res.getString("codins");
				elencoCodCorsiStudente.add(codIns);
			}
			//Eventuale studente senza corsi
			if(elencoCodCorsiStudente.size()==0){
				return null;
			}
			res.close();
			//Per ogni codins genero il corso corrispondente e lo salvo in una lista
			List<Corso> elencoCorsiStudente = new ArrayList<Corso>();
			for(String codins : elencoCodCorsiStudente){
				String sql2 = "SELECT crediti, nome, pd FROM corso WHERE codins = '"+codins+"';";
				ResultSet res2 = st.executeQuery(sql2);
				while(res2.next()){
					int crediti = res2.getInt("crediti");
					String nomeCorso = res2.getString("nome");
					int pd = res2.getInt("pd");
					Corso ctemp = new Corso(codins, crediti, nomeCorso, pd);
					elencoCorsiStudente.add(ctemp);
				}
				res2.close();
			}			
			c.close();
			return elencoCorsiStudente;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public boolean iscrivi(Studente s, Corso c){
		//Apertura connessione con database
		try {
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
			Connection con = DriverManager.getConnection(jdbcURL);
			Statement st = con.createStatement();
			String sql = "INSERT INTO iscritticorsi.iscrizione (matricola, codins) VALUES ("+s.getMatricola()+", '"+c.getCodIns()+"');";
			int result = st.executeUpdate(sql);
			if(result == 1){
				return true;
			}
			else return false;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}			
		return false;
	}
	
}
