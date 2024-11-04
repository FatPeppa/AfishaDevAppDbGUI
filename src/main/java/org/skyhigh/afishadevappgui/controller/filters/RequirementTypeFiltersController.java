package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class RequirementTypeFiltersController {
    @FXML
    TextField requirementTypeIdInputField;

    @FXML
    TextField requirementTypeNameInputField;

    @FXML
    Label requirementTypeIdLabel;

    @FXML
    Label typeNameLabel;

    public void initialize() {
        setRequirementTypeIdInputFieldChangeListener();
        setRequirementTypeNameInputFieldChangeListener();
    }

    public UUID getRequirementTypeId() {
        return ControllerUtils.getUUIDFromTextField(requirementTypeIdInputField, getFieldLocalNameFromItsLabel(requirementTypeIdLabel));
    }

    public String getRequirementTypeName() {
        return requirementTypeNameInputField.getText();
    }

    private void setRequirementTypeIdInputFieldChangeListener() {
        requirementTypeIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
                requirementTypeNameInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }

    private void setRequirementTypeNameInputFieldChangeListener() {
        requirementTypeNameInputField.textProperty().addListener((observable, oldValue, newValue) ->
                requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }
}
