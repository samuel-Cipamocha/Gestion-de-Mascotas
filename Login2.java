package APP_EMPLEADOS_CRUD_SQL;

import Controlador.controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Base64;

import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class Login2 extends Application {

    private Stage primaryStage;
    private Stage menuStage;
    private ListView<String> userListView;

    public static void main(String[] args) {
        launch(args);
    }

    private <T> T loadResource(String path, Class<T> resourceType) {
        InputStream resourceStream = getClass().getResourceAsStream(path);
        if (resourceStream == null) {
            throw new IllegalArgumentException("El recurso no se pudo encontrar: " + path);
        }

        if (resourceType == Image.class) {
            return resourceType.cast(new Image(resourceStream));
        } else if (resourceType == String.class) {
            return resourceType.cast(path);
        } else {
            throw new IllegalArgumentException("Tipo de recurso no manejado");
        }
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Inicio de Sesión - Gestor de Mascotas USTA");

        // Cargar recursos necesarios con el método auxiliar
        Image icon = loadResource("/APP_EMPLEADOS_CRUD_SQL/resources/icon.png", Image.class);
        primaryStage.getIcons().add(icon);

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

        Label usernameLabel = new Label("Nombre de Usuario:");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(screenWidth * 0.5);

        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(screenWidth * 0.5);
        Button loginButton = new Button("Iniciar Sesión");
        Button registerButton = new Button("Registrar nuevo usuario");
        Button forgotPasswordButton = new Button("Olvidé mi contraseña");
        Button changePasswordButton = new Button("Cambiar Contraseña");
        changePasswordButton.getStyleClass().add("hover-button");

        loginButton.getStyleClass().add("hover-button");
        registerButton.getStyleClass().add("hover-button");
        forgotPasswordButton.getStyleClass().add("hover-button");

        loginButton.setOnAction(event -> handleLogin(usernameField.getText(), passwordField.getText()));
        registerButton.setOnAction(event -> showRegisterForm());
        forgotPasswordButton.setOnAction(event -> showForgotPasswordDialog());
        changePasswordButton.setOnAction(event -> showChangePasswordDialog());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(loginButton, registerButton, forgotPasswordButton, changePasswordButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        Image bannerImage = loadResource("/APP_EMPLEADOS_CRUD_SQL/resources/banner.png", Image.class);
        ImageView bannerImageView = new ImageView(bannerImage);
        bannerImageView.setFitWidth(500);
        bannerImageView.setFitHeight(150);

        Label titleLabel = new Label("¡Bienvenido a la Aplicación de Gestión Mascotas!");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        titleLabel.setAlignment(Pos.CENTER);

        usernameField.setId("username-field");
        passwordField.setId("password-field");

        VBox formLayout = new VBox(10);
        formLayout.setStyle("-fx-background-color: #fff; -fx-background-radius: 10; -fx-padding: 20; -fx-spacing: 10;");
        formLayout.getChildren().addAll(bannerImageView, titleLabel, new Label(""), usernameLabel, usernameField, passwordLabel, passwordField, buttonBox);

        formLayout.setAlignment(Pos.CENTER);

        String stylePath = "/APP_EMPLEADOS_CRUD_SQL/resources/styles.css"; // Ajusta la ruta del archivo CSS según tu configuración
        Scene scene = new Scene(formLayout, screenWidth, Screen.getPrimary().getVisualBounds().getHeight());
        scene.getStylesheets().add(stylePath);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleLogin(usernameField.getText(), passwordField.getText());
            }
        });
    }

    private void handleLogin(String username, String password) {
        if (checkCredentials(username, password)) {
            openMenu();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de inicio de sesión");
            alert.setHeaderText("Credenciales incorrectas");
            alert.setContentText("Verifique su nombre de usuario y contraseña.");
            alert.showAndWait();
        }
    }

 private boolean checkCredentials(String username, String password) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("users.json"));

            for (JsonNode userNode : rootNode.get("users")) {
                String storedUsername = userNode.get("username").asText();
                String storedPassword = userNode.get("password").asText();

                if (storedUsername.equals(username) && BCrypt.checkpw(password, storedPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void openMenu() {
    // Cierra el escenario principal antes de abrir el nuevo
    primaryStage.close();

    // Crea el nuevo escenario del menú
    menuStage = new Stage();
    menuStage.setTitle("Menú Principal");

    // Obtener el ancho y alto de la pantalla
    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    double screenWidth = bounds.getWidth();
    double screenHeight = bounds.getHeight();

    // Configuración del escenario a pantalla completa
    menuStage.setWidth(screenWidth);
    menuStage.setHeight(screenHeight);

    // Crear el menú
    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("Archivo");
    MenuItem exitMenuItem = new MenuItem("Salir");
    exitMenuItem.setOnAction(event -> System.exit(0));
    MenuItem loadUsersMenuItem = new MenuItem("Cargar Lista de Usuarios");
    loadUsersMenuItem.setOnAction(event -> loadUserList());

    fileMenu.getItems().addAll(loadUsersMenuItem, exitMenuItem);

    Menu employeesMenu = new Menu("Gestor de Empleados");
    MenuItem manageEmployeesMenuItem = new MenuItem("Gestionar Empleados");
    manageEmployeesMenuItem.setOnAction(event -> openTabPaneDatabase());
    employeesMenu.getItems().add(manageEmployeesMenuItem);

    menuBar.getMenus().addAll(fileMenu, employeesMenu);

    // Crear lista de usuarios
    userListView = new ListView<>();
    userListView.setPrefHeight(screenHeight - menuBar.getHeight());

    VBox menuLayout = new VBox(menuBar, userListView);
    Scene menuScene = new Scene(menuLayout, screenWidth, screenHeight);

    menuStage.setScene(menuScene);
    menuStage.show();
}

   private void loadUserList() {
    try {
        // Crear un ObjectMapper para leer y parsear el archivo JSON
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");

        // Verificar si el archivo JSON existe y si no existe, crear un nuevo archivo vacío
        if (!file.exists() || file.length() == 0) {
            // Si el archivo JSON no existe o está vacío, inicializar un archivo vacío con el nodo "users"
            JsonNode emptyNode = objectMapper.createObjectNode().putArray("users");
            objectMapper.writeValue(file, emptyNode);
        }

        // Leer el archivo JSON y obtener el nodo "users"
        JsonNode rootNode = objectMapper.readTree(file);
        JsonNode usersNode = rootNode.get("users");

        // Crear una lista observable para almacenar los nombres de usuario
        ObservableList<String> userNames = FXCollections.observableArrayList();

        // Iterar sobre los nodos de usuario y agregar los nombres de usuario a la lista
        for (JsonNode userNode : usersNode) {
            String userName = userNode.get("username").asText();
            userNames.add(userName);
        }

        // Asignar la lista a la vista de lista de usuarios
        userListView.setItems(userNames);

    } catch (IOException e) {
        e.printStackTrace(); // Manejar la excepción adecuadamente
    }
}

    private void openTabPaneDatabase() {
        controller main = new controller();
        main.start(new Stage());
    }

 private void showRegisterForm() {
    Stage registerStage = new Stage();
    registerStage.setTitle("Registro de Usuario");

    Label idLabel = new Label("Identificación:");
    TextField idField = new TextField(); // Campo de entrada para la identificación
    Label usernameLabel = new Label("Nombre de Usuario:");
    TextField usernameField = new TextField();

    Label passwordLabel = new Label("Contraseña:");
    PasswordField passwordField = new PasswordField();
    Button registerButton = new Button("Registrar");
    registerButton.getStyleClass().add("hover-button");

    VBox formLayout = new VBox(10);
    formLayout.setStyle("-fx-background-color: #fff; -fx-background-radius: 10; -fx-padding: 20; -fx-spacing: 10;");
    formLayout.getChildren().addAll(idLabel, idField, usernameLabel, usernameField, passwordLabel, passwordField, registerButton);
    formLayout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(formLayout, 400, 300);
    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

    registerStage.setScene(scene);
    registerStage.show();

    registerButton.setOnAction(event -> {
        String id = idField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Validar campos
        if (id.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showErrorAlert("Error de registro", "Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        // Procesar el registro
        if (handleRegister(id, username, password)) {
            showInformationAlert("Registro exitoso", "Usuario registrado con éxito.");
            registerStage.close(); // Cierra la ventana de registro
        } else {
            showErrorAlert("Error de registro", "Nombre de usuario o ID ya existentes", "El nombre de usuario o la identificación ya están en uso.");
        }
    });
}

private boolean handleRegister(String id, String username, String password) {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("users.json");
        JsonNode rootNode;
        ArrayNode usersArray;

        if (!file.exists()) {
            rootNode = objectMapper.createObjectNode();
            usersArray = objectMapper.createArrayNode();
            ((ObjectNode) rootNode).set("users", usersArray);
            objectMapper.writeValue(file, rootNode); // Crear el archivo vacío
        } else {
            rootNode = objectMapper.readTree(file);
            usersArray = (ArrayNode) rootNode.get("users");
        }

        // Verificar si el ID o el nombre de usuario ya están en uso
        for (JsonNode userNode : usersArray) {
            if (userNode.get("id").asText().equals(id) || userNode.get("username").asText().equals(username)) {
                return false; // Ya existen registros
            }
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Crear y agregar el nuevo usuario
        ObjectNode newUser = objectMapper.createObjectNode();
        newUser.put("id", id);
        newUser.put("username", username);
        newUser.put("password", hashedPassword);
        usersArray.add(newUser);

        // Indentación para un formato más legible
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);

        return true;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

private void showErrorAlert(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
}

private void showInformationAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
}


    private void showForgotPasswordDialog() {
        Stage forgotPasswordStage = new Stage();
        forgotPasswordStage.setTitle("Recuperar Contraseña");

        Label idLabel = new Label("Identificación:");
        TextField idField = new TextField(); // Campo de entrada para la identificación
        Button resetButton = new Button("Restablecer Contraseña");
        resetButton.getStyleClass().add("hover-button");

        VBox formLayout = new VBox(10);
        formLayout.setStyle("-fx-background-color: #fff; -fx-background-radius: 10; -fx-padding: 20; -fx-spacing: 10;");
        formLayout.getChildren().addAll(idLabel, idField, resetButton);
        formLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(formLayout, 400, 200);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        forgotPasswordStage.setScene(scene);
        forgotPasswordStage.show();

        resetButton.setOnAction(event -> handlePasswordReset(idField.getText(), forgotPasswordStage));
    }

    private void handlePasswordReset(String id, Stage forgotPasswordStage) {
        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de restablecimiento");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Por favor, ingrese la identificación del usuario.");
            alert.showAndWait();
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("users.json");
            JsonNode rootNode = objectMapper.readTree(file);
            ArrayNode usersArray = (ArrayNode) rootNode.get("users");

            for (JsonNode userNode : usersArray) {
                JsonNode idNode = userNode.get("id");
                if (idNode != null && idNode.asText().equals(id)) {
                    String newTemporaryPassword = generateTemporaryPassword();
                    String hashedPassword = BCrypt.hashpw(newTemporaryPassword, BCrypt.gensalt());
                    ((ObjectNode) userNode).put("password", hashedPassword);

                    // Indentación para un formato más legible
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Contraseña restablecida");
                    alert.setHeaderText("Nueva contraseña temporal generada");
                    alert.setContentText("Su nueva contraseña temporal es: " + newTemporaryPassword);

                    // Copiar automáticamente la contraseña al portapapeles cuando se hace clic en el botón "Aceptar" de la alerta
                    alert.setOnShown(event -> {
                        Button copyButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                        copyButton.setText("Copiar contraseña");
                        copyButton.setOnAction(e -> {
                            Clipboard clipboard = Clipboard.getSystemClipboard();
                            ClipboardContent content = new ClipboardContent();
                            content.putString(newTemporaryPassword);
                            clipboard.setContent(content);
                            alert.close();
                        });
                    });

                    alert.showAndWait();

                    forgotPasswordStage.close();
                    return;
                }
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de restablecimiento");
            alert.setHeaderText("Usuario no encontrado");
            alert.setContentText("La identificación proporcionada no corresponde a ningún usuario registrado. Por favor, verifique e intente nuevamente.");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showChangePasswordDialog() {
        Stage changePasswordStage = new Stage();
        changePasswordStage.setTitle("Cambiar Contraseña");

        Label usernameLabel = new Label("Nombre de Usuario:");
        TextField usernameField = new TextField();
        Label currentPasswordLabel = new Label("Contraseña Actual:");
        PasswordField currentPasswordField = new PasswordField();
        Label newPasswordLabel = new Label("Nueva Contraseña:");
        PasswordField newPasswordField = new PasswordField();
        Button changePasswordButton = new Button("Cambiar Contraseña");
        changePasswordButton.getStyleClass().add("hover-button");

        VBox formLayout = new VBox(10);
        formLayout.setStyle("-fx-background-color: #fff; -fx-background-radius: 10; -fx-padding: 20; -fx-spacing: 10;");
        formLayout.getChildren().addAll(usernameLabel, usernameField, currentPasswordLabel, currentPasswordField, newPasswordLabel, newPasswordField, changePasswordButton);
        formLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(formLayout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        changePasswordStage.setScene(scene);
        changePasswordStage.show();

        changePasswordButton.setOnAction(event -> handleChangePassword(usernameField.getText(), currentPasswordField.getText(), newPasswordField.getText(), changePasswordStage));
    }

    private void handleChangePassword(String username, String currentPassword, String newPassword, Stage changePasswordStage) {
        try {
            // Crear un objeto ObjectMapper para leer y escribir JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Leer el archivo JSON de usuarios
            File file = new File("users.json");
            JsonNode rootNode = objectMapper.readTree(file);
            ArrayNode usersArray = (ArrayNode) rootNode.get("users");

            // Buscar el usuario en la lista de usuarios
            for (JsonNode userNode : usersArray) {
                // Obtener el nombre de usuario y la contraseña almacenada
                String storedUsername = userNode.get("username").asText();
                String storedPassword = userNode.get("password").asText();

                // Verificar si el nombre de usuario coincide con el proporcionado
                if (storedUsername.equals(username)) {
                    // Verificar si la contraseña actual coincide con la contraseña almacenada (usando BCrypt)
                    if (BCrypt.checkpw(currentPassword, storedPassword)) {
                        // Generar el hash de la nueva contraseña
                        String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

                        // Actualizar la contraseña almacenada en el JSON
                        ((ObjectNode) userNode).put("password", hashedNewPassword);

                        // Escribir el JSON actualizado de vuelta al archivo
                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);

                        // Mostrar un mensaje de éxito
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Cambio de Contraseña Exitoso");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("La contraseña ha sido cambiada exitosamente.");
                        successAlert.showAndWait();

                        // Cerrar la ventana de cambio de contraseña
                        changePasswordStage.close();

                        // Salir del método después de encontrar y actualizar el usuario
                        return;
                    } else {
                        // Mostrar un mensaje de error si la contraseña actual es incorrecta
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error de Cambio de Contraseña");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("La contraseña actual es incorrecta.");
                        errorAlert.showAndWait();
                        return;
                    }
                }
            }

            // Mostrar un mensaje de error si el nombre de usuario no se encuentra en el archivo JSON
            Alert userNotFoundErrorAlert = new Alert(Alert.AlertType.ERROR);
            userNotFoundErrorAlert.setTitle("Error de Cambio de Contraseña");
            userNotFoundErrorAlert.setHeaderText(null);
            userNotFoundErrorAlert.setContentText("El usuario no fue encontrado.");
            userNotFoundErrorAlert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private void openEmployeeManager() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
