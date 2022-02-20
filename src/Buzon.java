import java.util.LinkedList;
import java.util.List;

public class Buzon {
	
	
	private List<String> mensajes;
	private int tamano;
	private char id;
	private int contador;
	
	public Buzon(int tamano, char id) {
		this.tamano = tamano;
		this.mensajes = new LinkedList<String>();
		this.id = id;
		this.contador = 0;
	}
	
	public synchronized boolean hayMensajes() {
		return this.mensajes.size() > 0;
	}
	
	public synchronized void insertarMensaje(String mensaje) {
		while(this.mensajes.size()==this.tamano) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		this.mensajes.add(mensaje);
		contador++;
		
		notify();
			
	}
	
	public synchronized String retirarMensaje() {
		while(this.mensajes.size()==0) {
			try {
				wait();
				//Pendiente revisar espera activa
				//Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String mensaje = this.mensajes.remove(0);
		contador--;
		notify();
		
		return mensaje;
		
	}
	
	
	
}
