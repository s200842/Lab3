package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

//Classe per l'accesso al DB

public class CorsoDAO {
	
	public List<String> getCorsiFromDB(){
		//Apertura connessione con database
		try {
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";
			Connection c = DriverManager.getConnection(jdbcURL);
			Statement st = c.createStatement();
			String sql = "SELECT nome FROM corso";
			ResultSet res = st.executeQuery(sql);
			//Elaborazione risultati
			List<String> elencoCorsiDB = new ArrayList<String>();
			elencoCorsiDB.add("");
			while(res.next()){
				String nomeCorso = res.getString("nome");
				elencoCorsiDB.add(nomeCorso);
			}
			return elencoCorsiDB;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
