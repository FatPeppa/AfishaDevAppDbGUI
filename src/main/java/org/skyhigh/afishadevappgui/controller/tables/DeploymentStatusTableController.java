package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;

import java.util.UUID;

public class DeploymentStatusTableController {
    @FXML
    private TableView<DeploymentStatus> deploymentStatusTable;

    @FXML
    private TableColumn<DeploymentStatus, UUID> deploymentStatusIdDSTableColumn;

    @FXML
    private TableColumn<DeploymentStatus, String> statusNameDSTableColumn;
}
