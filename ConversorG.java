import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

import javax.swing.*;

public class ConversorG extends JFrame implements ActionListener{

    private JComboBox GRUPO1, GRUPO2 , GRUPO3;
    private JComboBox datos1 , datos2 , datos3 , datos4 , datos5, datos6 , datos7;
    private JButton BOTON;
    private JTextField textField1, textField2;
    private Hashtable subItems = new Hashtable();

    public ConversorG(){
// diseño del JPaanel
        setTitle("Conversor De Unidades - 3EV - :)");
        setLayout(null);

        JLabel INDICACION = new JLabel("Escoja La Unidad Que Quiera Convertir");
        INDICACION.setBounds(125 , 10 , 250 , 30);
        add(INDICACION);

        textField1 = new JTextField();
        textField1.setBounds(50 , 100 , 100 , 30);
        add(textField1);

        JLabel ingrese = new JLabel("INGRESE : ");
        ingrese.setBounds(65 , 125 , 100 , 30);
        add(ingrese);

        textField2 = new JTextField();
        textField2.setBounds(300 , 100 , 100 , 30);
        add(textField2);

        JLabel resultado = new JLabel("RESULTADO : ");
        resultado.setBounds(310 , 125 , 100 , 30);
        add(resultado);

        BOTON = new JButton("Convertir");
        BOTON.setBounds(165 , 220 , 100 , 30);
        add(BOTON);
        BOTON.addActionListener(this);

// principal combo
        String [] datos = {"Longitud" , "Superficie" , "Volumen" , "U de Presion" , "Energia" , "Macro. Energeticas" , "Potencia" };
        
        GRUPO1 = new JComboBox(datos);
        GRUPO1.setBounds(150 , 50 , 150 , 30);
        add(GRUPO1);
        GRUPO1.addActionListener(this);

// combo de la izquierda
        GRUPO2 = new JComboBox<String>();
        GRUPO2.setBounds(10 , 175 , 160 , 30);
        add(GRUPO2);
        GRUPO2.addActionListener(this);

// combo de la derecha
        GRUPO3 = new JComboBox<String>();
        GRUPO3.setBounds(250 , 175 , 160 , 30);
        add(GRUPO3);
        GRUPO3.addActionListener(this);

// al escojer cierta opcion:

// 0 Longitud
        String [] datos1 = {"Metro","Milimetro","Pulgada","Pie","Yarda","Milla"};
        subItems.put(datos[0], datos1);
        
// 1 Superficie
        String [] datos2 = {"Metro cuadrado", "Hectarea", "Pulgada cuadrada", "Pie cuadrado", "Yarda cuadrada", "Acre"};
        subItems.put(datos[1], datos2);
// 2 Volumen       
        String [] datos3 = {"Metro Cubico - m3", "Litro - dm3", "Pie Cubico - ft3", "Galon USA - gal", "Galon Imperial GB - gal", "Barril De Petroleo - bbl (oil)"};
        subItems.put(datos[2], datos3);
// 3 Unidades de presion       
        String [] datos4 = {"Kilo Pascal", "Atmosfera tecnica", "milimetro de c. Hg", "Metro de c. agua", "Libras por pulgada", "Bar"};
        subItems.put(datos[3], datos4);
// 4 Energia   
        String [] datos5 = {"Kilojulio", "kw/hora", "Hourse power/hora", "Caballo/hora", "Kilocaloria", "British"};
        subItems.put(datos[4], datos5);
// 5 Macro. Energeticas
        String [] datos6 = {"nose"};
        subItems.put(datos[5], datos6);
// 6 Potencia
        String [] datos7 = {"nose1"};
        subItems.put(datos[6], datos7);

    }

    public void actionPerformed (ActionEvent e ){
// al escojer cierto valor de los arrays        
        String box1 = (String) GRUPO2.getSelectedItem();
        String box2 = (String) GRUPO3.getSelectedItem();
        
        if (e.getSource()== BOTON) {
            if (box1.equals("Metro Cubico - m3") && box2.equals("Litro - dm3")) {
                double ans = Double.parseDouble(textField1.getText());
                double res = ans * 1000;
                textField2.setText(String.valueOf(String.format("%.4f", res)));
            }
            if (box1.equals("Metro Cubico - m3") && box2.equals("Pie Cubico - ft3")) {
                double ans = Double.parseDouble(textField1.getText());
                double res = ans * 35.31;
                textField2.setText(String.valueOf(String.format("%.4f", res)));
        
            }
            if (box1.equals("Metro Cubico - m3") && box2.equals("Galon USA - gal")) {
                double ans = Double.parseDouble(textField1.getText());
                double res = ans * 264.17;
                textField2.setText(String.valueOf(String.format("%.4f", res)));
            }
            if (box1.equals("Metro Cubico - m3") && box2.equals("Galon Imperial GB - gal")) {
                double ans = Double.parseDouble(textField1.getText());
                double res = ans * 219.96;
                textField2.setText(String.valueOf(String.format("%.4f", res)));
            }
            if (box1.equals("Metro Cubico - m3") && box2.equals("Barril De Petroleo - bbl (oil)")) {
                double ans = Double.parseDouble(textField1.getText());
                double res = ans * 6.28;
                textField2.setText(String.valueOf(String.format("%.4f", res)));
            }
        }

            // el evento del principal comobo ...
            String datos = (String) GRUPO1.getSelectedItem();
            Object o = subItems.get( datos );
            if (o == null) {
                GRUPO2.setModel( new DefaultComboBoxModel<>());
                GRUPO3.setModel( new DefaultComboBoxModel<>());
            } 
            else {
                GRUPO2.setModel( new DefaultComboBoxModel<>( (String[])o) );
                GRUPO3.setModel( new DefaultComboBoxModel<>( (String[])o) );
            }
        

    }
    public static void main(String[] args) {
        ConversorG ventana = new ConversorG();
        ventana.setBounds(500 , 100 , 450 , 450);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
