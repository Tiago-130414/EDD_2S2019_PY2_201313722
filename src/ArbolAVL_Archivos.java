
/**
 *
 * @author santi
 */
import java.io.*;
import java.awt.Desktop;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArbolAVL_Archivos {

    String cad1 = "", cad2 = "";
    NodoArbolAVL raiz;

    public NodoArbolAVL buscar(String nomA, NodoArbolAVL r) {
        if (raiz == null) {
            return null;
        } else if (r.nombre.equals(nomA)) {
            return r;
        } else if (Integer.parseInt(r.nombre) < Integer.parseInt(nomA)) {
            return buscar(nomA, r.derecho);
        } else {
            return buscar(nomA, r.izquierdo);
        }
    }

    public int obtenerFE(NodoArbolAVL r) {
        if (r == null) {
            return 0;
        } else {
            return r.altura;
        }
    }

    public NodoArbolAVL rotacionIzquierda(NodoArbolAVL h) {
        NodoArbolAVL aux = h.izquierdo;
        h.izquierdo = aux.derecho;
        aux.derecho = h;
        h.altura = Math.max(obtenerFE(h.izquierdo), obtenerFE(h.derecho)) + 1;
        aux.altura = Math.max(obtenerFE(aux.izquierdo), obtenerFE(aux.derecho)) + 1;
        return aux;
    }

    public NodoArbolAVL rotacionDerecha(NodoArbolAVL h) {
        NodoArbolAVL aux = h.derecho;
        h.derecho = aux.izquierdo;
        aux.izquierdo = h;
        h.altura = Math.max(obtenerFE(h.izquierdo), obtenerFE(h.derecho)) + 1;
        aux.altura = Math.max(obtenerFE(aux.izquierdo), obtenerFE(aux.derecho)) + 1;
        return aux;
    }

    public NodoArbolAVL rotacionDobleIzquierda(NodoArbolAVL h) {
        NodoArbolAVL aux;
        h.izquierdo = rotacionDerecha(h.izquierdo);
        aux = rotacionIzquierda(h);
        return aux;
    }

    public NodoArbolAVL rotacionDobleDerecha(NodoArbolAVL h) {
        NodoArbolAVL aux;
        h.derecho = rotacionIzquierda(h.derecho);
        aux = rotacionDerecha(h);
        return aux;
    }

    public NodoArbolAVL insertar(NodoArbolAVL nuevo, NodoArbolAVL raiz) {
        NodoArbolAVL nuevoPadre = raiz;
        int valN = devolverSumaAscii(nuevo.nombre);
        int valAr = devolverSumaAscii(raiz.nombre);
        if (devolverSumaAscii(nuevo.nombre) < devolverSumaAscii(raiz.nombre)) {
            if (raiz.izquierdo == null) {
                raiz.izquierdo = nuevo;
            } else {
                raiz.izquierdo = insertar(nuevo, raiz.izquierdo);
                if ((obtenerFE(raiz.izquierdo) - obtenerFE(raiz.derecho) == 2)) {
                    if (devolverSumaAscii(nuevo.nombre) < devolverSumaAscii(raiz.izquierdo.nombre)) {
                        nuevoPadre = rotacionIzquierda(raiz);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(raiz);
                    }
                }
            }
        } else if (devolverSumaAscii(nuevo.nombre) > devolverSumaAscii(raiz.nombre)) {
            if (raiz.derecho == null) {
                raiz.derecho = nuevo;
            } else {
                raiz.derecho = insertar(nuevo, raiz.derecho);
                if ((obtenerFE(raiz.derecho) - obtenerFE(raiz.izquierdo) == 2)) {
                    if (devolverSumaAscii(nuevo.nombre) > devolverSumaAscii(raiz.derecho.nombre)) {
                        nuevoPadre = rotacionDerecha(raiz);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(raiz);
                    }
                }
            }
        } else {
            System.out.println("Nodo Duplicado");
        }
        //Actualizando FE
        if ((raiz.izquierdo == null) && (raiz.derecho != null)) {
            raiz.altura = raiz.derecho.altura + 1;
        } else if ((raiz.derecho == null) && (raiz.izquierdo != null)) {
            raiz.altura = raiz.izquierdo.altura + 1;
        } else {
            raiz.altura = Math.max(obtenerFE(raiz.izquierdo), obtenerFE(raiz.derecho)) + 1;
        }
        return nuevoPadre;
    }

    public void insertarDato(String n, String c) {
        String fh = timestamp();
        NodoArbolAVL nuevo = new NodoArbolAVL(n, c, fh);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertar(nuevo, raiz);
        }
    }

    public void eliminar(String dat) {
        if (raiz == null) {
            System.out.println("Vacio");
        } else {
            raiz = eliminarDato(raiz, dat);
        }

    }

    public NodoArbolAVL eliminarDato(NodoArbolAVL raiz, String elemento) {
        if (raiz == null) {
            return raiz;
        }
        if (devolverSumaAscii(elemento) < devolverSumaAscii(raiz.nombre)) {
            raiz.izquierdo = eliminarDato(raiz.izquierdo, elemento);
        } else if (devolverSumaAscii(elemento) > devolverSumaAscii(raiz.nombre)) {
            raiz.derecho = eliminarDato(raiz.derecho, elemento);
        } else {
            if ((raiz.izquierdo == null) || (raiz.derecho == null)) {
                NodoArbolAVL temp = null;
                if (temp == raiz.izquierdo) {
                    temp = raiz.derecho;
                } else {
                    temp = raiz.izquierdo;
                }

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else {
                    raiz = temp;
                }
            } else {
                NodoArbolAVL temp = nodoMin(raiz.izquierdo);
                raiz.nombre = temp.nombre;
                raiz.contenido = temp.contenido;
                raiz.timestamp = temp.timestamp;
                raiz.derecho = eliminarDato(raiz.izquierdo, temp.nombre);
            }
        }

        if (raiz == null) {
            return raiz;
        }

        raiz.altura = Math.max(altura(raiz.izquierdo), altura(raiz.derecho)) + 1;

        int bal = getBalance(raiz);

        //izquierda izquierda
        if (bal > 1 && getBalance(raiz.izquierdo) >= 0) {
            return rotacionDerecha(raiz);
        }
        //izquierda derecha
        if (bal > 1 && getBalance(raiz.izquierdo) < 0) {
            raiz.izquierdo = rotacionIzquierda(raiz.izquierdo);
            return rotacionDerecha(raiz);
        }
        //derecha derecha
        if (bal < -1 && getBalance(raiz.derecho) <= 0) {
            return rotacionIzquierda(raiz);
        }
        //derecha izquierda
        if (bal < -1 && getBalance(raiz.derecho) > 0) {
            raiz.derecho = rotacionDerecha(raiz.derecho);
            return rotacionIzquierda(raiz);
        }

        return raiz;
    }

    int altura(NodoArbolAVL n) {
        if (n == null) {
            return 0;
        } else {
            return n.altura;
        }
    }

    int getBalance(NodoArbolAVL n) {
        if (n == null) {
            return 0;
        } else {
            return altura(n.izquierdo) - altura(n.derecho);
        }
    }

    NodoArbolAVL nodoMin(NodoArbolAVL n) {
        NodoArbolAVL actual = n;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        System.out.println(actual.nombre);
        return actual;

    }

    public void inOrden(NodoArbolAVL r) {
        if (r != null) {
            inOrden(r.izquierdo);
            System.out.println(r.nombre + "\taltura" + r.altura + "\tfe" + r.fe);
            inOrden(r.derecho);
        }
    }

    public void postOrden(NodoArbolAVL r) {
        if (r != null) {
            postOrden(r.izquierdo);
            postOrden(r.derecho);
            System.out.println(r.nombre + "");
        }
    }

    public void preOrden(NodoArbolAVL r) {
        if (r != null) {

            System.out.println(r.nombre + "");
            preOrden(r.izquierdo);
            preOrden(r.derecho);
        }
    }

    public int devolverSumaAscii(String cadena) {
        int sum = 0;
        for (int i = 0; i < cadena.length(); i++) {
            sum += (int) cadena.charAt(i);
        }
        return sum;
    }

    public String listarNodos(NodoArbolAVL raiz, String usuario) {
        if (raiz == null) {
            return cad1;
        } else {
            listarNodos(raiz.izquierdo, usuario);
            cad1 += "\t\"Nodo" + raiz.nombre + "\"[label=\"<izquierda>|" + "Nombre: " + raiz.nombre
                    + "\\n Contenido:" + raiz.contenido + "\\nFE: " + Integer.toString(raiz.fe)
                    + "\\nAltura: " + Integer.toString(raiz.altura)
                    + "\\nTimeStamp: " + raiz.timestamp + "\\nPropietario: " + usuario + "|<derecha>\"];\n";
            listarNodos(raiz.derecho, usuario);
        }
        return cad1;
    }

    public String apuntarNodos(NodoArbolAVL raiz) {
        if (raiz == null) {
            return cad2;
        } else {
            apuntarNodos(raiz.izquierdo);
            if (raiz.izquierdo != null) {
                cad2 += "\t\"Nodo" + raiz.nombre + "\":izquierda->\"Nodo" + raiz.izquierdo.nombre + "\";\n";
            }

            if (raiz.derecho != null) {
                cad2 += "\t\"Nodo" + raiz.nombre + "\":derecha->\"Nodo" + raiz.derecho.nombre + "\";\n";
            }
            apuntarNodos(raiz.derecho);
        }
        return cad2;
    }

    public void graficar(String dat, String usuario) {
        String ar = "C:\\Graficas_Proyecto2\\ArbolAVL_" + dat + ".dot";
        File f;
        f = new File(ar.toString());
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            w.write("digraph arbol\n{");
            w.write("\trankdir=TB;\n");
            w.write("\tordering=out;\n");
            w.write("\tgraph [splines=compound,nodesep=0.5];\n");
            w.write("\tsubgraph cluster_0{\n");
            w.write("\tstyle=filled;\n");
            w.write("\tcolor=lightgrey;\n");
            w.write("\tlabelloc=t;\n");
            w.write("\tnode [shape = record, style=\"rounded,filled\", fillcolor=\"orange:red\",width=0.7,height=0.5];\n");
            actualizarFE(raiz);
            w.write(listarNodos(raiz, usuario));
            w.write(apuntarNodos(raiz));
            w.write("\tlabel=\"Arbol AVL\";\n");
            w.write("\t}\n");
            w.write("}");
            wr.close();
            bw.close();
        } catch (IOException e) {
        }
        cad1="";
        cad2="";
        ejecutar(dat);
    }

    public void ejecutar(String dif) {
        String dot = "C:/Graficas_Proyecto2/ArbolAVL_" + dif + ".dot";
        String nomImg = "C:/Graficas_Proyecto2/ArbolAVL_" + dif + ".png";
        try {
            String[] cmd = {"C:/Program Files (x86)/Graphviz2.38/bin/dot.exe",
                "-Tpng",
                dot,
                "-o",
                nomImg};
            Runtime.getRuntime().exec(cmd);
            abrirImg("C:/Graficas_Proyecto2/ArbolAVL_" + dif + ".png");
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void abrirImg(String archivo) {
        try {

            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);

        }
    }

    public void leerCsv(String nombreAr) {
        String csvFile = nombreAr.toString();
        BufferedReader br = null;
        String line;
        //int cont = 0;
        String separador = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(separador);
                if (!datos[0].equals("Archivo") && !datos[1].equals("Contenido")) {
                    insertarDato(datos[0], datos[1]);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer archivo: " + e);
        } catch (IOException e) {
            System.out.println("Exception" + e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error al terminar lectura" + e);
                }
            }
        }
    }

    public String timestamp() {
        String tmstamp;
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        tmstamp = hourdateFormat.format(date);
        return tmstamp;
    }

    public void actualizarFE(NodoArbolAVL raiz) {
        int fe = 0;
        if (raiz != null) {
            inOrden(raiz.izquierdo);
            fe = getBalance(raiz);
            raiz.setFe(fe);
            inOrden(raiz.derecho);
        }
    }
}
