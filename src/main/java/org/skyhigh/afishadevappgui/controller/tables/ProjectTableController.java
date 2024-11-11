package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectTableController {
    @FXML
    private TableView<Project> projectTable;

    @FXML
    private TableColumn<Project, UUID> projectIdProjectTableColumn;

    @FXML
    private TableColumn<Project, String> projectNameProjectTableColumn;

    @FXML
    private TableColumn<Project, LocalDateTime> loadDateProjectTableColumn;

    @FXML
    private TableColumn<Project, LocalDateTime> lastChangeDateProjectTableColumn;

    @FXML
    private TableColumn<Project, JSONObject> structureProjectTableColumn;

    @FXML
    private TableColumn<Project, JSONObject> contentProjectTableColumn;

    @FXML
    private TableColumn<Project, JSONObject> settingsProjectTableColumn;

    @FXML
    private TableColumn<Project, String> versionNumberProjectTableColumn;

    @Getter
    private Project selectedProject;

    private final ProjectRepository projectRepository = new ProjectRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    public ProjectTableController() throws CommonFlkException {}

    public void initialize() {
        projectIdProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        loadDateProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("loadDate"));
        lastChangeDateProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastChangeDate"));
        structureProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("structure"));
        contentProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("settings"));
        settingsProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        versionNumberProjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("versionNumber"));
        setProjectTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<Project> projectsListView = FXCollections.observableArrayList();
        List<Project> projects = projectRepository.getAllProjects(
                SortDirection.NONE,
                null
        );
        projectsListView.addAll(projects);
        projectTable.setItems(projectsListView);
    }

    public void fillTable(List<Project> projects) {
        ObservableList<Project> projectObservableList = FXCollections.observableArrayList();
        projectObservableList.addAll(projects);
        projectTable.setItems(projectObservableList);
    }

    private void setProjectTableSelectedItemPropertyListener() {
        projectTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedProject = newSelection;
        });
    }

    public void clearSelection() {
        projectTable.getSelectionModel().clearSelection();
        selectedProject = null;
    }

    public ObservableValue<Project> getObservableSelectedProject() {
        return projectTable.getSelectionModel().selectedItemProperty();
    }
}
