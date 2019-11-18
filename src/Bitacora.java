
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;

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

    public void insertarBitacora(String operacion,String Usu) {
        NodoPila nuevo = new NodoPila(fecha(), hora(),operacion, Usu);
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
        if (estaVacia()) {
            System.out.println("Bitacora vacia no hay elementos para limpiar");
        } else {
            top = top.siguiente;
        }
    }

    public String listarBitacora() {
        NodoPila aux = top;
        String cad = "|";
        if (estaVacia()) {
            System.out.println("Bitacora Vacia");
        } else {
            while (aux != null) {
                cad += "Fecha: " +aux.getFecha() + "\\nHora: " + aux.getHora() + "\\nOperacion: "+aux.getOperacion() +"\\nUsuario: " + aux.getUsuarioHCambio();
                if(aux.siguiente!=null){
                    cad+="|\n";
                }
                aux = aux.siguiente;
            }
            cad+="}\"\n";
        }
        return cad;
    }
    
    
    public String fecha() {
        String dt;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dt = dateFormat.format(date);
        return dt;
    }

    public String hora() {
        String hr;
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        hr = hourFormat.format(date);
        return hr;
    }

    public void graficarBitacora() {
        File f;
        f = new File("C:\\Graficas_Proyecto2\\bit.dot");
        //f = new File("C:\\Graficas_Proyecto2\\bit.dot");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            w.write("digraph bitacora\n{");
            w.write("\trankdir=TB;\n");
            w.write("\tlabelloc=t;\n");
            w.write("\tsubgraph cluster_0{\n");
            w.write("\tstyle=filled;\n");
            w.write("\tcolor=lightgrey;\n");
            w.write("\tnode [shape = record, style=\"rounded,filled\", fillcolor=\"purple:red\",width=0.7,height=0.5];\n");
            w.write("\tnode_Bitacora[shape=record\n");
            w.write("\tlabel=\"{\n");
            //obtener valores de pila
            w.write(listarBitacora());
            w.write("\t];\n");
            w.write("\tlabel=\"Bitacora\";\n");
            w.write("\t}\n");
            w.write("}");
            wr.close();
            bw.close();
        } catch (IOException e) {
        }
        ejecutar();
    }

    public void ejecutar() {
        try {
            String[] cmd = {"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
                "-Tpng",
                "C:\\Graficas_Proyecto2\\bit.dot",
                "-o",
                "C:\\Graficas_Proyecto2\\bit.png"};
            Runtime.getRuntime().exec(cmd);
            //Desktop.getDesktop().open(new File("C:\\Graficas_Proyecto2\\bit.png"));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        System.out.println("imagen generada");
    }
    
    public void inserar(){
       insertarBitacora("hola","Santi");
       insertarBitacora("hola2","Santi2");
       insertarBitacora("hola3","Santi3");
       insertarBitacora("hola4","Santi4");
       //graficarBitacora();
    }
    
   
    
}
