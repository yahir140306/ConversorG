import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;

public class ConversorG extends JFrame implements ActionListener {

    private JComboBox<String> GRUPO1, GRUPO2, GRUPO3;
    private JButton BOTON;
    private JTextField textField1, textField2;
    private Hashtable<String, String[]> subItems = new Hashtable<>();

    public ConversorG() {
        // Diseño del JFrame
        setTitle("Conversor De Unidades - 3EV - :)");
        setLayout(null);

        JLabel INDICACION = new JLabel("Escoja La Unidad Que Quiera Convertir");
        INDICACION.setBounds(125, 10, 250, 30);
        add(INDICACION);

        textField1 = new JTextField();
        textField1.setBounds(50, 100, 100, 30);
        add(textField1);

        JLabel ingrese = new JLabel("INGRESE:");
        ingrese.setBounds(65, 125, 100, 30);
        add(ingrese);

        textField2 = new JTextField();
        textField2.setBounds(300, 100, 100, 30);
        textField2.setEditable(false); // Solo lectura
        add(textField2);

        JLabel resultado = new JLabel("RESULTADO:");
        resultado.setBounds(310, 125, 100, 30);
        add(resultado);

        BOTON = new JButton("Convertir");
        BOTON.setBounds(165, 220, 100, 30);
        add(BOTON);
        BOTON.addActionListener(this);

        // Grupo principal de unidades
        String[] datos = { "Longitud", "Superficie", "Volumen", "U de Presion", "Energia", "Macro. Energeticas",
                "Potencia" };
        GRUPO1 = new JComboBox<>(datos);
        GRUPO1.setBounds(150, 50, 150, 30);
        add(GRUPO1);
        GRUPO1.addActionListener(this);

        // ComboBox de unidades de conversión
        GRUPO2 = new JComboBox<>();
        GRUPO2.setBounds(10, 175, 160, 30);
        add(GRUPO2);

        GRUPO3 = new JComboBox<>();
        GRUPO3.setBounds(250, 175, 160, 30);
        add(GRUPO3);

        // Definir subcategorías de conversión
        subItems.put("Longitud", new String[] { "Metro", "Milímetro", "Pulgada", "Pie", "Yarda", "Milla" });
        subItems.put("Superficie", new String[] { "Metro cuadrado", "Hectárea", "Pulgada cuadrada", "Pie cuadrado","Yarda cuadrada", "Acre" });
        subItems.put("Volumen", new String[] { "Metro Cubico - m3", "Litro - dm3", "Pie Cubico - ft3","Galon USA - gal", "Galon Imperial GB - gal", "Barril De Petroleo - bbl (oil)" });
        subItems.put("U de Presion", new String[] { "Kilo Pascal", "Atmosfera tecnica", "Milímetro de c. Hg","Metro de c. agua", "Libras por pulgada", "Bar" });
        subItems.put("Energia",new String[] { "Kilojulio", "kw/hora", "Horse power/hora", "Caballo/hora", "Kilocaloria", "British" });
        subItems.put("Macro. Energeticas", new String[] { "Opción 1", "Opción 2" });
        subItems.put("Potencia", new String[] { "Opción 3", "Opción 4" });

        // Inicializar ComboBoxes con la primera categoría
        actualizarCombos(datos[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GRUPO1) {
            actualizarCombos((String) GRUPO1.getSelectedItem());
        }

        if (e.getSource() == BOTON) {
            realizarConversion();
        }
    }

    private void actualizarCombos(String categoria) {
        String[] items = subItems.get(categoria);
        if (items != null) {
            GRUPO2.setModel(new DefaultComboBoxModel<>(items));
            GRUPO3.setModel(new DefaultComboBoxModel<>(items));
        } else {
            GRUPO2.setModel(new DefaultComboBoxModel<>(new String[] {}));
            GRUPO3.setModel(new DefaultComboBoxModel<>(new String[] {}));
        }
    }

    private void realizarConversion() {
        String box1 = (String) GRUPO2.getSelectedItem();
        String box2 = (String) GRUPO3.getSelectedItem();

        if (box1 == null || box2 == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una unidad válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double valor = Double.parseDouble(textField1.getText());
            double resultado = 0;

            if (box1.equals("Metro Cubico - m3") && box2.equals("Litro - dm3")) {
                resultado = valor * 1000;
            } else if (box1.equals("Metro Cubico - m3") && box2.equals("Pie Cubico - ft3")) {
                resultado = valor * 35.31;
            } else if (box1.equals("Metro Cubico - m3") && box2.equals("Galon USA - gal")) {
                resultado = valor * 264.17;
            } else if (box1.equals("Metro Cubico - m3") && box2.equals("Galon Imperial GB - gal")) {
                resultado = valor * 219.96;
            } else if (box1.equals("Metro Cubico - m3") && box2.equals("Barril De Petroleo - bbl (oil)")) {
                resultado = valor * 6.28;
            } else {
                JOptionPane.showMessageDialog(this, "Conversión no soportada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            textField2.setText(String.format("%.4f", resultado));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ConversorG ventana = new ConversorG();
        ventana.setBounds(500, 100, 450, 450);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
