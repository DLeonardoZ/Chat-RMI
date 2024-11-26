package Cliente;

import Cliente.GUI.UIChat;
import Cliente.GUI.UIConectados;
import Cliente.GUI.UIMenu;

import javax.swing.JFrame;

public class ClienteMain extends JFrame {
    public ClienteMain() {
        super("Cliente RMI");
        setSize(870, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        UIChat uiChat = new UIChat();
        UIConectados uiOnline = new UIConectados();
        UIMenu uiMenu = new UIMenu();

        uiOnline.setLocation(20,30);
        uiChat.setLocation(230,30);
        uiMenu.setLocation(640, 30);

        add(uiOnline);
        add(uiChat);
        add(uiMenu);
    }

    public static void main(String[] args) {
        ClienteMain uiCliente = new ClienteMain();
        uiCliente.setVisible(true);
    }
}
