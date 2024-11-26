package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interfaz para recibir cosas del servidor
public interface InterfazCliente extends Remote {
    void recibirMensaje(String user, String mensaje) throws RemoteException;
    void recibirUsuarios(List<String> usuarios) throws RemoteException;
    void desconectar(String user) throws RemoteException;
}
