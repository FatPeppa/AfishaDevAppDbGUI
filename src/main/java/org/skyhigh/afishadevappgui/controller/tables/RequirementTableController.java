package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

import java.time.LocalDateTime;
import java.util.UUID;

public class RequirementTableController {
    @FXML
    private TableView<Requirement> requirementTable;

    @FXML
    private TableColumn<Requirement, UUID> requirementIdRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, UUID> requirementTypeIdRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, LocalDateTime> loadDateRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, LocalDateTime> lastChangeDateRequirementTableColumn;

    @FXML
    private TableColumn<Requirement, JSONObject> contentRequirementTableColumn;

}
