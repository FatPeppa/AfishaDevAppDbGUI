package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.io.File;
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
}
