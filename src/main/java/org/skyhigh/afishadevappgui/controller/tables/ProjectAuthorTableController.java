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
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;
import org.skyhigh.afishadevappgui.data.repository.ProjectAuthorRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectAuthorRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.util.List;
import java.util.UUID;

public class ProjectAuthorTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = false;
    private static final boolean isViewableForDeveloper = false;
    private static final boolean isViewableForQA= false;
    private static final boolean isViewableForDevOps = false;
    private static final boolean isEditableForAnalyst = false;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= false;
    private static final boolean isEditableForDevOps = false;

    @FXML
    private TableView<ProjectAuthor> projectAuthorTable;

    @FXML
    private TableColumn<ProjectAuthor, UUID> projectIdProjectAuthorTableColumn;

    @FXML
    private TableColumn<ProjectAuthor, UUID> authorIdProjectAuthorTableColumn;

    private final ProjectAuthorRepository projectAuthorRepository = new ProjectAuthorRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private ProjectAuthor selectedProjectAuthor;

    private RoleManagerService roleManagerService;

    public ProjectAuthorTableController() throws CommonFlkException {}

    public void initialize() {
        projectIdProjectAuthorTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        authorIdProjectAuthorTableColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        setProjectAuthorTableSelectedItemPropertyListener();
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

    public void fillTable(List<ProjectAuthor> projectAuthors) {
        ObservableList<ProjectAuthor> projectAuthorObservableList = FXCollections.observableArrayList();
        projectAuthorObservableList.addAll(projectAuthors);
        projectAuthorTable.setItems(projectAuthorObservableList);
    }

    private void setProjectAuthorTableSelectedItemPropertyListener() {
        projectAuthorTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedProjectAuthor = newSelection;
        });
    }

    public void clearSelection() {
        projectAuthorTable.getSelectionModel().clearSelection();
        selectedProjectAuthor = null;
    }

    public ObservableValue<ProjectAuthor> getObservableSelectedProjectAuthor() {
        return projectAuthorTable.getSelectionModel().selectedItemProperty();
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
