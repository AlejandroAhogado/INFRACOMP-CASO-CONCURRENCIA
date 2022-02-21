import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Productor extends Thread {
	
	private int id;
	private static ArrayList<Buzon> buzones;
	private int times;
	public boolean recibioActivo;
	private boolean transmitioActivo;
	private String mensajeCopia;
	
	//contador para saber veces que entrega el 1
	private int contadorUno;
	
	public Productor(int id,  ArrayList<Buzon> buzones, int times) {
		this.id = id;
		this.buzones = buzones;
		this.times = times;	
	}
	
	public void enviarMensajes(String mensaje, int cantidadMensajes) {
		
		mensajeCopia = mensaje;
		contadorUno = 0;
		
		if (!mensaje.equals("FIN")) {
			if (this.id==1) {
				if(cantidadMensajes>=contadorUno) {	
						this.buzones.get(0).insertarMensaje(
								mensajeCopia + " Identificador del thread: " +this.id +
								" Lo recibio de forma activa? "+ this.recibioActivo +
								" Lo transmitio de forma activa? "+ this.transmitioActivo 
								);
						contadorUno++;
				}else {System.out.println(mensajeCopia);}
			}
			if (this.id==2) {
				this.buzones.get(1).insertarMensaje(
						mensajeCopia + " Identificador del thread: " +this.id +
						" Lo recibio de forma activa? "+ this.recibioActivo +
						" Lo transmitio de forma activa? "+ this.transmitioActivo 
						);
				
			}
			if (this.id==3) {
				this.buzones.get(2).insertarMensaje(
						mensajeCopia + " Identificador del thread: " +this.id +
						" Lo recibio de forma activa? "+ this.recibioActivo +
						" Lo transmitio de forma activa? "+ this.transmitioActivo 
						);
				
			}
			if (this.id==4) {
				this.buzones.get(3).insertarMensaje(
						mensajeCopia + " Identificador del thread: " +this.id +
						" Lo recibio de forma activa? "+ this.recibioActivo +
						" Lo transmitio de forma activa? "+ this.transmitioActivo 
						);
				
			}
		
		}
		
		
		
		if (mensaje.equals("FIN") && this.buzones.get(3).hayMensajes()==false) {
			
			//TOCA MATAR THREADS
			this.interrupt();
		}
		
		
		
	}
	
	public void sacarMensajes() {
		
		if (this.id==1) {
			mensajeCopia = this.buzones.get(3).retirarMensaje();
			//System.out.println(mensajeCopia); //Imprime los mensajes finales antes de acabar con los threads
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
		
	}
	
	
	
	
	//Parte que ejecuta
	
	@Override
	public void run() {
		
		
//		while (contadorIM<1 && this.id==1) {
//			for (int i = 0; i < array.length; i++) {
//				
//			}
//			enviarMensajes(,cantiMensajes);	
//			
//			contadorIM++;
//		}
		
		enviarMensajes("Hola", cantiMensajes);
		sacarMensajes();
	}
	
	
	public static int cantiMensajes;
	public int contadorIM = 0;
	public String mensajes[] = new String[0];
	
	
	public static void main(String[] args) {
		
		
		Scanner teclado = new Scanner(System.in);
		int contador = 0;

		System.out.println("Cuantos mensajes van a ingresar?");
		cantiMensajes = teclado.nextInt();
		String mensajes[] = new String [cantiMensajes];

		while(contador<cantiMensajes){
			System.out.println("Digite su mensaje: ");
			mensajes[contador] = teclado.nextLine();
			contador++;
		}
		
		Buzon b1 = new Buzon(4, 'A');
		Buzon b2 = new Buzon(6, 'B');
		Buzon b3 = new Buzon(2, 'C');
		Buzon b4 = new Buzon(3, 'D');
		
		ArrayList<Buzon> buzones = new 	ArrayList();
		
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
	}
	
	
	
	
	
	
}
