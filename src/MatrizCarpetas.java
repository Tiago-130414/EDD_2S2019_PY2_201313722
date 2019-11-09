
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
            System.out.println("r");
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
            }

        }
    }

    public void enlazarContenido(String fil, String col, NodoMatriz contenido) {
        NodoMatriz temporalC = this.columnas;
        NodoMatriz temporalF = this.filas;

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
                    System.out.println("R");
                    tempCB.abajo = contenido;
                    contenido.arriba = tempCB;
                }
            } else {
                System.out.println("F");
                temporalC.abajo = contenido;
                contenido.arriba = temporalC;
            }
        }

        if (temporalF != null) {
            while ((temporalF != null) && (temporalF.abajo != null) && (!(temporalF.getFila()).equals(fil))) {
                //while ((temporalF != null) && (temporalF.abajo != null) && (temporalF.getFila() != fil))
                temporalF = temporalF.abajo;
            }
            if (temporalF.siguiente != null) {
                NodoMatriz tempCF = temporalF.siguiente;
                while ((tempCF != null) && (tempCF.siguiente != null) && (!(tempCF.getColumna()).equals(col))) {
                    //while ((tempCF!=null)&&(tempCF.siguiente!=null)&&(tempCF.getColumna()!=col))
                    tempCF = tempCF.siguiente;
                }
                if (tempCF.siguiente != null) {
                    NodoMatriz tempSigF = tempCF.siguiente;
                    contenido.siguiente = tempSigF;
                    contenido.anterior = tempCF;
                    tempSigF.anterior = contenido;
                    tempCF.siguiente = contenido;
                } else {
                    System.out.println("k");
                    tempCF.siguiente = contenido;
                    contenido.anterior = tempCF;
                }
            } else {
                System.out.println("L");
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
        if (temp != null) {
            while (temp != null) {
                NodoMatriz tempInt = temp.siguiente;
                //System.out.println(temp.getColumna());
                while (tempInt != null) {
                    System.out.println("F: " + tempInt.getFila() + " C: " + tempInt.getColumna() + " Nom: " + tempInt.getNombre());
                    tempInt = tempInt.siguiente;
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
            w.write(crearNodosFilaGuia());
            w.write(crearNodosColumnaGuia());
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
                    cad += "FP";
                }else{
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
                if (filaA.getFila().equals("/")){
                    cad += "FP";
                }else{
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
        if ((tempC.getColumna()).equals(" ") && (tempF.getFila()).equals(" ")) {

            if (tempC.getSiguiente().getColumna().equals("/")) {
                primC = "CP";
            }else{
                primC = "C" + quitarEspacios(tempC.siguiente.getColumna());
            }
            
            if (tempF.getAbajo().getFila().equals("/")) {
                primF = "FP";
            }else{
                primF = "F" + quitarEspacios(tempF.abajo.getFila());
            }
        }
        while (tempC != null) {
            if (!(tempC.getColumna()).equals(" ")) {
                if (tempC.getColumna().equals("/")) {
                    cad +="CP";
                }else{
                     cad += "C" + quitarEspacios(tempC.getColumna());
                }
                cad += "[label=\"";
                cad += tempC.getColumna() + "\"";
                cad += "width = 1.0 style = filled,shape=\"rectangle\", fillcolor = \"yellow\", group =\"";
                cad += quitarEspacios(tempC.getColumna()) + "\"];\n";
            }
            tempC = tempC.getSiguiente();
        }
        tempC = this.columnas;
        while (tempC != null) {
            if (tempC.getSiguiente() != null && !(tempC.getColumna()).equals(" ")) {
                if (tempC.getColumna().equals("/")) {
                    cad+="CP";
                }else{
                    cad += "C" + quitarEspacios(tempC.getColumna());
                }
                
                cad += "->";
                cad += "C" + quitarEspacios(tempC.siguiente.getColumna());
                cad += "[dir=both];\n";
            }

            tempC = tempC.getSiguiente();
        }
        tempC = this.columnas;
        cad += "{rank = same;Matriz;";
        while (tempC != null) {
            if (!(tempC.getColumna()).equals(" ")) {
                if (tempC.getColumna().equals("/")) {
                cad +="CP";    
                }else{
                    cad += "C" + quitarEspacios(tempC.getColumna());
                
                }
                if (tempC.getSiguiente() != null) {
                    cad += ";";
                }
            }
            tempC = tempC.getSiguiente();
        }
        cad += "}\n";
        cad += "Matriz ->" + primC + ";\n";
        cad += "Matriz ->" + primF + ";\n";

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
            Desktop.getDesktop().open(new File("C:\\Graficas_Proyecto2\\Matriz.png"));
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
