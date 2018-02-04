package vista;

import java.sql.CallableStatement;
import logica.Conexion_base;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

public final class MUniversidad extends JFrame {

    DefaultTableModel model1;
    CallableStatement comando;
    ResultSet resultado;

    public MUniversidad() throws SQLException {
        initComponents();
        configuraciones();
        activarCampos(false);
        setLocationRelativeTo(null);
    }

    private void configuraciones() {
        //Limpiar campos de inicio
        MUniversidad.this.limpiarCampos();
        //Activar e inactivar controles
        btn_insertar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_editar.setEnabled(false);
        btn_actualizar.setEnabled(false);
        //Inhabilitar el campo codigo
        txt_codigo_universidad.setEnabled(false);
        //Mostrar una etiqueta en cada campo para guíar al usuario
        txt_correo_universidad.setToolTipText("Ingrese el correo");
        txt_codigo_universidad.setToolTipText("Secuencia automática");
        txt_nombre_universidad.setToolTipText("Ingrese nombre de la Univerisdad");
        txt_direccion_universidad.setToolTipText("Ingrese la dirección");
        txt_telefono_universidad.setToolTipText("Ingrese el teléfono");
        //Inhabilitar tabla para edisión
    }

    private void cargarUniversidades() {
        try {
            String[] titulo = {"CODIGO", "NOMBRE", "DIRECCION", "CORREO", "TELEFONO"};
            String[] registros = new String[5];
            String sentencia = "{CALL PK_UNIVERSIDAD.P_CONSULTA(?)}";
            model1 = new DefaultTableModel(null, titulo);
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            comando.registerOutParameter(1, OracleTypes.CURSOR);
            comando.execute();
            resultado = (ResultSet) comando.getObject(1);
            while (resultado.next()) {
                registros[0] = resultado.getString(1);
                registros[1] = resultado.getString(2);
                registros[2] = resultado.getString(3);
                registros[3] = resultado.getString(4);
                registros[4] = resultado.getString(5);
                model1.addRow(registros);
            }
            tb_datos_universidad.setModel(model1);
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertarUniversidad() throws SQLException {
        int codigo_generado = 0;
        String sentencia = "{CALL ?:= PK_UNIVERSIDAD.F_INSERTAR(?,?,?,?)}";
        comando = Conexion_base.coneccion.prepareCall(sentencia);
        comando.registerOutParameter(1, Types.INTEGER);
        comando.setString(2, txt_nombre_universidad.getText());
        comando.setString(3, txt_direccion_universidad.getText());
        comando.setString(4, txt_correo_universidad.getText());
        comando.setString(5, txt_telefono_universidad.getText());
        comando.execute();
        codigo_generado = (int) comando.getObject(1);
        if (codigo_generado > 0) {
            txt_codigo_universidad.setText("" + codigo_generado);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR, EXISTEN CAMPOS VACIOS");
        }

        comando.close();
    }

    private void actualizarUniversidad() throws SQLException {
        int codigo_generado = 0;
        String sentencia = "{CALL ?:= PK_UNIVERSIDAD.F_ACTUALIZAR(?,?,?,?,?)}";
        comando = Conexion_base.coneccion.prepareCall(sentencia);
        comando.registerOutParameter(1, Types.INTEGER);
        comando.setInt(2, Integer.parseInt(txt_codigo_universidad.getText()));
        comando.setString(3, txt_nombre_universidad.getText());
        comando.setString(4, txt_direccion_universidad.getText());
        comando.setString(5, txt_correo_universidad.getText());
        comando.setString(6, txt_telefono_universidad.getText());
        comando.execute();
        codigo_generado = (int) comando.getObject(1);
        if (codigo_generado > 0) {
            JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO");
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: EXISTEN CAMPOS VACIOS");
        }

        comando.close();
    }

    private void eliminarUniversidad() throws SQLException {
        int codigo_eliminar = 0;
        String sentencia = "{CALL ?:= PK_UNIVERSIDAD.F_ELIMINAR(?)}";
        comando = Conexion_base.coneccion.prepareCall(sentencia);
        comando.registerOutParameter(1, Types.INTEGER);
        comando.setInt(2, Integer.parseInt(txt_codigo_universidad.getText()));
        comando.execute();
        codigo_eliminar = (int) comando.getObject(1);
        if (codigo_eliminar > 0) {
            JOptionPane.showInternalConfirmDialog(null, "Se agregó el nuevo registro correctamente", "AVISO DEL SISTEMA", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showInternalConfirmDialog(null, "ERROR: Existen campos en blanco", "AVISO DEL SISTEMA", JOptionPane.OK_OPTION);
        }
    }

    private void limpiarCampos() {
        txt_codigo_universidad.setText("Automático");
        txt_nombre_universidad.setText("");
        txt_direccion_universidad.setText("");
        txt_telefono_universidad.setText("");
        txt_correo_universidad.setText("");
    }

    private void activarCampos(boolean activar) {
        txt_nombre_universidad.setEnabled(activar);
        txt_direccion_universidad.setEnabled(activar);
        txt_telefono_universidad.setEnabled(activar);
        txt_correo_universidad.setEnabled(activar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_datos_universidad = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_insertar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_consultar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_correo_universidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_telefono_universidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo_universidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre_universidad = new javax.swing.JTextField();
        txt_direccion_universidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        tb_datos_universidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_datos_universidad.setColumnSelectionAllowed(true);
        tb_datos_universidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                obtenerDatosUniversidad(evt);
            }
        });
        jScrollPane1.setViewportView(tb_datos_universidad);
        tb_datos_universidad.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btn_insertar.setText("Insertar");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarUniversidad(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_consultar.setText("Consultar");
        btn_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaGeneral(evt);
            }
        });

        btn_limpiar.setText("Limpiar campos");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCampos(evt);
            }
        });

        btn_nuevo.setText("Nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoInsertarUniversidad(evt);
            }
        });

        btn_actualizar.setText("Actualizar");
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_eliminar)
                .addGap(18, 18, 18)
                .addComponent(btn_consultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_actualizar, btn_consultar, btn_editar, btn_eliminar, btn_insertar, btn_nuevo});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insertar)
                    .addComponent(btn_editar)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_consultar)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nuevo)
                    .addComponent(btn_actualizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Módulo Universidad");

        jLabel3.setText("Dirección");

        txt_correo_universidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_correo_universidadKeyReleased(evt);
            }
        });

        jLabel6.setText("Teléfono");

        txt_telefono_universidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soloNumeros(evt);
            }
        });

        jLabel1.setText("Codigo");

        jLabel2.setText("Nombre");

        txt_nombre_universidad.setToolTipText("");
        txt_nombre_universidad.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_nombre_universidad.setMaximumSize(new java.awt.Dimension(100, 100));
        txt_nombre_universidad.setVerifyInputWhenFocusTarget(false);
        txt_nombre_universidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                convertirMayusculas(evt);
            }
        });

        txt_direccion_universidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_direccion_universidadKeyReleased(evt);
            }
        });

        jLabel5.setText("Correo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_codigo_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre_universidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_direccion_universidad))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_telefono_universidad)
                        .addGap(139, 139, 139))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_correo_universidad)))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_codigo_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_correo_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_nombre_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_telefono_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_direccion_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultaGeneral(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaGeneral
        limpiarCampos();
        cargarUniversidades();
        btn_editar.setEnabled(false);
        btn_eliminar.setEnabled(false);
    }//GEN-LAST:event_consultaGeneral

    private void InsertarUniversidad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarUniversidad
        try {
            insertarUniversidad();
            limpiarCampos();
            activarCampos(false);
            btn_nuevo.setEnabled(true);
            btn_insertar.setEnabled(false);
            btn_editar.setEnabled(false);
            btn_eliminar.setEnabled(false);
            
            cargarUniversidades();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_InsertarUniversidad

    private void limpiarCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarCampos
        limpiarCampos();
        btn_editar.setEnabled(false);
        btn_actualizar.setEnabled(false);
        btn_eliminar.setEnabled(false);
    }//GEN-LAST:event_limpiarCampos

    private void obtenerDatosUniversidad(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_obtenerDatosUniversidad
        //Obtener el número de fila donde se dio el clic con el mouse
        int row = tb_datos_universidad.getSelectedRow();
        //Obtener los respectivos datos de los campos y setearlos en los campos de texto
        txt_codigo_universidad.setText(tb_datos_universidad.getValueAt(row, 0).toString());
        txt_nombre_universidad.setText(tb_datos_universidad.getValueAt(row, 1).toString());
        txt_direccion_universidad.setText(tb_datos_universidad.getValueAt(row, 2).toString());
        txt_correo_universidad.setText(tb_datos_universidad.getValueAt(row, 3).toString());
        txt_telefono_universidad.setText(tb_datos_universidad.getValueAt(row, 4).toString());
        //Activar contrones necesarios actualizar y eliminar
        btn_editar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        //Activar el campo Nombre Universidad
        txt_nombre_universidad.requestFocus();
    }//GEN-LAST:event_obtenerDatosUniversidad

    private void convertirMayusculas(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_convertirMayusculas
        txt_nombre_universidad.setText(txt_nombre_universidad.getText().toUpperCase());
    }//GEN-LAST:event_convertirMayusculas

    private void soloNumeros(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_soloNumeros
        char caracter = evt.getKeyChar();
        // Verificar si la tecla pulsada no es un digito
        if (caracter < '0' || caracter > '9' && caracter != '\b') {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_soloNumeros

    private void txt_direccion_universidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccion_universidadKeyReleased
        txt_direccion_universidad.setText(txt_direccion_universidad.getText().toUpperCase());
    }//GEN-LAST:event_txt_direccion_universidadKeyReleased

    private void txt_correo_universidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_correo_universidadKeyReleased
        txt_correo_universidad.setText(txt_correo_universidad.getText().toLowerCase());
    }//GEN-LAST:event_txt_correo_universidadKeyReleased

    private void btn_nuevoInsertarUniversidad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoInsertarUniversidad
        limpiarCampos();
        activarCampos(true);
        btn_nuevo.setEnabled(false);
        btn_actualizar.setEnabled(false);
        btn_editar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_insertar.setEnabled(true);
        txt_nombre_universidad.requestFocus();
    }//GEN-LAST:event_btn_nuevoInsertarUniversidad

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        activarCampos(true);
        btn_actualizar.setEnabled(true);
        btn_eliminar.setEnabled(true);

    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "Esta seguro de actualizar este registro?", "AVISO DEL SISTEMA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                actualizarUniversidad();
                consultaGeneral(evt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este registro?", "AVISO DEL SISTEMA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                eliminarUniversidad();
                limpiarCampos();
                activarCampos(false);
                btn_nuevo.setEnabled(true);
                btn_editar.setEnabled(false);
                btn_actualizar.setEnabled(false);
                btn_insertar.setEnabled(false);
                btn_eliminar.setEnabled(false);
                consultaGeneral(evt);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MUniversidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new MUniversidad().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_consultar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_datos_universidad;
    private javax.swing.JTextField txt_codigo_universidad;
    private javax.swing.JTextField txt_correo_universidad;
    private javax.swing.JTextField txt_direccion_universidad;
    private javax.swing.JTextField txt_nombre_universidad;
    private javax.swing.JTextField txt_telefono_universidad;
    // End of variables declaration//GEN-END:variables
}
