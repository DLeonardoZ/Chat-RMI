package Server.Hilos;

import Interfaces.InterfazCliente;

import java.rmi.Naming;
import java.util.List;

public class HiloEnviarMensaje extends Thread {
    private final String user;
    private final String mensaje;
    private final List<String> userAddress;

    public HiloEnviarMensaje(String user, String mensaje, List<String> userAddress) {
        this.user = user;
        this.mensaje = mensaje;
        this.userAddress = userAddress;
    }

    @Override
    public void run() {
        try {
            // Ejecutamos un metodo para enviar el mensaje a todos los clientes
            for (String userAddress : userAddress) {
                System.out.println("Enviando mensaje a: " + user);
                InterfazCliente objetoRemoto = (InterfazCliente) Naming.lookup("//" +
                        userAddress + ":1234/ChatRMI");
                objetoRemoto.recibirMensaje(user, mensaje); // Recibe el cliente
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
