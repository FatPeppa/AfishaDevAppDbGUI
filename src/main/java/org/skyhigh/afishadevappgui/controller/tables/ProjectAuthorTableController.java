package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.util.UUID;

public class ProjectAuthorTableController {
    @FXML
    private TableView<ProjectAuthor> projectAuthorTable;

    @FXML
    private TableColumn<ProjectAuthor, UUID> projectIdProjectAuthorTableColumn;

    @FXML
    private TableColumn<ProjectAuthor, UUID> authorIdProjectAuthorTableColumn;
}
