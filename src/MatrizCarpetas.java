
/**
 *
 * @author santi
 */
public class MatrizCarpetas {

    NodoMatriz columnas;
    NodoMatriz filas;

    public MatrizCarpetas() {
        columnas = new NodoMatriz(0, 0, " ", " ");
        filas = new NodoMatriz(0, 0, " ", " ");
    }

    public void insertarNodo(int fil, int col, String dat, String fech) {
        NodoMatriz nuevoCol = new NodoMatriz(0, col, "Columna", "123");
        enlazarColumnas(nuevoCol, col);
        NodoMatriz nuevoFil = new NodoMatriz(fil, 0, "Fila", "1234");
        enlazarFilas(nuevoFil, fil);

        NodoMatriz nuevo = new NodoMatriz(fil, col, dat, fech);
        enlazarContenido(fil, col, nuevo);
        /*System.out.println("aqui imprimo r");
        enlazarColumnas(nuevoFil, nuevo, col);
        enlazarFilas(nuevoCol, nuevo, fil);*/
    }

    public void enlazarColumnas(NodoMatriz nuevoCol, int columna) {
        NodoMatriz temporalColumnas = this.columnas;
        if (temporalColumnas == null) {
            System.out.println("r");
            this.columnas = nuevoCol;
        } else {
            while ((temporalColumnas != null) && (temporalColumnas.siguiente != null) && (temporalColumnas.siguiente.columna <= columna)) {
                temporalColumnas = temporalColumnas.siguiente;
            }
            if (temporalColumnas.columna < columna) {
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
            } else if (temporalColumnas.columna > columna) {
                nuevoCol.siguiente = temporalColumnas;
                temporalColumnas.anterior = nuevoCol;
                this.columnas = nuevoCol;
            } else {
                nuevoCol = temporalColumnas;
            }
        }
    }

    public void enlazarFilas(NodoMatriz nuevoFil, int fila) {
        NodoMatriz temporalFilas = this.filas;
        if (temporalFilas == null) {
            this.filas = nuevoFil;
        } else {
            while ((temporalFilas != null) && (temporalFilas.abajo != null) && (temporalFilas.abajo.fila <= fila)) {
                temporalFilas = temporalFilas.abajo;
            }
            if (temporalFilas.fila < fila) {
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
            } else if (temporalFilas.fila > fila) {
                nuevoFil.abajo = temporalFilas;
                temporalFilas.arriba = nuevoFil;
                this.filas = nuevoFil;
            } else {
                nuevoFil = temporalFilas;
            }

        }
    }

    public void enlazarContenido(int fil, int col, NodoMatriz contenido) {
        NodoMatriz temporalC = this.columnas;
        NodoMatriz temporalF = this.filas;

        if (temporalC != null) {
            while ((temporalC != null) && (temporalC.siguiente != null) && (temporalC.getColumna() != col)) {
                temporalC = temporalC.siguiente;
            }
            if (temporalC.abajo != null) {
                NodoMatriz tempCB = temporalC.abajo;
                while ((tempCB != null) && (tempCB.abajo != null) && (tempCB.getFila() != fil)) {
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

        if (temporalF!=null) {
            while ((temporalF != null) && (temporalF.abajo != null) && (temporalF.getFila() != fil)) {
                temporalF = temporalF.abajo;
            }
            if (temporalF.siguiente!=null) {
                NodoMatriz tempCF = temporalF.siguiente;
                while ((tempCF!=null)&&(tempCF.siguiente!=null)&&(tempCF.getColumna()!=col)) {                    
                    tempCF = tempCF.siguiente;
                }
                if (tempCF.siguiente!=null) {
                    NodoMatriz tempSigF = tempCF.siguiente;
                    contenido.siguiente = tempSigF;
                    contenido.anterior = tempCF;
                    tempSigF.anterior = contenido;
                    tempCF.siguiente = contenido;
                }else{
                    System.out.println("k");
                    tempCF.siguiente = contenido;
                    contenido.anterior = tempCF;
                }
            }else{
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
                    System.out.println(" F: "+tempInt.getFila() +" C: "+ tempInt.getColumna()+" Nom: " +tempInt.getNombre());
                    tempInt = tempInt.abajo;
                }
                temp = temp.siguiente;
            }
        } else {
            System.out.println("vacio");
        }
    }
    
    public  void mostrarF(){
        NodoMatriz temp = this.filas;
        if (temp != null) {
            while (temp != null) {
                NodoMatriz tempInt = temp.siguiente;
                //System.out.println(temp.getColumna());
                while (tempInt != null) {
                    System.out.println("F: "+tempInt.getFila() +" C: "+ tempInt.getColumna()+" Nom: " +tempInt.getNombre());
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
}
