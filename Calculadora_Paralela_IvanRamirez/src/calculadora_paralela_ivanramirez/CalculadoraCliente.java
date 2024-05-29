/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora_paralela_ivanramirez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CalculadoraCliente extends JFrame {
    JTextArea txtArea;
    JTextArea resultadoArea;
    JButton btnEnviar;
    JButton btnSuma, btnResta, btnMulti, btnDiv;
    JButton[] numButtons;
    String operacion;
    double numero1;

    public CalculadoraCliente() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Calculadora Cliente");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        txtArea = new JTextArea();
        txtArea.setBounds(10, 10, 260, 50);
        add(txtArea);

        resultadoArea = new JTextArea();
        resultadoArea.setBounds(10, 70, 260, 50);
        resultadoArea.setEditable(false);
        add(resultadoArea);

        btnSuma = new JButton("+");
        btnSuma.setBounds(190, 130, 50, 50);
        add(btnSuma);
        btnSuma.addActionListener(e -> operacion = "+");

        btnResta = new JButton("-");
        btnResta.setBounds(190, 190, 50, 50);
        add(btnResta);
        btnResta.addActionListener(e -> operacion = "-");

        btnMulti = new JButton("x");
        btnMulti.setBounds(190, 250, 50, 50);
        add(btnMulti);
        btnMulti.addActionListener(e -> operacion = "x");

        btnDiv = new JButton("/");
        btnDiv.setBounds(190, 310, 50, 50);
        add(btnDiv);
        btnDiv.addActionListener(e -> operacion = "/");

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(70, 310, 110, 50); // Ajuste de las coordenadas del botón Enviar // Ajuste del tamaño del botón Enviar
        add(btnEnviar);
        btnEnviar.addActionListener(e -> enviarOperacion());

        numButtons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setBounds((i % 3) * 60 + 10, (i / 3) * 60 + 130, 50, 50);
            add(numButtons[i]);
            numButtons[i].addActionListener(new NumberButtonListener());
        }

        setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            txtArea.append(source.getText());
        }
    }

    private void enviarOperacion() {
        try {
            numero1 = Double.parseDouble(txtArea.getText());

            try (Socket socket = new Socket("localhost", 3000);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true)) {

                salida.println(numero1);
                salida.println(operacion);

                double resultadoCliente = Double.parseDouble(entrada.readLine());
                double resultadoServidor = Double.parseDouble(entrada.readLine());

                resultadoArea.setText("Cliente: " + resultadoCliente + "\nServidor: " + resultadoServidor);

                salida.println(resultadoCliente);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            resultadoArea.setText("Número no válido");
        }
    }

    public static void main(String[] args) {
        new CalculadoraCliente();
    }
}