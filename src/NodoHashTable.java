/**
 *
 * @author santi
 */
public class NodoHashTable {
    String usuario;
    String contrasena;

    public NodoHashTable(String us, String cont) {
        this.usuario = us;
        this.contrasena = cont;
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
    
}
