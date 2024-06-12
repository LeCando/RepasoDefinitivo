import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JPanel ventana;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldCodigo;
    private JComboBox comboBoxMarca;
    private JComboBox comboBoxCilindraje;
    private JTextField textFieldPrecio;
    private JList listIngreso;
    private JList listOrdenamiento;
    private JComboBox comboBoxMarcaSumatoria;
    private JButton sumatoriaButton;
    private JList listSumatoria;
    private JButton ordenarButton;
    private JButton ingresarButton;
    Lista listaAutomoviles = new Lista();

    public Ventana() {
        quemarDatos();
        llenarJListIngreso();
        limpiarCampos();
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(textFieldCodigo.getText());
                    String marca = (String) comboBoxMarca.getSelectedItem();
                    int cilindraje = Integer.parseInt((String) comboBoxCilindraje.getSelectedItem());
                    float precio = Float.parseFloat(textFieldPrecio.getText());

                    Automovil auto = new Automovil(codigo, marca, cilindraje, precio);
                    listaAutomoviles.agregarAutomovil(auto);
                    actualizarListaIngreso();
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Automovil> autosOrdenados = listaAutomoviles.ordenarPorCilindraje();
                actualizarListaOrdenamiento(autosOrdenados);
            }
        });

        sumatoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca = (String) comboBoxMarcaSumatoria.getSelectedItem();
                float suma = listaAutomoviles.sumaTotalPorMarca(marca);
                DefaultListModel<String> listModelSumatoria = new DefaultListModel<>();
                listModelSumatoria.addElement("Sumatoria de precios para la marca '" + marca + "': " + suma);
                listSumatoria.setModel(listModelSumatoria);
            }
        });
    }

    private void actualizarListaIngreso() {
        DefaultListModel<String> listModelIngreso = new DefaultListModel<>();
        for (Automovil auto : listaAutomoviles.getAutos()) {
            listModelIngreso.addElement(auto.toString());
        }
        listIngreso.setModel(listModelIngreso);
    }

    private void actualizarListaOrdenamiento(List<Automovil> autosOrdenados) {
        DefaultListModel<String> listModelOrdenamiento = new DefaultListModel<>();
        for (Automovil auto : autosOrdenados) {
            listModelOrdenamiento.addElement(auto.toString());
        }
        listOrdenamiento.setModel(listModelOrdenamiento);
    }

    private void llenarJListIngreso() {
        DefaultListModel<String> listModelIngreso = new DefaultListModel<>();
        for (Automovil auto : listaAutomoviles.getAutos()) {
            listModelIngreso.addElement(auto.toString());
        }
        listIngreso.setModel(listModelIngreso);
    }

    private void quemarDatos() {
        try {
            listaAutomoviles.agregarAutomovil(new Automovil(1, "KIA", 1600, 20000));
            listaAutomoviles.agregarAutomovil(new Automovil(2, "BMW", 2000, 50000));
            listaAutomoviles.agregarAutomovil(new Automovil(3, "TOYOTA", 1300, 15000));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void limpiarCampos() {
        textFieldCodigo.setText("");
        comboBoxMarca.setSelectedIndex(0);
        comboBoxCilindraje.setSelectedIndex(0);
        textFieldPrecio.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}