package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

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
        return ControllerUtils.getUUIDFromTextField(codeFileIdInputField, getFieldLocalNameFromItsLabel(codeFileIdLabel));
    }

    public UUID getProjectId() {
        return ControllerUtils.getUUIDFromTextField(projectIdInputField, getFieldLocalNameFromItsLabel(projectIdLabel));
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
