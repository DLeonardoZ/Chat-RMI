package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
// Interfaz para recibir cosas del cliente
public interface InterfazRemota extends Remote {
    void addUsuario(String user, String address) throws RemoteException;
    void removeUsuario(String user, String address) throws RemoteException;
    void recibirReplicarMensaje(String user, String mensaje) throws RemoteException;
}