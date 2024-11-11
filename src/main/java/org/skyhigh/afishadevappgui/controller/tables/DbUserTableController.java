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
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepository;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class DbUserTableController {
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

    private final DbUserRepository dbUserRepository = new DbUserRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private DbUser selectedDbUser;

    public DbUserTableController() throws CommonFlkException {}

    public void initialize() {
        userIdDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        authorIdDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        userLoginDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
        userPassDbUserTableColumn.setCellValueFactory(new PropertyValueFactory<>("userPass"));
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
}
