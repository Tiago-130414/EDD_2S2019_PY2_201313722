/**
 *
 * @author santi
 */
public class NodoHashTable {

    String usuario;
    String contrasena;
    String timestamp;
    MatrizCarpetas matriz;

    public NodoHashTable(String usuario, String contrasena, String timestamp) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.timestamp = timestamp;
        this.matriz = new MatrizCarpetas();
    }

    public NodoHashTable(String usuario, String contrasena, String timestamp, MatrizCarpetas matriz) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.timestamp = timestamp;
        this.matriz = matriz;
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

    public MatrizCarpetas getMatrizCarpetas(){
        return matriz;
    }

    public void setMatrizCarpetas(MatrizCarpetas matriz){
        this.matriz = matriz;
    }
}