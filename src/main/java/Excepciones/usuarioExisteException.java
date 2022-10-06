/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author cardo
 */
public class usuarioExisteException extends RuntimeException{
    
    public usuarioExisteException(){
        super("Ya existe un usuario creado con esta cedula");
    }
    
}
