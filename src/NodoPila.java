/**
 *
 * @author santi
 */
public class NodoPila {
    String Fecha;
    String Hora;
    String UsuarioHCambio;
    NodoPila siguiente;
    public NodoPila(String f, String h, String uhc) {
       this.Fecha = f;
       this.Hora = h;
       this.UsuarioHCambio = uhc;
       this.siguiente = null;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getUsuarioHCambio() {
        return UsuarioHCambio;
    }

    public void setUsuarioHCambio(String UsuarioHCambio) {
        this.UsuarioHCambio = UsuarioHCambio;
    }

    public NodoPila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente;
    }
    
}

