import vista.LoginForm;

public class Main {

    public static void main(String[] args) {

        // Usar el Look and Feel del sistema operativo (más profesional)
        try {
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, continúa con el look and feel por defecto
        }

        // Lanzar la interfaz en el hilo de eventos de Swing (obligatorio)
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}