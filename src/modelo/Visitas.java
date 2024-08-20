/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmVisitas;


public class Visitas {
    //1- Parametros
    private String uuid_paciente;  
    private String nombre;  
    private int edad;  
    private String especialidad;  

    
    //Agregar getters y setters
    public String getUuid_paciente() {
        return uuid_paciente;
    }

    public void setUuid_paciente(String uuid_paciente) {
        this.uuid_paciente = uuid_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    //3 - Agregar las funciones (insertar, actualizar, eliminar, buscar etc)
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO TBVISITAS(UUID_paciente, Nombre, edad, especialidad) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setInt(3, getEdad());
            addProducto.setString(4, getEspecialidad());
            //Ejecutar la consulta
            addProducto.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla){
   //Creamos una variable de la clase de conexion
    Connection conexion = ClaseConexion.getConexion();
    //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_paciente", "Nombre", "Edad", "Especialidad"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM TBVISITAS");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Paciente"), 
                    rs.getString("Nombre"), 
                    rs.getInt("Edad"), 
                    rs.getString("Especialidad")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbVisitas where UUID_paciente = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
            PreparedStatement confirmacion = conexion.prepareStatement("Commit");
            confirmacion.executeQuery();

        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
      public void cargarDatosTabla(frmVisitas vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbPacientes.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbPacientes.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbPacientes.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbPacientes.getValueAt(filaSeleccionada, 2).toString();
            String EspecialidadDeTB = vista.jtbPacientes.getValueAt(filaSeleccionada, 3).toString();
 
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtEspecialidad.setText(EspecialidadDeTB);
        }
    }
      
          public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbVisitas set nombre= ?, edad = ?, especialidad = ? where UUID_paciente = ?");
 
                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setString(3, getEspecialidad());
                updateUser.setString(4, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
          public void limpiar (frmVisitas visitas) {
              visitas.txtNombre.setText("");                    
              visitas.txtEdad.setText("");
              visitas.txtEspecialidad.setText("");

          }
    }

