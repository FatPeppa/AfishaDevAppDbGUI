package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;

import java.util.UUID;

import static org.skyhigh.afishadevappgui.common.controller.ControllerUtils.getFieldLocalNameFromItsLabel;

public class DeploymentFiltersController {
    @FXML
    TextField deploymentIdInputField;

    @FXML
    TextField projectIdInputField;

    @FXML
    TextField deploymentStatusIdInputField;

    @FXML
    TextField builtVersionInputField;

    @FXML
    TextField deploymentPathInputField;

    @FXML
    Label deploymentIdLabel;

    @FXML
    Label projectIdLabel;

    @FXML
    Label deploymentPathLabel;

    @FXML
    Label builtVersionLabel;

    @FXML
    Label deploymentStatusIdLabel;

    public void initialize() {
        setDeploymentIdInputFieldChangeListener();
        setProjectIdInputFieldChangeListener();
        setDeploymentStatusIdInputFieldChangeListener();
        setBuiltVersionInputFieldChangeListener();
        setDeploymentPathInputFieldChangeListener();
    }

    public UUID getDeploymentId() {
        return ControllerUtils.getUUIDFromTextField(deploymentIdInputField, getFieldLocalNameFromItsLabel(deploymentIdLabel));
    }

    public UUID getDeploymentStatusId() {
        return ControllerUtils.getUUIDFromTextField(deploymentStatusIdInputField, getFieldLocalNameFromItsLabel(deploymentStatusIdLabel));
    }

    public UUID getProjectId() {
        return ControllerUtils.getUUIDFromTextField(projectIdInputField, getFieldLocalNameFromItsLabel(projectIdLabel));
    }

    public String getBuiltVersion() {
        return builtVersionInputField.getText();
    }

    public String getDeploymentPath() {
        return deploymentPathInputField.getText();
    }

    private void setDeploymentIdInputFieldChangeListener() {
        deploymentIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentStatusIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            builtVersionInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentPathInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setProjectIdInputFieldChangeListener() {
        projectIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            deploymentIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            builtVersionInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentPathInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setDeploymentStatusIdInputFieldChangeListener() {
        deploymentStatusIdInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            deploymentIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            builtVersionInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentPathInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setBuiltVersionInputFieldChangeListener() {
        builtVersionInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentStatusIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentPathInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }

    private void setDeploymentPathInputFieldChangeListener() {
        deploymentPathInputField.textProperty().addListener((observable, oldValue, newValue) -> {
            projectIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentStatusIdInputField.setDisable(newValue != null && !newValue.isEmpty());
            builtVersionInputField.setDisable(newValue != null && !newValue.isEmpty());
            deploymentIdInputField.setDisable(newValue != null && !newValue.isEmpty());
        });
    }
}
