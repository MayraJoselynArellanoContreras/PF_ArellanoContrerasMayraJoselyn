import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// ==================== CLASE PILA ====================
class Pila {
	private String[] elementos;
    private int tope;
    private int capacidad;

    public Pila(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = new String[capacidad];
        this.tope = -1;
    }
    
    public boolean estaVacia() {
        return tope == -1;
    }

    public boolean estaLlena() {
        return tope == capacidad - 1;
    }
    
    public void apilar(String elemento) {
        if (!estaLlena()) {
            tope++;
            elementos[tope] = elemento;
        }
    }

    public String desapilar() {
        if (!estaVacia()) {
            String elemento = elementos[tope];
            tope--;
            return elemento;
        }
        return null;
    }
    
    public String[] obtenerElementos() {
        String[] resultado = new String[tope + 1];
        for (int i = 0; i <= tope; i++) {
            resultado[i] = elementos[i];
        }
        return resultado;
    }
    
    public boolean contiene(String elemento) {
        for (int i = 0; i <= tope; i++) {
            if (elementos[i] != null && elementos[i].equalsIgnoreCase(elemento)) {
                return true;
            }
        }
        return false;
    }

    public int getTamanio() {
        return tope + 1;
    }
}//class Pila

//==================== CLASE OPORTUNIDADES ====================
class OportunidadesUsuario {
 private int oportunidades = 8;

 	public OportunidadesUsuario() {
 	}

 	public OportunidadesUsuario(int oportunidades) {
 		this.oportunidades = oportunidades;
 	}

 	public int getOportunidades() {
 		return oportunidades;
 	}

 	public void setOportunidades(int oportunidades) {
 		this.oportunidades = oportunidades;
 	}
 	
}//class Oportunidades

//==================== INTERFAZ AHORCADO ====================
interface ahorcadoInterface {
	public String lecturaArchivo(File archivo);
    public String[] cargarPalabras();
    public String elegirPalabra(String palabras[]);
}

//==================== CLASE MANIPULACION ARCHIVO ====================
class ManipulacionArchivo {
	private String rutaArchivo = "./Palabras/palabras.txt";

	public String lecturaArchivo(File archivo) {
	    String ln = null;
	    String contenidoCompleto = "";
	    String lns = "\n"; 

	    try {
	        FileReader fr = new FileReader(archivo);
	        BufferedReader br = new BufferedReader(fr);

	        while ((ln = br.readLine()) != null) {
	            contenidoCompleto = contenidoCompleto + ln + lns;
	        }

	        br.close();
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo");
	    }

	    return contenidoCompleto;
	}

	public void guardarPalabras(String contenido) {
        File archivo = new File(rutaArchivo);
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            archivo.delete();
            archivo.createNewFile();

            fw = new FileWriter(archivo, false);
            pw = new PrintWriter(fw);
            pw.print(contenido);

            System.out.println("Las palabras han sido guardadas correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) pw.close();
            try {
                if (fw != null) fw.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo");
            }
        }
    }
	
	
//===== METODOS AUXILIARES PARA EL ALGORITMO QUICKSORT =====
	private void quickSort(String[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(arr, bajo, alto);
            quickSort(arr, bajo, pi - 1);
            quickSort(arr, pi + 1, alto);
        }
    }

    private int particion(String[] arr, int bajo, int alto) {
        String pivote = arr[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
        	if (arr[j].toLowerCase().compareTo(pivote.toLowerCase()) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;

        return i + 1;
    }

//============= ALGORITMO DE ORDENAMIENTO: QUICKSORT =============
    public String[] ordenarPalabras(String[] palabras) {
        if (palabras == null || palabras.length <= 1) {
            return palabras;
        }
        quickSort(palabras, 0, palabras.length - 1);
        return palabras;
    }
    
 // Metodo auxiliar para comparar Strings
    public int compararStrings(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        int i = 0;
        while (i < a.length() && i < b.length()) {
            if (a.charAt(i) != b.charAt(i))
                return a.charAt(i) - b.charAt(i);
            i++;
        }
        return a.length() - b.length();
    }

  //============= ALGORITMO DE BUSQUEDA: BUSQUEDA BINARIA =============
    public int busquedaBinaria(String[] arr, String elemento) {
        int izq = 0;
        int der = arr.length - 1;

        while (izq <= der) {
            int medio = izq + (der - izq) / 2;

            int comparacion = compararStrings(arr[medio], elemento);

            if (comparacion == 0) {
                return medio; 
            }

            if (comparacion < 0) {
                izq = medio + 1; 
            } else {
                der = medio - 1;
            }
        }

        return -1; 
    }
    
}//class Archivo   

 // ==================== CLASE JUEGO AHORCADO ====================
    class JuegoAhorcado extends OportunidadesUsuario implements ahorcadoInterface {
    	
    	Scanner entrada = new Scanner(System.in);

        private String palabrasEspanol[];
        private String palabrasIngles[];
        private String palabras[];
        private int numero;
        private String palabraSecreta;

        private String alfabeto = "abcdefghijklmnñopqrstuvwxyz";

        private int contError = 0;
        private int contLetra = 0;

        // Pila para almacenar letras ingresadas
        private Pila pilaLetras = new Pila(27);

        ManipulacionArchivo manejadorArchivo = new ManipulacionArchivo();
        
        public JuegoAhorcado() {
        }

        public String[] getPalabrasEspanol() {
            return palabrasEspanol;
        }

        public String[] getPalabrasIngles() {
            return palabrasIngles;
        }

        public String[] getPalabras() {
            return palabras;
        }

        public String getPalabraSecreta() {
            return palabraSecreta;
        }

        public void setPalabraSecreta(String palabraSecreta) {
            this.palabraSecreta = palabraSecreta;
        }

        public int getContError() {
            return contError;
        }

        public void setContError(int contError) {
            this.contError = contError;
        }

		@Override
		public String lecturaArchivo(File archivo) {
			return manejadorArchivo.lecturaArchivo(archivo);
		}

		@Override
		public String[] cargarPalabras() {
			String ruta = "./Palabras/palabras.txt";
	        String txt = "";
	        String txtQueVaQuedando = "";

	        try {
	            FileReader fr = new FileReader(ruta);
	            BufferedReader br = new BufferedReader(fr);

	            while ((txt = br.readLine()) != null) {
	                txtQueVaQuedando = txtQueVaQuedando + txt;
	            }

	            br.close();

	            String txtE = txtQueVaQuedando.toUpperCase().substring(0, txtQueVaQuedando.indexOf("/"));
	            String txtI = txtQueVaQuedando.toUpperCase().substring(txtQueVaQuedando.indexOf("/") + 1, txtQueVaQuedando.length());

	            palabrasEspanol = txtE.split(",");
	            palabrasIngles = txtI.split(",");

	            // Ordenar palabras usando el algoritmo de ordenamiento
	            palabrasEspanol = manejadorArchivo.ordenarPalabras(palabrasEspanol);
	            palabrasIngles = manejadorArchivo.ordenarPalabras(palabrasIngles);

	            palabras = new String[palabrasEspanol.length + palabrasIngles.length];

	            int k;
	            for (k = 0; k < palabrasEspanol.length; k++) {
	                palabras[k] = palabrasEspanol[k];
	            }

	            numero = k;

	            for (int e = 0; e < palabrasIngles.length; e++) {
	                palabras[k] = palabrasIngles[e];
	                k++;
	            }

	        } catch (FileNotFoundException e) {
	            System.out.println("No se encontró el archivo...");
	        } catch (IOException e) {
	            System.out.println("Hubo un error en la lectura");
	        }

	        return palabras;
		}

		@Override
		public String elegirPalabra(String[] palabras) {
			Random gnr = new Random();
	        int nr = gnr.nextInt(palabras.length);
	        palabraSecreta = palabras[nr];

	        if (nr <= numero - 1) {
	            System.out.println("La palabra generada fue en Español...");
	        } else {
	            System.out.println("La palabra generada fue en Inglés...");
	        }

	        return palabraSecreta;
		}
		
		// Método: seAdivinoLaPalabra
	    public boolean seAdivinoLaPalabra(String palabraSecreta, Pila letrasIng) {
	        int contadorLetrasAcertadas = 0;
	        String[] letrasIngresadas = letrasIng.obtenerElementos();

	        for (int d = 0; d < palabraSecreta.length(); d++) {
	            String letra = palabraSecreta.substring(d, d + 1);

	            for (int l = 0; l < letrasIngresadas.length; l++) {
	                if (letra.equals(letrasIngresadas[l])) {
	                    contadorLetrasAcertadas++;
	                }
	            }
	        }

	        return contadorLetrasAcertadas == palabraSecreta.length();
    }
	    
	    // Método: obtenerPalabraAdivinada
	    public String obtenerPalabraAdivinada(String palabraSecreta, Pila letrasIng) {
	        String palabraSeparada[] = new String[palabraSecreta.length()];
	        String[] letrasIngresadas = letrasIng.obtenerElementos();

	        for (int j = 0; j < palabraSeparada.length; j++) {
	            palabraSeparada[j] = "_";
	        }

	        for (int i = 0; i < palabraSecreta.length(); i++) {
	            String letra = palabraSecreta.substring(i, i + 1);

	            for (int j = 0; j < letrasIngresadas.length; j++) {
	                if (letrasIngresadas[j].equals(letra)) {
	                    palabraSeparada[i] = letra;
	                }
	            }
	        }

	        String palabraMostrada = "";
	        for (int s = 0; s < palabraSeparada.length; s++) {
	            palabraMostrada = palabraMostrada + palabraSeparada[s] + " ";
	        }

	        return palabraMostrada.trim();
	    }
	    
	 // Método: obtenerLetrasDisponibles
	    public String obtenerLetrasDisponibles(Pila letrasIngresadas) {
	        String[] alfabetoArray = new String[alfabeto.length()];
	        String[] letrasIng = letrasIngresadas.obtenerElementos();

	        for (int i = 0; i < alfabeto.length(); i++) {
	            alfabetoArray[i] = "" + alfabeto.charAt(i);
	        }

	        // Usar algoritmo de búsqueda para verificar si la letra ya fue ingresada
	        for (int pos = 0; pos < alfabetoArray.length; pos++) {
	            String letraActual = alfabetoArray[pos];

	            for (int j = 0; j < letrasIng.length; j++) {
	                if (letrasIng[j] != null && letrasIng[j].equalsIgnoreCase(letraActual)) {
	                    alfabetoArray[pos] = "-";
	                    break;
	                }
	            }
	        }

	        String resultado = "";
	        for (int i = 0; i < alfabetoArray.length; i++) {
	            resultado = resultado + alfabetoArray[i];
	        }

	        return resultado;
	    }
	    
	    public void inicioJuegoAhorcado(String palabrasecreta) {
	    	this.palabraSecreta = palabrasecreta;
	        this.pilaLetras = new Pila(27);
	        this.contError = 0;
	        this.contLetra = 0;
	        setOportunidades(8);

	        System.out.println("\n¡Bienvenido al juego del Ahorcado!");
	        System.out.println("Estoy pensando en una palabra de " + getPalabraSecreta().length() + " letras.");
	        
	        do {
	            System.out.println("\n------------");
	            System.out.println("Te quedan " + getOportunidades() + " oportunidades para adivinar.");
	            System.out.print("Letras disponibles: ");
	            System.out.println(obtenerLetrasDisponibles(pilaLetras));

	            boolean caracterValido = false;
	            String letraIng = "";

	            while (!caracterValido) {
	                System.out.print("Por favor ingresa una letra: ");

	                try {
	                    letraIng = entrada.next();

	                    if (letraIng.toUpperCase().charAt(0) >= 65 && letraIng.toUpperCase().charAt(0) <= 90 && letraIng.length() == 1 || letraIng.toUpperCase().charAt(0) == 'Ñ') {
	                        caracterValido = true;
	                    } else {
	                        System.out.println("Carácter inválido, prueba de nuevo");
	                    }

	                } catch (InputMismatchException e) {
	                    System.out.println("Entrada inválida");
	                    entrada.next();
	                }
	            }
	           
	    }while (getOportunidades() != 0);

	}
   
}//class JuegoAhorcado
public class PruebaProyectoFinal {

	public static void main(String[] args) {
		

	}

}
