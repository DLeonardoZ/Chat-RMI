package Cliente.Logica;

import Cliente.GUI.UIChat;
import Cliente.GUI.UIConectados;
import Interfaces.InterfazCliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClaseCliente extends UnicastRemoteObject implements InterfazCliente {

    public ClaseCliente() throws RemoteException {
        // Constructor
    }

    public void recibirMensaje(String user, String mensaje) throws RemoteException {
        UIChat.nuevoMensaje(user, mensaje);
    }

    public void recibirUsuarios(List<String> usuarios) throws RemoteException {
        UIConectados.updateUsuarios(usuarios);
    }

    public void removeUsuario(String user) throws RemoteException {
        UIConectados.eliminarUsuario(user);
    }
}
