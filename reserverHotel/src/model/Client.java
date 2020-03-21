package model;

public class Client {

	
	public String Nom, Dni, Cognoms;

	public Client(String dni) {
		super();
		Dni = dni;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getCognoms() {
		return Cognoms;
	}

	public void setCognoms(String cognoms) {
		Cognoms = cognoms;
	}
	
	
	
}
