package model;

import java.util.ArrayList;

public class Hotel {

	String nomHotel;
	ArrayList<Client> alClients = new ArrayList<>();
	ArrayList<Habitaci�> alHabitaci� = new ArrayList<>();
	ArrayList<Reserva> alReservesPendents = new ArrayList<>();
	ArrayList<Reserva> alReservesConfirmades = new ArrayList<>();
	
	public Hotel(String nomHotel) {
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


	public ArrayList<Habitaci�> getAlHabitaci�() {
		return alHabitaci�;
	}

	public void setAlHabitaci�(ArrayList<Habitaci�> alHabitaci�) {
		this.alHabitaci� = alHabitaci�;
	}

	public ArrayList<Reserva> getAlReservesPendents() {
		return alReservesPendents;
	}

	public void setAlReservesPendents(ArrayList<Reserva> alReservesPendents) {
		this.alReservesPendents = alReservesPendents;
	}

	public ArrayList<Reserva> getAlReservesConfirmades() {
		return alReservesConfirmades;
	}

	public void setAlReservesConfirmades(ArrayList<Reserva> alReservesConfirmades) {
		this.alReservesConfirmades = alReservesConfirmades;
	}

	
	public boolean buscarHabitaci�(Reserva res) {
		int limit=2;
		System.out.println("Start for");
		for(int i =0; i<limit; i++) {
			
			for( Habitaci� hh : this.getAlHabitaci�()) {
				
				System.out.println("En el for"+ hh.getNumPersones()+"  "+(Integer.parseInt(res.getNumPersones())+i));
				
				if(hh.getNumPersones()==(Integer.parseInt(res.getNumPersones())+i)) {
					System.out.println(" habitacions iguals");
					
					//res.setNumHabitaci�(Integer.toString(hh.getNumHabitaci�()));
					
					if(res.checkHabitaci�(this, hh)) {
						System.out.println(" check habitaci�");
						
						return true;
					}
				}
			}
		}
		return false;
	}

	
	

}
