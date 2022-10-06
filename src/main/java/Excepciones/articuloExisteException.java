/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author cardo
 */
public class articuloExisteException extends RuntimeException {

    public articuloExisteException() {
        super("Ya existe un articulo con este codigo");
    }
}
