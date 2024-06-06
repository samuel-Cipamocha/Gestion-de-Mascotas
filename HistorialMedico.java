
package Modelo;

import javafx.beans.property.*;

public class HistorialMedico {
    // Declaraci�n de propiedades para la clase HistorialMedico
    private final IntegerProperty id; // Propiedad para el id
    private final IntegerProperty mascotaId; // Propiedad para el id de la mascota
    private final StringProperty fechaConsulta; // Propiedad para la fecha de consulta
    private final StringProperty motivoConsulta; // Propiedad para el motivo de consulta
    private final StringProperty diagnostico; // Propiedad para el diagn�stico
    private final StringProperty tratamiento; // Propiedad para el tratamiento
    private final StringProperty notas; // Propiedad para las notas

    // Constructor para la clase HistorialMedico
    public HistorialMedico(int id, int mascotaId, String fechaConsulta, String motivoConsulta, String diagnostico, String tratamiento, String notas) {
        // Inicializaci�n de las propiedades con los valores proporcionados
        this.id = new SimpleIntegerProperty(id);
        this.mascotaId = new SimpleIntegerProperty(mascotaId);
        this.fechaConsulta = new SimpleStringProperty(fechaConsulta);
        this.motivoConsulta = new SimpleStringProperty(motivoConsulta);
        this.diagnostico = new SimpleStringProperty(diagnostico);
        this.tratamiento = new SimpleStringProperty(tratamiento);
        this.notas = new SimpleStringProperty(notas);
    }

    // M�todo getter para obtener el id
    public int getId() {
        return id.get();
    }

    // M�todo getter para obtener la propiedad id
    public IntegerProperty idProperty() {
        return id;
    }

    // M�todo getter para obtener el id de la mascota
    public int getMascotaId() {
        return mascotaId.get();
    }

    // M�todo getter para obtener la propiedad mascotaId
    public IntegerProperty mascotaIdProperty() {
        return mascotaId;
    }

    // M�todo getter para obtener la fecha de consulta
    public String getFechaConsulta() {
        return fechaConsulta.get();
    }

    // M�todo getter para obtener la propiedad fechaConsulta
    public StringProperty fechaConsultaProperty() {
        return fechaConsulta;
    }

    // M�todo getter para obtener el motivo de consulta
    public String getMotivoConsulta() {
        return motivoConsulta.get();
    }

    // M�todo getter para obtener la propiedad motivoConsulta
    public StringProperty motivoConsultaProperty() {
        return motivoConsulta;
    }

    // M�todo getter para obtener el diagn�stico
    public String getDiagnostico() {
        return diagnostico.get();
    }

    // M�todo getter para obtener la propiedad diagnostico
    public StringProperty diagnosticoProperty() {
        return diagnostico;
    }

    // M�todo getter para obtener el tratamiento
    public String getTratamiento() {
        return tratamiento.get();
    }

    // M�todo getter para obtener la propiedad tratamiento
    public StringProperty tratamientoProperty() {
        return tratamiento;
    }

    // M�todo getter para obtener las notas
    public String getNotas() {
        return notas.get();
    }

    // M�todo getter para obtener la propiedad notas
    public StringProperty notasProperty() {
        return notas;
    }
}
