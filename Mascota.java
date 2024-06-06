package Modelo;

import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mascota {

    // Declaración de propiedades para la clase Mascota
    private final IntegerProperty id; // Propiedad para el id
    private final StringProperty nombre; // Propiedad para el nombre
    private final StringProperty tipo; // Propiedad para el tip
    private final IntegerProperty propietarioId; // Propiedad para el id del propietario
    private final IntegerProperty veterinarioId;
    private final StringProperty fecha_de_nacimiento;// Propiedad para el id del veterinario

    // Constructor para la clase Mascota
    public Mascota(int id, String nombre, String tipo, int propietarioId, int veterinarioId, String fecha_de_nacimiento) {
        // Inicialización de las propiedades con los valores proporcionados
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.propietarioId = new SimpleIntegerProperty(propietarioId);
        this.veterinarioId = new SimpleIntegerProperty(veterinarioId);
        this.fecha_de_nacimiento = new SimpleStringProperty(fecha_de_nacimiento);
    }

    // Método getter para obtener el id
    public int getId() {
        return id.get();
    }

    // Método getter para obtener la propiedad id
    public IntegerProperty idProperty() {
        return id;
    }

    // Método getter para obtener el nombre
    public String getNombre() {
        return nombre.get();
    }

    // Método getter para obtener la propiedad nombre
    public StringProperty nombreProperty() {
        return nombre;
    }

    // Método getter para obtener el tipo
    public String getTipo() {
        return tipo.get();
    }

    // Método getter para obtener la propiedad tipo
    public StringProperty tipoProperty() {
        return tipo;
    }

    // Método getter para obtener la fecha de nacimiento
    public String getFechaDeNacimiento() {
        return fecha_de_nacimiento.get();
    }

    // Método getter para obtener la propiedad fecha de nacimiento
    public StringProperty fechaDeNacimientoProperty() {
        return fecha_de_nacimiento;
    }

    // Método getter para obtener el id del propietario
    public int getPropietarioId() {
        return propietarioId.get();
    }

    // Método getter para obtener la propiedad propietarioId
    public IntegerProperty propietarioIdProperty() {
        return propietarioId;
    }

    // Método getter para obtener el id del veterinario
    public int getVeterinarioId() {
        return veterinarioId.get();
    }

    // Método getter para obtener la propiedad veterinarioId
    public IntegerProperty veterinarioIdProperty() {
        return veterinarioId;
    }
}
