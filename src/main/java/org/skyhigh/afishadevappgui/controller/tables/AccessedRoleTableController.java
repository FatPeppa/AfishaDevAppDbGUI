package org.skyhigh.afishadevappgui.controller.tables;

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

    public void fillTable() throws CommonFlkException {
        requirementIdAccessedRoleColumn.setCellValueFactory(new PropertyValueFactory<>("requirementId"));
        roleNameAccessedRoleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));

        AccessedRoleRepository accessedRoleRepository = new AccessedRoleRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());
        ObservableList<AccessedRole> authorsListView = FXCollections.observableArrayList();
        List<AccessedRole> roles = accessedRoleRepository.getAllAccessedRoles(
                SortDirection.NONE,
                null
        );
        authorsListView.addAll(roles);
        //tableExample.getItems().add(new Author(UUID.randomUUID(), "afs"));
        //tableExample.refresh();
        accessedRoleTableView.setItems(authorsListView);
    }
}
