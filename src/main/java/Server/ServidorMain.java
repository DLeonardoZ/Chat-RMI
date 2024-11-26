package Server;

import Server.GUI.UIConectados;
import Server.GUI.UIIConsole;
import Server.GUI.UITools;
import Server.GUI.UIChat;

import javax.swing.JFrame;

public class ServidorMain extends JFrame {
    public ServidorMain() {
        super("Servidor RMI");
        setSize(870, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        UIConectados uiOnline = new UIConectados();
        UIChat uiChat = new UIChat();
        UITools uiTools = new UITools();
        UIIConsole uiConsole = new UIIConsole();

        uiOnline.setLocation(20,30);
        uiChat.setLocation(230,30);
        uiTools.setLocation(640, 30);
        uiConsole.setLocation(640, 240);

        add(uiOnline);
        add(uiChat);
        add(uiTools);
        add(uiConsole);
    }

    public static void main(String[] args) {
        ServidorMain uiServidor = new ServidorMain();
        uiServidor.setVisible(true);
    }
}