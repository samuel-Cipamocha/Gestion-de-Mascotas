package Controlador;

import Modelo.Mascota;
import Modelo.Medicamentos;
import Modelo.Propietario;
import Modelo.Veterinario;
import Modelo.HistorialMedico;
import javafx.application.Application; // Importa la clase Application del paquete javafx.application, que es necesaria para crear una aplicación JavaFX.
import javafx.collections.FXCollections; // Importa la clase FXCollections del paquete javafx.collections, que proporciona métodos estáticos para crear instancias de listas observables.
import javafx.collections.ObservableList; // Importa la interfaz ObservableList del paquete javafx.collections, que define una lista que notifica a los observadores de cambios en los elementos de la lista.
import javafx.scene.Scene; // Importa la clase Scene del paquete javafx.scene, que representa el contenido visual de una aplicación JavaFX.
import javafx.scene.control.*; // Importa todas las clases del paquete javafx.scene.control, que proporciona controles de interfaz de usuario como botones, etiquetas y tablas.
import javafx.scene.layout.BorderPane; // Importa la clase BorderPane del paquete javafx.scene.layout, que es un contenedor que organiza los nodos en una disposición de borde alrededor de un centro.
import javafx.scene.layout.GridPane; // Importa la clase GridPane del paquete javafx.scene.layout, que es un contenedor que organiza los nodos en una cuadrícula.
import javafx.stage.Stage;// Importa la clase Stage del paquete javafx.stage, que representa la ventana principal de una aplicación JavaFX.
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;

import java.sql.Connection; // Importa la clase Connection del paquete java.sql, que representa una conexión con una base de datos SQL.
import java.sql.DriverManager; // Importa la clase DriverManager del paquete java.sql, que administra un conjunto de controladores de JDBC.
import java.sql.ResultSet; // Importa la clase ResultSet del paquete java.sql, que representa un conjunto de resultados de una consulta SQL.
import java.sql.Statement; // Importa la clase Statement del paquete java.sql, que representa una declaración SQL para ser ejecutada en una base de datos.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;

public class controller extends Application {

    private TableView<Propietario> propietarioTable;
    private TableView<Mascota> mascotaTable;
    private TableView<Medicamentos> medicamentosTable;
    private TableView<Veterinario> veterinariosTable;
    private TableView<HistorialMedico> historialMedicoTable;

    private TabPane tabPane;
    private Tab propietarioTab;
    private Tab mascotaTab;
    private Tab medicamentosTab;
    private Tab veterinariosTab;
    private Tab historialMedicoTab;
    private Tab editarPropietarioTab;
    private Tab editarMascotaTab;
    private Tab editarMedicamentoTab;
    private Tab editarveterinariosTab;
    private Tab editarhistorialMedicoTab;
    private ComboBox<Integer> propietarioIdComboBox;
    private ComboBox<Integer> mascotaIdComboBox;
    private ComboBox<Integer> medicamentosIdComboBox;
    private ComboBox<Integer> veterinariosIdComboBox;
    private ComboBox<Integer> historialMedicoIdComboBox;

    @Override
   public void start(Stage primaryStage) {
    tabPane = new TabPane();

    // Pestaña de Propietarios
    propietarioTab = new Tab("Propietarios");
    propietarioTable = new TableView<>();
    propietarioTab.setContent(propietarioTable);

    // Pestaña de Mascotas
    mascotaTab = new Tab("Mascotas");
    mascotaTable = new TableView<>();
    mascotaTab.setContent(mascotaTable);

    // Pestaña de Medicamentos
    medicamentosTab = new Tab("Medicamentos");
    medicamentosTable = new TableView<>();
    medicamentosTab.setContent(medicamentosTable);

    // Pestaña de Veterinarios
    veterinariosTab = new Tab("Veterinarios");
    veterinariosTable = new TableView<>();
    veterinariosTab.setContent(veterinariosTable);

    // Pestaña de Historial Médico
    historialMedicoTab = new Tab("Historial Médico");
    historialMedicoTable = new TableView<>();
    historialMedicoTab.setContent(historialMedicoTable);

    // Pestaña de Editar Propietarios
    editarPropietarioTab = new Tab("Editar/Eliminar Propietarios");
    editarPropietarioTab.setContent(createEditPropietarioPane());

    // Pestaña de Editar Mascotas
    editarMascotaTab = new Tab("Editar/Eliminar Mascotas");
    editarMascotaTab.setContent(createEditMascotaPane());

    // Pestaña de Editar Medicamentos
    editarMedicamentoTab = new Tab("Editar/Eliminar Medicamentos");
    editarMedicamentoTab.setContent(createEditMedicamentoPane());

    // Pestaña de Editar Veterinarios
    editarveterinariosTab = new Tab("Editar/Eliminar Veterinarios");
    editarveterinariosTab.setContent(createEditveterinariosPane());

    // Pestaña de Editar Historial Médico
    editarhistorialMedicoTab = new Tab("Editar/Eliminar Historial Médico");
    editarhistorialMedicoTab.setContent(createEdithistorialMedicoPane());

    // Configurar las columnas para Propietarios
    TableColumn<Propietario, Integer> propietarioIdColumn = new TableColumn<>("ID");
    propietarioIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

    TableColumn<Propietario, String> propietarioNombreColumn = new TableColumn<>("Nombre");
    propietarioNombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

    TableColumn<Propietario, String> propietarioDireccionColumn = new TableColumn<>("Dirección");
    propietarioDireccionColumn.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());

    propietarioTable.getColumns().addAll(propietarioIdColumn, propietarioNombreColumn, propietarioDireccionColumn);

    // Configurar las columnas para Mascotas
    TableColumn<Mascota, Integer> mascotaIdColumn = new TableColumn<>("ID");
    mascotaIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

    TableColumn<Mascota, String> mascotaNombreColumn = new TableColumn<>("Nombre");
    mascotaNombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

    TableColumn<Mascota, String> mascotaTipoColumn = new TableColumn<>("Tipo");
    mascotaTipoColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());

    TableColumn<Mascota, Integer> mascotaPropietarioIdColumn = new TableColumn<>("Propietario ID");
    mascotaPropietarioIdColumn.setCellValueFactory(cellData -> cellData.getValue().propietarioIdProperty().asObject());

    TableColumn<Mascota, Integer> mascotaVeterinarioIdColumn = new TableColumn<>("Veterinario ID");
    mascotaVeterinarioIdColumn.setCellValueFactory(cellData -> cellData.getValue().veterinarioIdProperty().asObject());

    TableColumn<Mascota, String> mascotafechaDeNacimientoColumn = new TableColumn<>("Fecha de Nacimiento");
    mascotafechaDeNacimientoColumn.setCellValueFactory(cellData -> cellData.getValue().fechaDeNacimientoProperty());

    mascotaTable.getColumns().addAll(mascotaIdColumn, mascotaNombreColumn, mascotaTipoColumn, mascotaPropietarioIdColumn, mascotaVeterinarioIdColumn, mascotafechaDeNacimientoColumn);

    // Configurar las columnas para Medicamentos
    TableColumn<Medicamentos, Integer> MedicamentoIdColumn = new TableColumn<>("ID");
    MedicamentoIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

    TableColumn<Medicamentos, String> MedicamentosNombreColumn = new TableColumn<>("Nombre");
    MedicamentosNombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

    TableColumn<Medicamentos, String> MedicamentoTipoColumn = new TableColumn<>("Tipo");
    MedicamentoTipoColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());

    TableColumn<Medicamentos, Integer> MedicamentosMascotaIdColumn = new TableColumn<>("Mascota ID");
    MedicamentosMascotaIdColumn.setCellValueFactory(cellData -> cellData.getValue().mascotaIdProperty().asObject());

    medicamentosTable.getColumns().addAll(MedicamentoIdColumn, MedicamentosNombreColumn, MedicamentoTipoColumn, MedicamentosMascotaIdColumn);

    // Configurar las columnas para Veterinarios
    TableColumn<Veterinario, Integer> veterinarioIdColumn = new TableColumn<>("ID");
    veterinarioIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

    TableColumn<Veterinario, String> veterinarioNombreColumn = new TableColumn<>("Nombre");
    veterinarioNombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

    TableColumn<Veterinario, String> veterinarioEspecializacionColumn = new TableColumn<>("Especialización");
    veterinarioEspecializacionColumn.setCellValueFactory(cellData -> cellData.getValue().especializacionProperty());

    veterinariosTable.getColumns().addAll(veterinarioIdColumn, veterinarioNombreColumn, veterinarioEspecializacionColumn);

    // Configurar las columnas para Historial Médico
    TableColumn<HistorialMedico, Integer> idColumn = new TableColumn<>("ID");
    idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

    TableColumn<HistorialMedico, Integer> HmascotaIdColumn = new TableColumn<>("Mascota ID");
    HmascotaIdColumn.setCellValueFactory(cellData -> cellData.getValue().mascotaIdProperty().asObject());

    TableColumn<HistorialMedico, String> fechaConsultaColumn = new TableColumn<>("Fecha de Consulta");
    fechaConsultaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaConsultaProperty());

    TableColumn<HistorialMedico, String> motivoConsultaColumn = new TableColumn<>("Motivo de Consulta");
    motivoConsultaColumn.setCellValueFactory(cellData -> cellData.getValue().motivoConsultaProperty());

    TableColumn<HistorialMedico, String> diagnosticoColumn = new TableColumn<>("Diagnóstico");
    diagnosticoColumn.setCellValueFactory(cellData -> cellData.getValue().diagnosticoProperty());

    TableColumn<HistorialMedico, String> tratamientoColumn = new TableColumn<>("Tratamiento");
    tratamientoColumn.setCellValueFactory(cellData -> cellData.getValue().tratamientoProperty());

    TableColumn<HistorialMedico, String> notasColumn = new TableColumn<>("Notas");
    notasColumn.setCellValueFactory(cellData -> cellData.getValue().notasProperty());

    historialMedicoTable.getColumns().addAll(idColumn, HmascotaIdColumn, fechaConsultaColumn, motivoConsultaColumn, diagnosticoColumn, tratamientoColumn, notasColumn);

    // Conectar a la base de datos y cargar datos
    loadData();

    // Crear el menú
    MenuBar menuBar = new MenuBar();
    Menu menu = new Menu("Opciones");

    MenuItem openPropietarioTab = new MenuItem("Abrir Propietarios");
    openPropietarioTab.setOnAction(e -> openTab(propietarioTab));

    MenuItem openMascotaTab = new MenuItem("Abrir Mascotas");
    openMascotaTab.setOnAction(e -> openTab(mascotaTab));

    MenuItem openmedicamentosTab = new MenuItem("Abrir Medicamentos");
    openmedicamentosTab.setOnAction(e -> openTab(medicamentosTab));

    MenuItem openmVeterinariosTab = new MenuItem("Abrir Veterinarios");
    openmVeterinariosTab.setOnAction(e -> openTab(veterinariosTab));

    MenuItem openmhistorialMedicoTab = new MenuItem("Abrir Historial Médico");
    openmhistorialMedicoTab.setOnAction(e -> openTab(historialMedicoTab));

    MenuItem openEditarPropietarioTab = new MenuItem("Editar/Eliminar Propietarios");
    openEditarPropietarioTab.setOnAction(e -> openTab(editarPropietarioTab));

    MenuItem openEditarMascotaTab = new MenuItem("Editar/Eliminar Mascotas");
    openEditarMascotaTab.setOnAction(e -> openTab(editarMascotaTab));

    MenuItem openEditarMedicamentoTab = new MenuItem("Editar/Eliminar Medicamentos");
    openEditarMedicamentoTab.setOnAction(e -> openTab(editarMedicamentoTab));

    MenuItem openEditarVeterinariosTab = new MenuItem("Editar/Eliminar Veterinarios");
    openEditarVeterinariosTab.setOnAction(e -> openTab(editarveterinariosTab));

    MenuItem openEditarhistorialMedicoTab = new MenuItem("Editar/Eliminar Historial Médico");
    openEditarhistorialMedicoTab.setOnAction(e -> openTab(editarhistorialMedicoTab));

    menu.getItems().addAll(openPropietarioTab, openMascotaTab, openmedicamentosTab, openmVeterinariosTab, openmhistorialMedicoTab, openEditarPropietarioTab, openEditarMascotaTab, openEditarMedicamentoTab, openEditarVeterinariosTab, openEditarhistorialMedicoTab);
    menuBar.getMenus().add(menu);

    // Layout principal
    BorderPane root = new BorderPane();
    root.setTop(menuBar);
    root.setCenter(tabPane);

    Scene scene = new Scene(root, 800, 600);
    scene.getStylesheets().add("file:///C:/Users/samue/OneDrive/Documentos/NetBeansProjects/PROYECTO_AWT_SWING_JAVAFX/PROYECTO_AWT_SWING_JAVAFX/GUI_JavaFX_JavaAWT_JavaSwing/src/Controlador/stily.css");

    primaryStage.setScene(scene);

    // Configurar pantalla completa
    primaryStage.setFullScreen(true);
    
    primaryStage.show();
}

    private void openTab(Tab tab) {
        if (!tabPane.getTabs().contains(tab)) {
            tabPane.getTabs().add(tab);
        }
        tabPane.getSelectionModel().select(tab);
    }

private GridPane createEditPropietarioPane() {
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new Insets(20));
    pane.getStyleClass().add("grid-pane");

    // Configurar las restricciones de columna
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(30);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(70);
    pane.getColumnConstraints().addAll(column1, column2);

    // Configurar las restricciones de fila
    for (int i = 0; i < 5; i++) {
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().add(row);
    }

    Label idLabel = new Label("ID:");
    idLabel.getStyleClass().add("label");
    propietarioIdComboBox = new ComboBox<>();
    propietarioIdComboBox.setOnAction(e -> loadPropietarioData());
    propietarioIdComboBox.setMaxWidth(Double.MAX_VALUE);
    propietarioIdComboBox.getStyleClass().add("combo-box");

    Label nombreLabel = new Label("Nombre:");
    nombreLabel.getStyleClass().add("label");
    TextField nombreField = new TextField();
    nombreField.setMaxWidth(Double.MAX_VALUE);
    nombreField.getStyleClass().add("text-field");

    Label direccionLabel = new Label("Dirección:");
    direccionLabel.getStyleClass().add("label");
    TextField direccionField = new TextField();
    direccionField.setMaxWidth(Double.MAX_VALUE);
    direccionField.getStyleClass().add("text-field");

    Button updateButton = new Button("Actualizar");
    Button deleteButton = new Button("Eliminar");

    updateButton.setOnAction(e -> {
        int id = propietarioIdComboBox.getValue();
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        updatePropietario(id, nombre, direccion);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Actualización exitosa");
        alert.setHeaderText(null);
        alert.setContentText("El propietario fue actualizado con éxito.");
        alert.initOwner(pane.getScene().getWindow()); // Ajusta el propietario del alert al GridPane
        alert.showAndWait();
        loadData(); // Actualizar la vista después de la actualización
    });

    deleteButton.setOnAction(e -> {
        int id = propietarioIdComboBox.getValue();
        deletePropietario(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eliminación exitosa");
        alert.setHeaderText(null);
        alert.setContentText("El propietario fue eliminado con éxito.");
        alert.initOwner(pane.getScene().getWindow()); // Ajusta el propietario del alert al GridPane
        alert.showAndWait();
        loadData(); // Actualizar la vista después de la eliminación
    });

    Button createPropietarioButton = new Button("Crear Propietario");
    createPropietarioButton.setOnAction(e -> {
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        crearPropietario(nombre, direccion);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Creación exitosa");
        alert.setHeaderText(null);
        alert.setContentText("El propietario fue creado con éxito.");
        alert.initOwner(pane.getScene().getWindow()); // Ajusta el propietario del alert al GridPane
        alert.showAndWait();
        loadData(); // Actualizar la vista después de la inserción
    });

    // Aplicar estilos a los botones
    updateButton.getStyleClass().add("button");
    deleteButton.getStyleClass().add("button");
    createPropietarioButton.getStyleClass().add("button");

    // Añadir componentes al GridPane
    pane.add(idLabel, 0, 0);
    pane.add(propietarioIdComboBox, 1, 0);
    pane.add(nombreLabel, 0, 1);
    pane.add(nombreField, 1, 1);
    pane.add(direccionLabel, 0, 2);
    pane.add(direccionField, 1, 2);

    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER_RIGHT);
    buttonBox.getChildren().addAll(updateButton, deleteButton, createPropietarioButton);

    pane.add(buttonBox, 0, 3, 2, 1);

    GridPane.setHgrow(propietarioIdComboBox, Priority.ALWAYS);
    GridPane.setHgrow(nombreField, Priority.ALWAYS);
    GridPane.setHgrow(direccionField, Priority.ALWAYS);
    GridPane.setHgrow(buttonBox, Priority.ALWAYS);

    return pane;
}

  private GridPane createEditMascotaPane() {
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new Insets(20));
    pane.getStyleClass().add("grid-pane");

    // Configurar las restricciones de columna
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(30);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(70);
    pane.getColumnConstraints().addAll(column1, column2);

    // Configurar las restricciones de fila
    for (int i = 0; i < 9; i++) {
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().add(row);
    }

    Label idLabel = new Label("ID:");
    idLabel.getStyleClass().add("label");
    mascotaIdComboBox = new ComboBox<>();
    mascotaIdComboBox.setOnAction(e -> loadMascotaData());
    mascotaIdComboBox.setMaxWidth(Double.MAX_VALUE);
    mascotaIdComboBox.getStyleClass().add("combo-box");

    Label nombreLabel = new Label("Nombre:");
    nombreLabel.getStyleClass().add("label");
    TextField nombreField = new TextField();
    nombreField.setMaxWidth(Double.MAX_VALUE);
    nombreField.getStyleClass().add("text-field");

    Label tipoLabel = new Label("Tipo:");
    tipoLabel.getStyleClass().add("label");
    TextField tipoField = new TextField();
    tipoField.setMaxWidth(Double.MAX_VALUE);
    tipoField.getStyleClass().add("text-field");

    Label propietarioIdLabel = new Label("Propietario ID:");
    propietarioIdLabel.getStyleClass().add("label");
    TextField propietarioIdField = new TextField();
    propietarioIdField.setMaxWidth(Double.MAX_VALUE);
    propietarioIdField.getStyleClass().add("text-field");

    Label veterinarioIdLabel = new Label("Veterinario ID:");
    veterinarioIdLabel.getStyleClass().add("label");
    TextField veterinarioIdField = new TextField();
    veterinarioIdField.setMaxWidth(Double.MAX_VALUE);
    veterinarioIdField.getStyleClass().add("text-field");

    Label fechaNacimientoLabel = new Label("Fecha de Nacimiento:");
    fechaNacimientoLabel.getStyleClass().add("label");
    TextField fechaNacimientoField = new TextField();
    fechaNacimientoField.setMaxWidth(Double.MAX_VALUE);
    fechaNacimientoField.getStyleClass().add("text-field");

    Button updateButton = new Button("Actualizar");
    Button deleteButton = new Button("Eliminar");

    updateButton.setOnAction(e -> {
        try {
            int id = mascotaIdComboBox.getValue();
            String nombre = nombreField.getText();
            String tipo = tipoField.getText();
            int propietarioId = Integer.parseInt(propietarioIdField.getText());
            int veterinarioId = Integer.parseInt(veterinarioIdField.getText());
            String fechaDeNacimiento = fechaNacimientoField.getText();
            updateMascota(id, nombre, tipo, propietarioId, veterinarioId, fechaDeNacimiento);
            loadData(); // Actualizar la vista después de la actualización
            
            // Mostrar la alerta de actualización exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización exitosa");
            alert.setHeaderText(null);
            alert.setContentText("La mascota fue actualizada con éxito.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            // Mostrar la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos de ID contengan solo números.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();
        }
    });

    deleteButton.setOnAction(e -> {
        int id = mascotaIdComboBox.getValue();
        deleteMascota(id);
        loadData(); // Actualizar la vista después de la eliminación
        
        // Mostrar la alerta de eliminación exitosa
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eliminación exitosa");
        alert.setHeaderText(null);
        alert.setContentText("La mascota fue eliminada con éxito.");
        alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
        alert.showAndWait();
    });

    Button createMascotaButton = new Button("Crear Mascota");
    createMascotaButton.setOnAction(e -> {
        try {
            String nombre = nombreField.getText();
            String tipo = tipoField.getText();
            int propietarioId = Integer.parseInt(propietarioIdField.getText());
            int veterinarioId = Integer.parseInt(veterinarioIdField.getText());
            String fechaDeNacimiento = fechaNacimientoField.getText();
            crearMascota(nombre, tipo, propietarioId, veterinarioId, fechaDeNacimiento);
            loadData(); // Actualizar la vista después de la inserción
            
            // Mostrar la alerta de creación exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("La mascota fue creada con éxito.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            // Mostrar la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos de ID contengan solo números.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();
        }
    });

    // Aplicar estilos a los botones
    updateButton.getStyleClass().add("button");
    deleteButton.getStyleClass().add("button");
    createMascotaButton.getStyleClass().add("button");

    // Añadir componentes al GridPane
    pane.add(idLabel, 0, 0);
    pane.add(mascotaIdComboBox, 1, 0);
    pane.add(nombreLabel, 0, 1);
    pane.add(nombreField, 1, 1);
    pane.add(tipoLabel, 0, 2);
    pane.add(tipoField, 1, 2);
    pane.add(propietarioIdLabel, 0, 3);
    pane.add(propietarioIdField, 1, 3);
    pane.add(veterinarioIdLabel, 0, 4);
    pane.add(veterinarioIdField, 1, 4);
    pane.add(fechaNacimientoLabel, 0, 5);
    pane.add(fechaNacimientoField, 1, 5);

    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER_RIGHT);
    buttonBox.getChildren().addAll(updateButton, deleteButton, createMascotaButton);

    pane.add(buttonBox, 0, 6, 2, 1);

    GridPane.setHgrow(mascotaIdComboBox, Priority.ALWAYS);
    GridPane.setHgrow(nombreField, Priority.ALWAYS);
    GridPane.setHgrow(tipoField, Priority.ALWAYS);
    GridPane.setHgrow(propietarioIdField, Priority.ALWAYS);
    GridPane.setHgrow(veterinarioIdField, Priority.ALWAYS);
    GridPane.setHgrow(fechaNacimientoField, Priority.ALWAYS);
    GridPane.setHgrow(buttonBox, Priority.ALWAYS);

    return pane;
}

private GridPane createEditMedicamentoPane() {
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new Insets(20));
    pane.getStyleClass().add("grid-pane");

    // Configurar las restricciones de columna
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(30);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(70);
    pane.getColumnConstraints().addAll(column1, column2);

    // Configurar las restricciones de fila
    for (int i = 0; i < 6; i++) {
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().add(row);
    }

    Label idLabel = new Label("ID:");
    idLabel.getStyleClass().add("label");
    medicamentosIdComboBox = new ComboBox<>();
    medicamentosIdComboBox.setOnAction(e -> loadMedicamentosData());
    medicamentosIdComboBox.setMaxWidth(Double.MAX_VALUE);
    medicamentosIdComboBox.getStyleClass().add("combo-box");

    Label nombreLabel = new Label("Nombre:");
    nombreLabel.getStyleClass().add("label");
    TextField nombreField = new TextField();
    nombreField.setMaxWidth(Double.MAX_VALUE);
    nombreField.getStyleClass().add("text-field");

    Label tipoLabel = new Label("Tipo:");
    tipoLabel.getStyleClass().add("label");
    TextField tipoField = new TextField();
    tipoField.setMaxWidth(Double.MAX_VALUE);
    tipoField.getStyleClass().add("text-field");

    Label mascotaIdLabel = new Label("Mascota ID:");
    mascotaIdLabel.getStyleClass().add("label");
    TextField mascotaIdField = new TextField();
    mascotaIdField.setMaxWidth(Double.MAX_VALUE);
    mascotaIdField.getStyleClass().add("text-field");

    Button updateButton = new Button("Actualizar");
    Button deleteButton = new Button("Eliminar");

    updateButton.setOnAction(e -> {
        try {
            int id = medicamentosIdComboBox.getValue();
            String nombre = nombreField.getText();
            String tipo = tipoField.getText();
            int mascotaId = Integer.parseInt(mascotaIdField.getText());
            updateMedicamentos(id, nombre, tipo, mascotaId);

            // Mostrar la alerta de actualización exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización exitosa");
            alert.setHeaderText(null);
            alert.setContentText("El medicamento fue actualizado con éxito.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();

            loadData(); // Actualizar la vista después de la actualización
        } catch (NumberFormatException ex) {
            // Mostrar la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos de ID contengan solo números.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();
        }
    });

    deleteButton.setOnAction(e -> {
        int id = medicamentosIdComboBox.getValue();
        deleteMedicamento(id);

        // Mostrar la alerta de eliminación exitosa
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eliminación exitosa");
        alert.setHeaderText(null);
        alert.setContentText("El medicamento fue eliminado con éxito.");
        alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
        alert.showAndWait();

        loadData(); // Actualizar la vista después de la eliminación
    });

    Button createMedicamentoButton = new Button("Crear Medicamento");
    createMedicamentoButton.setOnAction(e -> {
        try {
            String nombre = nombreField.getText();
            String tipo = tipoField.getText();
            int mascotaId = Integer.parseInt(mascotaIdField.getText());
            crearMedicamento(nombre, tipo, mascotaId);

            // Mostrar la alerta de creación exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("El medicamento fue creado con éxito.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();

            loadData(); // Actualizar la vista después de la inserción
        } catch (NumberFormatException ex) {
            // Mostrar la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos de ID contengan solo números.");
            alert.initOwner(pane.getScene().getWindow()); // Asignar el contexto del pane
            alert.showAndWait();
        }
    });

    // Aplicar estilos a los botones
    updateButton.getStyleClass().add("button");
    deleteButton.getStyleClass().add("button");
    createMedicamentoButton.getStyleClass().add("button");

    // Añadir componentes al GridPane
    pane.add(idLabel, 0, 0);
    pane.add(medicamentosIdComboBox, 1, 0);
    pane.add(nombreLabel, 0, 1);
    pane.add(nombreField, 1, 1);
    pane.add(tipoLabel, 0, 2);
    pane.add(tipoField, 1, 2);
    pane.add(mascotaIdLabel, 0, 3);
    pane.add(mascotaIdField, 1, 3);

    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER_RIGHT);
    buttonBox.getChildren().addAll(updateButton, deleteButton, createMedicamentoButton);

    pane.add(buttonBox, 0, 4, 2, 1);

    GridPane.setHgrow(medicamentosIdComboBox, Priority.ALWAYS);
    GridPane.setHgrow(nombreField, Priority.ALWAYS);
    GridPane.setHgrow(tipoField, Priority.ALWAYS);
    GridPane.setHgrow(mascotaIdField, Priority.ALWAYS);
    GridPane.setHgrow(buttonBox, Priority.ALWAYS);

    return pane;
}

   private GridPane createEditveterinariosPane() {
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new Insets(20));
    pane.getStyleClass().add("grid-pane");

    // Configurar las restricciones de columna
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(30);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(70);
    pane.getColumnConstraints().addAll(column1, column2);

    // Configurar las restricciones de fila
    for (int i = 0; i < 4; i++) {
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().add(row);
    }

    Label idLabel = new Label("ID:");
    idLabel.getStyleClass().add("label");
    veterinariosIdComboBox = new ComboBox<>();
    veterinariosIdComboBox.setOnAction(e -> loadveterinarioData());
    veterinariosIdComboBox.setMaxWidth(Double.MAX_VALUE);
    veterinariosIdComboBox.getStyleClass().add("combo-box");

    Label nombreLabel = new Label("Nombre:");
    nombreLabel.getStyleClass().add("label");
    TextField nombreField = new TextField();
    nombreField.setMaxWidth(Double.MAX_VALUE);
    nombreField.getStyleClass().add("text-field");

    Label especializacionLabel = new Label("Especialización:");
    especializacionLabel.getStyleClass().add("label");
    TextField especializacionField = new TextField();
    especializacionField.setMaxWidth(Double.MAX_VALUE);
    especializacionField.getStyleClass().add("text-field");

    Button updateButton = new Button("Actualizar");
    Button deleteButton = new Button("Eliminar");

    updateButton.setOnAction(e -> {
        try {
            int id = veterinariosIdComboBox.getValue();
            String nombre = nombreField.getText();
            String especializacion = especializacionField.getText();
            updateVeterinario(id, nombre, especializacion);

            // Crear la alerta de actualización exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización exitosa");
            alert.setHeaderText(null);
            alert.setContentText("El veterinario fue actualizado con éxito.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();

            loadData(); // Actualizar la vista después de la actualización
        } catch (Exception ex) {
            // Crear la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos no estén vacíos.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();
        }
    });

    deleteButton.setOnAction(e -> {
        int id = veterinariosIdComboBox.getValue();
        deleteVeterinario(id);

        // Crear la alerta de eliminación exitosa
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eliminación exitosa");
        alert.setHeaderText(null);
        alert.setContentText("El veterinario fue eliminado con éxito.");
        alert.initOwner(pane.getScene().getWindow());
        alert.showAndWait();

        loadData(); // Actualizar la vista después de la eliminación
    });

    Button createVeterinarioButton = new Button("Crear Veterinario");
    createVeterinarioButton.setOnAction(e -> {
        try {
            String nombre = nombreField.getText();
            String especializacion = especializacionField.getText();
            crearVeterinario(nombre, especializacion);

            // Crear la alerta de creación exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("El veterinario fue creado con éxito.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();

            loadData(); // Actualizar la vista después de la inserción
        } catch (Exception ex) {
            // Crear la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos no estén vacíos.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();
        }
    });

    // Aplicar estilos a los botones
    updateButton.getStyleClass().add("button");
    deleteButton.getStyleClass().add("button");
    createVeterinarioButton.getStyleClass().add("button");

    // Añadir componentes al GridPane
    pane.add(idLabel, 0, 0);
    pane.add(veterinariosIdComboBox, 1, 0);
    pane.add(nombreLabel, 0, 1);
    pane.add(nombreField, 1, 1);
    pane.add(especializacionLabel, 0, 2);
    pane.add(especializacionField, 1, 2);

    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER_RIGHT);
    buttonBox.getChildren().addAll(updateButton, deleteButton, createVeterinarioButton);

    pane.add(buttonBox, 0, 3, 2, 1);

    GridPane.setHgrow(veterinariosIdComboBox, Priority.ALWAYS);
    GridPane.setHgrow(nombreField, Priority.ALWAYS);
    GridPane.setHgrow(especializacionField, Priority.ALWAYS);
    GridPane.setHgrow(buttonBox, Priority.ALWAYS);

    return pane;
}

   private GridPane createEdithistorialMedicoPane() {
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new Insets(20));
    pane.getStyleClass().add("grid-pane");

    // Configurar las restricciones de columna
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(30);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(70);
    pane.getColumnConstraints().addAll(column1, column2);

    // Configurar las restricciones de fila
    for (int i = 0; i < 8; i++) {
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().add(row);
    }

    Label idLabel = new Label("ID:");
    idLabel.getStyleClass().add("label");
    historialMedicoIdComboBox = new ComboBox<>();
    historialMedicoIdComboBox.setOnAction(e -> loadHistorialMedicoData());
    historialMedicoIdComboBox.setMaxWidth(Double.MAX_VALUE);
    historialMedicoIdComboBox.getStyleClass().add("combo-box");

    Label mascotaIdLabel = new Label("Mascota ID:");
    mascotaIdLabel.getStyleClass().add("label");
    TextField mascotaIdField = new TextField();
    mascotaIdField.setMaxWidth(Double.MAX_VALUE);
    mascotaIdField.getStyleClass().add("text-field");

    Label fechaConsultaLabel = new Label("Fecha de Consulta:");
    fechaConsultaLabel.getStyleClass().add("label");
    TextField fechaConsultaField = new TextField();
    fechaConsultaField.setMaxWidth(Double.MAX_VALUE);
    fechaConsultaField.getStyleClass().add("text-field");

    Label motivoConsultaLabel = new Label("Motivo de Consulta:");
    motivoConsultaLabel.getStyleClass().add("label");
    TextField motivoConsultaField = new TextField();
    motivoConsultaField.setMaxWidth(Double.MAX_VALUE);
    motivoConsultaField.getStyleClass().add("text-field");

    Label diagnosticoLabel = new Label("Diagnóstico:");
    diagnosticoLabel.getStyleClass().add("label");
    TextField diagnosticoField = new TextField();
    diagnosticoField.setMaxWidth(Double.MAX_VALUE);
    diagnosticoField.getStyleClass().add("text-field");

    Label tratamientoLabel = new Label("Tratamiento:");
    tratamientoLabel.getStyleClass().add("label");
    TextField tratamientoField = new TextField();
    tratamientoField.setMaxWidth(Double.MAX_VALUE);
    tratamientoField.getStyleClass().add("text-field");

    Label notasLabel = new Label("Notas:");
    notasLabel.getStyleClass().add("label");
    TextField notasField = new TextField();
    notasField.setMaxWidth(Double.MAX_VALUE);
    notasField.getStyleClass().add("text-field");

    Button updateButton = new Button("Actualizar");
    Button deleteButton = new Button("Eliminar");

    updateButton.setOnAction(e -> {
        try {
            int id = historialMedicoIdComboBox.getValue();
            int mascotaId = Integer.parseInt(mascotaIdField.getText());
            String fechaConsulta = fechaConsultaField.getText();
            String motivoConsulta = motivoConsultaField.getText();
            String diagnostico = diagnosticoField.getText();
            String tratamiento = tratamientoField.getText();
            String notas = notasField.getText();
            updateHistorialMedico(id, mascotaId, fechaConsulta, motivoConsulta, diagnostico, tratamiento, notas);

            // Crear la alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualización exitosa");
            alert.setHeaderText(null);
            alert.setContentText("El historial médico fue actualizado con éxito.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();

            loadData(); // Actualizar la vista después de la actualización
        } catch (Exception ex) {
            // Crear la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos no estén vacíos.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();
        }
    });

    deleteButton.setOnAction(e -> {
        int id = historialMedicoIdComboBox.getValue();
        deleteHistorialMedico(id);

        // Crear la alerta de eliminación exitosa
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eliminación exitosa");
        alert.setHeaderText(null);
        alert.setContentText("El historial médico fue eliminado con éxito.");
        alert.initOwner(pane.getScene().getWindow());
        alert.showAndWait();

        loadData(); // Actualizar la vista después de la eliminación
    });

    Button createHistorialButton = new Button("Crear Historial Médico");
    createHistorialButton.setOnAction(e -> {
        try {
            int mascotaId = Integer.parseInt(mascotaIdField.getText());
            String fechaConsulta = fechaConsultaField.getText();
            String motivoConsulta = motivoConsultaField.getText();
            String diagnostico = diagnosticoField.getText();
            String tratamiento = tratamientoField.getText();
            String notas = notasField.getText();
            crearHistorialMedico(mascotaId, fechaConsulta, motivoConsulta, diagnostico, tratamiento, notas);

            // Crear la alerta de creación exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creación exitosa");
            alert.setHeaderText(null);
            alert.setContentText("El historial médico fue creado con éxito.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();

            loadData(); // Actualizar la vista después de la inserción
        } catch (Exception ex) {
            // Crear la alerta de error de entrada
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de entrada");
            alert.setHeaderText(null);
            alert.setContentText("Verifica que los campos no estén vacíos.");
            alert.initOwner(pane.getScene().getWindow());
            alert.showAndWait();
        }
    });

    // Aplicar estilos a los botones
    updateButton.getStyleClass().add("button");
    deleteButton.getStyleClass().add("button");
    createHistorialButton.getStyleClass().add("button");

    // Añadir componentes al GridPane
    pane.add(idLabel, 0, 0);
    pane.add(historialMedicoIdComboBox, 1, 0);
    pane.add(mascotaIdLabel, 0, 1);
    pane.add(mascotaIdField, 1, 1);
    pane.add(fechaConsultaLabel, 0, 2);
    pane.add(fechaConsultaField, 1, 2);
    pane.add(motivoConsultaLabel, 0, 3);
    pane.add(motivoConsultaField, 1, 3);
    pane.add(diagnosticoLabel, 0, 4);
    pane.add(diagnosticoField, 1, 4);
    pane.add(tratamientoLabel, 0, 5);
    pane.add(tratamientoField, 1, 5);
    pane.add(notasLabel, 0, 6);
    pane.add(notasField, 1, 6);
    pane.add(updateButton, 0, 7);
    pane.add(deleteButton, 1, 7);
    pane.add(createHistorialButton, 0, 8);

    GridPane.setHgrow(historialMedicoIdComboBox, Priority.ALWAYS);
    GridPane.setHgrow(mascotaIdField, Priority.ALWAYS);
    GridPane.setHgrow(fechaConsultaField, Priority.ALWAYS);
    GridPane.setHgrow(motivoConsultaField, Priority.ALWAYS);
    GridPane.setHgrow(diagnosticoField, Priority.ALWAYS);
    GridPane.setHgrow(tratamientoField, Priority.ALWAYS);
    GridPane.setHgrow(notasField, Priority.ALWAYS);

    return pane;
}

    private void updatePropietario(int id, String nombre, String direccion) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "UPDATE Propietario SET nombre = '" + nombre + "', direccion = '" + direccion + "' WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletePropietario(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Propietario WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMascota(int id, String nombre, String tipo, int propietarioId, int veterinarioId, String fecha_de_nacimiento) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "UPDATE Mascota SET nombre = '" + nombre + "', tipo = '" + tipo + "', propietario_id = " + propietarioId + ", veterinario_id = " + veterinarioId + ", fecha_de_nacimiento = '" + fecha_de_nacimiento + "' WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteMascota(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Mascota WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMedicamentos(int id, String nombre, String tipo, int mascotaId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "UPDATE Medicamentos SET nombre = '" + nombre + "', tipo = '" + tipo + "', mascota_id = " + mascotaId + " WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteMedicamento(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Medicamentos WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateVeterinario(int id, String nombre, String especializacion) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "UPDATE Veterinario SET nombre = '" + nombre + "', especializacion = '" + especializacion + "' WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteVeterinario(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Veterinario WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateHistorialMedico(int id, int mascotaId, String fechaConsulta, String motivoConsulta, String diagnostico, String tratamiento, String notas) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "UPDATE HistorialMedico SET mascota_id = " + mascotaId + ", fecha_consulta = '" + fechaConsulta + "', motivo_consulta = '" + motivoConsulta + "', diagnostico = '" + diagnostico + "', tratamiento = '" + tratamiento + "', notas = '" + notas + "' WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteHistorialMedico(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "DELETE FROM HistorialMedico WHERE id = " + id;
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////
    // Método para crear una nueva entrada de propietario en la base de datos
    private void crearPropietario(String nombre, String direccion) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Propietario (nombre, direccion) VALUES ('" + nombre + "', '" + direccion + "')";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Método para crear una nueva entrada de mascota en la base de datos
    private void crearMascota(String nombre, String tipo, int propietarioId, int veterinarioId, String fechaDeNacimiento) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Mascota (nombre, tipo, fecha_de_nacimiento, propietario_id, veterinario_id) VALUES ('" + nombre + "', '" + tipo + "', '" + fechaDeNacimiento + "', " + propietarioId + ", " + veterinarioId + ")";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearMedicamento(String nombre, String tipo, int mascotaId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Medicamentos (nombre, tipo, mascota_id) VALUES ('" + nombre + "', '" + tipo + "', " + mascotaId + ")";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearVeterinario(String nombre, String especializacion) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Veterinario (nombre, especializacion) VALUES ('" + nombre + "', '" + especializacion + "')";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearHistorialMedico(int mascotaId, String fechaConsulta, String motivoConsulta, String diagnostico, String tratamiento, String notas) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO HistorialMedico (mascota_id, fecha_consulta, motivo_consulta, diagnostico, tratamiento, notas) VALUES (" + mascotaId + ", '" + fechaConsulta + "', '" + motivoConsulta + "', '" + diagnostico + "', '" + tratamiento + "', '" + notas + "')";
            statement.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////
    private void loadData() {
        ObservableList<Propietario> propietarioData = FXCollections.observableArrayList();
        ObservableList<Mascota> mascotaData = FXCollections.observableArrayList();
        ObservableList<Medicamentos> medicamentosData = FXCollections.observableArrayList();
        ObservableList<Veterinario> veterinariosData = FXCollections.observableArrayList();
        ObservableList<HistorialMedico> historialMedicoData = FXCollections.observableArrayList(); // Nueva lista para historial médico
        ObservableList<Integer> propietarioIds = FXCollections.observableArrayList();
        ObservableList<Integer> mascotaIds = FXCollections.observableArrayList();
        ObservableList<Integer> medicamentosIds = FXCollections.observableArrayList();
        ObservableList<Integer> veterinariosIds = FXCollections.observableArrayList();
        ObservableList<Integer> historialMedicoIds = FXCollections.observableArrayList(); // Nueva lista para IDs de historial médico

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();

            // Cargar datos de propietarios
            ResultSet propietarioResultSet = statement.executeQuery("SELECT * FROM Propietario");
            while (propietarioResultSet.next()) {
                int id = propietarioResultSet.getInt("id");
                String nombre = propietarioResultSet.getString("nombre");
                String direccion = propietarioResultSet.getString("direccion");
                propietarioData.add(new Propietario(id, nombre, direccion));
                propietarioIds.add(id);
            }

            // Cargar datos de mascotas
            ResultSet mascotaResultSet = statement.executeQuery("SELECT * FROM Mascota");
            while (mascotaResultSet.next()) {
                int id = mascotaResultSet.getInt("id");
                String nombre = mascotaResultSet.getString("nombre");
                String tipo = mascotaResultSet.getString("tipo");
                int propietarioId = mascotaResultSet.getInt("propietario_id");
                int veterinarioId = mascotaResultSet.getInt("veterinario_id");
                String fechaDeNacimiento = mascotaResultSet.getString("fecha_de_nacimiento");
                mascotaData.add(new Mascota(id, nombre, tipo, propietarioId, veterinarioId, fechaDeNacimiento));
                mascotaIds.add(id);
            }

            // Cargar datos de medicamentos
            ResultSet medicamentosResultSet = statement.executeQuery("SELECT * FROM Medicamentos");
            while (medicamentosResultSet.next()) {
                int id = medicamentosResultSet.getInt("id");
                String nombre = medicamentosResultSet.getString("nombre");
                String tipo = medicamentosResultSet.getString("tipo");
                int mascotaId = medicamentosResultSet.getInt("mascota_id");
                medicamentosData.add(new Medicamentos(id, nombre, tipo, mascotaId));
                medicamentosIds.add(id);
            }

            // Cargar datos de veterinarios
            ResultSet veterinarioResultSet = statement.executeQuery("SELECT * FROM Veterinario");
            while (veterinarioResultSet.next()) {
                int id = veterinarioResultSet.getInt("id");
                String nombre = veterinarioResultSet.getString("nombre");
                String especializacion = veterinarioResultSet.getString("especializacion");
                veterinariosData.add(new Veterinario(id, nombre, especializacion));
                veterinariosIds.add(id);
            }

            // Cargar datos de historial médico
            ResultSet historialMedicoResultSet = statement.executeQuery("SELECT * FROM HistorialMedico");
            while (historialMedicoResultSet.next()) {
                int id = historialMedicoResultSet.getInt("id");
                int mascotaId = historialMedicoResultSet.getInt("mascota_id");
                String fechaConsulta = historialMedicoResultSet.getString("fecha_consulta");
                String motivoConsulta = historialMedicoResultSet.getString("motivo_consulta");
                String diagnostico = historialMedicoResultSet.getString("diagnostico");
                String tratamiento = historialMedicoResultSet.getString("tratamiento");
                String notas = historialMedicoResultSet.getString("notas");
                historialMedicoData.add(new HistorialMedico(id, mascotaId, fechaConsulta, motivoConsulta, diagnostico, tratamiento, notas));
                historialMedicoIds.add(id);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        propietarioTable.setItems(propietarioData);
        mascotaTable.setItems(mascotaData);
        medicamentosTable.setItems(medicamentosData);
        veterinariosTable.setItems(veterinariosData);
        historialMedicoTable.setItems(historialMedicoData); // Configurar tabla de historial médico
        propietarioIdComboBox.setItems(propietarioIds);
        mascotaIdComboBox.setItems(mascotaIds);
        medicamentosIdComboBox.setItems(medicamentosIds);
        veterinariosIdComboBox.setItems(veterinariosIds);
        historialMedicoIdComboBox.setItems(historialMedicoIds);

    }

   private void loadPropietarioData() {
    Integer id = propietarioIdComboBox.getValue();
    if (id != null) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Propietario WHERE id = " + id);
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                ((TextField) ((GridPane) editarPropietarioTab.getContent()).getChildren().get(3)).setText(nombre);
                ((TextField) ((GridPane) editarPropietarioTab.getContent()).getChildren().get(5)).setText(direccion);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    private void loadMascotaData() {
        Integer id = mascotaIdComboBox.getValue();
        if (id != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Mascota WHERE id = " + id);
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String tipo = resultSet.getString("tipo");
                    int propietarioId = resultSet.getInt("propietario_id");
                    int veterinarioId = resultSet.getInt("veterinario_id");
                    String fechaDeNacimiento = resultSet.getString("fecha_de_nacimiento"); // Corregido el nombre de la columna

                    ((TextField) ((GridPane) editarMascotaTab.getContent()).getChildren().get(3)).setText(nombre);
                    ((TextField) ((GridPane) editarMascotaTab.getContent()).getChildren().get(5)).setText(tipo);
                    ((TextField) ((GridPane) editarMascotaTab.getContent()).getChildren().get(7)).setText(String.valueOf(propietarioId));
                    ((TextField) ((GridPane) editarMascotaTab.getContent()).getChildren().get(9)).setText(String.valueOf(veterinarioId)); // Mostrar el ID del veterinario
                    ((TextField) ((GridPane) editarMascotaTab.getContent()).getChildren().get(11)).setText(fechaDeNacimiento); // Mostrar la fecha de nacimiento
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadMedicamentosData() {
        Integer id = medicamentosIdComboBox.getValue();
        if (id != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Medicamentos WHERE id = " + id);
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String tipo = resultSet.getString("tipo");
                    int mascotaId = resultSet.getInt("mascota_id");
                    ((TextField) ((GridPane) editarMedicamentoTab.getContent()).getChildren().get(3)).setText(nombre);
                    ((TextField) ((GridPane) editarMedicamentoTab.getContent()).getChildren().get(5)).setText(tipo);
                    ((TextField) ((GridPane) editarMedicamentoTab.getContent()).getChildren().get(7)).setText(String.valueOf(mascotaId));
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadveterinarioData() {
        Integer id = veterinariosIdComboBox.getValue();
        if (id != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Veterinario WHERE id = " + id);
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String especializacion = resultSet.getString("direccion");
                    ((TextField) ((GridPane) editarPropietarioTab.getContent()).getChildren().get(3)).setText(nombre);
                    ((TextField) ((GridPane) editarPropietarioTab.getContent()).getChildren().get(5)).setText(especializacion);
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadHistorialMedicoData() {
        Integer id = historialMedicoIdComboBox.getValue();
        if (id != null) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetsDatabase", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM HistorialMedico WHERE id = " + id);
                if (resultSet.next()) {
                    int mascotaId = resultSet.getInt("mascota_id");
                    String fechaConsulta = resultSet.getString("fecha_consulta"); // Aquí obtenemos la fecha como String
                    String motivoConsulta = resultSet.getString("motivo_consulta");
                    String diagnostico = resultSet.getString("diagnostico");
                    String tratamiento = resultSet.getString("tratamiento");
                    String notas = resultSet.getString("notas");

                    // Configurar los campos correspondientes en la interfaz gráfica
                    ((TextField) ((GridPane) editarhistorialMedicoTab.getContent()).getChildren().get(3)).setText(String.valueOf(mascotaId));
                    ((TextField) ((GridPane) editarhistorialMedicoTab.getContent()).getChildren().get(5)).setText(fechaConsulta);
                    ((TextField) ((GridPane) editarhistorialMedicoTab.getContent()).getChildren().get(7)).setText(motivoConsulta);
                    ((TextField) ((GridPane) editarhistorialMedicoTab.getContent()).getChildren().get(9)).setText(diagnostico);
                    ((TextField) ((GridPane) editarhistorialMedicoTab.getContent()).getChildren().get(11)).setText(tratamiento);
                    ((TextField) ((GridPane) editarhistorialMedicoTab.getContent()).getChildren().get(13)).setText(notas);
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
