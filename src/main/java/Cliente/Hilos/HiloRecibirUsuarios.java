package Cliente.Hilos;

import Interfaces.InterfazCliente;
import Server.GUI.UIConectados;
import Server.Logica.ClaseRemota;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class HiloRecibirUsuarios extends Thread {
    List<String> usuarios = new ArrayList<>();

    @Override
    public void run() {
        try {
            List<String> userAdress = ClaseRemota.getUserAddress();
            for (String destino : userAdress) {
                InterfazCliente objetoCliente = (InterfazCliente) Naming.lookup("//" +
                        destino + ":1234/ChatRMI");

                // Ejecuta Metodos de InterfazRemota
                List<String> usuarios = UIConectados.getUsuarios();
                objetoCliente.recibirUsuarios(usuarios);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
