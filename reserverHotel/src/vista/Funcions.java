package vista;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import model.Client;
import model.Hotels;


public class Funcions {

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
		
		if(tfNom.getText().matches("^[ A-Za-zÒ—·ÈÌÛ˙¡…Õ”⁄]+$")) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean comprovarCognoms(JTextField tfCognoms) {
		
		if(tfCognoms.getText().matches("^[ A-Za-zÒ—·ÈÌÛ˙¡…Õ”⁄]+$")) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean comprovarNits(JTextField tfNumNits) {
		
		
		if(tfNumNits.getText().matches("[1-9]{1,2}")) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean comprovarPersones(JTextField tfNumPersones) {
		
		if(tfNumPersones.getText().matches("[1-9]{1,2}")) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean comprovarClient(Hotels hotel , JTextField tfDni, JTextField tfNom, JTextField tfCognoms) {
		
		for (Client c : hotel.getAlClients()){
			
			if(c.getDni().equalsIgnoreCase(tfDni.getText()) && c.getNom().equalsIgnoreCase(tfNom.getText()) && c.getCognoms().equalsIgnoreCase(tfCognoms.getText())){
				return true;
				
			}
		}
		return false;
		
			
		
	}

	public static Client agafarClient(Hotels hotel, JTextField tfDni, JTextField tfNom, JTextField tfCognoms) {
		
		for (Client c : hotel.getAlClients()){
			
			if(c.getDni().equalsIgnoreCase(tfDni.getText()) && c.getNom().equalsIgnoreCase(tfNom.getText()) && c.getCognoms().equalsIgnoreCase(tfCognoms.getText())){
				return c;
			}
		}
		return null;
	}

	public static LocalDate dataEntrada(JCalendar calendari2) {
		
		Long dataNano = calendari2.getDate().getTime();
        LocalDate data = Instant.ofEpochMilli(dataNano).atZone(ZoneId.systemDefault()).toLocalDate();
        return data;
	}

	
	

	
	
}
