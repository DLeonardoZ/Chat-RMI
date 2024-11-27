package Cliente.Hilos;

import Interfaces.InterfazRemota;

public class HiloDesconectar extends Thread {
    private final String user;
    private final String ipserver;

    public HiloDesconectar(String user, String ipserver) {
        this.user = user;
        this.ipserver = ipserver;
    }

    public void run() {
        try {
            InterfazRemota objetoRemoto = (InterfazRemota) java.rmi.Naming.lookup("//" +
                    ipserver + ":1234/ChatRMI");
            objetoRemoto.removeUsuario(user, ipserver);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
