package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.util.UUID;

public class RequirementAuthorTableController {
    @FXML
    private TableView<RequirementAuthor> requirementAuthorTable;

    @FXML
    private TableColumn<RequirementAuthor, UUID> requirementIdRequirementTableColumn;

    @FXML
    private TableColumn<RequirementAuthor, UUID> authorIdRequirementTableColumn;
}
