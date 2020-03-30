package model;

import java.time.LocalDate;
import java.util.Vector;

public class Reserva {

	private Client client;
	private String numHabitació;
	private String numPersones;
	private LocalDate ldEntrada;
	private LocalDate ldSortida;

	public String[] arrayReservasPendent() {
		String[] array = new String[4];
		array[0] = client.getDni();
		array[1] = this.ldEntrada.getDayOfMonth() + "/" + this.ldEntrada.getMonthValue() + "/"
				+ this.ldEntrada.getYear();
		array[2] = this.numPersones + "";
		array[3] = this.getNumHabitació();
		return array;
	}

	public String[] arrayReservasComfirmades() {
		String[] array = new String[4];
		array[0] = client.getDni();
		array[1] = client.getNom();
		array[2] = client.getCognoms();
		array[3] = this.getNumHabitació();
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

		for (Reserva rr : hotel.getAlReservesPendents()) {
			   if(hh.getNumHabitació()==Integer.parseInt(rr.getNumHabitació())) {

			if (rr.getLdEntrada().equals(this.getLdEntrada())) {
				return false;
			} else if (this.getLdEntrada().isAfter(rr.getLdEntrada())
					&& this.getLdEntrada().isBefore(rr.getLdSortida())) {
				return false;
			} else if (this.getLdSortida().isAfter(rr.getLdEntrada()) && this.getLdSortida().isBefore(rr.getLdSortida())
					|| this.getLdSortida().isEqual((rr.getLdSortida()))) {
				return false;
			} else if (this.getLdEntrada().isBefore(rr.getLdEntrada())
					&& (this.getLdSortida().isAfter(rr.getLdSortida())
							|| this.getLdSortida().isEqual((rr.getLdSortida())))) {
				return false;
			}
		}
			   }

		for (Reserva rr : hotel.getAlReservesConfirmades()) {
			if(hh.getNumHabitació()==Integer.parseInt(rr.getNumHabitació())) {

			if (rr.getLdEntrada().equals(this.getLdEntrada())) {
				return false;
			} else if (this.getLdEntrada().isAfter(rr.getLdEntrada())
					&& this.getLdEntrada().isBefore(rr.getLdSortida())) {
				return false;
			} else if (this.getLdSortida().isAfter(rr.getLdEntrada()) && this.getLdSortida().isBefore(rr.getLdSortida())
					|| this.getLdSortida().isEqual((rr.getLdSortida()))) {
				return false;
			} else if (this.getLdEntrada().isBefore(rr.getLdEntrada())
					&& (this.getLdSortida().isAfter(rr.getLdSortida())
							|| this.getLdSortida().isEqual((rr.getLdSortida())))) {
				return false;
			}
		}
			   }

		this.setNumHabitació(Integer.toString(hh.getNumHabitació()));
		return true;
	}

	@Override
	public String toString() {
		return this.ldEntrada.getDayOfMonth() + "/" + this.ldEntrada.getMonthValue() + "/" + this.ldEntrada.getYear()
				+ " Pers:" + this.getNumPersones();
	}
}
