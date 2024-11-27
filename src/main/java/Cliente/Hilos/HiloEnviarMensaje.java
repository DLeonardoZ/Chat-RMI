package Cliente.Hilos;

import Interfaces.InterfazRemota;

import java.rmi.Naming;

public class HiloEnviarMensaje extends Thread {
    private final String user;
    private final String mensaje;
    private final String ipserver;

    public HiloEnviarMensaje(String user, String mensaje, String server) {
        this.user = user;
        this.mensaje = mensaje;
        this.ipserver = server;
    }

    @Override
    public void run() {
        try {
            // Ejecutamos un metodo para recubir el mensaje en el servidor
            InterfazRemota objetoRemoto = (InterfazRemota) Naming.lookup("//" +
                    ipserver + ":1234/ChatRMI");
            objetoRemoto.recibirMensaje(user, mensaje); // Recibe el servidor por parte del cliente

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
