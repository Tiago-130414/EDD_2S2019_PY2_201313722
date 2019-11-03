
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author santi
 */
public class HashTable {

    //creo el array
    NodoHashTable[] usuarios;

    public HashTable() {
        //reservo la memoria
        this.usuarios = new NodoHashTable[7];
        //inicializo el vector
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = new NodoHashTable("-1", "-1");
        }
        System.out.println(usuarios.length);

        usuarios[0].setUsuario("Admin");
        usuarios[0].setContrasena("Admin");

    }

    public void ingresarVal() {
        funcionHash(usuarios, "Antonio", "Admin");
        funcionHash(usuarios, "Anthony", "Admin");
        funcionHash(usuarios, "Andre", "Admin");
        funcionHash(usuarios, "Khrishaa", "Admin");
        funcionHash(usuarios, "Gilberto", "Admin");

    }

    public void funcionHash(NodoHashTable[] arr, String usu, String contra) {
        int ubi = hashDivision(usu, arr.length);
        int newUbi;
        int n = 1;
        String cont;
        //System.out.println(calcularUtilizacion(arr));
        if (arr[ubi].getUsuario().equals("-1")) {
            arr[ubi].setUsuario(usu);
            arr[ubi].setContrasena(contra);
        } else {
            while (true) {
                newUbi = ubi + squaring(n);
                if (newUbi < arr.length) {
                    if (arr[newUbi].getUsuario().equals("-1")) {
                        arr[newUbi].setUsuario(usu);
                        cont = retornarHash(contra);
                        arr[newUbi].setContrasena(cont);
                        break;
                    }
                } else {
                    n = 0;
                }
                n++;
            }
        }
        if (calcularUtilizacion(arr)) {
            redimensionar(arr);
            System.out.println("-----------------");
            System.out.println("REDIMENSIONADO");
            System.out.println("-----------------");
            mostrar(arr);
        }
    }

    public int hashDivision(String cad, int tamArr) {
        int pos;
        int llave = devolverSumaAscii(cad);
        pos = llave % tamArr;
        //System.out.println(cad + "pos arr" + pos);
        return pos;
    }

    public int devolverSumaAscii(String cadena) {
        int sum = 0;
        for (int i = 0; i < cadena.length(); i++) {
            sum += (int) cadena.charAt(i);
        }
        return sum;
    }

    public int squaring(int num) {
        int cuadrado;
        cuadrado = (int) Math.pow(2, num);
        return cuadrado;
    }

    public void mostrar(NodoHashTable[] arr) {
        System.out.println("Sin colisiones");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getUsuario() + "\tposicion en tabla:\t" + i);
        }
    }

    public boolean calcularUtilizacion(NodoHashTable[] arr) {
        int val = 0;
        int porc;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getUsuario() != "-1") {
                val++;
            }
        }
        porc = (int) (arr.length * 0.50);
        if (val == porc) {
            return true;
        } else {

            return false;
        }

    }

    public void redimensionar(NodoHashTable[] arr) {
        NodoHashTable[] temp = arr;
        int nuevoTam = numeroPrimoSiguiente(arr.length);

        this.usuarios = new NodoHashTable[nuevoTam];
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = new NodoHashTable("-1", "-1");
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].getUsuario() != "-1") {
                funcionHash(usuarios, temp[i].getUsuario(), temp[i].getContrasena());
            }
        }
    }

    public int numeroPrimoSiguiente(int num) {
        int numeroP = num;
        while (true) {
            numeroP = numeroP + 1;
            if (esPrimo(numeroP)) {
                break;
            }
        }
        return numeroP;
    }

    public boolean esPrimo(int numero) {
        int m = 2;
        boolean bandera = true;
        while ((bandera) && (m < numero)) {
            if ((numero % m) == 0) {
                bandera = false;
            } else {
                m = m + 1;
            }
        }
        return bandera;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public String retornarHash(String contrasena) {
        String hash = " ";
        try {
            hash = toHexString(getSHA(contrasena));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e);
        }
        return hash;
    }

    public void graficar() {
        File f;
        f = new File("C:\\Graficas_Proyecto2\\HashTable.dot");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            w.write("digraph hashT\n{");
            w.write("\tlabelloc=t;\n");
            w.write("\tsubgraph cluster_0{\n");
            w.write("\t\tstyle=filled;\n");
            w.write("\t\tcolor=lightgrey;\n");
            w.write("\t\t<TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n");
            //falta obtener los datos y construir la tabla mediante HTML

            //w.write(listarNodos(raiz));
            //w.write(apuntarNodos(raiz));
            w.write("\t\tlabel=\"HashTable Usuarios\";\n");
            w.write("\t}\n");
            w.write("}");
            wr.close();
            bw.close();
        } catch (IOException e) {
        }
    }

    public void ejecutar() {
        try {
            String[] cmd = {"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe",
                "-Tpng",
                "C:\\Graficas_Proyecto2\\HashTable.dot",
                "-o",
                "C:\\Graficas_Proyecto2\\HashTable.png"};
            Runtime.getRuntime().exec(cmd);
            Desktop.getDesktop().open(new File("C:\\Graficas_Proyecto2\\HashTable.png"));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
