/**
 *
 * @author santi
 */
public class NodoHashTable {
    String usuario;
    String contrasena;
    String timestamp;

    public NodoHashTable(String usuario, String contrasena, String timestamp) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.timestamp = timestamp;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
}