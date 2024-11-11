package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;
import org.skyhigh.afishadevappgui.data.repository.AccessedRoleRepository;
import org.skyhigh.afishadevappgui.data.repository.AccessedRoleRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class AccessedRoleTableController {
    @FXML
    private TableView<AccessedRole> accessedRoleTableView;

    @FXML
    private TableColumn<AccessedRole, UUID> requirementIdAccessedRoleColumn;

    @FXML
    private TableColumn<AccessedRole, String> roleNameAccessedRoleColumn;

    private final AccessedRoleRepository accessedRoleRepository = new AccessedRoleRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    private AccessedRole selectedAccessedRole;

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
}
