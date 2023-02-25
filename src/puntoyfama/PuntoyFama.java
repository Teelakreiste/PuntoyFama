package puntoyfama;

import java.util.Scanner;

/**
 * Esta clase inicia la partida del juego.
 * @author osmel dzc (@Teelakreiste)
 * @version 25/09/2023/A
 * @see <a href = "https://github.com/Teelakreiste"> Teelakreiste - GitHub </a>
 */

public class PuntoyFama {

    /**
     * Función principal del programa.
     * @param args the command line arguments
     * @see Juego
     * @see #jugar() 
     */
    public static void main(String[] args) {
        jugar();
    }

    /**
     * Método que crea un objeto de tipo juego para empezar a jugar el juego del punto y fama.
     * @see Juego
     * @see Juego#Juego()
     * @see Juego#comoJugar() 
     * @see Juego#partida()
     */
    private static void jugar() {
        boolean bandera;
        Scanner in = new Scanner(System.in);
        do {
            try {
                Juego objuego = new Juego();
                System.out.print("Instrucciones? 1. - Sí / Cualquier otro número. - No.\n>> ");
                if (in.nextInt() == 1) objuego.comoJugar();
                objuego.partida();
                System.out.print("Jugar de nuevo?\n1. - Sí.\nCualquier otro número. - No.\n>> ");
                bandera = (in.nextInt() == 1);
//                System.out.println(objuego);
            } catch (Exception e) {
                bandera = true;
                in.nextLine(); // Limpia el buffer de entrada
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        } while (bandera);
    }

}
