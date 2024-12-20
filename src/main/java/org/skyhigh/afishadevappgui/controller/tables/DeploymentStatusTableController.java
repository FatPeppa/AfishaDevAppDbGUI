package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.skyhigh.afishadevappgui.common.controller.RoleManagedTableController;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepository;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.util.List;
import java.util.UUID;

public class DeploymentStatusTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = false;
    private static final boolean isViewableForDeveloper = true;
    private static final boolean isViewableForQA= true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = false;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= false;
    private static final boolean isEditableForDevOps = true;

    @FXML
    private TableView<DeploymentStatus> deploymentStatusTable;

    @FXML
    private TableColumn<DeploymentStatus, UUID> deploymentStatusIdDSTableColumn;

    @FXML
    private TableColumn<DeploymentStatus, String> statusNameDSTableColumn;

    private final DeploymentStatusRepository deploymentStatusRepository = new DeploymentStatusRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private DeploymentStatus selectedDeploymentStatus;

    private RoleManagerService roleManagerService;

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

    @Override
    public boolean getAccessibilityForViewingByAnalyst() {
        return isViewableForAnalyst;
    }

    @Override
    public boolean getAccessibilityForViewingByDeveloper() {
        return isViewableForDeveloper;
    }

    @Override
    public boolean getAccessibilityForViewingByQA() {
        return isViewableForQA;
    }

    @Override
    public boolean getAccessibilityForViewingByDevOps() {
        return isViewableForDevOps;
    }

    @Override
    public boolean getAccessibilityForEditingByAnalyst() {
        return isEditableForAnalyst;
    }

    @Override
    public boolean getAccessibilityForEditingByDeveloper() {
        return isEditableForDeveloper;
    }

    @Override
    public boolean getAccessibilityForEditingByQA() {
        return isEditableForQA;
    }

    @Override
    public boolean getAccessibilityForEditingByDevOps() {
        return isEditableForDevOps;
    }

    @Override
    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;
    }
}
