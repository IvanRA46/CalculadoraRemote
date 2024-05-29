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
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CalculadoraServidor extends JFrame {
    JTextArea txtArea;
    JTextArea resultadoArea;
    JButton btnEnviar;
    JButton btnSuma, btnResta, btnMulti, btnDiv;
    JButton[] numButtons;
    String operacion;
    double numero1;

    public CalculadoraServidor() {
        initComponents();
        new Thread(this::startServer).start();
    }

    private void initComponents() {
        setTitle("Calculadora Servidor");
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
        btnEnviar.setBounds(70, 310, 110, 50);
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
        } catch (NumberFormatException e) {
            resultadoArea.setText("Número no válido");
        }
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true)) {

                    double numeroCliente = Double.parseDouble(entrada.readLine());
                    String operacionCliente = entrada.readLine();
                    double numeroServidor = numero1;
                    String operacionServidor = operacion;

                    double resultadoCliente = realizarOperacion(numeroCliente, numeroServidor, operacionCliente);
                    double resultadoServidor = realizarOperacion(numeroServidor, numeroCliente, operacionServidor);

                    salida.println(resultadoCliente);
                    salida.println(resultadoServidor);

                    String resultadoClienteStr = entrada.readLine();

                    resultadoArea.setText("Resultado Cliente: " + resultadoClienteStr + "\nResultado Servidor: " + resultadoServidor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double realizarOperacion(double num1, double num2, String operacion) {
        implementacion calculadora = new implementacion();
        switch (operacion) {
            case "+":
                return calculadora.Suma(num1, num2);
            case "-":
                return calculadora.Resta(num1, num2);
            case "x":
                return calculadora.Multi(num1, num2);
            case "/":
                return calculadora.Division(num1, num2);
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new CalculadoraServidor();
    }
}

