package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class ProjectFiltersController {
    @FXML
    TextField projectIdInputField;

    @FXML
    TextField projectNameInputField;

    @FXML
    TextField projectVersionNumberInputField;

    @FXML
    TextField loadDateStartInputField;

    @FXML
    TextField loadDateEndInputField;

    @FXML
    TextField lastChangeDateStartInputField;

    @FXML
    TextField lastChangeDateEndInputField;

    @FXML
    Label projectIdLabel;

    @FXML
    Label projectNameLabel;

    @FXML
    Label loadDateEndLabel;

    @FXML
    Label loadDateBeginLabel;

    @FXML
    Label versionNumberLabel;

    @FXML
    Label lastChangeDateBeginLabel;

    @FXML
    Label lastChangeDateEndLabel;

    public void initialize() {
        setProjectIdInputFieldChangeListener();
        setProjectNameInputFieldChangeListener();
        setProjectVersionNumberInputFieldChangeListener();
        setLoadDateStartInputFieldChangeListener();
        setLoadDateEndInputFieldFieldChangeListener();
        setLastChangeDateEndInputFieldChangeListener();
        setLastChangeDateStartInputFieldChangeListener();
    }

    public UUID getProjectId() {
        try {
            return ControllerUtils.getUUIDFromTextField(projectIdInputField, getFieldLocalNameFromItsLabel(projectIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public String getProjectName() {
        return projectNameInputField.getText();
    }

    public String getProjectVersion() {
        return projectVersionNumberInputField.getText();
    }

    public LocalDateTime getLoadDateStart() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(loadDateStartInputField, getFieldLocalNameFromItsLabel(loadDateBeginLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public LocalDateTime getLoadDateEnd() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(loadDateEndInputField, getFieldLocalNameFromItsLabel(loadDateEndLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public LocalDateTime getLastChangeDateStart() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(lastChangeDateStartInputField, getFieldLocalNameFromItsLabel(lastChangeDateBeginLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public LocalDateTime getLastChangeDateEnd() {
        try {
            return ControllerUtils.getLocalDateTimeFromTextField(lastChangeDateEndInputField, getFieldLocalNameFromItsLabel(lastChangeDateEndLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    private void setProjectIdInputFieldChangeListener() {
        projectIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectNameInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectVersionNumberInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setProjectNameInputFieldChangeListener() {
        projectNameInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectVersionNumberInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setProjectVersionNumberInputFieldChangeListener() {
        projectVersionNumberInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectNameInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLoadDateStartInputFieldChangeListener() {
        loadDateStartInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectNameInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectVersionNumberInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLoadDateEndInputFieldFieldChangeListener() {
        loadDateEndInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectNameInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectVersionNumberInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLastChangeDateStartInputFieldChangeListener() {
        lastChangeDateStartInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectNameInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectVersionNumberInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLastChangeDateEndInputFieldChangeListener() {
        lastChangeDateEndInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectNameInputField.setDisable(newValue != null && !newValue.isEmpty());
            projectVersionNumberInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }
}