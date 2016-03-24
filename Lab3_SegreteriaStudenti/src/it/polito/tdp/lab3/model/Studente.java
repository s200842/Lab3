package it.polito.tdp.lab3.model;

//Java Bean

public class Studente {
	
	private String matricola;
	private String nomeStudente;
	private String cognomeStudente;
	private String corsoStudi;
	
	public Studente(String matricola, String nomeStudente, String cognomeStudente, String corsoStudi) {
		this.matricola = matricola;
		this.nomeStudente = nomeStudente;
		this.cognomeStudente = cognomeStudente;
		this.corsoStudi = corsoStudi;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNomeStudente() {
		return nomeStudente;
	}

	public void setNomeStudente(String nomeStudente) {
		this.nomeStudente = nomeStudente;
	}

	public String getCognomeStudente() {
		return cognomeStudente;
	}

	public void setCognomeStudente(String cognomeStudente) {
		this.cognomeStudente = cognomeStudente;
	}

	public String getCorsoStudi() {
		return corsoStudi;
	}

	public void setCorsoStudi(String corsoStudi) {
		this.corsoStudi = corsoStudi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}
	
	public String toString(){
		return matricola+"   "+nomeStudente+" "+cognomeStudente+" "+corsoStudi;
	}
	
	

}
