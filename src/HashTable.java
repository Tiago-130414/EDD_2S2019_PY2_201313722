
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author santi
 */
public class HashTable {

    //creo el array
    NodoHashTable[] usuarios;
    NodoHashTable[] us;
    int tamano;
    public HashTable() {
        //reservo la memoria
        this.usuarios = new NodoHashTable[7];
        //inicializo el vector
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = new NodoHashTable("-1", "-1", "-1");
        }
        funcionHash(usuarios, "Admin", "Admin");

        us = new NodoHashTable[1000];
        for (int i = 0; i < us.length; i++) {
            us[i] = new NodoHashTable(" ", " ", " ");
        }
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
        String cont = retornarHash(contra);
        //System.out.println(calcularUtilizacion(arr));
        if (arr[ubi].getUsuario().equals("-1")) {
            arr[ubi].setUsuario(usu);
            arr[ubi].setContrasena(cont);
            arr[ubi].setTimestamp(timestamp());
            
        } else {
            while (true) {
                newUbi = ubi + squaring(n);
                if (newUbi < arr.length) {
                    if (arr[newUbi].getUsuario().equals("-1")) {
                        arr[newUbi].setUsuario(usu);
                        arr[newUbi].setContrasena(cont);
                        arr[newUbi].setTimestamp(timestamp());
                       
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

        }
        mostrar(usuarios);
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
            usuarios[i] = new NodoHashTable("-1", "-1", "-1");
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].getUsuario() != "-1") {
                insertarDatosRedimension(usuarios, temp[i].getUsuario(), temp[i].getContrasena(), temp[i].getTimestamp());
            }
        }
    }

    public void insertarDatosRedimension(NodoHashTable[] arr, String usu, String contra, String tmstmp) {
        int ubi = hashDivision(usu, arr.length);
        int newUbi;
        int n = 1;
        if (arr[ubi].getUsuario().equals("-1")) {
            arr[ubi].setUsuario(usu);
            arr[ubi].setContrasena(contra);
            arr[ubi].setTimestamp(tmstmp);
        } else {
            while (true) {
                newUbi = ubi + squaring(n);
                if (newUbi < arr.length) {
                    if (arr[newUbi].getUsuario().equals("-1")) {
                        arr[newUbi].setUsuario(usu);
                        arr[newUbi].setContrasena(contra);
                        arr[newUbi].setTimestamp(tmstmp);
                        break;
                    }
                } else {
                    n = 0;
                }
                n++;
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
            System.out.println(hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e);
        }
        return hash;
    }

    public String recorrerTabla(NodoHashTable[] arr) {
        String cad = "\t\t\t<tr><td>Indice</td><td>Usuario</td><td>Contraseña</td><td>Timestamp</td></tr>\n";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getUsuario() != "-1") {
                cad += "\t\t\t<tr><td>" + Integer.toString(i) + "</td><td>" + arr[i].getUsuario() + "</td><td>" + arr[i].getContrasena() + "</td><td>" + arr[i].getTimestamp() + "</td></tr>\n";
            } else {
                cad += "\t\t\t<tr><td>" + Integer.toString(i) + "</td><td>     </td><td>     </td><td>     </td></tr>\n";
            }
        }
        return cad;
    }

    public void graficar(NodoHashTable[] arr) {
        File f;
        f = new File("C:\\Graficas_Proyecto2\\HashTable.dot");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            w.write("digraph hashT{\n");
            w.write("\tlabelloc=t;\n");
            w.write("\tnode[shape=plaintext];\n");
            w.write("\tsubgraph cluster_0{\n");
            w.write("\t\tstyle=filled;\n");
            w.write("\t\tcolor=lightgrey;\n");
            w.write("\tstruct5[label=<<TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n");
            w.write(recorrerTabla(arr));
            w.write("\t\t</TABLE>>style = filled,fillcolor = \"orange:red\",fontname = \"helvetica\"];\n");
            w.write("\t}\n");
            w.write("\tlabel=\"HashTable Usuarios\";\n");
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
                "-Tjpg",
                "C:\\Graficas_Proyecto2\\HashTable.dot",
                "-o",
                "C:\\Graficas_Proyecto2\\HashTable.jpg"};
            Runtime.getRuntime().exec(cmd);
            Desktop.getDesktop().open(new File("C:\\Graficas_Proyecto2\\HashTable.jpg"));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public String timestamp() {
        String tmstamp;
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        tmstamp = hourdateFormat.format(date);
        return tmstamp;
    }

    public NodoHashTable[] leerCsv(String nombreAr) {
        String csvFile = nombreAr.toString();
        BufferedReader br = null;
        String line;
        int cont = 0;
        NodoHashTable[] arr = {};
        String separador = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(separador);
                if (!(datos[0].toLowerCase()).equals("usuario") && !(datos[1].toLowerCase()).equals("password")) {
                    arr = cargarUsuarios(datos[0], datos[1]);
                    //funcionHash(usuarios,datos[0],datos[1]);
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
        return arr;
    }

    public boolean existeUsuario(String usuario, String contrasena) {
        boolean existe = false;
        String has = retornarHash(contrasena);
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].usuario.equals(usuario) && usuarios[i].contrasena.equals(has)) {
                System.out.println("encontre");
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean existeU(String usuario) {
        boolean existe = false;
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].usuario.equals(usuario)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public NodoHashTable[] cargarUsuarios(String usuario, String contrasena) {
        int longC = contrasena.length();
        if (existeU(usuario) == false) {
            //System.out.println(existeU(usuario));

            if (longC < 8) {
                for (int i = 0; i < us.length; i++) {
                    if (us[i].usuario.equals(" ")) {
                        us[i].setUsuario(usuario);
                        us[i].setContrasena("Contraseña menor a 8 digitos");
                        break;
                    }
                }
            } else {
                
                funcionHash(this.usuarios, usuario, contrasena);
                tamano++;
            }
        } else {
            for (int i = 0; i < us.length; i++) {
                if (us[i].usuario.equals(" ")) {
                    us[i].setUsuario(usuario);
                    us[i].setContrasena("Usuario ya existe");
                    break;
                }
                
            }

        }
        return us;
    }

    public void registrarUsuario(String usuario,String contrasena){
        funcionHash(this.usuarios, usuario, retornarHash(contrasena));
    }


}