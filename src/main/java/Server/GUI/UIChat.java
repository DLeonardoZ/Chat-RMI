package Server.GUI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;

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
            //String mensaje = txtMensaje.getText();
            //String user = UIMenu.getUser();

            //new HiloEnviarMensaje(user, mensaje).start();
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
