/**
 *
 * @author santi
 */
public class Bitacora {

    NodoPila top;

    public Bitacora() {
        top = null;
    }

    public boolean estaVacia() {
        return top == null;
    }

    public void insertarBitacora(String fech, String Hor, String Usu) {
        NodoPila nuevo = new NodoPila(fech, Hor, Usu);
        if (estaVacia()) {
            top = nuevo;
            System.out.println("insertado al inicio");
        } else {
            nuevo.siguiente = top;
            top = nuevo;
            System.out.println("insertado en pila");
        }
    }

    public void mostrarBitacora() {
        NodoPila aux = top;
        if (estaVacia()) {
            System.out.println("Bitacora Vacia");
        } else {    
            while (aux != null) {
                System.out.println(aux.UsuarioHCambio);
                aux = aux.siguiente;
            }
        }
    }

    public void eliminarBitacora() {    
        if(estaVacia()){
            System.out.println("Bitacora vacia no hay elementos para limpiar");
        }else{
            top = top.siguiente;
        }
    }
}
