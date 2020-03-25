package model;

import java.time.LocalDate;

public class Reserva {

	private Client client;
	private String numHabitació;
	private	String numPersones;
	private LocalDate ldEntrada;
	private LocalDate ldSortida;
	
	
	public String[] arrayReservasPendent() {
        String[] array = new String[4];
        array[0]=client.getDni();
        array[1]=this.ldEntrada.getDayOfMonth()+"-"+this.ldEntrada.getMonthValue()+"-"+this.ldEntrada.getYear();
        array[2]=this.numPersones+"";
        array[3]=this.getNumHabitació();
        return array;
    }

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNumHabitació() {
		return numHabitació;
	}


	public void setNumHabitació(String numHabitació) {
		this.numHabitació = numHabitació;
	}

	public String getNumPersones() {
		return numPersones;
	}

	public void setNumPersones(String numPersones) {
		this.numPersones = numPersones;
	}

	public LocalDate getLdEntrada() {
		return ldEntrada;
	}

	public void setLdEntrada(LocalDate ldEntrada) {
		this.ldEntrada = ldEntrada;
	}

	public LocalDate getLdSortida() {
		return ldSortida;
	}

	public void setLdSortida(LocalDate ldSortida) {
		this.ldSortida = ldSortida;
	}

	public boolean checkHabitació(Hotel hotel, Habitació hh) {

			for(Reserva rr : hotel.getAlReservesPendents()) {
					if( Integer.parseInt(rr.getNumHabitació()) == hh.getNumHabitació() && !(
						(this.getLdEntrada().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdEntrada().isBefore(rr.getLdSortida())) ||
						(this.getLdSortida().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isBefore(rr.getLdSortida())) ||
						(this.getLdEntrada().isBefore(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isAfter(rr.getLdSortida())) )) {
						
						this.setNumHabitació(Integer.toString(hh.getNumHabitació()));
						return false;
					}
			}
			
			for(Reserva rr : hotel.getAlReservesConfirmades()) {
					if( Integer.parseInt(rr.getNumHabitació()) == hh.getNumHabitació() && !(
						(this.getLdEntrada().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdEntrada().isBefore(rr.getLdSortida())) ||
						(this.getLdSortida().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isBefore(rr.getLdSortida())) ||
						(this.getLdEntrada().isBefore(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isAfter(rr.getLdSortida())) )) {
						
						this.setNumHabitació(Integer.toString(hh.getNumHabitació()));
						return false;
					}
			}
			
			this.setNumHabitació(Integer.toString(hh.getNumHabitació()));
			return true;

	}
	
	
}
