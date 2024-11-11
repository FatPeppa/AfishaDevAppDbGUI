package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class AccessedRoleFiltersController {
    @FXML
    TextField roleNameInputField;

    @FXML
    TextField requirementIdInputField;

    @FXML
    Label requirementIdLabel;

    @FXML
    Label roleNameLabel;

    public String getRoleName() {
        return roleNameInputField.getText();
    }

    public UUID getRequirementId() throws CommonFlkException {
        try {
            return ControllerUtils.getUUIDFromTextField(requirementIdInputField, getFieldLocalNameFromItsLabel(requirementIdLabel));
        } catch (CommonFlkException e) {
            return null;
        }
    }
}
