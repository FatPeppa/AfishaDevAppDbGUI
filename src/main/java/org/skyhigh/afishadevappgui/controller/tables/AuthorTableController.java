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
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;
import org.skyhigh.afishadevappgui.data.repository.AuthorRepository;
import org.skyhigh.afishadevappgui.data.repository.AuthorRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.util.List;
import java.util.UUID;

public class AuthorTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = true;
    private static final boolean isViewableForDeveloper = true;
    private static final boolean isViewableForQA= true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = false;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= false;
    private static final boolean isEditableForDevOps = false;

    @FXML
    private TableView<Author> authorTable;

    @FXML
    private TableColumn<Author, UUID> authorIdAuthorColumn;

    @FXML
    private TableColumn<Author, String> loginAuthorTableColumn;

    private final AuthorRepository authorRepository = new AuthorRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private Author selectedAuthor;

    private RoleManagerService roleManagerService;

    public AuthorTableController() throws CommonFlkException {}

    public void initialize() {
        authorIdAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        loginAuthorTableColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        setAuthorTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<Author> authorsListView = FXCollections.observableArrayList();
        List<Author> authors = authorRepository.getAllAuthors(
                SortDirection.NONE,
                null
        );
        authorsListView.addAll(authors);
        authorTable.setItems(authorsListView);
    }

    public void fillTable(List<Author> authors) {
        ObservableList<Author> authorsListView = FXCollections.observableArrayList();
        authorsListView.addAll(authors);
        authorTable.setItems(authorsListView);
    }

    private void setAuthorTableSelectedItemPropertyListener() {
        authorTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedAuthor = newSelection;
        });
    }

    public void clearSelection() {
        authorTable.getSelectionModel().clearSelection();
        selectedAuthor = null;
    }

    public ObservableValue<Author> getObservableSelectedAuthor() {
        return authorTable.getSelectionModel().selectedItemProperty();
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
