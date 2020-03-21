package model;

import java.time.LocalDate;

public class Reserva {

	private Client client;
	private Habitació habitació;
	private	String numPersones;
	private LocalDate ldEntrada;
	private LocalDate ldSortida;
	
	
	public String[] arrayReservasPendent() {
        String[] array = new String[4];
        array[0]=client.getDni();
        array[1]=this.ldEntrada.getDayOfMonth()+"-"+this.ldEntrada.getMonthValue()+"-"+this.ldEntrada.getYear();
        array[2]= this.numPersones+"";
        array[3]="";
        return array;
    }

	

	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Habitació getHabitació() {
		return habitació;
	}


	public void setHabitació(Habitació habitació) {
		this.habitació = habitació;
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
	
	
}
