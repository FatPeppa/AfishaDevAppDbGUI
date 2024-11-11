package org.skyhigh.afishadevappgui.controller.tables;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;
import org.skyhigh.afishadevappgui.data.repository.DeploymentRepository;
import org.skyhigh.afishadevappgui.data.repository.DeploymentRepositoryImpl;
import org.skyhigh.afishadevappgui.data.repository.SecretRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.deploy.DeployService;
import org.skyhigh.afishadevappgui.service.logic.deploy.DeployServiceImpl;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.*;

public class DeploymentTableController {
    @FXML
    Button deployChosenBuiltBt;

    @FXML
    private TableView<Deployment> deploymentTable;

    @FXML
    private TableColumn<Deployment, UUID> deploymentIdDeploymentTableColumn;

    @FXML
    private TableColumn<Deployment, UUID> deploymentStatusIdDeploymentTableColumn;

    @FXML
    private TableColumn<Deployment, String> deploymentPathDeploymentTableColumn;

    @FXML
    private TableColumn<Deployment, JSONObject> settingsDeploymentTableColumn;

    @FXML
    private TableColumn<Deployment, String> builtVersionDeploymentTableColumn;

    @FXML
    private TableColumn<Deployment, JSONObject> builtSettingsDeploymentTableColumn;

    @FXML
    private TableColumn<Deployment, File> builtDeploymentTableColumn;

    @FXML
    private TableColumn<Deployment, UUID> projectIdDeploymentTableColumn;

    private final DeploymentRepository deploymentRepository = new DeploymentRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    private final DeployService deployService = new DeployServiceImpl(
            deploymentRepository,
            new SecretRepositoryImpl(
                    ApplicationPropertiesReader.getApplicationProperties()
            )
    );

    @Getter
    private Deployment selectedDeployment;

    public DeploymentTableController() throws CommonFlkException {}

    public void initialize() {
        deploymentIdDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("deploymentId"));
        deploymentStatusIdDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("deploymentStatusId"));
        deploymentPathDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("deploymentPath"));
        settingsDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("settings"));
        builtVersionDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("builtVersion"));
        builtSettingsDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("builtSettings"));
        builtDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("built"));
        projectIdDeploymentTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        setDeployChosenBuiltBtOnAction();
        setDeploymentTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<Deployment> deploymentListView = FXCollections.observableArrayList();
        List<Deployment> deployments = deploymentRepository.getAllDeployments(
                SortDirection.NONE,
                null
        );
        deploymentListView.addAll(deployments);
        deploymentTable.setItems(deploymentListView);
    }

    public void fillTable(List<Deployment> deployments) {
        ObservableList<Deployment> deploymentObservableList = FXCollections.observableArrayList();
        deploymentObservableList.addAll(deployments);
        deploymentTable.setItems(deploymentObservableList);
    }

    @Data
    @AllArgsConstructor
    private class DeployBuiltParams {
        private UUID deploymentId;
        private String address;
        private String login;
        private String password;
    }

    private DeployBuiltParams showBuildDeploymentDialog(Deployment deployment) {
        Dialog<DeployBuiltParams> dialog = new Dialog<>();
        dialog.setTitle("Ввод данных");
        dialog.setHeaderText("Ввод данных для выполнения развертывания");

        dialog.setResizable(true);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        dialog.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

        dialog.setGraphic(new ImageView(AfishaDevGUIApplication.class.getResource("images/deployment-pic.png").toString()));

        ButtonType deployBuilt = new ButtonType("Развернуть сборку", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deployBuilt, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField deploymentIdTextField = new TextField();
        deploymentIdTextField.setPromptText("deploymentId");
        deploymentIdTextField.setText(deployment.getDeploymentId().toString());
        Text text = new Text(deployment.getDeploymentId().toString());
        text.setFont(deploymentIdTextField.getFont());
        double textWidth = text.getLayoutBounds().getWidth();
        deploymentIdTextField.setPrefWidth(textWidth + 7);
        deploymentIdTextField.setMinHeight(Region.USE_PREF_SIZE);
        deploymentIdTextField.setDisable(true);

        TextField addressTextField = new TextField();
        addressTextField.setPromptText("Введите адрес развертывания (обяз.)");
        addressTextField.setMinHeight(Region.USE_PREF_SIZE);
        text = new Text(addressTextField.getPromptText());
        text.setFont(addressTextField.getFont());
        textWidth = text.getLayoutBounds().getWidth();
        addressTextField.setPrefWidth(textWidth + 7);

        TextField loginTextField = new TextField();
        loginTextField.setPromptText("Введите логин доступа к развертыванию (обяз.)");
        loginTextField.setMinHeight(Region.USE_COMPUTED_SIZE);
        text = new Text(loginTextField.getPromptText());
        text.setFont(loginTextField.getFont());
        textWidth = text.getLayoutBounds().getWidth();
        loginTextField.setPrefWidth(textWidth + 7);

        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("Введите пароль доступа к развертыванию (необяз.)");
        passwordTextField.setMinHeight(Region.USE_PREF_SIZE);
        text = new Text(passwordTextField.getPromptText());
        text.setFont(passwordTextField.getFont());
        textWidth = text.getLayoutBounds().getWidth();
        passwordTextField.setPrefWidth(textWidth + 15);

        grid.add(new Label("ID развертывания:"), 0, 0);
        grid.add(deploymentIdTextField, 1, 0);
        grid.add(new Label("Адрес развертывания:"), 0, 1);
        grid.add(addressTextField, 1, 1);
        grid.add(new Label("Логин доступа к развертыванию:"), 0, 2);
        grid.add(loginTextField, 1, 2);
        grid.add(new Label("Пароль доступа к развертыванию:"), 0, 3);
        grid.add(passwordTextField, 1, 3);

        Node loginButton = dialog.getDialogPane().lookupButton(deployBuilt);
        loginButton.setDisable(true);

        deploymentIdTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty() || addressTextField.getText().isEmpty() || loginTextField.getText().isEmpty());
        });

        addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty() || deploymentIdTextField.getText().isEmpty() || loginTextField.getText().isEmpty());
        });

        loginTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty() || deploymentIdTextField.getText().isEmpty() || addressTextField.getText().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(addressTextField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deployBuilt)
                return new DeployBuiltParams(
                        UUID.fromString(deploymentIdTextField.getText()),
                        addressTextField.getText(),
                        loginTextField.getText(),
                        passwordTextField.getText()
                );
            return null;
        });

        Optional<DeployBuiltParams> result = dialog.showAndWait();

        return result.orElse(null);
    }

    private void setDeployChosenBuiltBtOnAction() {
        deployChosenBuiltBt.setOnAction(event -> {
            try {
                if (selectedDeployment != null) {
                    DeployBuiltParams params = showBuildDeploymentDialog(selectedDeployment);
                    if (params != null) {
                        if (!deployService.checkIfTheDeploymentIsAbleToBeDeployed(params.getDeploymentId())) {
                            showCommonWarningAlert("Для развертывания сборки все поля соответствующей записи должны быть заполнены");
                            return;
                        }
                        if (deployService.checkIfTheBuiltIsDeployed(params.getDeploymentId(), params.getAddress())) {
                            showCommonWarningAlert("Развертывание указанной записи со сборкой уже было осуществлено");
                            return;
                        }
                        deploymentRepository.deployBuilt(
                                params.getDeploymentId(),
                                params.getAddress(),
                                params.getLogin(),
                                params.getPassword().isEmpty() ? null : params.getPassword()
                        );
                        showInformationDialog("Успешное развертывание", "Развертывание '" + params.getDeploymentId() + "' было опубликовано успешно");
                    }
                } else {
                    showCommonWarningAlert("Для развертывания сборки необходимо выбрать разворачиваемую запись");
                }
            } catch (CommonFlkException e) {
                showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setDeploymentTableSelectedItemPropertyListener() {
        deploymentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedDeployment = newSelection;
        });
    }

    public void clearSelection() {
        deploymentTable.getSelectionModel().clearSelection();
        selectedDeployment = null;
    }

    public ObservableValue<Deployment> getObservableSelectedDeployment() {
        return deploymentTable.getSelectionModel().selectedItemProperty();
    }
}
