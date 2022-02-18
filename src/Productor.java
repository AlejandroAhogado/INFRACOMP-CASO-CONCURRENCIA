import java.util.ArrayList;

public class Productor extends Thread {
	
	private int id;
	private static ArrayList<Buzon> buzones;
	private int times;
	private boolean recibioActivo;
	private boolean transmitioActivo;
	
	public Productor(int id,  ArrayList<Buzon> buzones, int times) {
		this.id = id;
		this.buzones = buzones;
		this.times = times;	
	}
	
	private void enviarMensajes(int i, char buzonEnviar, String mensaje) {
		if (this.id==1) {
			this.buzones.get(0).insertarMensaje(
					mensaje + " Identificador del thread: " +this.id +
					" Lo recibio de forma activa? "+ this.recibioActivo +
					" Lo transmitio de forma activa? "+ this.transmitioActivo 
					);
			
		}
		
	
		
	}
	
	
	
	
}
