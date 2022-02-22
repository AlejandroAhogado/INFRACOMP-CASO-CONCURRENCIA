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
	
	public Productor(int id,  ArrayList<Buzon> buzones, int times,  boolean transmitioActivo, boolean recibioActivo ) {
		this.id = id;
		this.buzones = buzones;
		this.times = times;	
		this.recibioActivo = recibioActivo;
		this.transmitioActivo = transmitioActivo;
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
			contadoor++;			
		}
		while (this.id==1 && contadoor==cantiMensajes 
//Condicion que puede dañar todo
				//&& this.buzones.get(3).hayMensajes()==true
				) {
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

		
		
		//Lectura de archivo txt
		File archivo;
		FileReader fr;
		BufferedReader br;
		

				
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
		
		//Arreglos 
		String[] primeraLinea = lineas[0].split(" ");
		String[] segundaLinea = lineas[1].split(" ");
		String[] terceraLinea = lineas[2].split(" ");
		String[] cuartaLinea = lineas[3].split(" ");
		String[] quintaLinea = lineas[4].split(" ");
		String[] sextaLinea = lineas[5].split(" ");
		String[] septimaLinea = lineas[6].split(" ");
		String[] octavaLinea = lineas[7].split(" ");
		
		//Variables a leer
		// Id buzones
		String pb = primeraLinea[0];
		String sb = segundaLinea[0];
		String tb = terceraLinea[0];
		String cb = cuartaLinea[0];
		// tamano buzones
		int pt = Integer.parseInt(primeraLinea[1]);
		int st = Integer.parseInt(segundaLinea[1]);
		int tt = Integer.parseInt(terceraLinea[1]);
		int ct = Integer.parseInt(cuartaLinea[1]);
		//Id procesos
		int pp = Integer.parseInt(quintaLinea[0]);
		int sp = Integer.parseInt(sextaLinea[0]);
		int tp = Integer.parseInt(septimaLinea[0]);
		int cp = Integer.parseInt(octavaLinea[0]);
		//Tiempos
		int t1 = Integer.parseInt(quintaLinea[1]);
		int t2 = Integer.parseInt(sextaLinea[1]);
		int t3 = Integer.parseInt(septimaLinea[1]);
		int t4 = Integer.parseInt(octavaLinea[1]);
		//Envio
		boolean pe = Boolean.parseBoolean(quintaLinea[2]);
		boolean se = Boolean.parseBoolean(sextaLinea[2]);
		boolean te = Boolean.parseBoolean(septimaLinea[2]);
		boolean ce = Boolean.parseBoolean(octavaLinea[2]);
		//Recepcion
		boolean ptt = Boolean.parseBoolean(quintaLinea[3]);
		boolean stt = Boolean.parseBoolean(sextaLinea[3]);
		boolean ttt = Boolean.parseBoolean(septimaLinea[3]);
		boolean ctt = Boolean.parseBoolean(octavaLinea[3]);
		
		
			br.close();
			fr.close();
		
	

		
		
		
		Buzon b1 = new Buzon(pt, pb);
		Buzon b2 = new Buzon(st, sb);
		Buzon b3 = new Buzon(tt, tb);
		Buzon b4 = new Buzon(ct, cb);
		
		ArrayList<Buzon> buzones = new 	ArrayList<Buzon>();
		
		buzones.add(b1);
		buzones.add(b2);
		buzones.add(b3);
		buzones.add(b4);	
		
		Productor p1 = new Productor(pp, buzones, t1, ptt, pe);
		Productor p2 = new Productor(sp, buzones, t2, stt, se);
		Productor p3 = new Productor(tp, buzones, t3, ttt, te);
		Productor p4 = new Productor(cp, buzones, t4, ctt, ce);
		
			
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		
		} catch (Exception e) {
			return;
		}
	}
	
	
	
	
	
	
}
