import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
}

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
 	
}

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
    

	
}
public class PruebaProyectoFinal {

	public static void main(String[] args) {
		

	}

}
