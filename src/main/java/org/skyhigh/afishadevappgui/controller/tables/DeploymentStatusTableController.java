package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepository;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class DeploymentStatusTableController {
    @FXML
    private TableView<DeploymentStatus> deploymentStatusTable;

    @FXML
    private TableColumn<DeploymentStatus, UUID> deploymentStatusIdDSTableColumn;

    @FXML
    private TableColumn<DeploymentStatus, String> statusNameDSTableColumn;

    private final DeploymentStatusRepository deploymentStatusRepository = new DeploymentStatusRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private DeploymentStatus selectedDeploymentStatus;

    public DeploymentStatusTableController() throws CommonFlkException {}

    public void initialize() {
        deploymentStatusIdDSTableColumn.setCellValueFactory(new PropertyValueFactory<>("deploymentStatusId"));
        statusNameDSTableColumn.setCellValueFactory(new PropertyValueFactory<>("statusName"));
        setDeploymentStatusTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<DeploymentStatus> deploymentStatusesListView = FXCollections.observableArrayList();
        List<DeploymentStatus> deploymentStatuses = deploymentStatusRepository.getAllDeploymentStatuses(
                SortDirection.NONE,
                null
        );
        deploymentStatusesListView.addAll(deploymentStatuses);
        deploymentStatusTable.setItems(deploymentStatusesListView);
    }

    public void fillTable(List<DeploymentStatus> deploymentStatuses) {
        ObservableList<DeploymentStatus> deploymentStatusObservableList = FXCollections.observableArrayList();
        deploymentStatusObservableList.addAll(deploymentStatuses);
        deploymentStatusTable.setItems(deploymentStatusObservableList);
    }

    private void setDeploymentStatusTableSelectedItemPropertyListener() {
        deploymentStatusTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedDeploymentStatus = newSelection;
        });
    }

    public void clearSelection() {
        deploymentStatusTable.getSelectionModel().clearSelection();
        selectedDeploymentStatus = null;
    }

    public ObservableValue<DeploymentStatus> getObservableSelectedDeploymentStatus() {
        return deploymentStatusTable.getSelectionModel().selectedItemProperty();
    }
}
