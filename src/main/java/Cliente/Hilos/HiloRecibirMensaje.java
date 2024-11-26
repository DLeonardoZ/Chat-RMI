package Cliente.Hilos;

public class HiloRecibirMensaje extends Thread {
    private final String user;
    private final String mensaje;

    public HiloRecibirMensaje(String user, String mensaje) {
        this.user = user;
        this.mensaje = mensaje;
    }

    public void run() {
        try {
            /*List<String> ips = ClaseRemota.getIps();
            for (String destino : ips) {
                InterfazCliente objetoCliente = (InterfazCliente) Naming.lookup("//" +
                        destino + ":1234/ChatRMI");

                // Ejecuta Metodos de InterfazRemota
                objetoCliente.recibirMensaje(user, mensaje);
            }*/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
