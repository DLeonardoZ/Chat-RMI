package Server.Hilos;

import Interfaces.InterfazCliente;

import java.rmi.Naming;
import java.util.List;

public class HiloEnviarMensaje extends Thread {
    private final String user;
    private final String mensaje;
    private final List<String> ipAddresses;

    public HiloEnviarMensaje(String user, String mensaje, List<String> ipAddresses) {
        this.user = user;
        this.mensaje = mensaje;
        this.ipAddresses = ipAddresses;
    }

    @Override
    public void run() {
        try {
            // Ejecutamos un metodo para enviar el mensaje a todos los clientes
            for (String ipuser : ipAddresses) {
                InterfazCliente objetoRemoto = (InterfazCliente) Naming.lookup("//" +
                        ipuser + ":1234/ChatRMI");
                objetoRemoto.recibirMensaje(user, mensaje); // Recibe el cliente
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
