

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



public class PruebaProyectoFinal {

	public static void main(String[] args) {
		

	}

}
