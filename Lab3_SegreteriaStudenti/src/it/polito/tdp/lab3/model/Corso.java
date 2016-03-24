package it.polito.tdp.lab3.model;

//Java Bean

public class Corso {
	
	private String codIns;
	private int crediti;
	private String nomeCorso;
	private String periodoDidattico;
	
	public Corso(String codIns, int crediti, String nomeCorso, String periodoDidattico) {
		this.codIns = codIns;
		this.crediti = crediti;
		this.nomeCorso = nomeCorso;
		this.periodoDidattico = periodoDidattico;
	}

	public String getCodIns() {
		return codIns;
	}

	public void setCodIns(String codIns) {
		this.codIns = codIns;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public String getPeriodoDidattico() {
		return periodoDidattico;
	}

	public void setPeriodoDidattico(String periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codIns == null) ? 0 : codIns.hashCode());
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
		Corso other = (Corso) obj;
		if (codIns == null) {
			if (other.codIns != null)
				return false;
		} else if (!codIns.equals(other.codIns))
			return false;
		return true;
	}
	
	
	

}
