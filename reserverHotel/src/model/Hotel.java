package model;

import java.util.ArrayList;

public class Hotel {

	String nomHotel;
	ArrayList<Client> alClients = new ArrayList<>();
	ArrayList<Habitació> alHabitació = new ArrayList<>();
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


	public ArrayList<Habitació> getAlHabitació() {
		return alHabitació;
	}

	public void setAlHabitació(ArrayList<Habitació> alHabitació) {
		this.alHabitació = alHabitació;
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

	
	public boolean buscarHabitació(Reserva res) {
		int limit=2;
		System.out.println("Start for");
		for(int i =0; i<limit; i++) {
			
			for( Habitació hh : this.getAlHabitació()) {
				
				System.out.println("En el for"+ hh.getNumPersones()+"  "+(Integer.parseInt(res.getNumPersones())+i));
				
				if(hh.getNumPersones()==(Integer.parseInt(res.getNumPersones())+i)) {
					System.out.println(" habitacions iguals");
					
					//res.setNumHabitació(Integer.toString(hh.getNumHabitació()));
					
					if(res.checkHabitació(this, hh)) {
						System.out.println(" check habitació");
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public ArrayList<Client> cercarClients(String text){
		
		ArrayList<Client> arrayListClients = new ArrayList<Client>();
		
		for(Client c : this.getAlClients()) {
			if(c.getNom().toLowerCase().contains(text.toLowerCase()) || c.getCognoms().toLowerCase().contains(text.toLowerCase()) || c.getDni().toLowerCase().contains(text.toLowerCase())) {
				System.out.println("a hotel");
				arrayListClients.add(c);
			}
		}
		return arrayListClients;
	}
	
	
	public ArrayList<Reserva> cercarReservas(Client c){
		
		ArrayList<Reserva> arrayListReservas = new ArrayList<Reserva>();
		
		
			for(Reserva r : this.getAlReservesPendents()) {
				if(c.getNom().toLowerCase().contains(r.getClient().getNom().toLowerCase()) || c.getCognoms().toLowerCase().contains(r.getClient().getCognoms().toLowerCase()) ||
						c.getDni().toLowerCase().contains(r.getClient().getDni().toLowerCase())) {
					arrayListReservas.add(r);
				}
			}

			for(Reserva rr : this.getAlReservesConfirmades()) {
				if(c.getNom().toLowerCase().contains(rr.getClient().getNom().toLowerCase()) || c.getCognoms().toLowerCase().contains(rr.getClient().getCognoms().toLowerCase()) ||
						c.getDni().toLowerCase().contains(rr.getClient().getDni().toLowerCase())) {
					arrayListReservas.add(rr);
				}
			}
		

		return arrayListReservas;

	}

	public void removeValueOfArrayList(ArrayList<Reserva> alAborrar, int i) {
		
		alAborrar.remove(i);
		
	}

	
	

}
