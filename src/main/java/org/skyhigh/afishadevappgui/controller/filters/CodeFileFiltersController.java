package org.skyhigh.afishadevappgui.controller.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

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

    private RoleManagerService roleManagerService;

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

    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;

        if (!roleManagerService.checkIfCurrentUserAdmin()) {
            projectIdInputField.setPromptText("Введите, пожалуйста, наименование проекта");
            projectIdLabel.setText("Наименование проекта:");
        }
    }
}
