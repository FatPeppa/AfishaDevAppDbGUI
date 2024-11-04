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
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;
import org.skyhigh.afishadevappgui.data.repository.RequirementAuthorRepository;
import org.skyhigh.afishadevappgui.data.repository.RequirementAuthorRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class RequirementAuthorTableController {
    @FXML
    private TableView<RequirementAuthor> requirementAuthorTable;

    @FXML
    private TableColumn<RequirementAuthor, UUID> requirementIdRequirementTableColumn;

    @FXML
    private TableColumn<RequirementAuthor, UUID> authorIdRequirementTableColumn;

    private final RequirementAuthorRepository requirementAuthorRepository = new RequirementAuthorRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    public RequirementAuthorTableController() throws CommonFlkException {}

    public void initialize() {
        requirementIdRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementId"));
        authorIdRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<RequirementAuthor> requirementAuthorsListView = FXCollections.observableArrayList();
        List<RequirementAuthor> requirementAuthors = requirementAuthorRepository.getAllRequirementAuthors(
                SortDirection.NONE,
                null
        );
        requirementAuthorsListView.addAll(requirementAuthors);
        requirementAuthorTable.setItems(requirementAuthorsListView);
    }

    public void fillTable(List<RequirementAuthor> requirementAuthors) {
        ObservableList<RequirementAuthor> requirementAuthorObservableList = FXCollections.observableArrayList();
        requirementAuthorObservableList.addAll(requirementAuthors);
        requirementAuthorTable.setItems(requirementAuthorObservableList);
    }
}
