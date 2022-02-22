import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class Buzon {
	
	
	private List<String> mensajes;
	private int tamano;
	private String id;

	
	public String getId() {
		return this.id;
	}
	
	public Buzon(int tamano, String id) {
		this.tamano = tamano;
		this.mensajes = new LinkedList<String>();
		this.id = id;
	}
	
	public synchronized boolean hayMensajes() {
		return this.mensajes.size() > 0;
	}
	
	public synchronized void insertarMensaje(String mensaje, boolean estado) {
		
		while(this.mensajes.size()>this.tamano) {
			if (estado==false) {
				try {
					wait();
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Se finalizo todo el procedimiento correctamente, la excepcion indica los que estan esperando porque ya no tienen mas mensajes"+e);
				}
			}else {
				Thread.yield();
				}
		}
		
		
		this.mensajes.add(mensaje);		
		notify();
			
	}
	
	public synchronized String retirarMensaje(boolean estado) {
		while(this.mensajes.size()==0) {

			if (estado== false){
				try {
					wait();
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Se finalizo todo el procedimiento correctamente, la excepcion indica los que estan esperando porque ya no tienen mas mensajes"+e);
				}
			}
			else{
				Thread.yield();
			}
		}
		
		String mensaje = this.mensajes.remove(0);
		notify();
		return mensaje;
		
	}
	
	
	
}
