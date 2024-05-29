/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora_paralela_ivanramirez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author ivann
 */
public class Calculadora_Paralela_IvanRamirez extends JFrame{
    
    /**
     * @param args the command line arguments
     */
    
    JButton btnCero;
    JButton btnUno;
    JButton btnDos;
    JButton btnTres;
    JButton btnCuatro;
    JButton btnCinco;
    JButton btnSeis;
    JButton btnSiete;
    JButton btnOcho;
    JButton btnNueve;
    JButton btnSuma;
    JButton btnResta;
    JButton btnMulti;
    JButton btnDiv;
    JButton btnIgual;
    JButton btnC;
    JTextArea txtArea;
    implementacion calculadora;
    String operacion;
    double numero1, numero2;
    
            
    public Calculadora_Paralela_IvanRamirez(){
        intiComponents();
        calculadora = new implementacion();
    }
    
    private void intiComponents() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        txtArea = new JTextArea();
        txtArea.setBounds(10, 10, 260, 50);
        add(txtArea);

        btnC = new JButton("C");
        btnC.setBounds(10, 70, 50, 50);
        add(btnC);
        btnC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.setText(" ");
            }
        });

        btnDiv = new JButton("/");
        btnDiv.setBounds(70, 70, 50, 50);
        add(btnDiv);
        btnDiv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion = "/";
                numero1 = Double.parseDouble(txtArea.getText());
                txtArea.setText(" ");
            }
        });

        btnMulti = new JButton("x");
        btnMulti.setBounds(130, 70, 50, 50);
        add(btnMulti);
        btnMulti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion = "x";
                numero1 = Double.parseDouble(txtArea.getText());
                txtArea.setText("");
            }
        });

        btnResta = new JButton("-");
        btnResta.setBounds(190, 70, 50, 50);
        add(btnResta);
        btnResta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion = "-";
                numero1 = Double.parseDouble(txtArea.getText());
                txtArea.setText(" ");   
            }
        });

        btnSuma = new JButton("+");
        btnSuma.setBounds(190, 130, 50, 50);
        add(btnSuma);
        btnSuma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operacion = "+";
                numero1 = Double.parseDouble(txtArea.getText());
                txtArea.setText(" "); 
            }
        });
        
        btnIgual = new JButton("=");
        btnIgual.setBounds(190, 190, 50, 110);
        add(btnIgual);
        btnIgual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double resultado = 0;
                numero2 = Double.parseDouble(txtArea.getText());
                switch(operacion){
                    case "+":
                        resultado = calculadora.Suma(numero1, numero2);
                        break;
                    case "-":
                        resultado = calculadora.Resta(numero1, numero2);
                        break;
                    case "x":
                        resultado = calculadora.Multi(numero1, numero2);
                        break;
                    case "/":
                        resultado = calculadora.Division(numero1, numero2);
                        break;
                }
                txtArea.setText(String.valueOf(resultado));
            }
        });

        btnNueve = new JButton("9");
        btnNueve.setBounds(130, 130, 50, 50);
        add(btnNueve);
        btnNueve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("9");
            }
        });
        
        btnOcho = new JButton("8");
        btnOcho.setBounds(70, 130, 50, 50);
        add(btnOcho);
        btnOcho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("8");
            }
        });
        
        btnSiete = new JButton("7");
        btnSiete.setBounds(10, 130, 50, 50);
        add(btnSiete);
        btnSiete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("7");
            }
        });

        btnSeis = new JButton("6");
        btnSeis.setBounds(130, 190, 50, 50);
        add(btnSeis);
        btnSeis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("6");
            }
        });
        
        btnCinco = new JButton("5");
        btnCinco.setBounds(70, 190, 50, 50);
        add(btnCinco);
        btnCinco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("5");
            }
        });

        btnCuatro = new JButton("4");
        btnCuatro.setBounds(10, 190, 50, 50);
        add(btnCuatro);
        btnCuatro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("4");
            }
        });

        btnTres = new JButton("3");
        btnTres.setBounds(130, 250, 50, 50);
        add(btnTres);
        btnTres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("3");
            }
        });

        btnDos = new JButton("2");
        btnDos.setBounds(70, 250, 50, 50);
        add(btnDos);
        btnDos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("2");
            }
        });
        
        btnUno = new JButton("1");
        btnUno.setBounds(10, 250, 50, 50);
        add(btnUno);
        btnUno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("1");
            }
        });

        btnCero = new JButton("0");
        btnCero.setBounds(10, 310, 110, 50);
        add(btnCero);
        btnCero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtArea.append("0");
            }
        });
        
        
        setVisible(true);
                    
    }
    
    public static void main(String[] args) {
        new Calculadora_Paralela_IvanRamirez();
    }

    
    
}
