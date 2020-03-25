package model;

import java.time.LocalDate;

public class Reserva {

	private Client client;
	private String numHabitaci�;
	private	String numPersones;
	private LocalDate ldEntrada;
	private LocalDate ldSortida;
	
	
	public String[] arrayReservasPendent() {
        String[] array = new String[4];
        array[0]=client.getDni();
        array[1]=this.ldEntrada.getDayOfMonth()+"-"+this.ldEntrada.getMonthValue()+"-"+this.ldEntrada.getYear();
        array[2]=this.numPersones+"";
        array[3]=this.getNumHabitaci�();
        return array;
    }

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNumHabitaci�() {
		return numHabitaci�;
	}


	public void setNumHabitaci�(String numHabitaci�) {
		this.numHabitaci� = numHabitaci�;
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

	public boolean checkHabitaci�(Hotel hotel, Habitaci� hh) {

			for(Reserva rr : hotel.getAlReservesPendents()) {
					if( Integer.parseInt(rr.getNumHabitaci�()) == hh.getNumHabitaci�() && !(
						(this.getLdEntrada().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdEntrada().isBefore(rr.getLdSortida())) ||
						(this.getLdSortida().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isBefore(rr.getLdSortida())) ||
						(this.getLdEntrada().isBefore(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isAfter(rr.getLdSortida())) )) {
						
						this.setNumHabitaci�(Integer.toString(hh.getNumHabitaci�()));
						return false;
					}
			}
			
			for(Reserva rr : hotel.getAlReservesConfirmades()) {
					if( Integer.parseInt(rr.getNumHabitaci�()) == hh.getNumHabitaci�() && !(
						(this.getLdEntrada().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdEntrada().isBefore(rr.getLdSortida())) ||
						(this.getLdSortida().isAfter(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isBefore(rr.getLdSortida())) ||
						(this.getLdEntrada().isBefore(rr.getLdEntrada().minusDays(1)) && this.getLdSortida().isAfter(rr.getLdSortida())) )) {
						
						this.setNumHabitaci�(Integer.toString(hh.getNumHabitaci�()));
						return false;
					}
			}
			
			this.setNumHabitaci�(Integer.toString(hh.getNumHabitaci�()));
			return true;

	}
	
	
}
