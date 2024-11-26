package Server.GUI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class UIIConsole extends JPanel {
    static JTextArea console = new JTextArea();

    public UIIConsole() {
        setSize(200, 300);
        setBackground(Color.RED);
        setLayout(null);

        console.setEditable(false);
        console.setFont(new Font("Arial", Font.PLAIN, 14));
        console.setSize(200, 300);

        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setSize(200, 300);

        add(scrollPane);
    }

    static public void addTextConsole(String text, Color color) {
        String texto = console.getText();
        console.setForeground(color);
        console.setText(texto + "\n" + text);
    }
}
