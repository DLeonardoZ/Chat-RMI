package Server.Logica;

import Interfaces.InterfazCliente;
import Server.GUI.UIConectados;
import Server.GUI.UIIConsole;
import Interfaces.InterfazRemota;
import Cliente.Hilos.HiloRecibirMensaje;

import java.awt.Color;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ClaseRemota extends UnicastRemoteObject implements InterfazRemota {
    static List<String> userAddress;
    static List<String> usuarios;

    public ClaseRemota() throws RemoteException {
        // Constructor y creación de listas
        userAddress = new ArrayList<>();
        usuarios = new ArrayList<>();
    }
    public void addUsuario(String user, String ipcliente) throws RemoteException {
        userAddress.add(ipcliente);
        usuarios.add(user);

        UIConectados.agregarUsuario(user);
        UIIConsole.addTextConsole(ipcliente + ": " + user + " (OK)" , Color.BLACK);

        // For para enviar a todos los usuarios conectados la lista de usuarios
        for (String userAddress : userAddress) {
            try {
                InterfazCliente objetoRemoto = (InterfazCliente) Naming.lookup("//" +
                        userAddress + ":1234/ChatRMI");
                objetoRemoto.recibirUsuarios(usuarios);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void removeUsuario(String user, String ip) throws RemoteException {
        UIConectados.eliminarUsuario(user);
        for (int i = 0; i < userAddress.size(); i++) {
            if (userAddress.get(i).equals(ip)) {
                UIIConsole.addTextConsole("Desconexión: " + user, Color.BLACK);
                userAddress.remove(i);
                break;
            }
        }
    }

    @Override
    public List<String> getUsuarios() throws RemoteException {
        return List.of();
    }

    public void enviarMensaje(String user, String mensaje) throws RemoteException {

        // El servidor replica el mensaje a todos los usuarios conectados
        for (String userAddress : userAddress) {
            try {
                InterfazCliente objetoRemoto = (InterfazCliente) Naming.lookup("//" +
                        userAddress + ":1234/ChatRMI");
                objetoRemoto.recibirMensaje(user, mensaje);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void desconectar(String user) throws RemoteException {
        // Recorremos la lista de usuarios conectados y eliminamos al usuario que se desconectó
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).equals(user)) {
                System.out.println("encontrado");
                UIConectados.eliminarUsuario(user);
                usuarios.remove(i);
                System.out.printf("Desconexión: %s\n", user);
                break;
            }
        }

        // For para quitar a ese usuario de la lista de conectados en los otros clientes
        for (String userAddress : userAddress) {
            try {
                InterfazCliente objetoRemoto = (InterfazCliente) Naming.lookup("//" +
                        userAddress + ":1234/ChatRMI");
                objetoRemoto.desconectar(user);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public void recibirMensaje(String user, String mensaje) throws RemoteException {
        new HiloRecibirMensaje(user, mensaje).start();
    }

    public static void inicializarServidor(String user, String ipserver) {
        userAddress.add(ipserver);
        usuarios.add(user);
    }

    public static List<String> getUserAddress() {
        return userAddress;
    }
}
