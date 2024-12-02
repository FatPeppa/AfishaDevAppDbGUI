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
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;
import org.skyhigh.afishadevappgui.data.repository.AccessedRoleRepository;
import org.skyhigh.afishadevappgui.data.repository.AccessedRoleRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.util.List;
import java.util.UUID;

public class AccessedRoleTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = true;
    private static final boolean isViewableForDeveloper = true;
    private static final boolean isViewableForQA= true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = true;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= true;
    private static final boolean isEditableForDevOps = false;

    @FXML
    private TableView<AccessedRole> accessedRoleTableView;

    @FXML
    private TableColumn<AccessedRole, UUID> requirementIdAccessedRoleColumn;

    @FXML
    private TableColumn<AccessedRole, String> roleNameAccessedRoleColumn;

    private final AccessedRoleRepository accessedRoleRepository = new AccessedRoleRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    private AccessedRole selectedAccessedRole;

    private RoleManagerService roleManagerService;

    public AccessedRoleTableController() throws CommonFlkException {}

    public void initialize() {
        requirementIdAccessedRoleColumn.setCellValueFactory(new PropertyValueFactory<>("requirementId"));
        roleNameAccessedRoleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        setAccessedRoleTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<AccessedRole> accessedRolesListView = FXCollections.observableArrayList();
        List<AccessedRole> roles = accessedRoleRepository.getAllAccessedRoles(
                SortDirection.NONE,
                null
        );
        accessedRolesListView.addAll(roles);
        accessedRoleTableView.setItems(accessedRolesListView);
    }

    public void fillTable(List<AccessedRole> roles) {
        ObservableList<AccessedRole> accessedRolesListView = FXCollections.observableArrayList();
        accessedRolesListView.addAll(roles);
        accessedRoleTableView.setItems(accessedRolesListView);
    }

    private void setAccessedRoleTableSelectedItemPropertyListener() {
        accessedRoleTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedAccessedRole = newSelection;
        });
    }

    public void clearSelection() {
        accessedRoleTableView.getSelectionModel().clearSelection();
        selectedAccessedRole = null;
    }

    public ObservableValue<AccessedRole> getObservableSelectedAccessedRole() {
        return accessedRoleTableView.getSelectionModel().selectedItemProperty();
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
    public void setRoleManagerService(RoleManagerService roleManagerService) {
        this.roleManagerService = roleManagerService;
    }
}
