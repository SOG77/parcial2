/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.Articulo;
import modelo.ClsConexion;
import modelo.Usuario;

/**
 *
 * @author cardo
 */
public class Controlador {

    ClsConexion conexion = new ClsConexion();

    public Controlador() {

    }

    public boolean guardarArticulo(int codigo, String nombre, String precio, String fecha, float cantidad, String descripcion) {

        if (buscar(codigo) != null) {
        }

        Articulo articulo = new Articulo(codigo, nombre, precio, fecha, cantidad, descripcion);
        conexion.conectar();
        try {

            conexion.getSentenciaSQL().execute("insert into articulo(codigo,nombre,precio,fecha,cantidad,descripcion) "
                    + "values('" + articulo.getCodigo() + "','"
                    + articulo.getNombre() + "','"
                    + articulo.getPrecio() + "',"
                    + articulo.getFecha() + "',"
                    + articulo.getCantidad() + "',"
                    + articulo.getDescripcion() + ")");
            conexion.desconectar();
            return true;

        } catch (SQLException ex) {
            conexion.desconectar();
            return false;
        }
    }

    public boolean guardarUsuario(int contrasena, String nombre, String apellido, String correo, int cedula) {

        if (buscarUsuario(contrasena) != null) {
        }

        Usuario usuario = new Usuario(contrasena, nombre, apellido, correo, cedula);

        conexion.conectar();

        try {

            conexion.getSentenciaSQL().execute("insert into prestamo(contrasena,nombre,apellido,correo,cedula) "
                    + "values('" + usuario.getContrasena() + "','"
                    + usuario.getNombre() + "','"
                    + usuario.getApellido() + "',"
                    + usuario.getCorreo() + "',"
                    + usuario.getCedula() + ")");
            conexion.desconectar();
            return true;

        } catch (SQLException ex) {
            conexion.desconectar();
            return false;
        }

    }

    public DefaultTableModel listar() {
        DefaultTableModel temporal;
        String nombreColumnas[] = {"Código", "Nombre", "Precio", "Fecha", "Cantidad", "Descripcion"};
        temporal = new DefaultTableModel(
                new Object[][]{}, nombreColumnas);
        conexion.conectar();
        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select Código,Nombre,Precio,Descripcion"
                            + "cantidad from articulo order by codigo"));
            while (conexion.getResultadoDB().next()) {
                temporal.addRow(new Object[]{
                    conexion.getResultadoDB().getInt("Código"),
                    conexion.getResultadoDB().getString("Nombre"),
                    conexion.getResultadoDB().getString("Precio"),
                    conexion.getResultadoDB().getString("Fecha"),
                    conexion.getResultadoDB().getInt("Cantidad"),
                    conexion.getResultadoDB().getString("Descripcion"),});
            }
            conexion.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).
                    log(Level.SEVERE, null, ex);
            conexion.desconectar();
        }

        return temporal;
    }

    public DefaultTableModel listarUsuario() {
        DefaultTableModel temporal;
        String nombreColumnas[] = {"Contrasena", "Nombre", "Apellido", "Cedula", "Correo"};
        temporal = new DefaultTableModel(
                new Object[][]{}, nombreColumnas);
        conexion.conectar();
        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select Contrasena,Nombre,Apellido,"
                            + "Nombre from usuario order by Cedula"));
            while (conexion.getResultadoDB().next()) {
                temporal.addRow(new Object[]{
                    conexion.getResultadoDB().getString("Contrasena"),
                    conexion.getResultadoDB().getString("Nombre"),
                    conexion.getResultadoDB().getString("Apellido"),
                    conexion.getResultadoDB().getInt("Cedula"),
                    conexion.getResultadoDB().getString("Correo")});
            }
            conexion.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).
                    log(Level.SEVERE, null, ex);
            conexion.desconectar();
        }

        return temporal;

    }

    public List<String> buscar(int codigo) {

        List<String> temp = new ArrayList<String>();

        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigo,nombre,precio,fecha,cantidad,descripcion"
                            + "cantidad from articulo where "
                            + "codigo='" + codigo + "'"));

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("codigo"));
                temp.add(conexion.getResultadoDB().getString("nombre"));
                temp.add(conexion.getResultadoDB().getString("precio"));
                temp.add(conexion.getResultadoDB().getString("fecha"));
                temp.add(conexion.getResultadoDB().getInt("cantidad") + "");
                temp.add(conexion.getResultadoDB().getString("descripcion"));
            }
            conexion.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();
        }
        return temp;
    }

    public List<String> buscarUsuario(int contrasena) {

        List<String> temp = new ArrayList<String>();

        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select contrasena,nombre,apellido,cedula,correo"
                            + "nombre from usuario where "
                            + "contraena='" + contrasena + "'"));

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("contrasena"));
                temp.add(conexion.getResultadoDB().getString("nombre"));
                temp.add(conexion.getResultadoDB().getString("apellido"));
                temp.add(conexion.getResultadoDB().getInt("cedula") + "");
                temp.add(conexion.getResultadoDB().getString("correo"));
            }
            conexion.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();
        }
        return temp;
    }

    public boolean modificar(int contrasena, String nombre, String apellido, String correo, int cedula) {
        Usuario usuario = new Usuario(contrasena, nombre, apellido, correo, cedula);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("update usuario set nombre='" + usuario.getNombre()
                    + "',apellido='" + usuario.getApellido() + "',"
                    + "correo=" + usuario.getCorreo()
                    + " where cedula='" + usuario.getCedula() + "'");
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();
            return false;
        }
    }

    public boolean modificarArticulo(int codigo, String nombre, String precio, String fecha, float cantidad, String descripcion) {
        Articulo articulo = new Articulo(codigo, nombre, precio, fecha, cantidad, descripcion);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("update articulo set nombre='" + articulo.getNombre()
                    + "',precio='" + articulo.getPrecio()
                    + "fecha=" + articulo.getFecha()
                    + "cantidad=" + articulo.getCantidad() + "'"
                    + "descripcion=" + articulo.getDescripcion()
                    + " where codigo='" + articulo.getCodigo() + "'");
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();
            return false;
        }
    }

    public boolean eliminar(int contrasena) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute("delete from usuario where contrasena='" + contrasena + "'");
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();
            return false;
        }
    }

    public boolean eliminarArticulo(int codigo) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute("delete from Articulo where codigo='" + codigo + "'");
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();
            return false;
        }
    }
}
