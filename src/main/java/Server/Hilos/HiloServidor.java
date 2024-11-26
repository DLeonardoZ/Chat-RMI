package Server.Hilos;

import Server.Logica.ClaseRemota;
import Server.GUI.UIIConsole;
import Interfaces.InterfazRemota;

import java.awt.Color;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Este hilo se encarga de levantar la comunicación estable con el servidor RMI.
public class HiloServidor extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Servidor RMI: Iniciando...");
            String url = "//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":1234/ChatRMI";
            InterfazRemota objetoRemoto = new ClaseRemota();
            java.rmi.Naming.rebind(url, objetoRemoto);

            UIIConsole.addTextConsole("Servidor RMI: OK", Color.BLACK);
            UIIConsole.addTextConsole("Host: " + url, Color.BLACK);
        } catch (Exception ex) {
            UIIConsole.addTextConsole("\nError: Arranque del RMI.", Color.RED);
            System.out.println(ex.getMessage());
        }
    }

    public static void abrirPuerto() {
        try {
            Registry registry = LocateRegistry.createRegistry(1234);
            System.out.println(registry);
        } catch (Exception ex) {
            UIIConsole.addTextConsole("\nError: Abrir puerto.", Color.RED);
            System.out.println(ex.getMessage());
        }
    }

    public static void detener() {
        try {
            String url = "//" + java.net.InetAddress.getLocalHost().getHostAddress() + ":1234/ChatRMI";
            java.rmi.Naming.unbind(url);
            UIIConsole.addTextConsole("Servidor RMI: OFF", Color.BLUE);
        } catch (Exception ex) {
            UIIConsole.addTextConsole("\nError: Detener el RMI.", Color.RED);
            System.out.println(ex.getMessage());
        }
        // Detiene servidor RMI
    }
}
