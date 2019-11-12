
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

    NodoMatriz columnas;
    NodoMatriz filas;

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
            //System.out.println("r");
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
            }/*else if(compararCadena(temporalColumnas.columna, columna)==0){
                NodoMatriz tempCI = temporalColumnas.siguiente;
                if (tempCI!=null) {
                    nuevoCol.anterior = temporalColumnas;
                    nuevoCol.siguiente = tempCI;
                    tempCI.anterior = nuevoCol;
                    temporalColumnas.siguiente = nuevoCol;
                }else{
                    temporalColumnas.siguiente = nuevoCol;
                    nuevoCol.anterior = temporalColumnas;
                }
            } */ else {
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
        //enlazar columnas
        if (temporalC != null) {
            while ((temporalC != null) && (temporalC.siguiente != null) && (!(temporalC.getColumna()).equals(col))) {
                //while ((temporalC != null) && (temporalC.siguiente != null) && (temporalC.getColumna() != col))
                temporalC = temporalC.siguiente;
            }
            if (temporalC.abajo != null) {
                NodoMatriz tempCB = temporalC.abajo;
                while ((tempCB != null) && (tempCB.abajo != null) && (!(tempCB.getFila()).equals(fil))) {
                    //while ((tempCB != null) && (tempCB.abajo != null) && (tempCB.getFila() != fil))
                    tempCB = tempCB.abajo;
                }
                if (tempCB.abajo != null) {
                    NodoMatriz tempSig = tempCB.abajo;
                    contenido.arriba = tempCB;
                    contenido.abajo = tempSig;
                    tempSig.arriba = contenido;
                    tempCB.abajo = contenido;
                } else {
                    //System.out.println("R");
                    tempCB.abajo = contenido;
                    contenido.arriba = tempCB;
                }
            } else {
                //System.out.println("F");
                temporalC.abajo = contenido;
                contenido.arriba = temporalC;
            }
        }
        //enlazar filas
        if (temporalF != null) {
            while ((temporalF != null) && (temporalF.abajo != null) && (!(temporalF.getFila()).equals(fil))) {
                //while ((temporalF != null) && (temporalF.abajo != null) && (temporalF.getFila() != fil))
                temporalF = temporalF.abajo;
            }
            if (temporalF.siguiente != null) {
                NodoMatriz tempCF = temporalF.siguiente;
                while ((temporalF != null) && (temporalF.siguiente != null) && (!(temporalF.getColumna()).equals(col))) {
                    //while ((tempCF!=null)&&(tempCF.siguiente!=null)&&(tempCF.getColumna()!=col))
                    temporalF = temporalF.siguiente;
                }
                if (temporalF.siguiente != null) {
                    NodoMatriz tempSigF = temporalF.siguiente;
                    
                    contenido.siguiente = tempSigF;
                    contenido.anterior = temporalF;
                    tempSigF.anterior = contenido;
                    temporalF.siguiente = contenido;
                } else {
                    temporalF.siguiente = contenido;
                    contenido.anterior = tempCF;
                }
            } else {
                //System.out.println("L");
                temporalF.siguiente = contenido;
                contenido.anterior = temporalF;
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
                //System.out.println(temp.getColumna());
                /*while (tempInt != null) {
                    t = tempInt.getSiguiente();
                    if (t!=null) {
                        System.out.println("F: " + tempInt.getFila() + " C: " + tempInt.getColumna() + " Nom: " + tempInt.getNombre()  + "anterior: " +t.getNombre());
                    }else{
                        System.out.println("F: " + tempInt.getFila() + " C: " + tempInt.getColumna() + " Nom: " + tempInt.getNombre());
                    }
                    tempInt = tempInt.siguiente;
                }*/
                t = temp.getSiguiente();
                if (t!=null) {
                    System.out.println(t.getNombre());
                    k = t.getAbajo();
                    if (k!=null) {
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
            w.write("Matriz[width = 0.3 group = \"Mt0\",  style=filled,shape=\"circle\",fillcolor=\"yellow:blue\" label=\" \"];\n");
            //obtener valores de pila
            //w.write(listarBitacora());
            //w.write(crearNodosFilaGuia());
            //w.write(crearNodosColumnaGuia());
            w.write(nodosContenidoFila());
            /*w.write(rankSame());
            w.write(enlazarColumnaConNodo());
            w.write(enlazarNodosMediosColumna());*/
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
                cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow\", group = \"Mt0\"] ;\n";
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
                cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow\", group =\"";
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
                    cad += "FP1";
                } else {
                    cad += quitarEspacios(tempF.siguiente.getFila());
                }
                if (quitarEspacios(tempF.siguiente.getColumna()).equals("/")) {
                    cad += "CP1";
                } else {
                    cad += quitarEspacios(tempF.siguiente.getColumna());
                }
                cad += "[label=\"";
                cad += tempF.getSiguiente().getNombre() + "\"";
                
                cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow\", group =";
                if (quitarEspacios(tempF.getSiguiente().getColumna()).equals("/")) {
                    cad += "CP1];";
                } else {
                    cad += quitarEspacios(tempF.getSiguiente().getColumna()) + "];\n";
                }
            }
            tempF = tempF.abajo;
        }
/*
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
            if (primero!= null) {
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
                    cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow\", group =";
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
                        if (primero.getFila().equals("/")) {
                            cad += "CP1";
                        } else {

                            cad += quitarEspacios(primero.getFila());
                        }
                        if (primero.getColumna().equals("/")) {
                            cad += "FP1";
                        } else {
                            cad += quitarEspacios(primero.getColumna());
                        }
                        cad += "->";
                        if (primero.getFila().equals("/")) {
                            cad += "CP1";
                        } else {

                            cad += quitarEspacios(primero.getSiguiente().getFila());
                        }
                        if (primero.getColumna().equals("/")) {
                            cad += "FP1";
                        } else {
                            cad += quitarEspacios(primero.getSiguiente().getColumna());
                        }
                        cad += "[dir=both,constraint = false];\n";;
                        cad += "\n";
                    }
                    primero = primero.getSiguiente();
                }
            }
            enlace = enlace.getAbajo();
        }*/
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
                                cad += quitarEspacios(primero.getFila());
                                cad += quitarEspacios(primero.getColumna());
                            cad += "->";
                                cad += quitarEspacios(primero.getAbajo().getFila());
                          
                                cad +=quitarEspacios( primero.getAbajo().getColumna());
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
}
