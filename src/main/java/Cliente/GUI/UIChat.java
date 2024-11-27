package Cliente.GUI;

import Cliente.Hilos.HiloEnviarMensaje;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UIChat extends JPanel {
    static JTextArea txtChat;
    static JTextField txtMensaje;

    public UIChat() {
        setSize(400, 600);
        setBackground(Color.BLACK);
        setLayout(null);

        txtChat = new JTextArea();
        txtChat.setEditable(false);
        txtChat.setSize(400,550);
        txtChat.setFont(txtChat.getFont().deriveFont(16.0f));

        JScrollPane scrollPane = new JScrollPane(txtChat);
        scrollPane.setSize(400, 550);

        txtMensaje = new JTextField();
        txtMensaje.setFont(txtMensaje.getFont().deriveFont(16.0f));
        txtMensaje.setLocation(0, 555);
        txtMensaje.setSize(400, 40);
        txtMensaje.setEnabled(false);

        add(scrollPane);
        add(txtMensaje);

        txtMensaje.addActionListener(e -> {
            String mensaje = txtMensaje.getText();
            String user = UIMenu.getUsuarioUI();
            String ipServer = UIMenu.getDireccionUI();

            new HiloEnviarMensaje(user, mensaje, ipServer).start();
            txtMensaje.setText("");
        });
    }

    public static void activarChat() {
        txtMensaje.setEnabled(true);
    }

    public static void desactivarChat() {
        txtMensaje.setEnabled(false);
    }

    public static void nuevoMensaje(String user, String mensaje) {
        txtChat.append(user + ": " + mensaje + "\n");
    }
}
