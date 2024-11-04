package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class RequirementAuthorFiltersController {
    @FXML
    TextField authorIdInputField;

    @FXML
    TextField requirementIdInputField;

    @FXML
    Label authorIdLabel;

    @FXML
    Label requirementIdLabel;

    public UUID getRequirementId() throws CommonFlkException {
        return ControllerUtils.getUUIDFromTextField(requirementIdInputField, getFieldLocalNameFromItsLabel(requirementIdLabel));
    }

    public UUID getAuthorId() throws CommonFlkException {
        return ControllerUtils.getUUIDFromTextField(authorIdInputField, getFieldLocalNameFromItsLabel(requirementIdLabel));
    }
}
