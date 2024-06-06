/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.beans.property.*;

public class Veterinario {
    // Declaraci�n de propiedades para la clase Mascota
    private final IntegerProperty id; // Propiedad para el id
    private final StringProperty nombre; // Propiedad para el nombre
    private final StringProperty especializacion; // Propiedad para el tipo
  

    // Constructor para la clase Mascota
    public Veterinario(int id, String nombre, String especializacion) {
        // Inicializaci�n de las propiedades con los valores proporcionados
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.especializacion = new SimpleStringProperty(especializacion);
    
    }

    // M�todo getter para obtener el id
    public int getId() {
        return id.get();
    }

    // M�todo getter para obtener la propiedad id
    public IntegerProperty idProperty() {
        return id;
    }

    // M�todo getter para obtener el nombre
    public String getNombre() {
        return nombre.get();
    }

    // M�todo getter para obtener la propiedad nombre
    public StringProperty nombreProperty() {
        return nombre;
    }

    // M�todo getter para obtener el tipo
    public String getEspecializacion() {
        return especializacion.get();
    }

    // M�todo getter para obtener la propiedad tipo
    public StringProperty especializacionProperty() {
        return especializacion;
    }

 
   
}