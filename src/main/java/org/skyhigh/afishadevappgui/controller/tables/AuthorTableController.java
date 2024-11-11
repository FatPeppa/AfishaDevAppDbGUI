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
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;
import org.skyhigh.afishadevappgui.data.repository.AuthorRepository;
import org.skyhigh.afishadevappgui.data.repository.AuthorRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class AuthorTableController {
    @FXML
    private TableView<Author> authorTable;

    @FXML
    private TableColumn<Author, UUID> authorIdAuthorColumn;

    @FXML
    private TableColumn<Author, String> loginAuthorTableColumn;

    private final AuthorRepository authorRepository = new AuthorRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private Author selectedAuthor;

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
}
