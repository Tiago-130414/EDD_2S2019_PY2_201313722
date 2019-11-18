
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.DEFAULT_OPTION;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santi
 */
public class Principal extends javax.swing.JFrame {

    NodoMatriz nodoMatriz;
    visorImagen img;

    /*Este codigo no se modifica*/
    private void propiedadesTabla() {
        tablaArchivos.setDefaultRenderer(Object.class, new imgTabla());
        String titulos[] = {" ", "Nombre"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        if (Login.aux != null) {
            NodoMatriz aux = Login.aux.filas;
            Login.aux.mostrar();
            if (aux != null) {
                while (aux != null) {
                    NodoMatriz tempInt = aux.siguiente;
                    while (tempInt != null) {
                        if (verificarSlash(aux.fila) && !aux.getFila().equals(" ")) {
                            tm.addRow(new Object[]{labelCarpeta, "Raiz"});
                        } else if (!aux.getFila().equals(" ")) {
                            tm.addRow(new Object[]{labelCarpeta2, aux.getFila()});
                        }
                        tempInt = tempInt.siguiente;
                    }
                    aux = aux.abajo;
                }
            } else {
                System.out.println("vacio");
            }
        }
        tablaArchivos.setRowHeight(130);
        tablaArchivos.setModel(tm);
    }

    /*LABEL PARA CARPETAS*/
    //tm.addRow(new Object[]{labelCarpeta, "Raiz2"});
    String path3 = "/Imagenes/carpetaR.png";
    URL url3 = this.getClass().getResource(path3);
    ImageIcon icon3 = new ImageIcon(url3);
    JLabel labelCarpeta = new JLabel(icon3);

    /*LABEL PARA CARPETAS*/
    //tm.addRow(new Object[]{labelCarpeta, "Raiz2"});
    String path = "/Imagenes/carpeta.png";
    URL url = this.getClass().getResource(path);
    ImageIcon icon = new ImageIcon(url);
    JLabel labelCarpeta2 = new JLabel(icon);

    /*LABEL PARA ARCHIVOS*/
    //tm.addRow(new Object[]{labelArchivo, "Raiz"});
    String path2 = "/Imagenes/archivo.png";
    URL url2 = this.getClass().getResource(path2);
    ImageIcon icon2 = new ImageIcon(url2);
    JLabel labelArchivo = new JLabel(icon2);

    public Principal() {
        initComponents();
        propiedadesTabla();
        this.setLocationRelativeTo(null);

    }

    /*Este codigo no se modifica*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        crearCarpeta = new javax.swing.JButton();
        entrarCarpeta = new javax.swing.JButton();
        regresarCarpeta = new javax.swing.JButton();
        panelArchivos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArchivos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        crearArchivo = new javax.swing.JButton();
        modificarArchivo = new javax.swing.JButton();
        eliminarArchivo = new javax.swing.JButton();
        modificarNombre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        UsuarioEnLinea = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        mostrarImg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Administrar = new javax.swing.JMenu();
        CargaUsuarios = new javax.swing.JMenu();
        CargaA = new javax.swing.JMenu();
        compartirArchivo = new javax.swing.JMenu();
        descargar = new javax.swing.JMenu();
        cerrarSesion = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        hashTable = new javax.swing.JMenu();
        graficaPila = new javax.swing.JMenu();
        grafiaMatriz = new javax.swing.JMenu();
        graficaArbolAVL = new javax.swing.JMenu();
        graficaGrafo = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("USAC FILE DRIVE");
        setIconImage(getIconImage());
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Carpetas"));
        jPanel1.setFocusable(false);

        crearCarpeta.setText("Crear");
        crearCarpeta.setFocusable(false);
        crearCarpeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearCarpetaMouseClicked(evt);
            }
        });
        crearCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearCarpetaActionPerformed(evt);
            }
        });

        entrarCarpeta.setText("Entrar");
        entrarCarpeta.setFocusable(false);
        entrarCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarCarpetaActionPerformed(evt);
            }
        });

        regresarCarpeta.setText("Subir");
        regresarCarpeta.setFocusable(false);
        regresarCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarCarpetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crearCarpeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(entrarCarpeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(regresarCarpeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(crearCarpeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entrarCarpeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regresarCarpeta)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        panelArchivos.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivos"));
        panelArchivos.setFocusable(false);

        tablaArchivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nombre"
            }
        ));
        tablaArchivos.setEnabled(false);
        jScrollPane1.setViewportView(tablaArchivos);

        javax.swing.GroupLayout panelArchivosLayout = new javax.swing.GroupLayout(panelArchivos);
        panelArchivos.setLayout(panelArchivosLayout);
        panelArchivosLayout.setHorizontalGroup(
            panelArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelArchivosLayout.setVerticalGroup(
            panelArchivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArchivosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivos"));

        crearArchivo.setText("Crear");
        crearArchivo.setFocusable(false);
        crearArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearArchivoActionPerformed(evt);
            }
        });

        modificarArchivo.setText("Modificar Contenido");
        modificarArchivo.setFocusable(false);
        modificarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarArchivoActionPerformed(evt);
            }
        });

        eliminarArchivo.setText("Eliminar");
        eliminarArchivo.setFocusable(false);
        eliminarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarArchivoActionPerformed(evt);
            }
        });

        modificarNombre.setText("Modificar Nombre");
        modificarNombre.setFocusable(false);
        modificarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarNombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crearArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modificarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modificarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(crearArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modificarArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modificarNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminarArchivo)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Online");
        jLabel1.setEnabled(false);

        UsuarioEnLinea.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Visor de Imagenes"));

        scroll.setOpaque(false);

        mostrarImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scroll.setViewportView(mostrarImg);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll)
                .addContainerGap())
        );

        Administrar.setText("Administrar");
        Administrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdministrarMouseClicked(evt);
            }
        });

        CargaUsuarios.setText("Cargar Usuarios");
        CargaUsuarios.setEnabled(false);
        CargaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CargaUsuariosMouseClicked(evt);
            }
        });
        Administrar.add(CargaUsuarios);

        CargaA.setText("Cargar Archivos");
        CargaA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CargaAMouseClicked(evt);
            }
        });
        Administrar.add(CargaA);

        compartirArchivo.setText("Compartir Archivo");
        compartirArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compartirArchivoMouseClicked(evt);
            }
        });
        Administrar.add(compartirArchivo);

        descargar.setText("Descargar Archivo");
        descargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descargarMouseClicked(evt);
            }
        });
        Administrar.add(descargar);

        cerrarSesion.setText("Cerrar Sesion");
        cerrarSesion.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                cerrarSesionMenuSelected(evt);
            }
        });
        cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarSesionMouseClicked(evt);
            }
        });
        Administrar.add(cerrarSesion);

        jMenuBar1.add(Administrar);

        jMenu1.setText("Reportes");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        hashTable.setText("Grafica Usuarios");
        hashTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hashTableMouseClicked(evt);
            }
        });
        jMenu1.add(hashTable);

        graficaPila.setText("Grafica Bitacora");
        graficaPila.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graficaPilaMouseClicked(evt);
            }
        });
        jMenu1.add(graficaPila);

        grafiaMatriz.setText("Grafica Carpetas");
        grafiaMatriz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grafiaMatrizMouseClicked(evt);
            }
        });
        jMenu1.add(grafiaMatriz);

        graficaArbolAVL.setText("Grafica Archivos");
        graficaArbolAVL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graficaArbolAVLMouseClicked(evt);
            }
        });
        jMenu1.add(graficaArbolAVL);

        graficaGrafo.setText("Grafica Grafo");
        graficaGrafo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graficaGrafoMouseClicked(evt);
            }
        });
        jMenu1.add(graficaGrafo);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UsuarioEnLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(panelArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(UsuarioEnLinea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelArchivos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearCarpetaActionPerformed

    }//GEN-LAST:event_crearCarpetaActionPerformed

    private void cerrarSesionMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_cerrarSesionMenuSelected

    }//GEN-LAST:event_cerrarSesionMenuSelected

    /*Modificar cierre de sesion*/

    private void cerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionMouseClicked
        // TODO add your handling code here:
        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("Usuario Salio de Sistema", Usu);
        if (Login.temp != null) {
            Login.usuarios.usuarioHash(UsuarioEnLinea.getText(), Login.temp, Login.aux);
        } else {
            System.out.println("soy null");
        }
        Login log = new Login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarSesionMouseClicked
    /*Este metodo no se modifica*/
    private void CargaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargaUsuariosMouseClicked
        if (CargaUsuarios.isEnabled()) {
            CargaUsuarios cargar = new CargaUsuarios();
            cargar.setVisible(true);
        } else {
            System.out.println("caiste perro");
        }
    }//GEN-LAST:event_CargaUsuariosMouseClicked

    private void AdministrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdministrarMouseClicked

    }//GEN-LAST:event_AdministrarMouseClicked
    /*---------------------------*/
    private void CargaAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargaAMouseClicked
        /*CARGAR ARCHIVOS ARBOL*/
        String nombreArchivo = JOptionPane.showInputDialog("Ingrese nombre de archivo: ");
        leerCsv(nombreArchivo);
        tablaArchivos.setDefaultRenderer(Object.class, new imgTabla());
        String titulos[] = {" ", "Nombre", "Contenido"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        if (nodoMatriz != null) {
            propiedadesArbol(nodoMatriz.arbol.raiz, tm);
            Login.temp.matriz.guardarNodo(nodoMatriz.getFila(), nodoMatriz.getColumna(), nodoMatriz);
        }
        tablaArchivos.setRowHeight(130);
        tablaArchivos.setModel(tm);
        /*String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("Agrego al sistema CSV de archivos ", Usu);*/
    }//GEN-LAST:event_CargaAMouseClicked

    private void graficaPilaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graficaPilaMouseClicked
        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("Grafico bitacora ", Usu);

        Login.b.graficarBitacora();
        espera();
        graficarBit();

    }//GEN-LAST:event_graficaPilaMouseClicked

    private void crearCarpetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearCarpetaMouseClicked
        String carpetaPadre;
        String carpetaHijo;
        String nomb;
        carpetaPadre = JOptionPane.showInputDialog("Ingrese la carpeta padre: ");
        carpetaHijo = JOptionPane.showInputDialog("Ingrese la carpeta hijo: ");
        if (carpetaPadre != null && carpetaHijo != null) {
            if (carpetaPadre.equals("/") || carpetaHijo.equals("/")) {
                nomb = "Raiz" + "/" + carpetaHijo;
            } else {

                nomb = carpetaPadre + "/" + carpetaHijo;
            }
            if (Login.aux != null) {
                Login.aux.insertarNodo(carpetaPadre, carpetaHijo, nomb, "horaFecha");

            }
            propiedadesTabla();
        }

        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("Creo Carpeta ", Usu);

    }//GEN-LAST:event_crearCarpetaMouseClicked

    private void grafiaMatrizMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grafiaMatrizMouseClicked
        if (Login.aux != null) {
            Login.aux.graficarBitacora();
            espera();
            graficaMt();
        } else {
            System.out.println("matriz vacia");
        }

        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("Grafico matriz de carpetas", Usu);
    }//GEN-LAST:event_grafiaMatrizMouseClicked
    /*no se modifica*/
    private void entrarCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrarCarpetaActionPerformed
        String fil = JOptionPane.showInputDialog("Ingrese Carpeta Padre: ");
        String col = JOptionPane.showInputDialog("Ingrese Carpeta Hijo: ");
        nodoMatriz = Login.aux.retornarArbol(fil, col);
        if (nodoMatriz != null) {
            panelArchivos.setBorder(javax.swing.BorderFactory.createTitledBorder(nodoMatriz.nombre));
        }
        tablaArchivos.setDefaultRenderer(Object.class, new imgTabla());
        String titulos[] = {" ", "Nombre", "Contenido"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        if (nodoMatriz != null) {
            propiedadesArbol(nodoMatriz.arbol.raiz, tm);
            System.out.println("si lo hice xd");
        }
        tablaArchivos.setRowHeight(130);
        tablaArchivos.setModel(tm);
    }//GEN-LAST:event_entrarCarpetaActionPerformed

    private void crearArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearArchivoActionPerformed
        String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre de archivo: ");
        String contenidoArchivo = JOptionPane.showInputDialog("Ingrese el contenido del archivo");
        tablaArchivos.setDefaultRenderer(Object.class, new imgTabla());
        String titulos[] = {" ", "Nombre", "Contenido"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        if (nombreArchivo != null && contenidoArchivo != null) {
            if (nodoMatriz != null) {
                nodoMatriz.arbol.insertarDato(nombreArchivo, contenidoArchivo, UsuarioEnLinea.getText());
                Login.aux.guardarNodo(nodoMatriz.getFila(), nodoMatriz.getColumna(), nodoMatriz);
                propiedadesArbol(nodoMatriz.arbol.raiz, tm);
                System.out.println("si lo hice xd");
            }
        }
        tablaArchivos.setRowHeight(130);
        tablaArchivos.setModel(tm);

        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("creo archivos ", Usu);

    }//GEN-LAST:event_crearArchivoActionPerformed

    private void graficaArbolAVLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graficaArbolAVLMouseClicked
        String rt = "C:\\Graficas_Proyecto2\\ArbolAVL_" + UsuarioEnLinea.getText() + ".png";
        if (nodoMatriz != null) {
            nodoMatriz.arbol.graficar(UsuarioEnLinea.getText(), UsuarioEnLinea.getText());
            espera();
            graficaR(rt);
        }

        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("Grafico arbol de archivos ", Usu);
    }//GEN-LAST:event_graficaArbolAVLMouseClicked

    private void regresarCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarCarpetaActionPerformed
        propiedadesTabla();
    }//GEN-LAST:event_regresarCarpetaActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked

    }//GEN-LAST:event_jMenu1MouseClicked

    private void eliminarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarArchivoActionPerformed
        String eliminar = JOptionPane.showInputDialog("Ingrese nombre de archivo a eliminar: ");

        tablaArchivos.setDefaultRenderer(Object.class, new imgTabla());
        String titulos[] = {" ", "Nombre", "Contenido"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        if (nodoMatriz != null) {
            if (eliminar != null) {
                nodoMatriz.arbol.eliminar(eliminar);
                propiedadesArbol(nodoMatriz.arbol.raiz, tm);
                System.out.println("elimine");
            }
        }
        tablaArchivos.setRowHeight(130);
        tablaArchivos.setModel(tm);

        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("elimino archivos ", Usu);

    }//GEN-LAST:event_eliminarArchivoActionPerformed

    private void modificarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarArchivoActionPerformed
        /*Modificar Archivo*/
        String nomb = JOptionPane.showInputDialog("Ingrese nombre de archivo: ");
        String cont = JOptionPane.showInputDialog("Ingrese nuevo contenido: ");
        NodoArbolAVL arbolTemp;
        tablaArchivos.setDefaultRenderer(Object.class, new imgTabla());
        String titulos[] = {" ", "Nombre", "Contenido"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        if (nodoMatriz != null) {
            arbolTemp = nodoMatriz.arbol.buscar(nomb, nodoMatriz.arbol.raiz);
            if (arbolTemp != null) {
                arbolTemp.contenido = cont;
                propiedadesArbol(nodoMatriz.arbol.raiz, tm);
            } else {
                JOptionPane.showMessageDialog(null, "Archivo buscado no encontrado");
            }
        }
        tablaArchivos.setRowHeight(130);
        tablaArchivos.setModel(tm);

        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("modifico contenido archivos ", Usu);

    }//GEN-LAST:event_modificarArchivoActionPerformed

    private void modificarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarNombreActionPerformed
        /*modificar nombre archivo*/
        NodoArbolAVL temp;
        String eliminar = JOptionPane.showInputDialog("Ingrese nombre de archivo a modificar nombre: ");
        String nuevoNom = JOptionPane.showInputDialog("Ingrese nombre nuevo del archivo: ");
        tablaArchivos.setDefaultRenderer(Object.class, new imgTabla());
        String titulos[] = {" ", "Nombre", "Contenido"};
        DefaultTableModel tm = new DefaultTableModel(null, titulos);
        if (nodoMatriz != null) {
            if (eliminar != null) {
                temp = nodoMatriz.arbol.buscar(eliminar, nodoMatriz.arbol.raiz);
                if (temp != null) {
                    String cont = temp.contenido;
                    nodoMatriz.arbol.eliminar(eliminar);
                    nodoMatriz.arbol.insertarDato(nuevoNom, cont, UsuarioEnLinea.getText());
                    propiedadesArbol(nodoMatriz.arbol.raiz, tm);
                    System.out.println("modifique nombre archivo");
                }
            }
        }
        tablaArchivos.setRowHeight(130);
        tablaArchivos.setModel(tm);

        String Usu = UsuarioEnLinea.getText();
        Login.b.insertarBitacora("modifico nombre de archivo ", Usu);

    }//GEN-LAST:event_modificarNombreActionPerformed

    private void compartirArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compartirArchivoMouseClicked
        /*COMPARTIR ARCHIVOS ENTRE USUARIOS*/
        String carpetaPadre = JOptionPane.showInputDialog("Ingrese carpeta Padre: ");
        String carpetaHijo = JOptionPane.showInputDialog("Ingrese carpeta Hijo: ");
        String usuario = JOptionPane.showInputDialog("Ingrese nombre de usuario a compartir: ");
        String archivo = JOptionPane.showInputDialog("Ingrese nombre de archivo a compartir: ");
        String contenidoM;
        String contenidoArchivo;
        String prop;
        NodoMatriz tempk;
        NodoArbolAVL tempAr;
        if (Login.usuarios.existeU(usuario)) {
            tempk = Login.aux.retornarArbol(carpetaPadre, carpetaHijo);
            tempAr = tempk.arbol.buscar(archivo, tempk.arbol.raiz);
            if (tempAr != null) {
                contenidoM = tempk.nombre;
                contenidoArchivo = tempAr.contenido;
                prop = tempAr.propietario;
                //public void insertarVal(*String usu,*String fila,*String col,*String cont,*String nombre,String contenido,String prop)
                Login.usuarios.insertarVal(usuario, carpetaPadre, carpetaHijo, contenidoM, archivo, contenidoArchivo, prop);
            } else {
                System.out.println("No encontre archivo");
            }
        } else {
            JOptionPane.showConfirmDialog(null, "Usuario no existe en registro", "USAC FILE DRIVE", DEFAULT_OPTION);
        }


    }//GEN-LAST:event_compartirArchivoMouseClicked

    private void hashTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hashTableMouseClicked
        Login.usuarios.graficar(Login.usuarios.usuarios);
        espera();
        graficaR("C:\\Graficas_Proyecto2\\HashTable.jpg");
    }//GEN-LAST:event_hashTableMouseClicked

    private void graficaGrafoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graficaGrafoMouseClicked
        Login.aux.graficarGrafo();
        espera();
        graficaR("C:\\Graficas_Proyecto2\\grafo.png");
    }//GEN-LAST:event_graficaGrafoMouseClicked

    private void descargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descargarMouseClicked
        /*DESCARGA DE ARCHIVOS*/
        String nom = JOptionPane.showInputDialog("Ingrese el nombre del archivo: ");
        if (nom != null) {
            NodoArbolAVL aux = nodoMatriz.arbol.buscar(nom, nodoMatriz.arbol.raiz);
            if (aux != null) {
                descargar(aux.nombre, aux.contenido);
                JOptionPane.showMessageDialog(null, "Archivo descargado");
            } else {
                JOptionPane.showMessageDialog(null, "Archivo no disponible");
            }
        }
    }//GEN-LAST:event_descargarMouseClicked
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/logoUsac.png"));
        return retValue;
    }

    public static void main(String args[]) {

        try {
            //com.jtattoo.plaf.noire.NoireLookAndFeel
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        /* Create and display the form */

        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Administrar;
    private javax.swing.JMenu CargaA;
    private javax.swing.JMenu CargaUsuarios;
    private javax.swing.JLabel UsuarioEnLinea;
    private javax.swing.JMenu cerrarSesion;
    private javax.swing.JMenu compartirArchivo;
    private javax.swing.JButton crearArchivo;
    private javax.swing.JButton crearCarpeta;
    private javax.swing.JMenu descargar;
    private javax.swing.JButton eliminarArchivo;
    private javax.swing.JButton entrarCarpeta;
    private javax.swing.JMenu grafiaMatriz;
    private javax.swing.JMenu graficaArbolAVL;
    private javax.swing.JMenu graficaGrafo;
    private javax.swing.JMenu graficaPila;
    private javax.swing.JMenu hashTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarArchivo;
    private javax.swing.JButton modificarNombre;
    private javax.swing.JLabel mostrarImg;
    private javax.swing.JPanel panelArchivos;
    private javax.swing.JButton regresarCarpeta;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaArchivos;
    // End of variables declaration//GEN-END:variables

    public void actualizarUsuario(String Usuario) {
        UsuarioEnLinea.setText(Usuario);
        activarCargaUsuarios(Usuario);
    }

    public void activarCargaUsuarios(String usuario) {
        if (usuario.equals("Admin")) {
            CargaUsuarios.setEnabled(true);
        }
    }

    public boolean verificarSlash(String cad) {
        return cad.equals("/");
    }

    private void propiedadesArbol(NodoArbolAVL raiz, DefaultTableModel tm) {
        if (raiz != null) {
            propiedadesArbol(raiz.izquierdo, tm);
            tm.addRow(new Object[]{labelArchivo, raiz.nombre, raiz.contenido});
            propiedadesArbol(raiz.derecho, tm);
        }
    }

    public void leerCsv(String nombreAr) {
        NodoArbolAVL arbolTemp;
        String csvFile = nombreAr;
        BufferedReader br = null;
        String line;
        String nom;
        String cont;
        String separador = ",";
        if (nodoMatriz != null) {
            try {
                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    String[] datos = line.split(separador);
                    if (!datos[0].equals("Archivo") && !datos[1].equals("Contenido")) {
                        nom = datos[0];
                        cont = datos[1];
                        xy(nom, cont);
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
    }

    public boolean existe(String exist) {
        NodoArbolAVL temp;
        if (nodoMatriz != null) {
            temp = nodoMatriz.arbol.buscar(exist, nodoMatriz.arbol.raiz);
            return temp != null;
        } else {
            return false;
        }
    }

    public void xy(String nom, String cont) {
        if (existe(nom)) {
            NodoArbolAVL arbolTemp = nodoMatriz.arbol.buscar(nom, nodoMatriz.arbol.raiz);
            if (arbolTemp != null) {
                int op = JOptionPane.showConfirmDialog(null, "Se SobreEscribira Archivo: " + nom, "USAC FILE DRIVE",JOptionPane.YES_NO_CANCEL_OPTION);
                 switch(op){
                     case 0:
                         arbolTemp.contenido = cont;
                         break;
                     case 1:
                         JOptionPane.showMessageDialog(null,"No se sobre escribio archivo");
                         break;
                     case 2:
                         JOptionPane.showMessageDialog(null,"No se sobre escribio archivo");
                         break;
                 }
            } else {
                JOptionPane.showMessageDialog(null, "Archivo buscado no encontrado");
            }
        } else {
            nodoMatriz.arbol.insertarDato(nom, cont, UsuarioEnLinea.getText());
        }
    }

    public void graficarBit() {
        ImageIcon image = new ImageIcon("C:\\Graficas_Proyecto2\\bit.png");
        image.getImage().flush();
        mostrarImg.setIcon(image);
        scroll.setViewportView(mostrarImg);
    }

    public void graficaMt() {
        ImageIcon image = new ImageIcon("C:\\Graficas_Proyecto2\\Matriz.png");
        image.getImage().flush();
        mostrarImg.setIcon(image);
        scroll.setViewportView(mostrarImg);
    }

    public void graficaR(String rt) {
        ImageIcon image = new ImageIcon(rt);
        image.getImage().flush();
        mostrarImg.setIcon(image);
        scroll.setViewportView(mostrarImg);
    }

    public void espera() {
        Thread hilo = new Thread();
        hilo.start();
        try {
            Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            System.out.println("Error en hilo" + e);
        }
        hilo.stop();
    }

    public void descargar(String archivo, String cont) {
        String rt = "C:\\Graficas_Proyecto2\\" + archivo;
        File f;
        f = new File(rt);
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            w.write(cont);
            wr.close();
            bw.close();
        } catch (IOException e) {
        }

        abrirarchivo(rt);
    }

    public void abrirarchivo(String archivo) {
        try {
            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
