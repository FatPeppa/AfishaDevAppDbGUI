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
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;
import org.skyhigh.afishadevappgui.data.repository.RequirementAuthorRepository;
import org.skyhigh.afishadevappgui.data.repository.RequirementAuthorRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class RequirementAuthorTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = true;
    private static final boolean isViewableForDeveloper = true;
    private static final boolean isViewableForQA= true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = true;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= true;
    private static final boolean isEditableForDevOps = false;

    @FXML
    private TableView<RequirementAuthor> requirementAuthorTable;

    @FXML
    private TableColumn<RequirementAuthor, UUID> requirementIdRequirementTableColumn;

    @FXML
    private TableColumn<RequirementAuthor, UUID> authorIdRequirementTableColumn;

    @Getter
    private RequirementAuthor selectedRequirementAuthor;

    private final RequirementAuthorRepository requirementAuthorRepository = new RequirementAuthorRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    public RequirementAuthorTableController() throws CommonFlkException {}

    public void initialize() {
        requirementIdRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("requirementId"));
        authorIdRequirementTableColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        setRequirementAuthorTableSelectedItemPropertyListener();
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

    private void setRequirementAuthorTableSelectedItemPropertyListener() {
        requirementAuthorTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedRequirementAuthor = newSelection;
        });
    }

    public void clearSelection() {
        requirementAuthorTable.getSelectionModel().clearSelection();
        selectedRequirementAuthor = null;
    }

    public ObservableValue<RequirementAuthor> getObservableSelectedRequirementAuthor() {
        return requirementAuthorTable.getSelectionModel().selectedItemProperty();
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
