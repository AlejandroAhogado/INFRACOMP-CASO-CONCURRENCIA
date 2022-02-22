import java.util.LinkedList;
import java.util.List;

public class Buzon {
	
	
	private List<String> mensajes;
	private int tamano;
	private char id;
	//private int contador;
	//private static Productor thr;
	
	public int getId() {
		return this.id;
	}
	
	public Buzon(int tamano, char id) {
		this.tamano = tamano;
		this.mensajes = new LinkedList<String>();
		this.id = id;
		//this.contador = 0;
		//this.thr = thr;
	}
	
	public synchronized boolean hayMensajes() {
		return this.mensajes.size() > 0;
	}
	
	public synchronized void insertarMensaje(String mensaje) {
		
//IMPORTANTE!!		
//revisar el while con el tamano
		
		while(this.mensajes.size()>this.tamano) {
			try {
				wait();
				//falta espera activa4
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		this.mensajes.add(mensaje);
		//contador++;		
		notify();
			
	}
	
	public synchronized String retirarMensaje() {
		while(this.mensajes.size()==0) {

			//if (thr.recibioActivo == false){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			//}
//			else{
//				thr.yield();
//			}
		}
		
		String mensaje = this.mensajes.remove(0);
		//contador--;
		notify();
		return mensaje;
		
	}
	
	
	
}
