/**
 *
 * @author santi
 */
import java.io.*;
import java.awt.Desktop;
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

    public void insertarDato(String n, String e, String c, String fh) {
        NodoArbolAVL nuevo = new NodoArbolAVL(n, e, c, fh);
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

    public String listarNodos(NodoArbolAVL raiz) {
        if (raiz == null) {
            return cad1;
        } else {
            listarNodos(raiz.izquierdo);
            cad1 += "\tNodo" + raiz.nombre + "[label=\"<izquierda>|" + raiz.nombre + Integer.toString(raiz.altura) + "|<derecha>\"];\n";
            listarNodos(raiz.derecho);
        }
        return cad1;
    }

    public String apuntarNodos(NodoArbolAVL raiz) {
        if (raiz == null) {
            return cad2;
        } else {
            apuntarNodos(raiz.izquierdo);
            if (raiz.izquierdo != null) {
                cad2 += "\tNodo" + raiz.nombre + ":izquierda->Nodo" + raiz.izquierdo.nombre + ";\n";
            }

            if (raiz.derecho != null) {
                cad2 += "\tNodo" + raiz.nombre + ":derecha->Nodo" + raiz.derecho.nombre + ";\n";
            }
            apuntarNodos(raiz.derecho);
        }
        return cad2;
    }

    public void graficar() {
        File f;
        f = new File("C:\\Graficas_Proyecto2\\ArbolAVL.dot");
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
            //strings con datos arbol
            //w.write("prueba->prueba2");
            w.write(listarNodos(raiz));
            w.write(apuntarNodos(raiz));
            w.write("\tlabel=\"Arbol Binario De Busqueda\";\n");
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
                "C:\\Graficas_Proyecto2\\ArbolAVL.dot",
                "-o",
                "C:\\Graficas_Proyecto2\\ArbolAVL.png"}; //Comando de apagado en windows
            Runtime.getRuntime().exec(cmd);
            Desktop.getDesktop().open(new File("C:\\Graficas_Proyecto2\\ArbolAVL.png"));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}