package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import it.polito.tdp.lab3.model.Corso;

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
	
	

}
