
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Visitas;
import vista.frmVisitas;


public class ctrlVisitas implements MouseListener {

//Mandar a llamar a las otras cappas (modelo y vista)
    
    private Visitas modelo;
    private frmVisitas vistas;
    
    
   //2 - Crear el constructor
   public  ctrlVisitas(Visitas modelo, frmVisitas vistas){
       this.modelo = modelo;
       this.vistas = vistas;
       
       vistas.btnAgregar.addMouseListener(this);
       vistas.btnActualizar.addMouseListener(this);
       vistas.btnEliminar.addMouseListener(this);
       vistas.btnBuscar.addMouseListener(this);
       vistas.btnLimpiar.addMouseListener(this);
       vistas.jtbPacientes.addMouseListener(this);
       
       
       modelo.Mostrar(vistas.jtbPacientes);
modelo.cargarDatosTabla(vistas);

   }

   
   //Heredar de la clase que detecta las acciones
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vistas.btnAgregar){
        modelo.setNombre(vistas.txtNombre.getText());
         modelo.setEdad(Integer.parseInt(vistas.txtEdad.getText()));
          modelo.setEspecialidad(vistas.txtEspecialidad.getText());
          
          modelo.Guardar();
       modelo.Mostrar(vistas.jtbPacientes);
        
        }
        
      if  (e.getSource() == vistas.btnEliminar){
        modelo.Eliminar(vistas.jtbPacientes);
         modelo.Mostrar(vistas.jtbPacientes);
    }
      
      if (e.getSource() == vistas.jtbPacientes){
      modelo.cargarDatosTabla(vistas);
      }
      if(e.getSource() == vistas.btnActualizar){
      modelo.setNombre(vistas.txtNombre.getText());
      modelo.setEdad(Integer.parseInt(vistas.txtEdad.getText()));
      modelo.setEspecialidad(vistas.txtEspecialidad.getText());
      
      modelo.Actualizar(vistas.jtbPacientes);
      modelo.Mostrar(vistas.jtbPacientes);
      
    
   }
     if(e.getSource() == vistas.btnLimpiar){
        modelo.limpiar(vistas);
      
      }  
      
    }
  
    
    

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
