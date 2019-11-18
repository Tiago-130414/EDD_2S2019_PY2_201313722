
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author santi
 */
public class MatrizCarpetas {

    static NodoMatriz columnas;
    static NodoMatriz filas;
    static int m = 0;

    public MatrizCarpetas() {
        columnas = new NodoMatriz(" ", " ", " ", " ");
        filas = new NodoMatriz(" ", " ", " ", " ");
    }

    public void insertarNodo(String fil, String col, String dat, String fech) {
        NodoMatriz nuevoCol = new NodoMatriz("/", col, "Columna", "1234");
        enlazarColumnas(nuevoCol, col);
        NodoMatriz nuevoFil = new NodoMatriz(fil, "/", "Fila", "1234");
        enlazarFilas(nuevoFil, fil);
        NodoMatriz nuevo = new NodoMatriz(fil, col, dat, fech);
        enlazarContenido(fil, col, nuevo);
    }

    public void enlazarColumnas(NodoMatriz nuevoCol, String columna) {
        NodoMatriz temporalColumnas = this.columnas;
        if (temporalColumnas == null) {
            this.columnas = nuevoCol;
        } else {
            while ((temporalColumnas != null) && (temporalColumnas.siguiente != null) && (compararCadena(temporalColumnas.siguiente.columna, columna) <= 0)) {
                temporalColumnas = temporalColumnas.siguiente;
            }
            if (compararCadena(temporalColumnas.columna, columna) == -1) {
                if (temporalColumnas.siguiente == null) {
                    temporalColumnas.siguiente = nuevoCol;
                    nuevoCol.anterior = temporalColumnas;
                } else {
                    NodoMatriz tempSig = temporalColumnas.siguiente;
                    nuevoCol.siguiente = temporalColumnas.siguiente;
                    nuevoCol.anterior = temporalColumnas.anterior;
                    temporalColumnas.siguiente = nuevoCol;
                    tempSig.anterior = nuevoCol;
                }
            } else if (compararCadena(temporalColumnas.columna, columna) == 1) {
                //else if (temporalColumnas.columna > columna) 
                nuevoCol.siguiente = temporalColumnas;
                temporalColumnas.anterior = nuevoCol;
                this.columnas = nuevoCol;
            } else {
                nuevoCol = temporalColumnas;
            }
        }
    }

    public void enlazarFilas(NodoMatriz nuevoFil, String fila) {
        NodoMatriz temporalFilas = this.filas;
        if (temporalFilas == null) {
            this.filas = nuevoFil;
        } else {
            while ((temporalFilas != null) && (temporalFilas.abajo != null) && (compararCadena(temporalFilas.abajo.fila, fila) <= 0)) {
                temporalFilas = temporalFilas.abajo;
            }
            if (compararCadena(temporalFilas.fila, fila) == -1) {
                //(temporalFilas.fila < fila)
                if (temporalFilas.abajo == null) {
                    temporalFilas.abajo = nuevoFil;
                    nuevoFil.arriba = temporalFilas;
                } else {
                    NodoMatriz tempSig = temporalFilas.abajo;
                    nuevoFil.abajo = temporalFilas.abajo;
                    nuevoFil.arriba = temporalFilas.arriba;
                    temporalFilas.abajo = nuevoFil;
                    tempSig.arriba = nuevoFil;
                }
            } else if (compararCadena(temporalFilas.fila, fila) == 1) {
                //else if (temporalFilas.fila > fila)
                nuevoFil.abajo = temporalFilas;
                temporalFilas.arriba = nuevoFil;
                this.filas = nuevoFil;
            } else {
                nuevoFil = temporalFilas;
                //System.out.println("hola");
            }

        }
    }

    public void enlazarContenido(String fil, String col, NodoMatriz contenido) {
        NodoMatriz temporalC = this.columnas;
        NodoMatriz temporalF = this.filas;
        boolean bandera = false;
        boolean bandera2 = false;
        ///-------------------------------------------------
        while (temporalC != null) {
            if (temporalC.getColumna().equals(col)) {
                bandera = true;
                break;
            }
            temporalC = temporalC.getSiguiente();
        }
        if (bandera == true) {
            System.out.println("se insertara" + fil);
            if (temporalC.getAbajo() == null) {
                contenido.setArriba(temporalC);
                temporalC.setAbajo(contenido);
            } else {
                while ((temporalC != null) && (temporalC.abajo != null) && (compararCadena(temporalC.abajo.columna, col) <= 0)) {
                    temporalC = temporalC.abajo;
                }
                if (compararCadena(temporalC.getColumna(), fil) == -1) {
                    if (temporalC.getAbajo() == null) {
                        temporalC.setAbajo(contenido);
                        contenido.setArriba(temporalC);
                    } else {
                        NodoMatriz abajo = temporalC.getAbajo();
                        contenido.setArriba(temporalC);
                        contenido.setAbajo(abajo);
                        abajo.setArriba(contenido);
                        temporalC.setAbajo(contenido);
                    }
                } else if (compararCadena(temporalC.getColumna(), fil) == 1) {
                    System.out.println(temporalC.arriba.getNombre());
                    contenido.arriba = temporalC.arriba;
                    contenido.abajo = temporalC;
                    temporalC.arriba.abajo = contenido;
                    temporalC.arriba = contenido;
                }

            }
        }

        //-------------------------------------------------
        while (temporalF != null) {
            if (temporalF.getFila().equals(fil)) {
                bandera2 = true;
                break;
            }
            temporalF = temporalF.getAbajo();
        }

        if (bandera2 == true) {
            if (temporalF.siguiente == null) {
                contenido.setAnterior(temporalF);
                temporalF.setSiguiente(contenido);
            } else {
                while ((temporalF != null) && (temporalF.siguiente != null) && (compararCadena(temporalF.siguiente.fila, fil) <= 0)) {
                    temporalF = temporalF.siguiente;
                }
                if (compararCadena(temporalF.getFila(), col) == -1) {
                    if (temporalF.getSiguiente() == null) {
                        temporalF.setSiguiente(contenido);
                        contenido.setAnterior(temporalF);
                    } else {
                        NodoMatriz sig = temporalF.getSiguiente();
                        contenido.setAnterior(temporalF);
                        contenido.setSiguiente(sig);
                        sig.setAnterior(contenido);
                        temporalC.setSiguiente(contenido);
                    }
                } else if (compararCadena(temporalF.getFila(), col) == 1) {
                    contenido.setAnterior(temporalF.anterior);
                    contenido.setSiguiente(temporalF);
                    temporalF.anterior.siguiente = contenido;
                    temporalF.anterior = contenido;
                }

            }
        }

    }

    public void mostrar() {
        NodoMatriz temp = this.columnas;
        if (temp != null) {
            while (temp != null) {
                NodoMatriz tempInt = temp.abajo;
                //System.out.println(temp.getColumna());
                while (tempInt != null) {
                    System.out.println(" F: " + tempInt.getFila() + " C: " + tempInt.getColumna() + " Nom: " + tempInt.getNombre());

                    tempInt = tempInt.abajo;
                }
                temp = temp.siguiente;
            }
        } else {
            System.out.println("vacio");
        }
    }

    public void mostrarF() {
        NodoMatriz temp = this.filas;
        NodoMatriz t;
        NodoMatriz k;
        if (temp != null) {
            while (temp != null) {
                NodoMatriz tempInt = temp.siguiente;
                t = temp.getSiguiente();
                if (t != null) {
                    System.out.println(t.getNombre());
                    k = t.getAbajo();
                    if (k != null) {
                        System.out.println("abajo" + k.getNombre());
                    }
                }

                temp = temp.abajo;
            }
        } else {
            System.out.println("vacio");
        }
    }

    public int compararCadena(String cad1, String cad2) {
        if (cad1.compareTo(cad2) == 0) {
            return 0;
        } else if (cad1.compareTo(cad2) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public void graficarBitacora() {
        File f;
        f = new File("C:\\Graficas_Proyecto2\\Matriz.dot");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            w.write("digraph matriz{\n");
            w.write("graph [ranksep=\"0.5\", nodesep=\"0.5\"];\n");
            w.write("Matriz[width = 0.3 group = \"Mt0\",  style=filled,shape=\"circle\",fillcolor=\"yellow:red\" label=\" \"];\n");
            w.write(crearNodosFilaGuia());
            w.write(crearNodosColumnaGuia());
            w.write(nodosContenidoFila());
            w.write(rankSame());
            w.write(enlazarColumnaConNodo());
            w.write(enlazarNodosMediosColumna());
            w.write("\n");
            w.write("}");
            wr.close();
            bw.close();
        } catch (IOException e) {
        }
        ejecutar();
    }

    public String crearNodosFilaGuia() {
        String cad = "";
        NodoMatriz filaA = this.filas;
        while (filaA != null) {
            if (!(filaA.getFila()).equals(" ")) {
                if (filaA.getFila().equals("/")) {
                    cad += "FP1";
                } else {
                    cad += "F" + quitarEspacios(filaA.getFila());
                }
                cad += "[label=\"";
                cad += filaA.getFila() + "\"";
                cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow:red\", group = \"Mt0\"] ;\n";
            }
            filaA = filaA.getAbajo();
        }

        filaA = this.filas;
        while (filaA != null) {
            if (filaA.abajo != null && !(filaA.getFila()).equals(" ")) {
                if (filaA.getFila().equals("/")) {
                    cad += "FP1";
                } else {
                    cad += "F" + quitarEspacios(filaA.getFila());
                }

                cad += "->";
                cad += "F" + quitarEspacios(filaA.abajo.getFila());
                cad += "[dir=both];\n";
            }
            filaA = filaA.getAbajo();
        }
        return cad;
    }

    public String crearNodosColumnaGuia() {
        NodoMatriz tempC = this.columnas;
        NodoMatriz tempF = this.filas;
        String cad = "";
        String primF = "";
        String primC = "";
        //int cont = 0;
        if ((tempC.getColumna()).equals(" ") && (tempF.getFila()).equals(" ")) {
            System.out.println("hola");
            if (tempC.getSiguiente().getColumna().equals("/")) {
                primC = "CP";

            } else {
                primC = "C" + quitarEspacios(tempC.siguiente.getColumna());

            }

            if (tempF.getAbajo().getFila().equals("/")) {
                primF = "FP";
            } else {
                primF = "F" + quitarEspacios(tempF.abajo.getFila());

            }
        }
        while (tempC != null) {
            if (!(tempC.getColumna()).equals(" ")) {
                if (tempC.getColumna().equals("/")) {
                    cad += "CP1";
                    //cad += "CP" + cont;
                } else {
                    cad += "C" + quitarEspacios(tempC.getColumna());
                }
                cad += "[label=\"";
                cad += tempC.getColumna() + "\"";
                cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow:red\", group =\"";
                cad += quitarEspacios(tempC.getColumna()) + "\"];\n";
            }
            //cont++;
            tempC = tempC.getSiguiente();
        }
        tempC = this.columnas;
        while (tempC != null) {
            if (tempC.getSiguiente() != null && !(tempC.getColumna()).equals(" ")) {
                if (tempC.getColumna().equals("/")) {
                    cad += "CP1";
                } else {
                    cad += "C" + quitarEspacios(tempC.getColumna());
                }

                cad += "->";
                cad += "C" + quitarEspacios(tempC.siguiente.getColumna());
                cad += "[dir=both];\n";
            }
            //cont++;
            tempC = tempC.getSiguiente();
        }
        //cont = 0;
        tempC = this.columnas;
        cad += "{rank = same;Matriz;";
        while (tempC != null) {
            if (!(tempC.getColumna()).equals(" ")) {
                if (tempC.getColumna().equals("/")) {
                    cad += "CP1";
                } else {
                    cad += "C" + quitarEspacios(tempC.getColumna());

                }
                if (tempC.getSiguiente() != null) {
                    cad += ";";
                }
            }
            //cont++;
            tempC = tempC.getSiguiente();
        }
        cad += "}\n";
        //cont = 1;
        cad += "Matriz ->" + primC + "1" + ";\n";
        cad += "Matriz ->" + primF + "1" + ";\n";

        return cad;
    }

    public String nodosContenidoFila() {
        String cad = "";
        NodoMatriz enlace = this.filas;
        NodoMatriz tempF = this.filas;
        NodoMatriz aux;
        NodoMatriz primero;
        while (tempF != null) {
            if (tempF.siguiente != null) {
                if (quitarEspacios(tempF.siguiente.getFila()).equals("/")) {
                    System.out.println("usofila");
                    cad += "FP1";
                } else {
                    cad += quitarEspacios(tempF.siguiente.getFila());
                }
                if (quitarEspacios(tempF.siguiente.getColumna()).equals("/")) {
                    System.out.println("uso columna");
                    cad += "CP1";
                } else {
                    cad += quitarEspacios(tempF.siguiente.getColumna());
                }
                cad += "[label=\"";
                cad += tempF.getSiguiente().getNombre() + "\"";

                cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow:red\", group =";
                if (quitarEspacios(tempF.getSiguiente().getColumna()).equals("/")) {
                    cad += "CP1];";
                } else {
                    cad += quitarEspacios(tempF.getSiguiente().getColumna()) + "];\n";
                }
            }
            tempF = tempF.abajo;
        }

        while (enlace != null) {
            if (enlace.getSiguiente() != null) {
                if (quitarEspacios(enlace.getFila()).equals("/")) {
                    cad += "FP1";
                } else {
                    cad += "F" + quitarEspacios(enlace.getFila());

                }
                cad += "->";
                if (quitarEspacios(enlace.getSiguiente().getFila()).equals("/")) {
                    cad += "FP1";
                } else {

                    cad += quitarEspacios(enlace.getSiguiente().getFila());
                }
                if (quitarEspacios(enlace.getSiguiente().getColumna()).equals("/")) {
                    cad += "CP1";
                } else {

                    cad += quitarEspacios(enlace.getSiguiente().getColumna());
                }
                cad += "[dir=both,constraint = false];\n";
            }
            enlace = enlace.getAbajo();
        }

        enlace = this.filas;
        while (enlace != null) {
            primero = enlace.getSiguiente();
            if (primero != null) {
                primero = primero.getSiguiente();
                while (primero != null) {
                    if (quitarEspacios(primero.getFila()).equals("/")) {
                        cad += "FP1";
                    } else {
                        cad += quitarEspacios(primero.getFila());

                    }
                    if (quitarEspacios(primero.getColumna()).equals("/")) {
                        cad += "CP1";
                    } else {
                        cad += quitarEspacios(primero.getColumna());
                    }
                    cad += "[label=\"";
                    cad += primero.getNombre() + "\"";
                    cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow:red\", group =";
                    if (quitarEspacios(primero.getColumna()).equals("/")) {
                        cad += "CP1];\n";
                    } else {
                        cad += quitarEspacios(primero.getColumna()) + "];\n";
                    }
                    primero = primero.getSiguiente();
                }
            }
            enlace = enlace.getAbajo();
        }

        enlace = this.filas;
        while (enlace != null) {
            if (enlace.getSiguiente() != null) {
                primero = enlace.getSiguiente();
                while (primero != null) {
                    if (primero.siguiente != null) {
                        if (primero.getFila().equals("/") && primero.getColumna().equals("/")) {
                            cad += "FP1CP1";
                        } else if (!primero.getFila().equals("/") && primero.getColumna().equals("/")) {
                            cad += quitarEspacios(primero.getFila()) + "CP1";
                        } else if (primero.getFila().equals("/") && !primero.getColumna().equals("/")) {

                            cad += "FP1" + quitarEspacios(primero.getColumna());
                        } else {
                            cad += quitarEspacios(primero.getFila());
                            cad += quitarEspacios(primero.getColumna());
                        }

                        /*
                        if (primero.getFila().equals("/")) {
                            cad += "FP1";
                        } else {

                            cad += quitarEspacios(primero.getFila());
                        }
                        if (primero.getColumna().equals("/")) {
                            cad += "CP1";
                        } else {
                            cad += quitarEspacios(primero.getColumna());
                        }*/
                        cad += "->";
                        /*if (primero.getFila().equals("/")) {
                            cad += "FP1";
                        } else {

                            cad += quitarEspacios(primero.getSiguiente().getFila());
                        }
                        if (primero.getColumna().equals("/")) {
                            cad += "CP1";
                        } else {
                            cad += quitarEspacios(primero.getSiguiente().getColumna());
                        }*/
                        if (primero.getSiguiente().getFila().equals("/") && primero.getSiguiente().getColumna().equals("/")) {
                            cad += "FP1CP1";
                        } else if (!primero.getSiguiente().getFila().equals("/") && primero.getSiguiente().getColumna().equals("/")) {
                            cad += quitarEspacios(primero.getSiguiente().getFila()) + "CP1";
                        } else if (primero.getSiguiente().getFila().equals("/") && !primero.getSiguiente().getColumna().equals("/")) {
                            cad += "FP1" + quitarEspacios(primero.getSiguiente().getColumna());
                        } else {
                            cad += quitarEspacios(primero.getSiguiente().getFila());
                            cad += quitarEspacios(primero.getSiguiente().getColumna());
                        }

                        cad += "[dir=both,constraint = false];\n";
                        cad += "\n";
                    }
                    primero = primero.getSiguiente();
                }
            }
            enlace = enlace.getAbajo();

        }
        return cad;
    }

    public String rankSame() {
        NodoMatriz tempBaja = this.filas;
        NodoMatriz tempDerecha;
        String cad = "";
        while (tempBaja != null) {
            if (!(tempBaja.getFila().equals(" "))) {
                cad += "{rank=same;";
                if (quitarEspacios(tempBaja.getFila()).equals("/")) {
                    cad += "FP1;";
                } else {
                    cad += "F" + quitarEspacios(tempBaja.getFila()) + ";";
                }
                tempDerecha = tempBaja.getSiguiente();
                while (tempDerecha != null) {
                    if (quitarEspacios(tempDerecha.getFila()).equals("/")) {
                        cad += "FP1";
                    } else {
                        cad += quitarEspacios(tempDerecha.getFila());
                    }
                    if (quitarEspacios(tempDerecha.getColumna()).equals("/")) {
                        cad += "CP1";
                    } else {
                        cad += quitarEspacios(tempDerecha.getColumna());
                    }
                    cad += ";";
                    tempDerecha = tempDerecha.getSiguiente();
                }
                cad += "}\n";
            }
            tempBaja = tempBaja.abajo;
        }
        return cad;
    }

    public String enlazarColumnaConNodo() {
        String cad = "";
        NodoMatriz auxCol = this.columnas;
        while (auxCol != null) {
            if (auxCol.getAbajo() != null) {
                if (!auxCol.getColumna().equals(" ")) {
                    if (auxCol.getColumna().equals("/")) {
                        cad += "CP1";
                    } else {
                        cad += "C" + auxCol.getColumna();
                    }
                    cad += "->";
                    if (auxCol.getAbajo().getFila().equals("/")) {
                        cad += "FP1";
                    } else {
                        cad += auxCol.getAbajo().getFila();
                    }
                    if (auxCol.getAbajo().getColumna().equals("/")) {
                        cad += "CP1";
                    } else {
                        cad += auxCol.getAbajo().getColumna();
                    }
                    cad += "[dir=both];\n";
                }
            }

            auxCol = auxCol.getSiguiente();
        }

        return cad;
    }

    public String enlazarNodosMediosColumna() {
        String cad = "";
        NodoMatriz auxND = this.columnas;
        NodoMatriz primero;
        while (auxND != null) {
            if (auxND.getAbajo() != null) {
                primero = auxND.getAbajo();
                while (primero != null) {
                    if (primero.getAbajo() != null) {
                        if (!(primero.getFila().equals(" "))) {

                            if (primero.getFila().equals("/") && primero.getColumna().equals("/")) {
                                cad += "FP1CP1";
                            } else if (!primero.getFila().equals("/") && primero.getColumna().equals("/")) {
                                cad += quitarEspacios(primero.getFila()) + "CP1";
                            } else if (primero.getFila().equals("/") && !primero.getColumna().equals("/")) {

                                cad += "FP1" + quitarEspacios(primero.getColumna());
                            } else {
                                cad += quitarEspacios(primero.getFila());
                                cad += quitarEspacios(primero.getColumna());
                            }

                            //cad += quitarEspacios(primero.getFila());
                            //cad += quitarEspacios(primero.getColumna());
                            cad += "->";
                            //cad += quitarEspacios(primero.getAbajo().getFila());
                            //cad += quitarEspacios(primero.getAbajo().getColumna());

                            if (primero.getAbajo().getFila().equals("/") && primero.getAbajo().getColumna().equals("/")) {
                                cad += "FP1CP1";
                            } else if (!primero.getAbajo().getFila().equals("/") && primero.getAbajo().getColumna().equals("/")) {
                                cad += quitarEspacios(primero.getAbajo().getFila()) + "CP1";
                            } else if (primero.getAbajo().getFila().equals("/") && !primero.getAbajo().getColumna().equals("/")) {
                                cad += "FP1" + quitarEspacios(primero.getAbajo().getColumna());
                            } else {
                                cad += quitarEspacios(primero.getAbajo().getFila());
                                cad += quitarEspacios(primero.getAbajo().getColumna());
                            }

                            cad += "\n";
                            cad += "[dir=both];\n";
                        }
                    }
                    primero = primero.getAbajo();
                }
            }
            auxND = auxND.getSiguiente();
        }
        return cad;
    }

    public void ejecutar() {
        try {
            String[] cmd = {"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
                "-Tpng",
                "C:\\Graficas_Proyecto2\\Matriz.dot",
                "-o",
                "C:\\Graficas_Proyecto2\\Matriz.png"};
            Runtime.getRuntime().exec(cmd);
            /*Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("dot -Tpng C:\\Graficas_Proyecto2\\Matriz.dot Matriz.dot -o C:\\Graficas_Proyecto2\\Matriz.png");
            p.waitFor();*/
            //Desktop.getDesktop().open(new File("C:\\Graficas_Proyecto2\\Matriz.png"));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public String quitarEspacios(String c) {
        String cad = "";
        cad = c.replace(" ", "");
        cad = cad.replace("\t", "");
        cad = cad.replace("\n", "");
        return cad;
    }

    public NodoMatriz retornarArbol(String fil, String col) {
        NodoMatriz temp = null;
        NodoMatriz aux = this.filas;
        if (aux != null) {
            while (aux != null) {
                NodoMatriz tempInt = aux.siguiente;
                while (tempInt != null) {
                    if (tempInt.getColumna().equals(col) && tempInt.getFila().equals(fil)) {
                        temp = tempInt;
                        System.out.println("encontre el nodo buscado en matriz");
                        break;
                    }
                    tempInt = tempInt.siguiente;
                }
                aux = aux.abajo;
            }
        } else {
            System.out.println("vacio");
        }
        return temp;
    }

    public void guardarNodo(String fil, String col, NodoMatriz ins) {
        NodoMatriz aux = this.filas;
        if (aux != null) {
            while (aux != null) {
                NodoMatriz tempInt = aux.siguiente;
                while (tempInt != null) {
                    if (tempInt.getColumna().equals(fil) && tempInt.getFila().equals(col)) {
                        tempInt = ins;
                        System.out.println("encontre el nodo buscado en matriz y lo guarde");
                        break;
                    }
                    tempInt = tempInt.siguiente;
                }
                aux = aux.abajo;
            }
        } else {
            System.out.println("vacio");
        }
    }

    public boolean existe(String fil, String col) {
        NodoMatriz temp = this.columnas;
        boolean tempB = false;
        if (temp != null) {
            while (temp != null) {
                NodoMatriz tempInt = temp.abajo;
                while (tempInt != null) {
                    if (tempInt.getFila().equals(fil) && tempInt.getColumna().equals(col)) {
                        tempB = true;
                    }
                    tempInt = tempInt.abajo;
                }
                temp = temp.siguiente;
            }
        } else {
            System.out.println("vacio");
        }
        return tempB;
    }

    public void graficarGrafo() {
        File f;
        f = new File("C:\\Graficas_Proyecto2\\grafo.dot");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            w.write("digraph grafo{\n");
            w.write("\trankdir=LR;\n");
            w.write("\tlabelloc=t;\n");
            w.write("\tsubgraph cluster_0{\n");
            w.write("\tstyle=filled;\n");
            w.write("\tcolor=lightgrey;\n");
            w.write(retornarGrafo());
            w.write("\tlabel=\"Grafo\";\n");
            w.write("\t}\n");
            w.write("\n");
            w.write("}");
            wr.close();
            bw.close();
        } catch (IOException e) {
        }

        ejecutarGrafo();
    }

    public void ejecutarGrafo() {
        try {
            String[] cmd = {"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
                "-Tpng",
                "C:\\Graficas_Proyecto2\\grafo.dot",
                "-o",
                "C:\\Graficas_Proyecto2\\grafo.png"};
            Runtime.getRuntime().exec(cmd);
            //Desktop.getDesktop().open(new File("C:\\Graficas_Proyecto2\\grafo.png"));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public String retornarGrafo() {
        String cad = "";
        NodoMatriz temp = this.filas;
        if (temp != null) {
            while (temp != null) {
                NodoMatriz tempInt = temp.siguiente;
                if (temp.getAbajo()!=null) {
                    if (!temp.getFila().equals(" ")) {
                        cad+="\""+tempInt.getFila()+"\"->\""+temp.getAbajo().getFila()+"\";\n";
                    }
                }
                while (tempInt != null) {
                    
                    if (!tempInt.getFila().equals(" ")&&!tempInt.getColumna().equals(" ")) {
                        cad+="\""+tempInt.getFila()+"\"->\""+tempInt.getColumna()+"\";\n";
                    }
                    tempInt = tempInt.siguiente;
                }
                temp = temp.abajo;
            }
        } else {
            System.out.println("vacio");
        }
        return cad;
    }
}
