package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import controller.Controller;
import model.Client;
import model.Reserva;

import java.awt.Image;

public class Vista extends JFrame{
	
	Controller c;
	JPanel panell1;
	JLabel titolP1;
	JLabel text1P1;
	JLabel text2P1;
	DefaultTableModel model1;
	JTable taula1;
	JScrollPane scroll1;
	DefaultTableModel model2;
	JTable taula2;
	JScrollPane scroll2;
	JDateChooser calendari1;
	JToggleButton toggle;
	
	JPanel panell2;
	JLabel titolP2;
	JLabel textDni;
	JLabel textNom;
	JLabel textCognoms;
	JLabel textNumNits;
	JLabel textNumPersones;
	JLabel textDataEntrada;
	JTextField tfDni;
	JTextField tfNom;
	JTextField tfCognoms;
	JTextField tfNumPersones;
	JTextField tfNumNits;
	JCalendar calendari2;
	JButton botoReserva;
	JButton botoBorrar;
	
	JPanel panell3;
	JLabel titolP3;
	JLabel textNomHotel;
	JLabel nomHotel;
	JLabel registreNou;
	JLabel backNum;
	JLabel backPers;
	JLabel consultaRes;
	JLabel nomClient;
	JTextField tfNomHotel;
	JTextField tfBackNum;
	JTextField tfBackPers;
	JTextField tfNomClient;
	JButton butoGuarda1;
	JButton butoGuarda2;
	
	
	JList<Client> lista1;
	DefaultListModel<Client> listModel1;
	
	JList<Reserva> lista2;
	DefaultListModel<Reserva> listModel2;
	
	JLabel resultatDni;
	JLabel resultatNom;
	JLabel resultatCognoms;
	JLabel resultatNits;
	JLabel resultatPersones;
	JLabel resultatNumPersones;
	JLabel resultatNumHabitació;
	
	ImageIcon imgTic = new ImageIcon("tic.png");
	ImageIcon imgX = new ImageIcon("x.png");
	ImageIcon ticReduit = new ImageIcon(imgTic.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	ImageIcon xReduit = new ImageIcon(imgX.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
	
	
	public Vista() {
        this.setVisible(true);
        this.setSize(1204, 900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Rohit's Hotel");
        //setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);
        c = new Controller();
        c.ompirLesArrayLists();
        iniciar();
    }

    private void iniciar() {
    	posarPanells();
    	modificacionsPanell1();
    	modificacionsPanell2();
    	modificacionsPanell3();
    	listener2();
    	listenerBotoTitle();
    	listenerBotoReserva();
    	listenerHabitació();
    	listenerBotoGuarda2();
    	listenerDobleClick();
    	toggleListener();
    	dateChooserListener();
    	cercadorListener();
    	cercadorReservesListener();
    	perBorrarReservesListener();
    	botoEliminaListener();
		c.buidarIOmplirTaulaReservesPendents(model1);
		c.ferUpdateAlaTaula(calendari1, model2, toggle);
    }
    
	private void posarPanells() {
    	panell1 = new JPanel();
    	panell1.setBounds(0, 0, 400, 900);
    	panell1.setBackground(Color.GRAY);
    	this.getContentPane().add(panell1);
    	panell1.setLayout(null);
    	
    	panell2 = new JPanel();
    	panell2.setBounds(402, 0, 400, 900);
    	panell2.setBackground(Color.GRAY);
    	this.getContentPane().add(panell2);
    	panell2.setLayout(null);
    	
    	panell3 = new JPanel();
    	panell3.setBounds(804, 0, 400, 900);
    	panell3.setBackground(Color.GRAY);
    	this.getContentPane().add(panell3);
    	panell3.setLayout(null);
    }
    
    
    private void modificacionsPanell1() {
    	
    	titolP1 = new JLabel();
    	titolP1.setText("Gestió");
    	titolP1.setBounds(0, 20 ,panell1.getWidth(),100);
    	titolP1.setHorizontalAlignment(SwingConstants.CENTER);
    	titolP1.setFont(new Font("Arial", Font.BOLD, 20));
    	panell1.add(titolP1);
    	
    	text1P1 = new JLabel();
    	text1P1.setText(" Reservas pendents: ");
    	text1P1.setBounds(20,100,160,40);
    	text1P1.setFont(new Font("Arial", Font.BOLD, 12));
    	panell1.add(text1P1);
    	
    	model1 = new DefaultTableModel();
    	model1.addColumn("DNI");
        model1.addColumn("Dia");
        model1.addColumn("Persones");
        model1.addColumn("Habitació");
    	taula1 = new JTable (model1);
    	taula1.setBounds(20,150,300,200);
    	taula1.setBorder(BorderFactory.createLineBorder(Color.black));
    	taula1.getTableHeader().setReorderingAllowed(false);
    	taula1.setEnabled(false);
    	panell1.add(taula1);
    	scroll1 = new JScrollPane(taula1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scroll1.setBounds(20,150,300,200);
    	panell1.add(scroll1);
    	
    	text2P1 = new JLabel();
    	text2P1.setText("Reserves confirmades: ");
    	text2P1.setBounds(20,380,180,40);
    	text2P1.setFont(new Font("Arial", Font.BOLD, 12));
    	panell1.add(text2P1);
    	
    	toggle = new JToggleButton();
    	toggle.setBounds(20, 430, 130, 30);
    	toggle.setText("Etrada");
    	panell1.add(toggle);
    	
    	calendari1 = new JDateChooser();
    	calendari1.setBounds(220,430,130,30);
    	long currentMilliseconds = System.currentTimeMillis();
		Date result = new Date(currentMilliseconds); 
		calendari1.setDate(result);
    	panell1.add(calendari1);
    	
    	model2 = new DefaultTableModel();
    	model2.addColumn("Dni");
        model2.addColumn("Nom");
        model2.addColumn("Cognoms");
        model2.addColumn("Habitació");
    	taula2 = new JTable (model2);
    	taula2.setBounds(20,480,300,200);
    	taula2.setBorder(BorderFactory.createLineBorder(Color.black));
    	taula2.getTableHeader().setReorderingAllowed(false);
    	taula2.setEnabled(false);
    	panell1.add(taula2);
    	scroll2 = new JScrollPane(taula2,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scroll2.setBounds(20,480,350,250);
    	panell1.add(scroll2);
    	
    }
    
    private void modificacionsPanell2() {
    	
    	titolP2 = new JLabel();
    	titolP2.setText("Clients");
    	titolP2.setBounds(0,20,panell2.getWidth(),100);
    	titolP2.setHorizontalAlignment(SwingConstants.CENTER);
    	titolP2.setFont(new Font("Arial", Font.BOLD, 20));
    	panell2.add(titolP2);
    	
    	textDni = new JLabel();
    	textDni.setText("Dni: ");
    	textDni.setBounds(20,100,150,30);
    	textDni.setFont(new Font("Arial", Font.BOLD, 12));
    	panell2.add(textDni);
    	
    	tfDni = new JTextField();
    	tfDni.setBounds(150,100,200,30);
    	tfDni.setName("dni");
    	panell2.add(tfDni);
    	
    	resultatDni = new JLabel();
    	resultatDni.setBounds(360,100,30,30);
    	panell2.add(resultatDni);
    	
    	textNom = new JLabel();
    	textNom.setText("Nom: ");
    	textNom.setBounds(20,150,150,30);
    	textNom.setFont(new Font("Arial", Font.BOLD, 12));
    	panell2.add(textNom);
    	
    	tfNom = new JTextField();
    	tfNom.setName("nom");
    	tfNom.setBounds(150,150,200,30);
    	panell2.add(tfNom);
    	
    	resultatNom = new JLabel();
    	resultatNom.setBounds(360,150,30,30);
    	panell2.add(resultatNom);
    	
    	textCognoms = new JLabel();
    	textCognoms.setText("Cognoms: ");
    	textCognoms.setBounds(20,200,150,30);
    	textCognoms.setFont(new Font("Arial", Font.BOLD, 12));
    	panell2.add(textCognoms);
    	
    	tfCognoms = new JTextField();
    	tfCognoms.setName("cognoms");
    	tfCognoms.setBounds(150,200,200,30);
    	panell2.add(tfCognoms);
    	
    	resultatCognoms = new JLabel();
    	resultatCognoms.setBounds(360,200,30,30);
    	panell2.add(resultatCognoms);
    	
    	textNumPersones = new JLabel();
    	textNumPersones.setText("Num. Persones: ");
    	textNumPersones.setBounds(20,250,150,30);
    	textNumPersones.setHorizontalAlignment(SwingConstants.LEFT);
    	textNumPersones.setFont(new Font("Arial", Font.BOLD, 12));
    	panell2.add(textNumPersones);
    	
    	tfNumPersones = new JTextField();
    	tfNumPersones.setName("persones");
    	tfNumPersones.setBounds(150,250,80,30);
    	panell2.add(tfNumPersones);
    	
    	resultatPersones = new JLabel();
    	resultatPersones.setBounds(260, 250,30,30);
    	panell2.add(resultatPersones);
    	
    	textNumNits = new JLabel();
    	textNumNits.setText(" Num. Nits: ");
    	textNumNits.setBounds(20,300,150,30);
    	textNumNits.setFont(new Font("Arial", Font.BOLD, 12));
    	panell2.add(textNumNits);
    	
    	tfNumNits = new JTextField();
    	tfNumNits.setName("nits");
    	tfNumNits.setBounds(150,300,80,30);
    	panell2.add(tfNumNits);
    	
    	resultatNits = new JLabel();
    	resultatNits.setBounds(260, 300,30,30);
    	panell2.add(resultatNits);
    	
    	textDataEntrada = new JLabel();
    	textDataEntrada.setText(" Data d'entrada: ");
    	textDataEntrada.setBounds(20,400,150,30);
    	textDataEntrada.setFont(new Font("Arial", Font.BOLD, 12));
    	panell2.add(textDataEntrada);
    	
    	
    	calendari2 = new JCalendar();
    	calendari2.setBounds(40,440,300,250);
    	calendari2.setAlignmentX(SwingConstants.CENTER);
    	panell2.add(calendari2);
    	
    	botoReserva = new JButton();
    	botoReserva.setText("Reserva");
    	botoReserva.setEnabled(false);
    	botoReserva.setBounds(140, 715, 100, 30);
    	botoReserva.setFont(new Font("Arial", Font.BOLD, 12));
    	panell2.add(botoReserva);
    	
    	listener2();
    }
    
    
    private void modificacionsPanell3() {
    	
    	titolP3 = new JLabel();
    	titolP3.setText("Back");
    	titolP3.setBounds(0,20,panell3.getWidth(),100);
    	titolP3.setHorizontalAlignment(SwingConstants.CENTER);
    	titolP3.setFont(new Font("Arial", Font.BOLD, 20));
    	panell3.add(titolP3);
    	
    	 nomHotel = new JLabel();
    	 nomHotel.setText(" Nom hotel: ");
    	 nomHotel.setBounds(20,150,140,30);
    	 panell3.add(nomHotel);
    	 
    	 tfNomHotel = new JTextField();
    	 tfNomHotel.setBounds(170,150,200,30);
    	 panell3.add(tfNomHotel);
    	 
    	 butoGuarda1 = new JButton();
    	 butoGuarda1.setText(" Guarda! ");
    	 butoGuarda1.setBounds(120,200,140,30);
    	 panell3.add(butoGuarda1);
    	 
    	 registreNou = new JLabel();
    	 registreNou.setText(" Registre nova habitació ");
    	 registreNou.setBounds(20,250,200,30);
    	 panell3.add(registreNou);
    	 
    	 backNum = new JLabel();
    	 backNum.setText(" Num. ");
    	 backNum.setBounds(20,290,40,30);
    	 panell3.add(backNum);
    	 
    	 tfBackNum = new JTextField();
    	 tfBackNum.setBounds(65,290,70,30);
    	 tfBackNum.setName("numHab");
    	 panell3.add(tfBackNum);
    	 
    	 resultatNumHabitació= new JLabel();
    	 resultatNumHabitació.setBounds(140,290,30,30);
    	 panell3.add(resultatNumHabitació);
    	 
    	 backPers = new JLabel();
    	 backPers.setText(" #Pers. ");
    	 backPers.setBounds(170,290,50,30);
    	 panell3.add(backPers);
    	 
    	 tfBackPers = new JTextField();
    	 tfBackPers.setBounds(230,290,70,30);
    	 tfBackPers.setName("numPers");
    	 panell3.add(tfBackPers);
    	  
    	 resultatNumPersones = new JLabel();
    	 resultatNumPersones.setBounds(305, 290, 30, 30);
    	 panell3.add(resultatNumPersones);
    	 
    	 butoGuarda2 = new JButton();
    	 butoGuarda2.setText(" Guarda! ");
    	 butoGuarda2.setBounds(120,340, 140, 30);
    	 butoGuarda2.setEnabled(false);
    	 panell3.add(butoGuarda2);
    	 
    	 consultaRes = new JLabel();
    	 consultaRes.setText(" Consulta reserva ");
    	 consultaRes.setBounds(20, 400, 140, 30);
    	 panell3.add(consultaRes);
    	 
    	 nomClient = new JLabel();
    	 nomClient.setText(" Nom Client: ");
    	 nomClient.setBounds(20, 440, 100, 30);
    	 panell3.add(nomClient);
    	 
    	 tfNomClient = new JTextField();
    	 tfNomClient.setBounds(130 ,440, 140, 30);
    	 panell3.add(tfNomClient);
    	 
    	 listModel1 = new DefaultListModel();
    	 lista1 = new JList(listModel1);
    	 lista1.setBorder(BorderFactory.createLineBorder(Color.black));
    	 lista1.setBounds(30,500, 150,200);
    	 panell3.add(lista1);
    	 
       	 listModel2 = new DefaultListModel();
    	 lista2 = new JList(listModel2);
    	 lista2.setBorder(BorderFactory.createLineBorder(Color.black));
    	 lista2.setBounds(210,500, 150,200);
    	 panell3.add(lista2);
    	 listenerBotoTitle();
    	 
    	 botoBorrar = new JButton();
    	 botoBorrar.setBounds(150,750,100,30);
    	 botoBorrar.setText("Eliminar");
    	 botoBorrar.setEnabled(false);
    	 panell3.add(botoBorrar);
    	 
    }
      
    private void listener2() {

    	KeyListener key = new KeyListener(){

            @Override
            public void keyPressed(KeyEvent e) {
            	
            }

            @Override
            public void keyReleased(KeyEvent e) {
            	
            	if(e.getComponent().getName().equalsIgnoreCase("dni")) {

            		if(c.comprovarDni(tfDni)) {
            			c.autoCompletarCampsSiExisteix(tfNom, tfDni, tfCognoms);
            			resultatDni.setIcon(ticReduit);
                		panell2.add(resultatDni);
                	}else {
                		resultatDni.setIcon(xReduit);
                		panell2.add(resultatDni);
        				tfNom.setEnabled(true);
        				tfCognoms.setEnabled(true);
        				tfNom.setText("");
        				tfCognoms.setText("");
            			resultatCognoms.setIcon(null);
            			resultatNom.setIcon(null);
        				
                	}
           		
            		if(c.comprovarDni(tfDni) && c.comprovarNom(tfNom) && c.comprovarCognoms(tfCognoms) && c.comprovarNits(tfNumNits) && c.comprovarPersones(tfNumPersones)) {
            			botoReserva.setEnabled(true);
            			panell2.add(botoReserva);
            		}else {
            			botoReserva.setEnabled(false);
            			panell2.add(botoReserva);
            		}
            		if(tfDni.getText().isBlank()) {
            			resultatDni.setIcon(null);
            		}

                }else if(e.getComponent().getName().equalsIgnoreCase("nom")) {
                	
            		if(c.comprovarNom(tfNom)) {
            			resultatNom.setIcon(ticReduit);
                		panell2.add(resultatNom);
                	}else {
                		resultatNom.setIcon(xReduit);
                		panell2.add(resultatNom);
                	}
            		
            		if(c.comprovarDni(tfDni) && c.comprovarNom(tfNom) && c.comprovarCognoms(tfCognoms) && c.comprovarNits(tfNumNits) && c.comprovarPersones(tfNumPersones)) {
            			botoReserva.setEnabled(true);
            			panell2.add(botoReserva);
            		}else {
            			botoReserva.setEnabled(false);
            			panell2.add(botoReserva);
            		}
            		
            		
            		if(tfNom.getText().isBlank()) {
            			resultatNom.setIcon(null);
            		}
                }else if (e.getComponent().getName().equalsIgnoreCase("cognoms")) {
                	
            		if(c.comprovarCognoms(tfCognoms)) {
            			resultatCognoms.setIcon(ticReduit);
                		panell2.add(resultatCognoms);
                	}else {
                		resultatCognoms.setIcon(xReduit);
                		panell2.add(resultatCognoms);
                	}
                	
            		if(c.comprovarDni(tfDni) && c.comprovarNom(tfNom) && c.comprovarCognoms(tfCognoms) && c.comprovarNits(tfNumNits) && c.comprovarPersones(tfNumPersones)) {
            			botoReserva.setEnabled(true);
            			panell2.add(botoReserva);
            		}else {
            			botoReserva.setEnabled(false);
            			panell2.add(botoReserva);
            		}
            		
            		
            		if(tfCognoms.getText().isBlank()) {
            			resultatCognoms.setIcon(null);
            		}
                }else if (e.getComponent().getName().equalsIgnoreCase("nits")) {
                	
            		if(c.comprovarNits(tfNumNits)) {
            			resultatNits.setIcon(ticReduit);
                		panell2.add(resultatNits);
                	}else {
                		resultatNits.setIcon(xReduit);
                		panell2.add(resultatNits);
                	}
                	
            		if(c.comprovarDni(tfDni) && c.comprovarNom(tfNom) && c.comprovarCognoms(tfCognoms) && c.comprovarNits(tfNumNits) && c.comprovarPersones(tfNumPersones)) {
            			botoReserva.setEnabled(true);
            			panell2.add(botoReserva);
            		}else {
            			botoReserva.setEnabled(false);
            			panell2.add(botoReserva);
            		}
            		
            		if(tfNumNits.getText().isBlank()) {
            			resultatNits.setIcon(null);
            		}
                }else  {
                	
            		if(c.comprovarPersones(tfNumPersones)) {
            			resultatPersones.setIcon(ticReduit);
                		panell2.add(resultatPersones);
                	}else {
                		resultatPersones.setIcon(xReduit);
                		panell2.add(resultatPersones);
                	}
            		
            		if(c.comprovarDni(tfDni) && c.comprovarNom(tfNom) && c.comprovarCognoms(tfCognoms) && c.comprovarNits(tfNumNits) && c.comprovarPersones(tfNumPersones)) {
            			botoReserva.setEnabled(true);
            			panell2.add(botoReserva);
            		}else {
            			botoReserva.setEnabled(false);
            			panell2.add(botoReserva);
            		}
            		
            		if(tfNumPersones.getText().isBlank()) {
            			resultatPersones.setIcon(null);
            		}
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

        };
        tfDni.addKeyListener(key);
        tfNom.addKeyListener(key);
        tfCognoms.addKeyListener(key);
        tfNumNits.addKeyListener(key);
        tfNumPersones.addKeyListener(key);
        
	}
    
    private void listenerBotoTitle() {
    	
        ActionListener listenerTitle = new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle(tfNomHotel.getText());
				c.addTitle(tfNomHotel);
			}
            
        };
        butoGuarda1.addActionListener(listenerTitle);
    }
    
    private void listenerBotoReserva() {
        ActionListener listenerReserva = new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle(tfNomHotel.getText());
				if(c.comprovarData(calendari2)) {
					
					if(c.comprovarClient(tfDni)) {
						
						
						if(c.ferReserva(tfDni, tfNom, tfCognoms, tfNumPersones, calendari2, tfNumNits, model1 )) {
							System.out.println(" Fer reserva ");
							JOptionPane.showMessageDialog(null, " S'ha reaservat correctament ");
							
						}else {
							JOptionPane.showMessageDialog(null, " No hi han habitacions disponibles amb les caracteristiques indicades anteriorment. ");
							
						}

					}else {
						if(c.crearClientReserva(tfDni, tfNom, tfCognoms, tfNumPersones, calendari2, tfNumNits, model1)) {
							System.out.println(" Fer reserva ");
							JOptionPane.showMessageDialog(null, " S'ha reaservat correctament ");
						}else {
							JOptionPane.showMessageDialog(null, " No hi han habitacions disponibles amb les caracteristiques indicades anteriorment. ");
							
						

						}
					}
				}else {
					JOptionPane.showMessageDialog(null, " Comprovi que la data d'entrada no sigui menor a la d'avui. ");
				}
				tfDni.setText("");
				tfNom.setText("");
				tfCognoms.setText("");
				tfNumNits.setText(null);
				tfNumPersones.setText(null);
				
				long currentMilliseconds = System.currentTimeMillis();
				Date result = new Date(currentMilliseconds); 
				calendari2.setDate(result);
				
				
	    	
				resultatDni.setIcon(null);
		    	resultatNom.setIcon(null);
		    	resultatCognoms.setIcon(null);
		    	resultatNits.setIcon(null);
		    	resultatPersones.setIcon(null);
		    	
		    	botoReserva.setEnabled(false);
			}
        };
        botoReserva.addActionListener(listenerReserva);
    }
    
    public void listenerHabitació() {
    	
    	KeyListener key2 = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getComponent().getName().equalsIgnoreCase("numPers")) {
					if(c.comprovarPersones(tfBackPers)) {
						resultatNumPersones.setIcon(ticReduit);
						panell3.add(resultatNumPersones);
					}else {
						resultatNumPersones.setIcon(xReduit);
						panell3.add(resultatNumPersones);
					}
					if(c.comprovarPersones(tfBackPers) && c.comprovarHabitació(tfBackNum)){
						butoGuarda2.setEnabled(true);
						panell3.add(butoGuarda2);
					}else {
						butoGuarda2.setEnabled(false);
						panell3.add(butoGuarda2);
					}
					if(tfBackPers.getText().isBlank()) {
						resultatNumPersones.setIcon(null);
					}
					

				}else{
					if(c.comprovarHabitació(tfBackNum)) {
						resultatNumHabitació.setIcon(ticReduit);
						panell3.add(resultatNumHabitació);
					}else {
						resultatNumHabitació.setIcon(xReduit);
						panell3.add(resultatNumHabitació);
					}
					if(tfBackNum.getText().isBlank()) {
						resultatNumHabitació.setIcon(null);
					}

				}
				if(c.comprovarPersones(tfBackPers) && c.comprovarHabitació(tfBackNum)){
					butoGuarda2.setEnabled(true);
					panell3.add(butoGuarda2);
				}else {
					butoGuarda2.setEnabled(false);
					panell3.add(butoGuarda2);
				}
			}
    	};
    	tfBackNum.addKeyListener(key2);
    	tfBackPers.addKeyListener(key2);
    }
    
    
    public void listenerBotoGuarda2() {
    	
    	ActionListener bb = new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {

				int capacitatActual =c.agafarCapacitat(tfBackNum.getText());

				if(c.afegirHabitació(tfBackNum.getText(), tfBackPers.getText())==0) {

					JOptionPane.showMessageDialog(null, "L'habitació s'ha afegit correctament. ");

				}else {
						if(c.comrpovarSilaHabEstaReservada(tfBackNum.getText(), tfBackPers.getText())) {
							JOptionPane.showConfirmDialog(null, "L'habitació en aquest moment està reservada llavors no es poden fer canvis");
						}else {
						int opció = JOptionPane.showConfirmDialog(null, "L'habitació amb aquest numero ja existeix. Segur que vols canviar la capacitat? (La capacitat actual és de : "+capacitatActual);
	
						switch(opció) {
						case 0:
							c.actualitzarHabitació(tfBackNum.getText(), tfBackPers.getText());
							break;
						case 1:
							break;
						case 2:
							break;
						}
					}
				}
			    	tfBackPers.setText("");
			    	tfBackNum.setText("");
			    	resultatNumHabitació.setIcon(null);
			    	resultatNumPersones.setIcon(null);
			    	butoGuarda2.setEnabled(false);
			    	
			}
    	};
    	butoGuarda2.addActionListener(bb);
    }
    
    
    	public void listenerDobleClick() {
    		
    		taula1.addMouseListener(new MouseAdapter(){
    		    @Override
    		    public void mouseClicked(MouseEvent e){
    		        if(e.getClickCount()==2){
    		        	System.out.println("doble click");
    		        	 int row = taula1.rowAtPoint(e.getPoint());
    		             System.out.println("You clicked at row " + row);
    		            int opció= JOptionPane.showOptionDialog(null, "Finestra de confirmació", "Tria que vols fer amb aquesta reserva.", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Comfirmar-la", "Descartar-la", "Cancelar"},"default");
    		            
    		            switch(opció) {
    		            	case 0:
    		            		JOptionPane.showMessageDialog(null, "La reserva pendent d'ha confirmat correctament");
    		            		c.comfirmarLaReserva(row);
    		            		c.buidarIOmplirTaulaReservesPendents(model1);
    		            		

    		            		break;
    		            	case 1:
    		            		c.borrarReservaPendent(row);
    		            		c.buidarIOmplirTaulaReservesPendents(model1);
    		            		break;
    		            	case 2:
    		            		break;
    		            }
    		        }
    				model2.setRowCount(0);
    				if(toggle.isSelected()) {
    					toggle.setText("Sortida");
    					System.out.println("Esta selecionat(Sortida)");
    					c.toggleSelecionat(calendari1,  model2);
    				}else {
    					toggle.setText("Entrada");
    					System.out.println("No esta selecionat(Entrada)");
    					c.toggleNoSelecionat(calendari1, model2);
    				}
    		    }  
    	});   		
    }
    	
    	public void toggleListener() {
    		
    		ActionListener key = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					model2.setRowCount(0);
					if(toggle.isSelected()) {
						toggle.setText("Sortida");
						System.out.println("Esta selecionat(Sortida)");
						c.toggleSelecionat(calendari1,  model2);
					}else {
						toggle.setText("Entrada");
						System.out.println("No esta selecionat(Entrada)");
						c.toggleNoSelecionat(calendari1, model2);
					}
				}
    		};
    		toggle.addActionListener(key);
    	}
    	
    	public void dateChooserListener() {
    		
    		PropertyChangeListener ll = new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {

					c.ferUpdateAlaTaula(calendari1, model2, toggle);
				}
    		};
    		calendari1.addPropertyChangeListener(ll);
    	}
    	
    
    	public void cercadorListener() {
    		KeyListener key3 = new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {			
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					
					if(tfNomClient.getText().equals("")) {
						listModel2.clear();
						lista1.clearSelection();
						listModel1.clear();
					}else {
						listModel2.clear();
						listModel1.clear();
						
						c.cercar(tfNomClient.getText(), listModel1);
					}
				}
    		};
    		tfNomClient.addKeyListener(key3);
    		
    	}
    	
    	
    	public void cercadorReservesListener() {
    		
    		ListSelectionListener ll = new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {

					if(e.getValueIsAdjusting()) {
						c.cercarR(lista1.getSelectedValue(),listModel2);	
					}
				}
    		};
    		lista1.addListSelectionListener(ll);
    	}
    	
    	
    	
    	public void perBorrarReservesListener() {
    		ListSelectionListener triarllistaReserves = new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					
					if(e.getValueIsAdjusting()) {
						if(lista2.isSelectionEmpty()) {
							botoBorrar.setEnabled(false);
						}else {
							botoBorrar.setEnabled(true);
						}
					}
				}
    		};
    		lista2.addListSelectionListener(triarllistaReserves);	
    	}
    	
    	public void botoEliminaListener() {
    		
    		ActionListener eliminar = new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					int opció = JOptionPane.showConfirmDialog(null, "Segur que vols eliminar la seguent reserva? ");
					switch(opció) {
					
					case 0:
						JOptionPane.showMessageDialog(null, "S'ha eliminat la reserva correctament ");
						c.borrarReserva(lista2.getSelectedValue());
						listModel2.remove(lista2.getSelectedIndex());
						c.ferUpdateAlaTaula(calendari1, model2, toggle);
						c.buidarIOmplirTaulaReservesPendents(model1);
						
						break;
					case 1:
						
						break;
					case 2:
					
						break;
					}
				}
    		};
    		botoBorrar.addActionListener(eliminar);    		
    	}
}