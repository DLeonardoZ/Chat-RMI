package Cliente.GUI;

import Cliente.Hilos.HiloCliente;
import Cliente.Hilos.HiloDesconectar;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;

public class UIMenu extends JPanel {
    static JTextField txtUser;
    static JTextField txtAddress;
    static JLabel estado;
    static JButton btnConnect;
    static JButton btnDisconnect;
    boolean puertoAbierto = false;

    public UIMenu() {
        setSize(200, 350);
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel title = new JLabel("Servidor");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setSize(200, 50);

        txtAddress = new JTextField();
        txtAddress.setSize(200, 40);
        txtAddress.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAddress.setHorizontalAlignment(JTextField.CENTER);
        txtAddress.setSize(200, 30);
        txtAddress.setLocation(0, 50);
        txtAddress.setEnabled(true);

        JLabel subtitle = new JLabel("usuario");
        subtitle.setFont(new Font("Arial", Font.BOLD, 18));
        subtitle.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setLocation(0, 80);
        subtitle.setSize(200, 50);

        txtUser = new JTextField();
        txtUser.setSize(200, 40);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUser.setHorizontalAlignment(JTextField.CENTER);
        txtUser.setLocation(0, 130);
        txtUser.setSize(200, 30);
        txtUser.setEnabled(true);

        btnConnect = new JButton("Conectarse");
        btnConnect.setFont(new Font("Arial", Font.PLAIN, 16));
        btnConnect.setLocation(0,170);
        btnConnect.setSize(200, 40);

        btnDisconnect = new JButton("Desconectarse");
        btnDisconnect.setFont(new Font("Arial", Font.PLAIN, 16));
        btnDisconnect.setLocation(0,220);
        btnDisconnect.setSize(200, 40);
        btnDisconnect.setEnabled(false);

        estado = new JLabel("Desconectado");
        estado.setFont(new Font("Arial", Font.PLAIN, 16));
        estado.setForeground(Color.RED);
        estado.setHorizontalAlignment(JLabel.CENTER);
        estado.setLocation(0, 280);
        estado.setSize(200, 40);

        add(title);
        add(txtAddress);
        add(subtitle);
        add(txtUser);
        add(btnConnect);
        add(btnDisconnect);
        add(estado);

        btnConnect.addActionListener(e -> {
            if (getUsuarioUI().isEmpty() || getDireccionUI().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Favor de llenar los campos.",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!puertoAbierto) {
                    try {
                        HiloCliente.abrirPuerto();
                        // btnDesconectar();
                        puertoAbierto = true;
                    } catch (Exception ex) {
                        System.out.println("Error al abrir puerto (UIMenu -> btnConnect): " + ex.getMessage());
                        return;
                    }
                }
                btnConectar(); // Actualizar UI
                new HiloCliente().start();
            }
        });

        btnDisconnect.addActionListener(e -> {
            btnDesconectar(); // Actualizar UI
            HiloCliente.detener(); // Detener RMI del cliente

            new HiloDesconectar(getUsuarioUI(), getDireccionUI()).start();
            UIMenu.setTextEstado("Desconectado", Color.RED);
        });
    }

    public static void resetUIError() {
        btnConnect.setEnabled(true);
        txtUser.setEditable(true);
        txtUser.setText("");
    } // UI

    public static void btnConectar() {
        btnConnect.setEnabled(false);
        txtUser.setEditable(false);
        txtAddress.setEnabled(false);
    } // UI

    public static void btnDesconectar() {
        UIChat.desactivarChat();
        txtAddress.setEnabled(true);
        txtUser.setEnabled(true);
        UIMenu.btnConnect.setEnabled(true);
        UIMenu.txtUser.setEditable(true);
        UIMenu.btnDisconnect.setEnabled(false);
        txtUser.setText("");
    } // UI

    public static String getUsuarioUI() {
        return txtUser.getText();
    }

    public static String getDireccionUI() {
        return txtAddress.getText();
    }

    public static void setTextEstado(String text, Color color) {
        estado.setForeground(color);
        estado.setText(text);
    }
}