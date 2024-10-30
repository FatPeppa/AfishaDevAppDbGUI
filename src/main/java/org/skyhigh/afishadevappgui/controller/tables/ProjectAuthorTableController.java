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
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;
import org.skyhigh.afishadevappgui.data.repository.ProjectAuthorRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectAuthorRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class ProjectAuthorTableController {
    @FXML
    private TableView<ProjectAuthor> projectAuthorTable;

    @FXML
    private TableColumn<ProjectAuthor, UUID> projectIdProjectAuthorTableColumn;

    @FXML
    private TableColumn<ProjectAuthor, UUID> authorIdProjectAuthorTableColumn;

    private final ProjectAuthorRepository projectAuthorRepository = new ProjectAuthorRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    public ProjectAuthorTableController() throws CommonFlkException {}

    public void initialize() {
        projectIdProjectAuthorTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        authorIdProjectAuthorTableColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<ProjectAuthor> projectAuthorsListView = FXCollections.observableArrayList();
        List<ProjectAuthor> projectAuthors = projectAuthorRepository.getAllProjectAuthors(
                SortDirection.NONE,
                null
        );
        projectAuthorsListView.addAll(projectAuthors);
        projectAuthorTable.setItems(projectAuthorsListView);
    }
}
