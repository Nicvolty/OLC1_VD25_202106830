/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author manue
 */
public class Consola {
    private static String texto = "";

    public static void log(String msg) {
        texto += msg + "\n";
    }

    public static String getTexto() {
        return texto;
    }

    public static void limpiar() {
        texto = "";
    }
}