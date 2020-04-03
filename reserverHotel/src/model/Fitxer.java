package model;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Fitxer {

	File fitxerHab, fitxerCli, fitxerReservesComfirmades, fitxerReservesPendents, carpeta;
	FileWriter fWriter;
	BufferedWriter bWriter;
	FileReader fReader;
	BufferedReader buffReader;

	public Fitxer() {
		crearDirectori();
		crearFitxerHabitacions();
		crearFitxerClients();
		crearFitxerReservesPendents();
		crearFitxerReservesComfirmades();
	}

	// Crear la carpeta de dades si no existeix.
	public void crearDirectori() {
		carpeta = new File("dades");
		if (carpeta.mkdir()) {
			System.out.println("Carpeta creada correctament");
		} else {
			System.err.println("Error al crear el directori, potser ja estava creat..");
		}
	}

////////////////////////////HABITACIÓ/////////////////////////////////////////////////////

// Al començament pasar les dades del fitxerHab a l'arraylist de habitacions.

	// Crear el fitxerHab habitació si no existeix.
	public void crearFitxerHabitacions() {
		fitxerHab = new File("dades" + File.separator + "habitacions.txt");
		if (fitxerHab.exists()) {
			System.out.println("El fitxerHab ja existeix");
		} else {
			try {
				if (fitxerHab.createNewFile()) {
					System.out.println("fitxerHab creat correctament");
				} else {
					System.out.println("Error al crear el fitxerHab...");
				}
			} catch (IOException e) {
				System.out.println("Error al crear el fitxerHab: " + e);
			}
		}
	}
	
	
	public void afegirHabitació(Habitació h) {
		try {
			fWriter = new FileWriter(fitxerHab, true);
			bWriter = new BufferedWriter(fWriter);
		} catch (IOException e) {

			e.printStackTrace();
		}

				try {
					bWriter.write(Integer.toString(h.getNumHabitació()));
					bWriter.write("-");
					bWriter.write(Integer.toString(h.getNumPersones()));
					bWriter.write(System.lineSeparator());

				} catch (IOException e) {

					e.printStackTrace();
				}
			
			try {
				bWriter.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
	}


	// Omplir les habitacions a quan comença el programa.
	public void omplirALHabitació(ArrayList<Habitació> alHabitació) {

		try {
			fReader = new FileReader(fitxerHab);
			buffReader = new BufferedReader(fReader);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		String liniaActual = "";
		while (true) {
			try {
				if (!((liniaActual = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] arr = liniaActual.split("-");

			Habitació hab = new Habitació();
			hab.setNumHabitació(Integer.parseInt(arr[0]));
			hab.setNumPersones(Integer.parseInt(arr[1]));
			alHabitació.add(hab);
		}

		try {
			buffReader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void actualitzarHabitació(ArrayList<Habitació> alHabitacions, Habitació hh) {

		File fileTemp = new File("dades" + File.separator + "fitxerTemp.txt");

		try {
			fReader = new FileReader(fitxerHab);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fWriter = new FileWriter(fileTemp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		buffReader = new BufferedReader(fReader);
		bWriter = new BufferedWriter(fWriter);

		String liniaActual = "";
		while (true) {
			try {
				if (!((liniaActual = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] arr = liniaActual.split("-");

			if (hh.getNumHabitació() == Integer.parseInt(arr[0])) {
				try {
					bWriter.write(Integer.toString(hh.getNumHabitació()));
					bWriter.write("-");
					bWriter.write(Integer.toString(hh.getNumPersones()));
					bWriter.write(System.lineSeparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			} else {
				try {
					bWriter.write(arr[0]);
					bWriter.write("-");
					bWriter.write(arr[1]);
					bWriter.write(System.lineSeparator());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			buffReader.close();
			bWriter.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		fitxerHab.delete();
		fileTemp.renameTo(fitxerHab);
	}

	//////////////////////////// CLIENT/////////////////////////////////////////////////////

	public void crearFitxerClients() {
		
		fitxerCli = new File("dades" + File.separator + "clients.txt");
		
		if (fitxerCli.exists()) {
			System.out.println("El fitxerCli ja existeix");
		} else {
			try {
				if (fitxerCli.createNewFile()) {
					System.out.println("fitxerClie creat correctament");
				} else {
					System.out.println("Error al crear el fitxerCli...");
				}
			} catch (IOException e) {
				System.out.println("Error al crear el fitxerCli: " + e);
			}
		}
	}

	public void afegirClient(Client cli) {

		try {
			fWriter = new FileWriter(fitxerCli, true);
			bWriter = new BufferedWriter(fWriter);
		} catch (IOException e) {

			e.printStackTrace();
		}
				try {
					bWriter.write(cli.getDni() + "-" + cli.getNom() + "-" + cli.getCognoms());
					bWriter.write(System.lineSeparator());

				} catch (IOException e) {

					e.printStackTrace();
				}
			
			try {
				bWriter.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
	}

	public void omplirALClients(ArrayList<Client> arrayList ) {
		
		try {
			fReader = new FileReader(fitxerCli);
			buffReader = new BufferedReader(fReader);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		String liniaActual = "";
		while (true) {
			try {
				if (!((liniaActual = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] arr = liniaActual.split("-");

			Client cli = new Client(arr[0]);
			cli.setNom(arr[1]);
			cli.setCognoms(arr[2]);
			arrayList.add(cli);
		}

		try {
			buffReader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	//////////////////////////////RESERVES PENDENTS///////////////////////////////////////////
	
	
	public void crearFitxerReservesPendents() {
		fitxerReservesPendents = new File("dades" + File.separator + "reservesPendents.txt");
		if (fitxerReservesPendents.exists()) {
			System.out.println("El fitxerReservesPendents ja existeix");
		} else {
			try {
				if (fitxerReservesPendents.createNewFile()) {
					System.out.println("fitxerReservesPendents creat correctament");
				} else {
					System.out.println("Error al crear el fitxerReservesPendents...");
				}
			} catch (IOException e) {
				System.out.println("Error al crear el fitxerReservesPendents: " + e);
			}
		}
	}

	public void omplirFitxerReservesPendents(Reserva res) {
		
		try {
			fWriter = new FileWriter(fitxerReservesPendents, true);
			bWriter = new BufferedWriter(fWriter);
		} catch (IOException e) {

			e.printStackTrace();
		}
				try {
					bWriter.write(res.getClient().getDni()+"-"+res.getNumHabitació()+"-"+res.getNumPersones()+"-");
					bWriter.write(res.getLdEntrada().getDayOfMonth()+"/"+res.getLdEntrada().getMonthValue()+"/"+res.getLdEntrada().getYear()+"-");
					bWriter.write(res.getLdSortida().getDayOfMonth()+"/"+res.getLdSortida().getMonthValue()+"/"+res.getLdSortida().getYear());
					bWriter.write(System.lineSeparator());
				} catch (IOException e) {

					e.printStackTrace();
				}
			
			try {
				bWriter.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		
	}

	public void omplirALReservesPendents(ArrayList<Reserva> alReservesPendents, ArrayList<Client> alClients) {
		
		try {
			fReader = new FileReader(fitxerReservesPendents);
			buffReader = new BufferedReader(fReader);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		String liniaActual = "";
		while (true) {
			try {
				if (!((liniaActual = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] arr = liniaActual.split("-");

			Client cli = new Client("sd");
			for(Client cc : alClients) {
				if(cc.getDni().equals(arr[0])) {
					cli.setDni(cc.getDni());
					cli.setNom(cc.getNom());
					cli.setCognoms(cc.getCognoms());
				}
			}			

			String [] entrada = arr[3].split("/");
			LocalDate ldentradaOk = LocalDate.of(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[1]), Integer.parseInt(entrada[0]));
			String [] sortida = arr[4].split("/");
			LocalDate ldsortidaOk = LocalDate.of(Integer.parseInt(sortida[2]), Integer.parseInt(sortida[1]), Integer.parseInt(sortida[0]));
			
			Reserva res = new Reserva();
			res.setClient(cli);
			res.setNumHabitació(arr[1]);
			res.setNumPersones(arr[2]);
			res.setLdEntrada(ldentradaOk);
			res.setLdSortida(ldsortidaOk);
			alReservesPendents.add(res);
		}

		try {
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	///////////////////////RESERVES COMFIRMADES////////////////////////////
	private void crearFitxerReservesComfirmades() {
		
		fitxerReservesComfirmades = new File("dades" + File.separator + "fitxerReservesComfirmades.txt");
		if (fitxerReservesComfirmades.exists()) {
			System.out.println("El fitxerReservesComfirmades ja existeix");
		} else {
			try {
				if (fitxerReservesComfirmades.createNewFile()) {
					System.out.println("fitxerReservesComfirmades creat correctament");
				} else {
					System.out.println("Error al crear el fitxerReservesComfirmades...");
				}
			} catch (IOException e) {
				System.out.println("Error al crear el fitxerReservesComfirmades: " + e);
			}
		}
	}
	
	public void passarReservaConfirmadaAlFitxer(Reserva reserva) {
		
		try {
			fWriter = new FileWriter(fitxerReservesComfirmades, true);
			bWriter = new BufferedWriter(fWriter);
		} catch (IOException e) {

			e.printStackTrace();
		}
				try {
					bWriter.write(reserva.getClient().getDni()+"-"+reserva.getNumHabitació()+"-"+reserva.getNumPersones()+"-");
					bWriter.write(reserva.getLdEntrada().getDayOfMonth()+"/"+reserva.getLdEntrada().getMonthValue()+"/"+reserva.getLdEntrada().getYear()+"-");
					bWriter.write(reserva.getLdSortida().getDayOfMonth()+"/"+reserva.getLdSortida().getMonthValue()+"/"+reserva.getLdSortida().getYear());
					bWriter.write(System.lineSeparator());
				} catch (IOException e) {

					e.printStackTrace();
				}
			
			try {
				bWriter.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
	}
	
	
	public void borrarReservaPendent(Reserva res) {
	
	File fileTemp = new File("dades" + File.separator + "fitxerTemp.txt");

	try {
		fReader = new FileReader(fitxerReservesPendents);
	} catch (FileNotFoundException e1) {
		
		e1.printStackTrace();
	}
	try {
		fWriter = new FileWriter(fileTemp,false);
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}

	buffReader = new BufferedReader(fReader);
	bWriter = new BufferedWriter(fWriter);

	String liniaActual = "";
	while (true) {
		try {
			if (!((liniaActual = buffReader.readLine()) != null))
				break;
		} catch (IOException e) {
			e.printStackTrace();
		}

		String [] arr = liniaActual.split("-");	
		String [] entrada = arr[3].split("/");
		String [] sortida = arr[4].split("/");
		
		LocalDate ldEntrada= LocalDate.of(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[1]), Integer.parseInt(entrada[0]));
		LocalDate ldSortida= LocalDate.of(Integer.parseInt(sortida[2]), Integer.parseInt(sortida[1]), Integer.parseInt(sortida[0]));
		
		if (res.getClient().getDni().equals(arr[0]) && res.getNumHabitació().equals(arr[1]) && res.getNumPersones().equals(arr[2]) && 
				res.getLdEntrada().isEqual((ldEntrada)) && res.getLdSortida().isEqual((ldSortida))) {
			System.out.println("Linia borrada");
			continue;
		} else {
			try {
				bWriter.write(arr[0]+"-"+arr[1]+"-"+arr[2]+"-");
				bWriter.write(arr[3]+"-");
				bWriter.write(arr[4]);
				bWriter.write(System.lineSeparator());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	try {
		buffReader.close();
		bWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	fitxerReservesPendents.delete();
	fileTemp.renameTo(fitxerReservesPendents);
}

	public void omplirALReservesComfirmades(ArrayList<Reserva> alReservesConfirmades, ArrayList<Client> alClients) {
		
		try {
			fReader = new FileReader(fitxerReservesComfirmades);
			buffReader = new BufferedReader(fReader);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		String liniaActual = "";
		while (true) {
			try {
				if (!((liniaActual = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] arr = liniaActual.split("-");

			Client cli = new Client("sd");
			for(Client cc : alClients) {
				if(cc.getDni().equals(arr[0])) {
					cli.setDni(cc.getDni());
					cli.setNom(cc.getNom());
					cli.setCognoms(cc.getCognoms());
				}
			}			

			String [] entrada = arr[3].split("/");
			LocalDate ldentradaOk = LocalDate.of(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[1]), Integer.parseInt(entrada[0]));
			String [] sortida = arr[4].split("/");
			LocalDate ldsortidaOk = LocalDate.of(Integer.parseInt(sortida[2]), Integer.parseInt(sortida[1]), Integer.parseInt(sortida[0]));
			
			Reserva res = new Reserva();
			res.setClient(cli);
			res.setNumHabitació(arr[1]);
			res.setNumPersones(arr[2]);
			res.setLdEntrada(ldentradaOk);
			res.setLdSortida(ldsortidaOk);
			alReservesConfirmades.add(res);
		}

		try {
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
		
		
	}

	public void borrarReservaComfirmada(Reserva res) {
		
		File fileTemp = new File("dades" + File.separator + "fitxerTemp.txt");

		try {
			fReader = new FileReader(fitxerReservesComfirmades);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		try {
			fWriter = new FileWriter(fileTemp,false);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		buffReader = new BufferedReader(fReader);
		bWriter = new BufferedWriter(fWriter);

		String liniaActual = "";
		while (true) {
			try {
				if (!((liniaActual = buffReader.readLine()) != null))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			String [] arr = liniaActual.split("-");	
			String [] entrada = arr[3].split("/");
			String [] sortida = arr[4].split("/");
			
			LocalDate ldEntrada= LocalDate.of(Integer.parseInt(entrada[2]), Integer.parseInt(entrada[1]), Integer.parseInt(entrada[0]));
			LocalDate ldSortida= LocalDate.of(Integer.parseInt(sortida[2]), Integer.parseInt(sortida[1]), Integer.parseInt(sortida[0]));
			
			if (res.getClient().getDni().equals(arr[0]) && res.getNumHabitació().equals(arr[1]) && res.getNumPersones().equals(arr[2]) && 
					res.getLdEntrada().isEqual((ldEntrada)) && res.getLdSortida().isEqual((ldSortida))) {
				System.out.println("XAXAAAXAXAXAXAXAX");
				System.out.println("XAXAAAXAXAXAXAXAX");
				System.out.println("XAXAAAXAXAXAXAXAX");
				System.out.println("XAXAAAXAXAXAXAXAX");
				System.out.println("Linia borrada");
				continue;
			} else {
				try {
					bWriter.write(arr[0]+"-"+arr[1]+"-"+arr[2]+"-");
					bWriter.write(arr[3]+"-");
					bWriter.write(arr[4]);
					bWriter.write(System.lineSeparator());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			buffReader.close();
			bWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fitxerReservesComfirmades.delete();
		fileTemp.renameTo(fitxerReservesComfirmades);
		
	}
	
	


	
	

	
	

}