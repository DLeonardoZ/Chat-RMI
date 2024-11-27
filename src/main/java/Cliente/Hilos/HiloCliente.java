package Cliente.Hilos;

import Cliente.GUI.UIChat;
import Cliente.Logica.ClaseCliente;
import Interfaces.InterfazCliente;
import Cliente.GUI.UIMenu;

import java.awt.Color;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HiloCliente extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Cliente RMI: Iniciando...");
            UIMenu.setTextEstado("Estableciendo conexión", Color.ORANGE);
            String url = "//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":1234/ChatRMI";
            InterfazCliente objetoRemoto = new ClaseCliente();
            java.rmi.Naming.rebind(url, objetoRemoto);
            System.out.println("Cliente RMI: OK");
            UIMenu.setTextEstado("Conectado", Color.GREEN);
            UIChat.activarChat();

            /*String serverAddress = UIMenu.getDireccionUI();
            String localAddress = InetAddress.getLocalHost().getHostAddress();

            //Registra la conexion en el servidor
            InterfazRemota claseRemota = (InterfazRemota) Naming.lookup("//" +
                    serverAddress + ":1234/ChatRMI");
            claseRemota.addUsuario(UIMenu.getUsuarioUI(), localAddress);*/


        } catch (Exception ex) {
            UIMenu.resetUIError();
            UIMenu.setTextEstado(ex.getMessage(), Color.RED);
            System.out.println("(HiloCliente.java) Error: " + ex.getMessage());
        }
    }

    public static void abrirPuerto() {
        try {
            Registry registry = LocateRegistry.createRegistry(1234);
            System.out.println(registry);
        } catch (Exception ex) {
            UIMenu.setTextEstado("\nError: Abrir puerto.", Color.RED);
            System.out.println(ex.getMessage());
        }
    }

    public static void detener() {
        try {
            String url = "//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":1234/ChatRMI";
            java.rmi.Naming.unbind(url);
            UIMenu.setTextEstado("Servidor RMI: OFF", Color.BLUE);
        } catch (Exception ex) {
            UIMenu.setTextEstado("\nError: Detener el RMI.", Color.RED);
            System.out.println(ex.getMessage());
        }
        // Detiene servidor RMI
    }


}
