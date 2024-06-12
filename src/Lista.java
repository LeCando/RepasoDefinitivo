import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lista {
    private List<Automovil> autos;

    public Lista() {
        autos = new ArrayList<>();
    }

    public List<Automovil> getAutos() {
        return autos;
    }

    public void agregarAutomovil(Automovil automovil) throws Exception {
        if (autos.isEmpty()) {
            autos.add(0, automovil);
        } else {
            for (Automovil a : autos) {
                if (a.getCodigo() == automovil.getCodigo()) {
                    throw new Exception("El código ya existe");
                }
            }
            autos.add(0, automovil);
        }
        JOptionPane.showMessageDialog(null, "Automóvil agregado exitosamente al inicio de la lista");
    }

    public String listarAutos() {
        StringBuilder mensaje = new StringBuilder();
        for (Automovil auto : autos) {
            mensaje.append(auto.toString()).append("\n");
        }
        return mensaje.toString();
    }

    public List<Automovil> ordenarPorCilindraje() {
        List<Automovil> autosOrdenados = new ArrayList<>(autos);
        Collections.sort(autosOrdenados, Comparator.comparingInt(Automovil::getCilindraje));
        return autosOrdenados;
    }

    public float sumaTotalPorMarca(String marca) {
        return sumarTotalPorMarca(0, marca, 0);
    }

    private float sumarTotalPorMarca(int indice, String marca, float acumulado) {
        if (autos.size() == indice) {
            return acumulado;
        }
        if (autos.get(indice).getMarca().equals(marca)) {
            acumulado += autos.get(indice).getPrecio();
        }
        return sumarTotalPorMarca(indice + 1, marca, acumulado);
    }
}