/*
PUNTO Y FAMA (PICAS y FIJAS)
El Punto y Fama es un juego que consiste básicamente en intentar adivinar un número secreto en un número 
determinado de intentos. Cuando era niño jugábamos con papel y lápiz entre 2 personas y ganaba quien adivinara 
primero el número secreto del adversario. Es un juego de razonamiento lógico que consiste en hallar el número secreto 
de (4) dígitos diferentes en la menor cantidad de intentos.

Reglas:
1. El número secreto tiene 4 dígitos que no se repiten dentro del número, ej.: 3428, 8734, 0295, etc.
Estos números, por ejemplo, no serían válidos: 3345, 0119, 1111, etc.

2. Cada participante tiene derecho a "cantar o decir" un número por intento y esperará el resultado de su 
número cantado o dicho dado por su adversario.

3. El resultado de los números cantados se analiza de la siguiente forma: Si un dígito del número cantado 
está en la misma posición del número secreto entonces eso es una Fama (F).
Si un dígito del númerocantado está en el número secreto, pero no en la misma posición, eso es un Punto (.).
 */

package puntoyfama;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase define todos las reglas y validaciones del juego Punto y Fama
 * @author osmel dzc (@Teelakreiste)
 * @version 25/09/2023/A
 * @see <a href = "https://github.com/Teelakreiste"> Teelakreiste - GitHub </a>
 */

public class Juego {
    
    // Atributos del juego
    private final int longitud; // Tamaño máximo de los vectos nUsuario y nSecreto.
    private int[] nSecreto; // Almacena el número secreto generado aleatoreamente.
    private int[] nUsuario; // Almacena el número ingresado por el usuario.
    private int intentos; // Establece la cantidad de intentor por ronda.
    private boolean ganador; // Establece si hay o no ganador.
    private final Scanner in = new Scanner(System.in);

    /**
     * Constructor del juego, donde se incializan todos los atributos.
     * @see #longitud
     * @see #nSecreto
     * @see #nUsuario
     * @see #ganador
     */
    public Juego() {
        this.longitud = 4;
        this.nSecreto = new int[this.longitud];
        this.nUsuario = new int[this.longitud];
        this.ganador = false;
    }
    
    /**
     * Método que devuelve un array de 4 posiciones.
     * @return El número secreto.
     * @see #nSecreto
     */
    public int[] getnSecreto() {
        return nSecreto;
    }
    
    /**
     * Método que asigna un array de longitud posiciones al número secreto.
     * @see #longitud
     * @param nSecreto Array[longitud] (Para asignar el número secreto).
     * @see #nSecreto
     */
    public void setnSecreto(int[] nSecreto) {
        this.nSecreto = nSecreto;
    }

    /**
     * Método que devuelve un array de 4 posiciones.
     * @return El número ingresado por el usuario.
     * @see #nUsuario
     */
    public int[] getnUsuario() {
        return nUsuario;
    }

    /**
     * Método que asigna un array de longitud posiciones al número del usuario.
     * @see #longitud
     * @param nUsuario Array[4] (Para asignar el número ingresado por el usuario).
     * @see #nUsuario
     */
    public void setnUsuario(int[] nUsuario) {
        this.nUsuario = nUsuario;
    }

    /**
     * Método que devuelve un número (Referencia al número de jugadas).
     * @return El número de intentos posibles para acertar el número secreto.
     * @see #intentos
     */
    public int getIntentos() {
        return intentos;
    }

    /**
     * Método para asignar el número de intentos en la ronda actual.
     * @param intentos Número entero establece el número de intentos posibles en la partida.
     * @see #intentos
     */
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    /**
     * Método para escoger el nivel de la ronda.
     * @see #establecerIntentos()
     */
    public void setNivel() {
        establecerIntentos();
    }
    
    /**
     * Método para establecer el número de intentos dependiendo del nivel elegido.
     * Dónde:
     * 1. Nivel Principiante - 6 intentos en la partida.
     * 2. Nivel Intermedio - 4 intentos en la partida.
     * 3. Nivel Experto - 2 intentos en la partida.
     * @see #intentos
     */
    private void establecerIntentos() {
        System.out.print("Seleccione un nivel:\n 1. Principiante.\n 2. Intermedio.\n 3. Expero.\n>> ");
        int dificultad = in.nextInt();
        switch (dificultad) {
            case 1:
                System.out.println("Nivel: Principiante.");
                this.intentos = 6;
                break;
            case 2:
                System.out.println("Nivel: Intermedio.");
                this.intentos = 4;
                break;
            case 3:
                System.out.println("Nivel: Experto.");
                this.intentos = 2;
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    /**
     * Método para validar que no haya valores repetidos en el vector del nSecreto.
     * @param x Hace referencia al array.
     * @param y Número con el cual se va a comparar cada posición del array.
     * @param k Hace referencia al índice n del vector (Cantidad de interaciones que se compararán).
     * 
     * @return Verdadero (Sí el número se repite.) o Falso (Caso contrario.)
     */
    private boolean validarRepeticion(int[] x, int y, int k) {
        boolean bandera = false;
        for (int i = 0; i < k; i++) {
            bandera = (x[i] == y);
            if (bandera) {
                break;
            }
        }
        return bandera;
    }

    /**
     * Método para validar el número ingresado por el usuario.
     * Donde se comprobara que el número sea unidad por campo del array.
     * Además, de que el array no cuente con valores repetidos por campo.
     * @param x Hace referencia al array.
     * @return Vedadero (Sí el número no es unidad por campo o si hay repetidos.) o Falso (Caso contrario).
     */
    private boolean validarIngreso(int[] x) {
        int contador = 0;
        for (int i = 0; i < x.length; i++) {
            if ((x[i] % 10) == x[i]) {
                for (int j = 0; j < x.length; j++) {
                    if (x[i] == x[j]) {
                        contador++;
                    }
                }
                if (contador > 1) {
                    System.out.println("No pueden haber números repetidos.\n");
                    break;
                } else {
                    contador = 0;
                }
            } else {
                contador = 2;
                System.out.println("Solo se aceptan numeros del 0 - 9 por cada campo.\n");
                break;
            }
        }
        return (contador > 1);
    }

    /**
     * Método que genera un número aleatorio y verifica que no exista en el array para asignarlo al array nSecreto[]
     * @see #validarRepeticion(int[], int, int)
     * @see #nSecreto
     */
    private void generarNSecreto() {
        Random rnd = new Random();
        for (int i = 0; i < this.longitud; i++) {
            int x = rnd.nextInt(10 - 0);
            if (i == 0) {
                this.nSecreto[i] = x;
            } else {
                if (!validarRepeticion(this.nSecreto, x, i)) {
                    this.nSecreto[i] = x;
                } else {
                    i--;
                }
            }
        }
    }

    /**
     * Método que le pide longitud de números al usuario.
     * @see #longitud
     */
    private void pedirNusuario() {
        for (int i = 0; i < this.longitud; i++) {
            this.nUsuario[i] = in.nextInt();
        }
    }

    /**
     * Método que imprime un array entero.
     * @param x Hace referencia al array.
     */
    private void mostrar(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Método que imprime un array de caracteres, el cual hace referencia a los Puntos y Famas por cada ronda.
     * @param x Hace referencia al Array de carácter.
     */
    private void mostrarPuntosoFamas(char[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println("");
    }

    /**
     * Método que imprime el número ingresado por el usuario, los Puntos - Famas y la cantidad de Puntos o Famas por ronda o iteración.
     * @param puntos Número con la cantidad de puntos logrados en la ronda.
     * @param famas Número con la cantidad de famas logrados en la ronda.
     * @param resultado Array de carácter con la posición de los puntos o famas logrados.
     * @see #nUsuario
     * @see #mostrar(int[]) 
     * @see #mostrarPuntosoFamas(char[]) 
     */
    private void mostrarResultado(int puntos, int famas, char[] resultado) {
        mostrar(this.nUsuario);
        mostrarPuntosoFamas(resultado);
        System.out.println("Puntos: " + puntos);
        System.out.println("Famas: " + famas);
    }

    /**
     * Método que comprueba si el usuario hizo punto o fama y asigna P o F a la posición del número que hizo punto o fama.
     * @see #longitud
     * @see #nUsuario
     * @see #nSecreto
     * @see #ganador
     * @see #mostrarResultado(int, int, char[])
     * @see #ganador() 
     */
    private void comprobacion() {
        int famas = 0, puntos = 0;
        char[] resultado = {'-', '-', '-', '-'};
        for (int i = 0; i < this.longitud; i++) {
            if (nSecreto[i] == nUsuario[i]) {
                famas++;
                resultado[i] = 'F';
            } else {
                for (int j = 0; j < this.longitud; j++) {
                    if (nSecreto[j] == nUsuario[i]) {
                        puntos++;
                        resultado[i] = 'P';
                        break;
                    }
                }
            }
        }
        mostrarResultado(puntos, famas, resultado);
        if (famas == 4) {
            ganador = true;
            ganador();
        }
    }

    /**
     * Método que comprueba si ganó o no el usuario y muestra el número secreto.
     * @see #ganador
     * @see #nSecreto
     * @see #mostrar(int[]) 
     */
    private void ganador() {
        System.out.println((ganador) ? "\n\n******** Felicidades has ganado. ********"
                : "\n\nNo los logrado. Vuelve a intentarlo.");
        System.out.print("Número secreto: ");
        mostrar(this.nSecreto);
        System.out.println("");
    }

    /**
     * Método que explica las instrucciones del juego PUNTO y FAMA.
     */
    public void comoJugar() {
        System.out.println("\n\n\n----------------------------------INSTRUCCIONES----------------------------------");
        System.out.println("Para jugar ingrese 4 digitos separados por coma. \nEjemplo: 1 2 3 4");
        System.out.println("Solo se pueden ingresar un número entre 0 y 9 por campo. Además no se aceptan números repetidos.");
        System.out.println("\nREGLAS:");
        System.out.println("1. El número secreto tiene 4 dígitos que no se repiten dentro del número, ej.: 3428, 8734, 0295, etc.\n"
                + "Estos números, por ejemplo, no serían válidos: 3345, 0119, 1111, etc.\n"
                + "\n"
                + "2. Cada participante tiene derecho a \"cantar o decir\" un número por intento y esperará el resultado de su \n"
                + "número cantado o dicho dado por su adversario.\n"
                + "\n"
                + "3. El resultado de los números cantados se analiza de la siguiente forma: Si un dígito del número cantado \n"
                + "está en la misma posición del número secreto entonces eso es una Fama (F).\n"
                + "Si un dígito del número cantado está en el número secreto, pero no en la misma posición, eso es un Punto (P).");
        System.out.println("----------------------------------FIN INSTRUCCIONES----------------------------------\n\n\n");
    }

    /**
     * Método que empieza la partida del juego. 
     * @see #intentos
     * @see #ganador
     * @see #nUsuario
     * @see #establecerIntentos() 
     * @see #generarNSecreto()
     * @see #pedirNusuario()
     * @see #comprobacion() 
     * @see #ganador()
     */
    public void partida() {
        establecerIntentos();
        generarNSecreto();
//        mostrar(this.nSecreto);
        for (int i = this.intentos; i > 0 && !ganador; i--) {
            System.out.println("Intentos: " + i);
            do {
                System.out.print(">> ");
                pedirNusuario();
            } while (validarIngreso(this.nUsuario));
            System.out.println("Comprobaciones: ");
            comprobacion();
            System.out.println("-----------------------\n");
        }
        if(!ganador) ganador();
    }
    
    /**
     * Método sobreescrito que retorna un String
     * @return Los datos del objeto
     */
    @Override
    public String toString() {
        return "Juego{" + "longitud=" + longitud + ", nSecreto=" + Arrays.toString(nSecreto) + ", nUsuario=" + Arrays.toString(nUsuario) + ", intentos=" + intentos + ", ganador=" + ganador + '}';
    }
}
