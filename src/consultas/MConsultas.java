package consultas;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import logica.Conexion_base;
import oracle.jdbc.OracleTypes;
import vista.MUniversidad;

public final class MConsultas extends javax.swing.JFrame {

    private String descripcion1, descripcion2, descripcion3, descripcion4, descripcion5;
    private DefaultTableModel model1;
    private CallableStatement comando;
    private ResultSet resultado;

    public MConsultas() {
        initComponents();
        descripcionPropuestas();
        activarCampos(false);
    }
    
    private void descripcionPropuestas() {
        descripcion1
                = "PRESENTAR LOS NOMBRES DE PROFESORES, NOMBRES DE ESTUDIANTES, NOMBRES DE CARRERAS, NOMBRES DE EVENTOS,"
                + "\nDÍA DEL EVENTO, LA FECHA DEL EVENTO Y EL NÚMERO DE SEMANA."
                + "\nLOS CUALES SEA HAYAN PROGRAMADO POSTERIOR AL 10 DE JULIO DEL 2017 O (FECHA PARÁMETRO)"
                + "\nORDENADO POR LA FECHA MÁS RECIENTE Y EN FORMATO (10/JUL/2017)";
        descripcion2
                = "LISTAR EL NOMBRE DEL DOCENTE, EL NOMBRE Y NUMERO DE EVENTO AL CUAL ASISTIRÁ; A QUE UNIVERSIDAD,"
                + "\nFACULTAD Y CARRERA PERTENECE EL EVENTO; LA FECHA PARA LA CUAL ESTA PROGRAMADA EL EVENTO "
                + "\nY EL NUMERO DE ESTUDIANTES POR EVENTO.";

        descripcion3
                = "LISTAR EL NOMBRE DE LA CARRERA, NOMBRE DE FACULTAD, NOMBRE DE LA UNIVERSIDAD CON EL PROMEDIO DE LOS"
                + "\nESTUDIANTE QUE ASISTIRAN A LOS EVENTO TECNOLOGICOS O VALOR POR PARÁMETRO. CON SU RESPECTIVA FECHA"
                + "\nY ORDENE EN ORDEN ASC EL NOMBRE DEL DOCENTE CON SU RESPECTIVA CARRERA.";

        descripcion4
                = "LISTAR EL NOMBRE DE LA FACULTAD, NOMBRE DE CARRERA, NOMBRE DE PROFESOR, NOMBRE DEL EVENTO, "
                + "\nNOMBRE DEL ESTUDIANTE, TIPO DE EVENTO Y LA FECHA DE LOS EVENTOS POR EL PROFESOR CON MAYOR"
                + "\nNÚMERO DE EVENTOS CREADOS QUE SEAN DE TIPO 'PROGRAMACIÓN' O VALOR POR PARÁMETRO.";

        descripcion5
                = "LISTAR TODOS LOS EVENTOS CREADOS POR PROFESORES DE LA ‘FACULTAD DE MATEMATICAS"
                + "\nY FISICAS’ O VALOR POR PARÁMETRO. DE LOS CUALES ASISTAN MÁS DE 3 ESTUDIANTES.";
    }

    private void activarCampos(boolean bloquear){
        par_nombre.setEnabled(bloquear);
        par_top.setEnabled(bloquear);
        par_fecha.setEnabled(bloquear);
        btn_ejecutar.setEnabled(bloquear);
        btn_limpiar.setEnabled(bloquear);
    }
    
    public void ejecutarSQL1(){
        try {
            SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
            String[] titulo = {"PROFESOR", "ESTUDIANTE", "CARRERA","EVENTO", "DIA EVENTO", "FECHA EVENTO","NUM. SEMANA"};
            String[] registros = new String[7];
            String sentencia = "{CALL PK_CONSULTAS.P_CONSULTA1(?,?)}";
            model1 = new DefaultTableModel(null, titulo);
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            comando.setString(1, fechaFormato.format(par_fecha.getDate()));
            comando.registerOutParameter(2, OracleTypes.CURSOR);
            comando.execute();
            resultado = (ResultSet) comando.getObject(2);
            while (resultado.next()) {
                registros[0] = resultado.getString(1);
                registros[1] = resultado.getString(2);
                registros[2] = resultado.getString(3);
                registros[3] = resultado.getString(4);
                registros[4] = resultado.getString(5);
                registros[5] = resultado.getString(6);
                registros[6] = resultado.getString(7);
                model1.addRow(registros);
            }
            tb_resultado_consulta.setModel(model1);
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ejecutarSQL2(){
        try {
            String[] titulo = {"NOMBRE", "EVENTO", "NUM. EVENTOS","LUGAR", "ESTUDIANTES", "FECHA"};
            String[] registros = new String[6];
            String sentencia = "{CALL PK_CONSULTAS.P_CONSULTA2(?)}";
            model1 = new DefaultTableModel(null, titulo);
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            //comando.setString(1, txt_fecha.getText());
            comando.registerOutParameter(1, OracleTypes.CURSOR);
            comando.execute();
            resultado = (ResultSet) comando.getObject(1);
            while (resultado.next()) {
                registros[0] = resultado.getString(1);
                registros[1] = resultado.getString(2);
                registros[2] = resultado.getString(3);
                registros[3] = resultado.getString(4);
                registros[4] = resultado.getString(5);
                registros[5] = resultado.getString(6);
                //registros[6] = resultado.getString(7);
                model1.addRow(registros);
            }
            tb_resultado_consulta.setModel(model1);
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void ejecutarSQL3() {
         try {
            String[] titulo = {"DOCENTE", "CARRERA", "FACULTAD", "UNIVERSIDAD", "PROM. ESTUDIANTES","NOMBRE", "FECHA"};
            String[] registros = new String[7];
            String sentencia = "{CALL PK_CONSULTAS.P_CONSULTA3(?)}";
            model1 = new DefaultTableModel(null, titulo);
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            //comando.setString(1, txt_fecha.getText());
            comando.registerOutParameter(1, OracleTypes.CURSOR);
            comando.execute();
            resultado = (ResultSet) comando.getObject(1);
            while (resultado.next()) {
                registros[0] = resultado.getString(1);
                registros[1] = resultado.getString(2);
                registros[2] = resultado.getString(3);
                registros[3] = resultado.getString(4);
                registros[4] = resultado.getString(5);
                registros[5] = resultado.getString(6);
                registros[6] = resultado.getString(7);
                model1.addRow(registros);
            }
            tb_resultado_consulta.setModel(model1);
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ejecutarSQL4() {
        try {
            String[] titulo = {"FACULTAD", "CARRERA", "PROFESOR", "NOMBRE EVENTO", "ESTUDIANTES", "TIPO EVENTO", "FECHA EVENTO"};
            String[] registros = new String[7];
            String sentencia = "{CALL PK_CONSULTAS.P_CONSULTA4(?)}";
            model1 = new DefaultTableModel(null, titulo);
            comando = Conexion_base.coneccion.prepareCall(sentencia);
            //comando.setString(1, txt_fecha.getText());
            comando.registerOutParameter(1, OracleTypes.CURSOR);
            comando.execute();
            resultado = (ResultSet) comando.getObject(1);
            while (resultado.next()) {
                registros[0] = resultado.getString(1);
                registros[1] = resultado.getString(2);
                registros[2] = resultado.getString(3);
                registros[3] = resultado.getString(4);
                registros[4] = resultado.getString(5);
                registros[5] = resultado.getString(6);
                registros[6] = resultado.getString(7);
                model1.addRow(registros);
            }
            tb_resultado_consulta.setModel(model1);
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(MUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ejecutarSQL5() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_resultado_consulta = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_ejecutar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_descripcion_consulta = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmb_consulta = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmb_tipo_evento = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        par_top = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        par_nombre = new javax.swing.JTextField();
        par_fecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(tb_resultado_consulta);

        btn_ejecutar.setText("Ejecutar");
        btn_ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ejecutarActionPerformed(evt);
            }
        });

        btn_limpiar.setText("Limpiar datos");

        txt_descripcion_consulta.setEditable(false);
        txt_descripcion_consulta.setColumns(20);
        txt_descripcion_consulta.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txt_descripcion_consulta.setRows(5);
        txt_descripcion_consulta.setText("PRESENTAR LOS NOMBRES DE PROFESORES, NOMBRES DE ESTUDIANTES, NOMBRES DE CARRERAS, NOMBRES DE EVENTOS,\nDÍA DEL EVENTO, LA FECHA DEL EVENTO Y EL NÚMERO DE SEMANA.\nLOS CUALES SEA HAYAN PROGRAMADO POSTERIOR AL 10 DE JULIO DEL 2017 O (FECHA PARÁMETRO)\nORDENADO POR LA FECHA MÁS RECIENTE Y EN FORMATO (10/JUL/2017)");
        txt_descripcion_consulta.setName(""); // NOI18N
        jScrollPane2.setViewportView(txt_descripcion_consulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_ejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ejecutar)
                    .addComponent(btn_limpiar))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Módulo Consultas SQL");

        cmb_consulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECIONE CONSULTA", "CONSULTA 1", "CONSULTA 2", "CONSULTA 3", "CONSULTA 4", "CONSULTA 5" }));
        cmb_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_consultaActionPerformed(evt);
            }
        });

        jLabel2.setText("Seleccione consulta");

        cmb_tipo_evento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE TIPO DE EVENTO", "TECNOLOGICO", "MATEMATICO", "PROGRAMACION", "AMBIENTAL", "SOCIAL", "CULTURAL", "DEPORTIVO" }));

        jLabel3.setText("Tipo de evento");

        jLabel6.setText("No TOP");

        jLabel7.setText("Fecha");

        jLabel1.setText("Campo de texto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(par_nombre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_tipo_evento, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(par_top, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(par_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cmb_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cmb_tipo_evento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(par_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(par_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(par_top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_consultaActionPerformed
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 1")) {
            txt_descripcion_consulta.setText(descripcion1);
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 2")) {
            txt_descripcion_consulta.setText(descripcion2);
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 3")) {
            txt_descripcion_consulta.setText(descripcion3);
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 4")) {
            txt_descripcion_consulta.setText(descripcion4);
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 5")) {
            txt_descripcion_consulta.setText(descripcion5);
        }
    }//GEN-LAST:event_cmb_consultaActionPerformed

    private void btn_ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ejecutarActionPerformed
       if (cmb_consulta.getSelectedItem().equals("CONSULTA 1")) {
            ejecutarSQL1();
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 2")) {
            ejecutarSQL2();
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 3")) {
            ejecutarSQL3();
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 4")) {
            ejecutarSQL4();
        }
        if (cmb_consulta.getSelectedItem().equals("CONSULTA 5")) {
            ejecutarSQL5();
        }
        
    }//GEN-LAST:event_btn_ejecutarActionPerformed

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
            java.util.logging.Logger.getLogger(MConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MConsultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ejecutar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JComboBox cmb_consulta;
    private javax.swing.JComboBox cmb_tipo_evento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser par_fecha;
    private javax.swing.JTextField par_nombre;
    private javax.swing.JTextField par_top;
    private javax.swing.JTable tb_resultado_consulta;
    private javax.swing.JTextArea txt_descripcion_consulta;
    // End of variables declaration//GEN-END:variables
}
