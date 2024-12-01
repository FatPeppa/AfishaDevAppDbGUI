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
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepository;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class DbUserTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = false;
    private static final boolean isViewableForDeveloper = false;
    private static final boolean isViewableForQA= false;
    private static final boolean isViewableForDevOps = false;
    private static final boolean isEditableForAnalyst = false;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= false;
    private static final boolean isEditableForDevOps = false;

    @FXML
    private TableView<DbUser> dbUserTable;

    @FXML
    private TableColumn<DbUser, UUID> userIdDbUserTableColumn;

    @FXML
    private TableColumn<DbUser, UUID> authorIdDbUserTableColumn;

    @FXML
    private TableColumn<DbUser, String> userLoginDbUserTableColumn;

    @FXML
    private TableColumn<DbUser, String> userPassDbUserTableColumn;

    @FXML
    private TableColumn<DbUser, Integer> roleIdTableColumn;

    private final DbUserRepository dbUserRepository = new DbUserRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private DbUser selectedDbUser;

    public DbUserTableController() throws CommonFlkException {}

    public void initialize() {
        userIdDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        authorIdDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        userLoginDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
        userPassDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("userPass"));
        roleIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("systemRoleId"));
        setDbUserTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<DbUser> usersListView = FXCollections.observableArrayList();
        List<DbUser> users = dbUserRepository.getAllDbUsers(
                SortDirection.NONE,
                null
        );
        usersListView.addAll(users);
        dbUserTable.setItems(usersListView);
    }

    public void fillTable(List<DbUser> dbUsers) {
        ObservableList<DbUser> dbUserObservableList = FXCollections.observableArrayList();
        dbUserObservableList.addAll(dbUsers);
        dbUserTable.setItems(dbUserObservableList);
    }

    private void setDbUserTableSelectedItemPropertyListener() {
        dbUserTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedDbUser = newSelection;
        });
    }

    public void clearSelection() {
        dbUserTable.getSelectionModel().clearSelection();
        selectedDbUser = null;
    }

    public ObservableValue<DbUser> getObservableSelectedDbUser() {
        return dbUserTable.getSelectionModel().selectedItemProperty();
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
}
