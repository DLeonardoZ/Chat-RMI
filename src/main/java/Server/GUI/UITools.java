package Server.GUI;

import Server.Hilos.HiloServidor;
import Server.Logica.ClaseRemota;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class UITools extends JPanel {
    Boolean puertoAbierto = false;

    public UITools() {
        setSize(200, 200);
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel title = new JLabel("Herramientas");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setSize(200, 50);

        JButton btnStart = new JButton("Start Server");
        btnStart.setFont(new Font("Arial", Font.PLAIN, 16));
        btnStart.setLocation(0,50);
        btnStart.setSize(200, 40);

        JButton btnStop = new JButton("Stop Server");
        btnStop.setFont(new Font("Arial", Font.PLAIN, 16));
        btnStop.setEnabled(false);
        btnStop.setLocation(0,100);
        btnStop.setSize(200, 40);

        add(title);
        add(btnStart);
        add(btnStop);

        btnStart.addActionListener(e -> {
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            new HiloServidor().start(); // Hilo

            if (!puertoAbierto) {
                HiloServidor.abrirPuerto();
                puertoAbierto = true;
            }
        });

        btnStop.addActionListener(e -> {
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
            ClaseRemota.desconetarServidor();
            HiloServidor.detener(); // Hilo
        });
    }
}
