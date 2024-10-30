package org.skyhigh.afishadevappgui.controller.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;
import org.skyhigh.afishadevappgui.data.repository.DeploymentRepository;
import org.skyhigh.afishadevappgui.data.repository.DeploymentRepositoryImpl;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class DeploymentTableController {
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
}
