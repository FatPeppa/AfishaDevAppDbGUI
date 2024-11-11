package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class CodeFileFiltersController {
    @FXML
    TextField codeFileIdInputField;

    @FXML
    TextField projectIdInputField;

    @FXML
    Label codeFileIdLabel;

    @FXML
    Label projectIdLabel;

    public void initialize() throws CommonFlkException {
        setAuthorIdInputFieldChangeListener();
        setAuthorLoginInputFieldChangeListener();
    }

    public UUID getCodeFileId() {
        try {
            return ControllerUtils.getUUIDFromTextField(codeFileIdInputField, getFieldLocalNameFromItsLabel(codeFileIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public UUID getProjectId() {
        try {
            return ControllerUtils.getUUIDFromTextField(projectIdInputField, getFieldLocalNameFromItsLabel(projectIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    private void setAuthorIdInputFieldChangeListener() {
        codeFileIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
                projectIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }

    private void setAuthorLoginInputFieldChangeListener() {
        projectIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
                codeFileIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }
}
