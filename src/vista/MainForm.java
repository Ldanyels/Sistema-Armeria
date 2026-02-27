/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import servicio.Almacen;



public class MainForm extends javax.swing.JFrame {

    // ── ATRIBUTOS ────────────────────────────────────────────
    private Almacen almacen;    // El almacén con todos los datos
    private String  usuario;    // Nombre del usuario logueado

    // ── CONSTRUCTOR ──────────────────────────────────────────
    public MainForm(Almacen almacen, String usuario) {
        this.almacen = almacen;
        this.usuario = usuario;
        initComponents();
        setTitle("Sistema de Control de Armería SUCAMEC");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); // pantalla completa
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        lblBienvenida.setText("Bienvenido: " + usuario.toUpperCase()
                              + "  |  Sistema de Control de Armería SUCAMEC");
    }
// ── EVENTOS DE LOS BOTONES ────────────────────────────────

    private void btnArmaActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir formulario de registro de armas
        new ArmaForm(almacen).setVisible(true);
    }

    private void btnMunicionActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir formulario de registro de munición
        new MunicionForm(almacen).setVisible(true);
    }

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir formulario de asignación (salida)
        new SalidaForm(almacen).setVisible(true);
    }

    private void btnDevolucionActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir formulario de devolución
        new DevolucionForm(almacen).setVisible(true);
    }

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir formulario de inventario (reporte)
        new InventarioForm(almacen).setVisible(true);
    }

    private void btnAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {
        // Abrir formulario de asignaciones activas
        new AsignacionesForm(almacen).setVisible(true);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Desea cerrar sesión?",
            "Cerrar Sesión",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (opcion == javax.swing.JOptionPane.YES_OPTION) {
            new LoginForm().setVisible(true);
            this.dispose();
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBienvenida = new javax.swing.JLabel();
        btnArma = new javax.swing.JButton();
        btnMunicion = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();
        btnDevolucion = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnAsignaciones = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblBienvenida.setText("muestra usuario");

        btnArma.setText("Ingresar Arma");

        btnMunicion.setText("Registrar Municion");

        btnSalida.setText("Asignar Arma");

        btnDevolucion.setText("Devolucion");

        btnInventario.setText("Ver Inventario");

        btnAsignaciones.setText("Ver Asignaciones");

        btnLogout.setText("Cerrar Sesion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnArma, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMunicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAsignaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnArma, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMunicion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArma;
    private javax.swing.JButton btnAsignaciones;
    private javax.swing.JButton btnDevolucion;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMunicion;
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel lblBienvenida;
    // End of variables declaration//GEN-END:variables
}
