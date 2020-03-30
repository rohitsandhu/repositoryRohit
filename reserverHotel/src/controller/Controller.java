package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

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
        
        System.out.println("#########################");
        System.out.println("Data abans--> "+ data);
        System.out.println("#########################");
        
        LocalDate data2=data.plus(Integer.parseInt(nits.getText()), ChronoUnit.DAYS);
        
        
        System.out.println("nits --> "+ nits.getText());
        System.out.println("#########################");
        System.out.println("Data despres--> "+ data2);
        System.out.println("#########################");
        return data2;
	}

	public static void addTitle(JTextField tfNomHotel) {
		hotel.setNomHotel(tfNomHotel.getText());
	}

	
	public static boolean ferReserva(JTextField tfDni, JTextField tfNom, JTextField tfCognoms, JTextField tfNumPersones,
			JCalendar calendari2, JTextField nits, DefaultTableModel model1 ) {
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
					
					Controller.buidarIOmplirTaulaReservesPendents(model1);

					return true;
				}
			}
		}
		return false;		
	}
	
	public static boolean crearClientReserva(JTextField tfDni, JTextField tfNom, JTextField tfCognoms, JTextField tfNumPersones,
			JCalendar calendari2, JTextField nits, DefaultTableModel model1) {
		
		Client cli = new Client(tfDni.getText());
		cli.setNom(tfNom.getText());
		cli.setCognoms(tfCognoms.getText());

		System.out.println("Aclient");
		
		Reserva res = new Reserva();
		res.setClient(cli);
		res.setNumPersones(tfNumPersones.getText());
		res.setLdEntrada(Controller.dataEntrada(calendari2));
		res.setLdSortida(Controller.dataSortida(calendari2, nits));
		if(hotel.buscarHabitació(res)) {
			hotel.getAlClients().add(cli);
			System.out.println(" Buscar habitació2");
			hotel.getAlReservesPendents().add(res);			
			Controller.buidarIOmplirTaulaReservesPendents(model1);

			return true;
		}
		return false;	
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

	public static void comfirmarLaReserva(int row) {

		hotel.getAlReservesConfirmades().add(hotel.getAlReservesPendents().get(row));
		hotel.getAlReservesPendents().remove(row);
	}

	public static void borrarReservaPendent(int row) {

		hotel.getAlReservesPendents().remove(row);
	}

	public static void posarReservaAComfirmades() {
		
	}
	
	public static void buidarIOmplirTaulaReservesPendents(DefaultTableModel model) {
		model.setRowCount(0);
		for (Reserva rr : hotel.getAlReservesPendents()) {
			model.addRow(rr.arrayReservasPendent());
		}			
	}

	public static void toggleNoSelecionat(JDateChooser calendari1, DefaultTableModel model2) {
		
		Long data = calendari1.getDate().getTime();
		LocalDate ld = Instant.ofEpochMilli(data).atZone(ZoneId.systemDefault()).toLocalDate();
		model2.setRowCount(0);
		
		for(Reserva rr: hotel.getAlReservesConfirmades()) {
			System.out.println("En el for per comprovar si funciona entrada");
			if(rr.getLdEntrada().isEqual(ld)) {
				
				System.out.println("la comparació funciona entrada");
				model2.addRow(rr.arrayReservasComfirmades());
			}
		}
	}
	
	public static void toggleSelecionat(JDateChooser calendari1, DefaultTableModel model2) {
		
		Long data = calendari1.getDate().getTime();
		LocalDate ld = Instant.ofEpochMilli(data).atZone(ZoneId.systemDefault()).toLocalDate();
		model2.setRowCount(0);
		
		for(Reserva rr: hotel.getAlReservesConfirmades()) {
			System.out.println("En el for per comprovar si funciona sortida");
			System.out.println(rr.getLdSortida()+"        "+ld);
			if(rr.getLdSortida().isEqual(ld)) {
				
				System.out.println("la comparació funcionzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzza sortida");
				model2.addRow(rr.arrayReservasComfirmades());
			}
		}
	}

	public static void ferUpdateAlaTaula(JDateChooser calendari1, DefaultTableModel model2, JToggleButton toggle) {
		
		if(toggle.isSelected()) {
			System.out.println(" funciona1");
			 toggleSelecionat(calendari1, model2);
		}else {
			System.out.println("Funciona2");
			toggleNoSelecionat( calendari1,  model2);
		}
	}

	public static void cercar(String text, DefaultListModel<Client> listModel1) {

			listModel1.clear();
			
		for(Client cc : hotel.cercarClients(text)) {
			System.out.println("A controller");
			
			listModel1.addElement(cc);
		}
		
	}

	public static void cercarR(Client client, DefaultListModel<Reserva> listModel2) {
		
		listModel2.clear();
		
			for(Reserva rr : hotel.cercarReservas(client)) {
				
				listModel2.addElement(rr);
			}
		}

	public static void borrarReserva(Reserva reservaABorrar) {
		
		Reserva res = reservaABorrar;
		int i=0;
	//if(hotel.getAlReservesConfirmades().size()>0) {
		for(Reserva rr : hotel.getAlReservesConfirmades()) {
			if(rr.getLdEntrada()==res.getLdEntrada() && rr.getLdSortida()==res.getLdSortida() && rr.getNumHabitació()==res.getNumHabitació() && rr.getClient().getDni().equals(res.getClient().getDni())) {

				hotel.removeValueOfArrayList(hotel.getAlReservesConfirmades(), i);
				break;
			}
			i++;
		}
	//}

	//if(hotel.getAlReservesConfirmades().size()>0) {
		i=0;	
		for(Reserva rr : hotel.getAlReservesPendents()) {
			if(rr.getLdEntrada()==res.getLdEntrada() && rr.getLdSortida()==res.getLdSortida() && rr.getNumHabitació().equals(res.getNumHabitació()) && rr.getClient().getDni()==res.getClient().getDni()) {

				hotel.removeValueOfArrayList(hotel.getAlReservesPendents(), i);
				break;
			}
			i++;
		}
	//}
	}
}
