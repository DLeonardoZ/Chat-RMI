package Server.Hilos;

public class HiloAddUserEnCliente extends Thread {
    String user;
    String ipcliente;

    public HiloAddUserEnCliente(String user, String ipcliente) {
        this.user = user;
        this.ipcliente = ipcliente;
    }

    public void run() {
        // Agregar usuario
    }
}
