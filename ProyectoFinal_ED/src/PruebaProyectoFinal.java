
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
}

public class PruebaProyectoFinal {

	public static void main(String[] args) {
		

	}

}
