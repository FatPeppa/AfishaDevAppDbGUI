package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.time.LocalDateTime;
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
}
