/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora_paralela_ivanramirez;

/**
 *
 * @author ivann
 */
public class implementacion implements operacion{

    @Override
    public double Suma(double num1, double num2) {
        return num1+num2;
    }

    @Override
    public double Resta(double num1, double num2) {
        return num1-num2;
    }

    @Override
    public double Multi(double num1, double num2) {
        return num1*num2;
    }

    @Override
    public double Division(double num1, double num2) {
        return num1/num2;
   }
    
}
