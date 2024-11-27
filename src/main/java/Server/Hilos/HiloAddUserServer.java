package Server.Hilos;

public class HiloAddUserServer extends Thread {
    String user;
    String ipcliente;

    public HiloAddUserServer(String user, String ipcliente) {
        this.user = user;
        this.ipcliente = ipcliente;
    }

    public void run() {
        // Agregar usuario
    }
}
