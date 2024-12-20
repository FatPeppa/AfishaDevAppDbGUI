package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepository;
import org.skyhigh.afishadevappgui.data.repository.DeploymentStatusRepositoryImpl;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowDeploymentController {
    @FXML
    TextField deploymentIdField;

    @FXML
    TextField addressField;

    @FXML
    TextField statusIdField;

    @FXML
    TextField settingsField;

    @FXML
    TextField builtVersionField;

    @FXML
    TextField builtSettingsField;

    @FXML
    TextField builtField;

    @FXML
    TextField projectIdField;

    @FXML
    Label deploymentIdLabel;

    @FXML
    Label statusIdLabel;

    @FXML
    Label addressLabel;

    @FXML
    Label settingsLabel;

    @FXML
    Label builtSettingsLabel;

    @FXML
    Label builtVersionLabel;

    @FXML
    Label builtLabel;

    @FXML
    Label projectIdLabel;

    private RoleManagerService roleManagerService;

    private String deploymentIdFieldPrompt;
    private String addressFieldPrompt;
    private String statusIdFieldPrompt;
    private String settingsFieldPrompt;
    private String builtVersionFieldPrompt;
    private String builtSettingsFieldPrompt;
    private String builtFieldPrompt;
    private String projectIdFieldPrompt;

    private String deploymentIdFieldBasicStyle;
    private String addressFieldBasicStyle;
    private String statusIdFieldBasicStyle;
    private String settingsFieldBasicStyle;
    private String builtVersionFieldBasicStyle;
    private String builtSettingsFieldBasicStyle;
    private String builtFieldBasicStyle;
    private String projectIdFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        deploymentIdFieldPrompt = deploymentIdField.getPromptText();
        addressFieldPrompt = addressField.getPromptText();
        statusIdFieldPrompt = statusIdField.getPromptText();
        settingsFieldPrompt = settingsField.getPromptText();
        builtVersionFieldPrompt = builtVersionField.getPromptText();
        builtSettingsFieldPrompt = builtSettingsField.getPromptText();
        builtFieldPrompt = builtField.getPromptText();
        projectIdFieldPrompt = projectIdField.getPromptText();

        deploymentIdFieldBasicStyle = deploymentIdField.getStyle();
        addressFieldBasicStyle = addressField.getStyle();
        statusIdFieldBasicStyle = statusIdField.getStyle();
        settingsFieldBasicStyle = settingsField.getStyle();
        builtVersionFieldBasicStyle = builtVersionField.getStyle();
        builtSettingsFieldBasicStyle = builtSettingsField.getStyle();
        builtFieldBasicStyle = builtField.getStyle();
        projectIdFieldBasicStyle = projectIdField.getStyle();
    }

    public void autoFillFields(Deployment deployment) throws CommonFlkException {
        ProjectRepository projectRepository = new ProjectRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        DeploymentStatusRepository deploymentStatusRepository = new DeploymentStatusRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        deploymentIdField.setText(deployment.getDeploymentId().toString());
        if (deployment.getDeploymentPath() != null) {addressField.setText(deployment.getDeploymentPath());}
        if (deployment.getSettings() != null) {settingsField.setText(deployment.getSettings().toString());}
        if (deployment.getBuiltSettings() != null) {builtSettingsField.setText(deployment.getBuiltSettings().toString());}
        if (deployment.getBuiltVersion() != null) {builtVersionField.setText(deployment.getBuiltVersion());}
        if (deployment.getBuilt() != null) {builtField.setText(deployment.getBuilt().toString());}
        if (roleManagerService.checkIfCurrentUserAdmin()) {
            statusIdField.setText(deployment.getDeploymentStatusId().toString());
            projectIdField.setText(deployment.getProjectId().toString());
        } else {
            statusIdField.setText(deploymentStatusRepository.getDeploymentStatusById(deployment.getDeploymentStatusId()).getStatusName());
            projectIdField.setText(projectRepository.getProjectById(deployment.getProjectId()).getProjectName());
        }
    }

    public void clearFields() {
        deploymentIdField.setText(null);
        addressField.setText(null);
        settingsField.setText(null);
        builtSettingsField.setText(null);
        builtVersionField.setText(null);
        builtField.setText(null);
        statusIdField.setText(null);
        projectIdField.setText(null);
    }

    public Deployment getDeployment() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        ProjectRepository projectRepository = new ProjectRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        DeploymentStatusRepository deploymentStatusRepository = new DeploymentStatusRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        if (emptyField.isEmpty())
            try {
                UUID projectId = null;
                if (!roleManagerService.checkIfCurrentUserAdmin()) {
                    if (projectRepository.getProjectByName(projectIdField.getText()) == null)
                        throw new CommonFlkException(
                                "Проект '" + projectIdField.getText() + "' не найден"
                        );
                    projectId = projectRepository.getProjectByName(projectIdField.getText()).getProjectId();
                } else {
                    projectId =  ControllerUtils.getUUIDFromTextField(
                            projectIdField,
                            ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel)
                    );
                }
                UUID deploymentStatusId = null;
                if (!roleManagerService.checkIfCurrentUserAdmin()) {
                    if (deploymentStatusRepository.getDeploymentStatusByName(statusIdField.getText()) == null)
                        throw new CommonFlkException(
                                "Статус развертывания '" + statusIdField.getText() + "' не найден"
                        );
                    deploymentStatusId = deploymentStatusRepository.getDeploymentStatusByName(statusIdField.getText()).getDeploymentStatusId();
                } else {
                    deploymentStatusId = ControllerUtils.getUUIDFromTextField(
                            statusIdField,
                            ControllerUtils.getFieldLocalNameFromItsLabel(statusIdLabel)
                    );
                }
                return new Deployment(
                        ControllerUtils.getUUIDFromTextField(
                                deploymentIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel)
                        ),
                        deploymentStatusId,
                        addressField.getText(),
                        settingsField.getText() == null || settingsField.getText().isEmpty() ? null : ControllerUtils.getJSONFromTextField(
                                settingsField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(settingsLabel)
                        ),
                        builtVersionField.getText(),
                        builtSettingsField.getText() == null || settingsField.getText().isEmpty() ? null : ControllerUtils.getJSONFromTextField(
                                builtSettingsField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(builtSettingsLabel)
                        ),
                        builtField.getText() == null || builtField.getText().isEmpty() ? null : ControllerUtils.getFileFromTextField(
                                builtField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(builtLabel)
                        ),
                        projectId
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении развертывания должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getDeploymentId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                deploymentIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (deploymentIdField.getText() == null || deploymentIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(deploymentIdLabel));
        if (statusIdField.getText() == null || statusIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(statusIdLabel));
        if (projectIdField.getText() == null || projectIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            deploymentIdField.setPromptText(deploymentIdFieldPrompt);
            deploymentIdField.setEditable(false);
            deploymentIdField.setStyle(deploymentIdFieldBasicStyle);

            addressField.setPromptText(addressFieldPrompt);
            addressField.setEditable(true);
            addressField.setStyle(addressFieldBasicStyle);

            statusIdField.setPromptText(statusIdFieldPrompt);
            statusIdField.setEditable(true);
            statusIdField.setStyle(statusIdFieldBasicStyle);

            settingsField.setPromptText(settingsFieldPrompt);
            settingsField.setEditable(true);
            settingsField.setStyle(settingsFieldBasicStyle);

            builtVersionField.setPromptText(builtVersionFieldPrompt);
            builtVersionField.setEditable(true);
            builtVersionField.setStyle(builtVersionFieldBasicStyle);

            builtSettingsField.setPromptText(builtSettingsFieldPrompt);
            builtSettingsField.setEditable(true);
            builtSettingsField.setStyle(builtSettingsFieldBasicStyle);

            builtField.setPromptText(builtFieldPrompt);
            builtField.setEditable(true);
            builtField.setStyle(builtFieldBasicStyle);

            projectIdField.setPromptText(projectIdFieldPrompt);
            projectIdField.setEditable(true);
            projectIdField.setStyle(projectIdFieldBasicStyle);
        } else {
            deploymentIdField.setPromptText("Заполняется автоматически");
            deploymentIdField.setEditable(false);
            deploymentIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            addressField.setPromptText("Заполняется автоматически");
            addressField.setEditable(false);
            addressField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            statusIdField.setPromptText("Заполняется автоматически");
            statusIdField.setEditable(false);
            statusIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            settingsField.setPromptText("Заполняется автоматически");
            settingsField.setEditable(false);
            settingsField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            builtVersionField.setPromptText("Заполняется автоматически");
            builtVersionField.setEditable(false);
            builtVersionField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            builtSettingsField.setPromptText("Заполняется автоматически");
            builtSettingsField.setEditable(false);
            builtSettingsField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            builtField.setPromptText("Заполняется автоматически");
            builtField.setEditable(false);
            builtField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            projectIdField.setPromptText("Заполняется автоматически");
            projectIdField.setEditable(false);
            projectIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }

    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;

        if (!roleManagerService.checkIfCurrentUserAdmin()) {
            if (!projectIdField.getPromptText().equals("Заполняется автоматически"))
                projectIdField.setPromptText("Введите имя проекта");
            projectIdLabel.setText("Имя проекта:");
            projectIdFieldPrompt = "Введите имя проекта";

            if (!statusIdField.getPromptText().equals("Заполняется автоматически"))
                statusIdField.setPromptText("Введите статус развертывания");
            statusIdLabel.setText("Статус развертывания:");
            statusIdFieldPrompt = "Введите статус развертывания";
        }
    }
}
