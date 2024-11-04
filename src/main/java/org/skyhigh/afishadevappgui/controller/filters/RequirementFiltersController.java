package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class RequirementFiltersController {
    @FXML
    TextField requirementIdInputField;

    @FXML
    TextField requirementTypeIdInputField;

    @FXML
    TextField loadDateStartInputField;

    @FXML
    TextField loadDateEndInputField;

    @FXML
    TextField lastChangeDateStartInputField;

    @FXML
    TextField lastChangeDateEndInputField;

    @FXML
    Label requirementIdLabel;

    @FXML
    Label requirementTypeIdLabel;

    @FXML
    Label loadDateBeginLabel;

    @FXML
    Label loadDateEndLabel;

    @FXML
    Label lastChangeDateBeginLabel;

    @FXML
    Label lastChangeDateEndLabel;

    public void initialize() {
        setRequirementIdInputFieldChangeListener();
        setRequirementTypeIdInputFieldChangeListener();
        setLoadDateStartInputFieldChangeListener();
        setLoadDateEndInputFieldChangeListener();
        setLastChangeDateStartInputFieldChangeListener();
        setLastChangeDateEndInputFieldChangeListener();
    }

    public UUID getRequirementId() {
        return ControllerUtils.getUUIDFromTextField(requirementIdInputField, getFieldLocalNameFromItsLabel(requirementIdLabel));
    }

    public UUID getRequirementTypeId() {
        return ControllerUtils.getUUIDFromTextField(requirementTypeIdInputField, getFieldLocalNameFromItsLabel(requirementTypeIdLabel));
    }

    public LocalDateTime getLoadDateStart() {
        return ControllerUtils.getLocalDateTimeFromTextField(loadDateStartInputField, getFieldLocalNameFromItsLabel(loadDateBeginLabel));
    }

    public LocalDateTime getLoadDateEnd() {
        return ControllerUtils.getLocalDateTimeFromTextField(loadDateEndInputField, getFieldLocalNameFromItsLabel(loadDateEndLabel));
    }

    public LocalDateTime getLastChangeDateStart() {
        return ControllerUtils.getLocalDateTimeFromTextField(lastChangeDateStartInputField, getFieldLocalNameFromItsLabel(lastChangeDateBeginLabel));
    }

    public LocalDateTime getLastChangeDateEnd() {
        return ControllerUtils.getLocalDateTimeFromTextField(lastChangeDateEndInputField, getFieldLocalNameFromItsLabel(lastChangeDateEndLabel));
    }

    private void setRequirementIdInputFieldChangeListener() {
        requirementIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setRequirementTypeIdInputFieldChangeListener() {
        requirementTypeIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLoadDateStartInputFieldChangeListener() {
        loadDateStartInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLoadDateEndInputFieldChangeListener() {
        loadDateEndInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            lastChangeDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLastChangeDateStartInputFieldChangeListener() {
        lastChangeDateStartInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setLastChangeDateEndInputFieldChangeListener() {
        lastChangeDateEndInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            requirementIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            requirementTypeIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateStartInputField.setDisable(newValue != null && !newValue.isEmpty());
            loadDateEndInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }
}
