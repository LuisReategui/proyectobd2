package vista;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;
import logica.Conexion_base;

public class MFacultad extends javax.swing.JFrame {

    DefaultTableModel model1;
    CallableStatement comando;
    ResultSet resultado;

    public MFacultad() {
        initComponents();
        configuraciones();
        activarCampos(false);
        cargarNombresUniversidades();
        setLocationRelativeTo(null);
    }

    private void configuraciones() {
        //Limpiar campos de inicio
        MFacultad.this.limpiarCampos();
        //Activar e inactivar controles
        btn_refresh.setEnabled(false);
        btn_insertar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_editar.setEnabled(false);
        btn_actualizar.setEnabled(false);
        //Inhabilitar el campo codigo
        txt_codigo_facultad.setEnabled(false);
        //Mostrar una etiqueta en cada campo para guíar al usuario
        cmb_universidad.setToolTipText("Escoja la Universidad");
        txt_codigo_facultad.setToolTipText("Secuencia automática");
        txt_nombre_facultad.setToolTipText("Ingrese nombre de la Facultad");
    }

    private void limpiarCampos() {
        txt_codigo_facultad.setText("Automático");
        cmb_universidad.setSelectedItem("SELECCIONE UNIVERSIDAD");
        txt_nombre_facultad.setText("");
    }

    private void activarCampos(boolean b) {
        txt_nombre_facultad.setEditable(b);
        cmb_universidad.setEnabled(b);
    }

    private void cargarFacultades() {
        try {
            String[] titulo = {"CODIGO FACULTAD", "UNIVERSIDAD", "NOMBRE FACULTAD"};
            String[] registros = new String[3];
            String sentencia = "{CALL PK_FACULTAD.P_CONSULTA(?)}";
            model1 = new DefaultTableModel(null, titulo);
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            comando.registerOutParameter(1, OracleTypes.CURSOR);
            comando.execute();
            resultado = (ResultSet) comando.getObject(1);
            while (resultado.next()) {
                registros[0] = resultado.getString(1);
                registros[1] = resultado.getString(2);
                registros[2] = resultado.getString(3);
                model1.addRow(registros);
            }
            tb_datos_facultad.setModel(model1);
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarFacultad() throws SQLException {
        try {
            @SuppressWarnings("UnusedAssignment")
            int codigo_actualizado = 0;
            String sentencia = "{CALL ?:= PK_FACULTAD.F_ACTUALIZAR(?,?,?)}";
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            comando.registerOutParameter(1, Types.INTEGER);
            comando.setInt(2, Integer.parseInt(txt_codigo_facultad.getText()));
            comando.setString(3, cmb_universidad.getSelectedItem().toString());
            comando.setString(4, txt_nombre_facultad.getText());
            comando.execute();
            codigo_actualizado = (int) comando.getObject(1);
            if (codigo_actualizado > 0) {
                JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: EXISTEN CAMPOS VACIOS");
            }
            comando.close();
        } catch (SQLException | NumberFormatException | HeadlessException e) {

        }
    }

    private void eliminarFacultad() throws SQLException {
        try {
            int codigo_eliminado = 0;
            String sentencia = "{CALL ?:= PK_FACULTAD.F_ELIMINAR(?)}";
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            comando.registerOutParameter(1, Types.INTEGER);
            comando.setInt(2, Integer.parseInt(txt_codigo_facultad.getText()));
            comando.execute();
            codigo_eliminado = (int) comando.getObject(1);
            if (codigo_eliminado > 0) {
                JOptionPane.showInternalConfirmDialog(null, "Se agregó el nuevo registro correctamente", "AVISO DEL SISTEMA", JOptionPane.OK_OPTION);
            } else {
                JOptionPane.showInternalConfirmDialog(null, "ERROR: Existen campos en blanco", "AVISO DEL SISTEMA", JOptionPane.OK_OPTION);
            }
        } catch (SQLException | NumberFormatException e) {

        }

    }

    private void insertarFacultadad() throws SQLException {
        try {
            int codigo_insertado = 0;
            String sentencia = "{CALL ?:= PK_FACULTAD.F_INSERTAR(?,?)}";
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            comando.registerOutParameter(1, Types.INTEGER);
            comando.setString(2, cmb_universidad.getSelectedItem().toString());
            comando.setString(3, txt_nombre_facultad.getText());
            comando.execute();
            codigo_insertado = (int) comando.getObject(1);
            if (codigo_insertado > 0) {
                txt_codigo_facultad.setText("" + codigo_insertado);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: EXISTEN CAMPOS VACIOS");
            }
            comando.close();
        } catch (SQLException | HeadlessException e) {
        }
    }

    //CARGA TODOS LOS NOMBRES DE UNIVERSIDADES EXISTENTES EN LA BASE Y LOS AGREGA AL COMBOBOX UNIVERSIDAD
    private void cargarNombresUniversidades() {
        try {
            //Se limpia los datos del combobox de univerisdades
            cmb_universidad.removeAllItems();
            //Creamos la sentencia a ejecutar invocando la respectica función/procedimiento mediante el package
            String sentencia = "{CALL PK_UNIVERSIDAD.P_CONSULTA(?)}";
            CallableStatement comando = Conexion_base.coneccion.prepareCall(sentencia);
            comando.registerOutParameter(1, OracleTypes.CURSOR);
            comando.execute();
            resultado = (ResultSet) comando.getObject(1);
            while (resultado.next()) {
                cmb_universidad.addItem(resultado.getString(2));
            }
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_datos_facultad = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmb_universidad = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo_facultad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre_facultad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_insertar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_consultar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();

        tb_datos_facultad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_datos_facultad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                obtenerDatosFacultad(evt);
            }
        });
        jScrollPane1.setViewportView(tb_datos_facultad);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Módulo Facultad");

        jLabel1.setText("Codigo:");

        jLabel2.setText("Universidad");

        jLabel3.setText("Facultad");

        btn_insertar.setText("Insertar");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarInsertarUniversidad(evt);
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
                btn_consultarconsultaGeneral(evt);
            }
        });

        btn_limpiar.setText("Limpiar campos");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarlimpiarCampos(evt);
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

        btn_refresh.setText("REFRESH");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_nombre_facultad)
                    .addComponent(txt_codigo_facultad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_codigo_facultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmb_universidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_refresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nombre_facultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_insertarInsertarUniversidad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarInsertarUniversidad
        try {
            insertarFacultadad();
            limpiarCampos();
            activarCampos(false);
            btn_refresh.setEnabled(false);
            btn_nuevo.setEnabled(true);
            btn_insertar.setEnabled(false);
            btn_editar.setEnabled(false);
            btn_eliminar.setEnabled(false);

            cargarFacultades();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_insertarInsertarUniversidad

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        activarCampos(true);
        btn_actualizar.setEnabled(true);
        btn_eliminar.setEnabled(true);
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este registro?", "AVISO DEL SISTEMA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                eliminarFacultad();
                limpiarCampos();
                activarCampos(false);
                cargarFacultades();
                btn_nuevo.setEnabled(true);
                btn_editar.setEnabled(false);
                btn_actualizar.setEnabled(false);
                btn_insertar.setEnabled(false);
                btn_eliminar.setEnabled(false);
            }
        } catch (HeadlessException | SQLException e) {
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_consultarconsultaGeneral(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultarconsultaGeneral
        limpiarCampos();
        cargarFacultades();
        btn_editar.setEnabled(false);
        btn_eliminar.setEnabled(false);
    }//GEN-LAST:event_btn_consultarconsultaGeneral

    private void btn_limpiarlimpiarCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarlimpiarCampos
        try {
            //Limpia los campos del formulario
            limpiarCampos();
            //Inhabilita los campos
            activarCampos(false);
            //Activar o inactivar los controles
            btn_editar.setEnabled(false);
            btn_actualizar.setEnabled(false);
            btn_eliminar.setEnabled(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_limpiarlimpiarCampos

    private void btn_nuevoInsertarUniversidad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoInsertarUniversidad
        try {
            limpiarCampos();
            activarCampos(true);
            btn_refresh.setEnabled(true);
            btn_nuevo.setEnabled(false);
            btn_actualizar.setEnabled(false);
            btn_editar.setEnabled(false);
            btn_eliminar.setEnabled(false);
            btn_insertar.setEnabled(true);
            cmb_universidad.requestFocus();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_nuevoInsertarUniversidad

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(null, "Esta seguro de actualizar este registro?", "AVISO DEL SISTEMA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                actualizarFacultad();
                activarCampos(false);
                cargarFacultades();
                limpiarCampos();
                btn_actualizar.setEnabled(false);
                btn_editar.setEnabled(false);
                btn_eliminar.setEnabled(false);
                btn_refresh.setEnabled(false);
                btn_nuevo.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        cargarNombresUniversidades();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void obtenerDatosFacultad(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_obtenerDatosFacultad
        try {
            //Obtener el número de fila donde se dio el clic con el mouse
            int row = tb_datos_facultad.getSelectedRow();
            //Obtener los respectivos datos de los campos y setearlos en los campos de texto
            txt_codigo_facultad.setText(tb_datos_facultad.getValueAt(row, 0).toString());
            cmb_universidad.setSelectedItem(tb_datos_facultad.getValueAt(row, 1).toString());
            txt_nombre_facultad.setText(tb_datos_facultad.getValueAt(row, 2).toString());
            //Activar contrones necesarios actualizar y eliminar
            btn_editar.setEnabled(true);
            btn_eliminar.setEnabled(true);
            btn_refresh.setEnabled(true);
            //Activar el campo Nombre Universidad
            cmb_universidad.requestFocus();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_obtenerDatosFacultad

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MFacultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MFacultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MFacultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MFacultad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MFacultad().setVisible(true);
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
    private javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox cmb_universidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_datos_facultad;
    private javax.swing.JTextField txt_codigo_facultad;
    private javax.swing.JTextField txt_nombre_facultad;
    // End of variables declaration//GEN-END:variables

}
