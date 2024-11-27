package Cliente.Hilos;

import Cliente.GUI.UIConectados;
import Interfaces.InterfazRemota;

public class HiloDesconectar extends Thread {
    private final String user;
    private final String ipserver;
    private final String iplocal;

    public HiloDesconectar(String user, String ipserver, String iplocal) {
        this.user = user;
        this.ipserver = ipserver;
        this.iplocal = iplocal;
    }

    public void run() {
        try {
            InterfazRemota objetoRemoto = (InterfazRemota) java.rmi.Naming.lookup("//" +
                    ipserver + ":1234/ChatRMI");
            objetoRemoto.removeUsuario(user, iplocal);
            UIConectados.clearTabla();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
