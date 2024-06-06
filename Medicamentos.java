package Modelo;

import javafx.beans.property.*;

public class Medicamentos {
    // Declaración de propiedades para la clase Mascota
    private final IntegerProperty id; // Propiedad para el id
    private final StringProperty nombre; // Propiedad para el nombre
    private final StringProperty tipo; // Propiedad para el tipo
    private final IntegerProperty mascotaId; // Propiedad para el id del propietario

    // Constructor para la clase Mascota
    public Medicamentos(int id, String nombre, String tipo, int mascotaId) {
        // Inicialización de las propiedades con los valores proporcionados
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.mascotaId = new SimpleIntegerProperty(mascotaId);
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

    // Método getter para obtener el id del propietario
    public int getmascotaId() {
        return mascotaId.get();
    }

    // Método getter para obtener la propiedad propietarioId
    public IntegerProperty mascotaIdProperty() {
        return mascotaId;
    }
}
