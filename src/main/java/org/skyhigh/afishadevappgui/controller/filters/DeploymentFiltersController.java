package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepository;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepositoryImpl;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

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

    private RoleManagerService roleManagerService;

    public void initialize() {
        setDeploymentIdInputFieldChangeListener();
        setProjectIdInputFieldChangeListener();
        setDeploymentStatusIdInputFieldChangeListener();
        setBuiltVersionInputFieldChangeListener();
        setDeploymentPathInputFieldChangeListener();
    }

    public UUID getDeploymentId() {
        try {
            return ControllerUtils.getUUIDFromTextField(deploymentIdInputField, getFieldLocalNameFromItsLabel(deploymentIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public UUID getDeploymentStatusId() throws CommonFlkException {
        try {
            if (deploymentStatusIdInputField.getText() == null || deploymentStatusIdInputField.getText().isEmpty())
                return null;
            if (roleManagerService != null && !roleManagerService.checkIfCurrentUserAdmin()) {
                String deploymentStatusIdInputFieldText = deploymentStatusIdInputField.getText();
                DeploymentStatusRepository deploymentStatusRepository = new DeploymentStatusRepositoryImpl(
                        ApplicationPropertiesReader.getApplicationProperties()
                );
                DeploymentStatus ds = deploymentStatusRepository.getDeploymentStatusByName(deploymentStatusIdInputFieldText);
                if (ds == null)
                    throw new CommonFlkException("Статус развертывания с именем '" + deploymentStatusIdInputFieldText + "' не существует");
                return ds.getDeploymentStatusId();
            }
            return ControllerUtils.getUUIDFromTextField(deploymentStatusIdInputField, getFieldLocalNameFromItsLabel(projectIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
    }

    public UUID getProjectId() throws CommonFlkException {
        try {
            if (projectIdInputField.getText() == null || projectIdInputField.getText().isEmpty())
                return null;
            if (roleManagerService != null && !roleManagerService.checkIfCurrentUserAdmin()) {
                String projectName = projectIdInputField.getText();
                ProjectRepository projectRepository = new ProjectRepositoryImpl(
                        ApplicationPropertiesReader.getApplicationProperties()
                );
                Project project = projectRepository.getProjectByName(projectName);
                if (project == null)
                    throw new CommonFlkException("Проект с именем '" + projectName + "' не существует");
                return project.getProjectId();
            }
            return ControllerUtils.getUUIDFromTextField(projectIdInputField, getFieldLocalNameFromItsLabel(projectIdLabel));
        } catch (CommonUIFormatException e) {
            return null;
        }
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

    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;

        if (!roleManagerService.checkIfCurrentUserAdmin()) {
            projectIdInputField.setPromptText("Введите, пожалуйста, наименование проекта (Строка)");
            projectIdLabel.setText("Наименование проекта:");


            deploymentStatusIdInputField.setPromptText("Введите, пожалуйста, статус развертывания (Строка)");
            deploymentStatusIdLabel.setText("Статус развертывания:");
        }
    }
}
