import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Productor extends Thread {
	
	private int id;
	//revisar static
	private static ArrayList<Buzon> buzones;
	private int times;
	public boolean recibioActivo;
	private boolean transmitioActivo;
	private String mensajeCopia;
	
	public Productor(int id,  ArrayList<Buzon> buzones, int times) {
		this.id = id;
		this.buzones = buzones;
		this.times = times;	
	}
	
	
	public String transformar (String mensaje) {
		
		String mensajeTransformado = " ";
		if (!mensaje.equals("FIN")) {
			mensajeTransformado = mensaje + " ID: " +this.id +
					" RA? "+ this.recibioActivo +
					" TA? "+ this.transmitioActivo; 
					 return mensajeTransformado;
		}
		else {
			return mensaje;
		}
		
	}
	
	
	public void enviarMensajes(String mensaje) {

			if (this.id==1) {
					this.buzones.get(0).insertarMensaje(mensaje);
			}
			if (this.id==2) {
				this.buzones.get(1).insertarMensaje(mensaje);
				
			}
			if (this.id==3) {
				this.buzones.get(2).insertarMensaje(mensaje);
				
			}
			if (this.id==4) {
				this.buzones.get(3).insertarMensaje(mensaje);
				
			}
		
		
//Pendiente de revisar
		if (mensaje.equals("FIN") && this.buzones.get(3).hayMensajes()==false) {
			
			//TOCA MATAR THREADS
			this.interrupt();
		}
		
		
		
	}
	
	public String sacarMensajes() {
		
		if (this.id==1) {
			mensajeCopia = this.buzones.get(3).retirarMensaje();
			
//sirve 
			System.out.println(mensajeCopia); //Imprime los mensajes finales antes de acabar con los threads
		}
		if (this.id==2) {
			mensajeCopia = this.buzones.get(0).retirarMensaje();
		}
		if (this.id==3) {
			mensajeCopia = this.buzones.get(1).retirarMensaje();
		}
		if (this.id==4) {
			mensajeCopia = this.buzones.get(2).retirarMensaje();
		}	
		
		return mensajeCopia;
		
	}
	
	//Metodo para iniciar mensajes
	public String iniciarMensajes(int diferenciador) {
		String msjT = "1";
		
		msjT = msjT + diferenciador;
		return msjT;
		
		
	}
	
	
	
	
	
	//Parte que ejecuta
	
	@Override
	public void run() {
		
		while (this.id==1 && contadoor<cantiMensajes) {
			String msj = this.iniciarMensajes(contadoor);
			String msjTrans = transformar(msj);
			enviarMensajes(msjTrans);
			System.out.println("entro1");
			contadoor++;			
		}
		while (this.id==1 && contadoor==cantiMensajes 
//Condicion que puede dañar todo
				//&& this.buzones.get(3).hayMensajes()==true
				) {
			System.out.println("entro");
			sacarMensajes();
		}
		
		while (this.id!=1) {
			String msg = sacarMensajes();	
			String msgtTr = transformar(msg);
			enviarMensajes(msgtTr);
		}
	}
	
	
	public static int cantiMensajes;
	private int contadoor = 0;
	//public int contadorIM = 0;
	//public String mensajes[] = new String[0];
	
	
	public static void main(String[] args) {
		
		
		Scanner teclado = new Scanner(System.in);
		int contador = 0;

		System.out.println("Cuantos mensajes se van a crear?");
		cantiMensajes = teclado.nextInt();
		System.out.println(cantiMensajes);
		//Ya no lo necesitamos, crea los mensajes el proceso 1
		//String mensajes[] = new String [cantiMensajes];
		
		
		//Lectura de archivo txt
		File archivo;
		FileReader fr;
		BufferedReader br;
		
		//Variables a leer
		// Id buzones
		char pb = ' ';
		char sb = ' ';
		char tb = ' ';
		char cb = ' ';
		// tamano buzones
		int pt = 0;
		int st = 0;
		int tt = 0;
		int ct = 0;
		//Id procesos
		int pp = 0;
		int sp = 0;
		int tp = 0;
		int cp = 0;
		//Tiempos
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		int t4 = 0;
		//Envio
		boolean pe = false;
		boolean se = false;
		boolean te = false;
		boolean ce = false;
		//Transmision
		boolean ptt = false;
		boolean stt = false;
		boolean ttt = false;
		boolean ctt = false;
				
		String lineas[] = new String [8];
		
		try {
		archivo = new File("archivo.txt");
		fr = new FileReader(archivo);
		br = new BufferedReader(fr);
		
		String linea;
		int ii = 0;
		while((linea=br.readLine())!=null) {
			lineas[ii] = linea;
			ii++;
		}
			
			
		br.close();
		fr.close();
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, " Hubo un error leyendo el archivo " + e);
		}
		
		
		
		Buzon b1 = new Buzon(1, 'A');
		Buzon b2 = new Buzon(1, 'B');
		Buzon b3 = new Buzon(1, 'C');
		Buzon b4 = new Buzon(1, 'D');
		
		ArrayList<Buzon> buzones = new 	ArrayList<Buzon>();
		
		buzones.add(b1);
		buzones.add(b2);
		buzones.add(b3);
		buzones.add(b4);	
		
		Productor p1 = new Productor(1, buzones, 0);
		Productor p2 = new Productor(2, buzones, 0);
		Productor p3 = new Productor(3, buzones, 0);
		Productor p4 = new Productor(4, buzones, 0);
			
		System.out.println("antes del start");
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		System.out.println("despues del start");
	}
	
	
	
	
	
	
}
