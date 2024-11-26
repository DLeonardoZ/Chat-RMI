package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interfaz para recibir cosas del cliente
public interface InterfazRemota extends Remote {
    void addUsuario(String user, String address) throws RemoteException;
    void desconectar(String user) throws RemoteException;
    List<String> getUsuarios() throws RemoteException;
    void enviarMensaje(String user, String mensaje) throws RemoteException;
    void recibirMensaje(String user, String mensaje) throws RemoteException;
}