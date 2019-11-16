/**
 *
 * @author santi
 */
public class NodoMatriz {
    String nombre;
    String fechaCreacion;
    String fila;
    String columna;
    int indiceC;
    int indiceF;
    NodoMatriz siguiente;
    NodoMatriz anterior;
    NodoMatriz arriba;
    NodoMatriz abajo;
    ArbolAVL_Archivos arbol = new ArbolAVL_Archivos();

    public NodoMatriz(String fila,String columna,String nombre, String fechaCreacion) {
        this.indiceC = 0;
        this.indiceF = 0;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fila = fila;
        this.columna = columna;
        this.siguiente = null;
        this.anterior = null;
        this.arriba = null;
        this.abajo = null;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public NodoMatriz getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMatriz siguiente) {
        this.siguiente = siguiente;
    }

    public NodoMatriz getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoMatriz anterior) {
        this.anterior = anterior;
    }

    public NodoMatriz getArriba() {
        return arriba;
    }

    public void setArriba(NodoMatriz arriba) {
        this.arriba = arriba;
    }

    public NodoMatriz getAbajo() {
        return abajo;
    }

    public void setAbajo(NodoMatriz abajo) {
        this.abajo = abajo;
    }
}
