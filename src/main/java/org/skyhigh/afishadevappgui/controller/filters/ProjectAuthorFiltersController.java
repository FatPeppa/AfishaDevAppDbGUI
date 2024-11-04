package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class ProjectAuthorFiltersController {
    @FXML
    TextField authorIdInputField;

    @FXML
    TextField projectIdInputField;

    @FXML
    Label authorIdLabel;

    @FXML
    Label projectIdLabel;

    /*public void initialize() {
        setAuthorIdInputFieldChangeListener();
        setProjectIdInputFieldChangeListener();
    }*/

    public UUID getAuthorId() {
        return ControllerUtils.getUUIDFromTextField(authorIdInputField, getFieldLocalNameFromItsLabel(authorIdLabel));
    }

    public UUID getProjectId() {
        return ControllerUtils.getUUIDFromTextField(projectIdInputField, getFieldLocalNameFromItsLabel(projectIdLabel));
    }
    /*
    private void setAuthorIdInputFieldChangeListener() {
        authorIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
                projectIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }

    private void setProjectIdInputFieldChangeListener() {
        projectIdInputField.textProperty().addListener((observable, oldValue, newValue) ->
                authorIdInputField.setDisable(newValue != null && !newValue.isEmpty())
        );
    }
    */
}
