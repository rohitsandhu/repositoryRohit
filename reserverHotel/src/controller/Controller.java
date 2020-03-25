package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import model.*;


public class Controller {

	static Hotel hotel = new Hotel("HOTEL");
	
	public static boolean comprovarDni(JTextField tfDni) {
		
		    boolean resultat = false;

		    Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		    Matcher matcher = pattern.matcher(tfDni.getText());

		    if (matcher.matches()) {

		        String letra = matcher.group(2);

		        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

		        int index = Integer.parseInt(matcher.group(1));

		        index = index % 23;

		        String reference = letras.substring(index, index + 1);

		        if (reference.equalsIgnoreCase(letra)) {
		        	resultat = true;
		        } else {
		        	resultat = false;
		        }
		    } else {
		    	resultat = false;
		    }
		    return resultat;
	}

	public static boolean comprovarNom(JTextField tfNom) {
		
		if(tfNom.getText().matches("^[ A-Za-zñÑáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙÇç]+$")) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean comprovarCognoms(JTextField tfCognoms) {
		
		if(tfCognoms.getText().matches("^[ A-Za-zñÑáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙÇç]+$")) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean comprovarNits(JTextField tfNumNits) {	
		if(tfNumNits.getText().matches("\\d+")) {
			if(Integer.parseInt(tfNumNits.getText()) > 0 && Integer.parseInt(tfNumNits.getText()) <31) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	public static boolean comprovarPersones(JTextField tfNumPersones) {
		
		if(tfNumPersones.getText().matches("\\d+")) {
			if(Integer.parseInt(tfNumPersones.getText()) > 0 && Integer.parseInt(tfNumPersones.getText()) <11) {
				return true;
			}	
		}
		return false;
	}

	
	public static boolean comprovarClient(JTextField tfDni, JTextField tfNom, JTextField tfCognoms) {


		for (Client c : hotel.getAlClients()){
			
			if(c.getDni().equalsIgnoreCase(tfDni.getText()) && c.getNom().equalsIgnoreCase(tfNom.getText()) && c.getCognoms().equalsIgnoreCase(tfCognoms.getText())){
				return true;
			}
		}
		return false;	
	}
	
	public static LocalDate dataEntrada(JCalendar calendari2) {
		
		Long dataNano = calendari2.getDate().getTime();
        LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
        return data;
	}
	
	public static LocalDate dataSortida(JCalendar calendari2, JTextField nits) {
		
		Long dataNano = calendari2.getDate().getTime();
		
        LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
        data.plusDays(Integer.parseInt(nits.getText()));
        return data;
	}

	public static void addTitle(JTextField tfNomHotel) {
		hotel.setNomHotel(tfNomHotel.getText());
	}

	
	public static String[] ferReserva(JTextField tfDni, JTextField tfNom, JTextField tfCognoms, JTextField tfNumPersones,
			JCalendar calendari2, JTextField nits ) {
		Reserva res = new Reserva();
		for (Client c : hotel.getAlClients()){
			
			if(c.getDni().equalsIgnoreCase(tfDni.getText()) && c.getNom().equalsIgnoreCase(tfNom.getText()) && c.getCognoms().equalsIgnoreCase(tfCognoms.getText())){
				
				res.setClient(c);
				res.setNumPersones(tfNumPersones.getText());
				res.setLdEntrada(Controller.dataEntrada(calendari2));
				res.setLdSortida(Controller.dataSortida(calendari2, nits));
				if(hotel.buscarHabitació(res)) {
					System.out.println(" Buscar habitació1");
					hotel.getAlReservesPendents().add(res);
					return res.arrayReservasPendent();
				}
			}
		}
		String [] xd = {"--","--","--","--"};
		return xd;		
	}
	
	public static String[] crearClientReserva(JTextField tfDni, JTextField tfNom, JTextField tfCognoms, JTextField tfNumPersones,
			JCalendar calendari2, JTextField nits) {
		
		Client cli = new Client(tfDni.getText());
		cli.setNom(tfNom.getText());
		cli.setCognoms(tfCognoms.getText());
		hotel.addClient(cli);
		hotel.getAlClients().add(cli);
		
		Reserva res = new Reserva();
		res.setClient(cli);
		res.setNumPersones(tfNumPersones.getText());
		res.setLdEntrada(Controller.dataEntrada(calendari2));
		res.setLdSortida(Controller.dataSortida(calendari2, nits));
		if(hotel.buscarHabitació(res)) {
			System.out.println(" Buscar habitació2");
			hotel.getAlReservesPendents().add(res);
			return res.arrayReservasPendent();
		}
		 String [] xd = {"--","--","--","--"};
		return xd;	
	}

	public static boolean comprovarHabitació(JTextField tfBackPers) {
		
		if(tfBackPers.getText().matches("\\d+")) {
			return true;
		}
		return false;
	}

	public static int afegirHabitació(String numHab, String numPersones) {
		
		for (Habitació hh : hotel.getAlHabitació()) {
			if(hh.getNumHabitació()==Integer.parseInt(numHab) && hh.getNumPersones()==Integer.parseInt(numPersones) || 
					hh.getNumHabitació()==Integer.parseInt(numHab) && hh.getNumPersones()!=Integer.parseInt(numPersones)) {
				
				return hh.getNumPersones();
			}
		}
		
		Habitació h = new Habitació();
		h.setNumHabitació(Integer.parseInt(numHab));
		h.setNumPersones(Integer.parseInt(numPersones));
		hotel.getAlHabitació().add(h);
		System.out.println(" num:" + numHab+" pes:"+numPersones);
		return 0;
	}

	public static int agafarCapacitat(String numHab) {
	
		for (Habitació hh : hotel.getAlHabitació()) {
			if(Integer.parseInt(numHab)==hh.getNumHabitació()) {
				return hh.getNumPersones();
			}
		}
		return 0;
	}

	public static void actualitzarHabitació(String numHab, String numPers) {
		
		for (Habitació hh : hotel.getAlHabitació()) {
			if(Integer.parseInt(numHab)==hh.getNumHabitació()) {
				hh.setNumPersones(Integer.parseInt(numPers));
			}
		}		
	}

	public static boolean comrpovarSilaHabEstaReservada(String numHab, String numPers) {
			
		for (Reserva rr : hotel.getAlReservesPendents()) {
			if(Integer.parseInt(numHab)==Integer.parseInt(rr.getNumHabitació())) {
				return true;
			}
		}
		for (Reserva rr : hotel.getAlReservesConfirmades()) {
			if(Integer.parseInt(numHab)==Integer.parseInt(rr.getNumHabitació())) {
				return true;
			}
		}
		return false;
	}

	public static boolean comprovarData(JCalendar calendari2) {
		
		Long dataNano = calendari2.getDate().getTime();
        LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
        if(data.isBefore(LocalDate.now())) {
        	return false;
        }
		return true;
	}




	
	

	
	
}
