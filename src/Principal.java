import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		int cantiMensajes;
		Scanner teclado = new Scanner(System.in);
		int contador = 0;

		System.out.println("Cuantos mensajes van a ingresar?");
		cantiMensajes = teclado.nextInt();
		String mensajes[] = new String [cantiMensajes];

		while(contador<=cantiMensajes){
			System.out.println("Digite su mensaje: ");
			mensajes[contador] = teclado.nextLine();
			contador++;
		}
		
		//Run
	}

}
