package Server.GUI;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class UIConectados extends JPanel {

    static DefaultTableModel model = new DefaultTableModel(new Object[]{"Usuarios"}, 0);
    DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();

    public UIConectados() {
        setSize(200, 600);
        setBackground(Color.BLUE);
        setLayout(null);

        centerRender.setHorizontalAlignment(SwingConstants.CENTER);

        JTable usuarios = new JTable(model);
        usuarios.setFont(usuarios.getFont().deriveFont(16.0f));
        usuarios.setEnabled(false);
        usuarios.setRowHeight(40);
        usuarios.setDefaultRenderer(Object.class, centerRender);
        usuarios.setSize(200, 600);

        add(usuarios);
        agregarUsuario("Servidor");
    }

    public static void agregarUsuario(String usuario) {
        model.addRow(new Object[]{usuario});
    }

    public static void eliminarUsuario(String usuario) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(usuario)) {
                model.removeRow(i);
                break;
            }
        }
    }

    public static List<String> getUsuarios() {
        List<String> usuarios = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            usuarios.add((String) model.getValueAt(i, 0));
        }
        return usuarios;
    }

    /*public static void updateUsuarios(List<String> usuarios) {
        model.setRowCount(0);
        for (String usuario : usuarios) {
            model.addRow(new Object[]{usuario});
        }
    }*/

    public static void clearTabla() {
        model.setRowCount(0);
    }
}
