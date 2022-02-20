import java.util.ArrayList;
import java.util.Iterator;

public class Productor extends Thread {
	
	private int id;
	private static ArrayList<Buzon> buzones;
	private int times;
	private boolean recibioActivo;
	private boolean transmitioActivo;
	private String mensajeCopia;
	
	public Productor(int id,  ArrayList<Buzon> buzones, int times) {
		this.id = id;
		this.buzones = buzones;
		this.times = times;	
	}
	
	private void enviarMensajes(int i, char buzonEnviar, String mensaje) {
		
		mensajeCopia = mensaje;
		
		if (!mensaje.equals("FIN")) {
			if (this.id==1) {
				this.buzones.get(0).insertarMensaje(
						mensajeCopia + " Identificador del thread: " +this.id +
						" Lo recibio de forma activa? "+ this.recibioActivo +
						" Lo transmitio de forma activa? "+ this.transmitioActivo 
						);			
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
	
	private void sacarMensajes(int i, char buzonSacar) {
		
		if (this.id==1) {
			mensajeCopia = this.buzones.get(3).retirarMensaje();
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
	
	
	
	
}
