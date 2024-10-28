package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

import java.util.UUID;

public class RequirementTypeTableController {
    @FXML
    private TableView<RequirementType> requirementTypeTable;

    @FXML
    private TableColumn<RequirementType, UUID> requirementTypeIdRequirementTypeTableColumn;

    @FXML
    private TableColumn<RequirementType, String> requirementTypeNameRequirementTypeTableColumn;
}
