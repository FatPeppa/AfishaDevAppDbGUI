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
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;
import org.skyhigh.afishadevappgui.data.repository.CodeFileRepository;
import org.skyhigh.afishadevappgui.data.repository.CodeFileRepositoryImpl;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CodeFileTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = false;
    private static final boolean isViewableForDeveloper = true;
    private static final boolean isViewableForQA= true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = false;
    private static final boolean isEditableForDeveloper = true;
    private static final boolean isEditableForQA= false;
    private static final boolean isEditableForDevOps = true;

    @FXML
    private TableView<CodeFile> codeFileTable;

    @FXML
    private TableColumn<CodeFile, UUID> codeFileIdTableColumn;

    @FXML
    private TableColumn<CodeFile, ?> projectIdCodeFileTableColumn;

    @FXML
    private TableColumn<CodeFile, String> fileContentCodeFileTableColumn;

    @FXML
    private TableColumn<CodeFile, LocalDateTime> loadDateCodeFileTableColumn;

    private final CodeFileRepository codeFileRepository = new CodeFileRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private CodeFile selectedCodeFile;

    private RoleManagerService roleManagerService;

    public CodeFileTableController() throws CommonFlkException {}

    public void initialize() throws CommonFlkException {
        codeFileIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("codeFileId"));
        projectIdCodeFileTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        fileContentCodeFileTableColumn.setCellValueFactory(new PropertyValueFactory<>("fileContent"));
        loadDateCodeFileTableColumn.setCellValueFactory(new PropertyValueFactory<>("loadDate"));
        setCodeFileTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<CodeFile> codeFilesListView = FXCollections.observableArrayList();
        List<CodeFile> codeFiles = codeFileRepository.getAllCodeFiles(
                SortDirection.NONE,
                null
        );

        ProjectRepository projectRepository = new ProjectRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );

        codeFiles.stream().forEach(codeFile -> {
            try {
                codeFile.setProjectName(projectRepository.getProjectById(codeFile.getProjectId()).getProjectName());
            } catch (CommonFlkException e) {
                throw new RuntimeException(e);
            }
        });

        codeFilesListView.addAll(codeFiles);
        codeFileTable.setItems(codeFilesListView);
    }

    public void fillTable(List<CodeFile> codeFiles) throws CommonFlkException {
        ObservableList<CodeFile> codeFileObservableList = FXCollections.observableArrayList();
        ProjectRepository projectRepository = new ProjectRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );

        codeFiles.forEach(codeFile -> {
            try {
                codeFile.setProjectName(projectRepository.getProjectById(codeFile.getProjectId()).getProjectName());
            } catch (CommonFlkException e) {
                throw new RuntimeException(e);
            }
        });
        codeFileObservableList.addAll(codeFiles);
        codeFileTable.setItems(codeFileObservableList);
    }

    private void setCodeFileTableSelectedItemPropertyListener() {
        codeFileTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedCodeFile = newSelection;
        });
    }

    public void clearSelection() {
        codeFileTable.getSelectionModel().clearSelection();
        selectedCodeFile = null;
    }

    public ObservableValue<CodeFile> getObservableSelectedCodeFile() {
        return codeFileTable.getSelectionModel().selectedItemProperty();
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
    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;

        if (!roleManagerService.checkIfCurrentUserAdmin()) {
            projectIdCodeFileTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
            projectIdCodeFileTableColumn.setText("Наименование проекта");
        }
    }
}
