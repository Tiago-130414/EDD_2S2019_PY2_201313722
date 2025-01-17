/**
 *
 * @author santi
 */
public class NodoArbolAVL {

    String nombre;
    String contenido;
    String timestamp;
    String propietario;
    int fe;
    int altura;
    NodoArbolAVL derecho;
    NodoArbolAVL izquierdo;
    
    public NodoArbolAVL(String nom,String cont,String fh,String prop) {
        this.nombre = nom;
        this.contenido = cont;
        this.timestamp = fh;
        this.propietario =prop;
        this.fe = 0;
        this.altura = 1;
        this.derecho = null;
        this.izquierdo = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public NodoArbolAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbolAVL derecho) {
        this.derecho = derecho;
    }

    public NodoArbolAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbolAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }    

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
    
    
    
}