package classes;

import java.util.ArrayList;

public class Hotels {

	String nomHotel;
	ArrayList<Client> alClients = new ArrayList<>();
	ArrayList<Client> alHabitació = new ArrayList<>();
	ArrayList<Client> alReservesPendents = new ArrayList<>();
	ArrayList<Client> alReservesConfirmades = new ArrayList<>();
	
	public Hotels(String nomHotel) {
		super();
		this.nomHotel = nomHotel;
	}

	public String getNomHotel() {
		return nomHotel;
	}

	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}

	public ArrayList<Client> getAlClients() {
		return alClients;
	}

	public void setAlClients(ArrayList<Client> alClients) {
		this.alClients = alClients;
	}
	
	public void addClient(Client cli) {
		this.alClients.add(cli);
	}

	public ArrayList<Client> getAlHabitació() {
		return alHabitació;
	}

	public void setAlHabitació(ArrayList<Client> alHabitació) {
		this.alHabitació = alHabitació;
	}

	public ArrayList<Client> getAlReservesPendents() {
		return alReservesPendents;
	}

	public void setAlReservesPendents(ArrayList<Client> alReservesPendents) {
		this.alReservesPendents = alReservesPendents;
	}

	public ArrayList<Client> getAlReservesConfirmades() {
		return alReservesConfirmades;
	}

	public void setAlReservesConfirmades(ArrayList<Client> alReservesConfirmades) {
		this.alReservesConfirmades = alReservesConfirmades;
	}
	
	
	

}
