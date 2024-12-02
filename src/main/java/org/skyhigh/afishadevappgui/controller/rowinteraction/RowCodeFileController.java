package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowCodeFileController {
    @FXML
    TextField codeFileIdField;

    @FXML
    TextField projectIdField;

    @FXML
    TextField contentField;

    @FXML
    TextField codeFileUploadDateField;

    @FXML
    Label codeFileIdLabel;

    @FXML
    Label projectIdLabel;

    @FXML
    Label contentLabel;

    @FXML
    Label uploadDateLabel;

    private RoleManagerService roleManagerService;

    private String codeFileIdFieldPrompt;
    private String projectIdFieldPrompt;
    private String contentFieldPrompt;
    private String codeFileUploadDateFieldPrompt;

    private String codeFileIdFieldBasicStyle;
    private String projectIdFieldBasicStyle;
    private String contentFieldBasicStyle;
    private String codeFileUploadDateFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        codeFileIdFieldPrompt = codeFileIdField.getPromptText();
        projectIdFieldPrompt = projectIdField.getPromptText();
        projectIdFieldPrompt = projectIdField.getPromptText();
        codeFileUploadDateFieldPrompt = codeFileUploadDateField.getPromptText();

        codeFileIdFieldBasicStyle = codeFileIdField.getStyle();
        projectIdFieldBasicStyle = projectIdField.getStyle();
        contentFieldBasicStyle = projectIdField.getStyle();
        codeFileUploadDateFieldBasicStyle = codeFileUploadDateField.getStyle();
    }

    public void autoFillFields(CodeFile codeFile) throws CommonFlkException {
        ProjectRepository projectRepository = new ProjectRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        codeFileIdField.setText(codeFile.getCodeFileId().toString());
        if (roleManagerService.checkIfCurrentUserAdmin())
            projectIdField.setText(codeFile.getProjectId().toString());
        else
            projectIdField.setText(projectRepository.getProjectById(codeFile.getProjectId()).getProjectName());
        projectIdField.setEditable(false);
        projectIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        contentField.setText(codeFile.getFileContent());
        if (codeFile.getLoadDate() != null) {codeFileUploadDateField.setText(codeFile.getLoadDate().toString());}
    }

    public void clearFields() {
        codeFileIdField.setText(null);
        projectIdField.setText(null);
        projectIdField.setEditable(true);
        projectIdField.setStyle(null);
        contentField.setText(null);
        codeFileUploadDateField.setText(null);
    }

    public CodeFile getCodeFile() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        ProjectRepository projectRepository = new ProjectRepositoryImpl(
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
                return new CodeFile(
                        ControllerUtils.getUUIDFromTextField(
                                codeFileIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(codeFileIdLabel)
                        ),
                        projectId,
                        contentField.getText(),
                        codeFileUploadDateField.getText() == null ? null : ControllerUtils.getLocalDateTimeFromTextField(
                                codeFileUploadDateField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении файла с кодом должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getCodeFileId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                codeFileIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(codeFileIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (codeFileIdField.getText() == null || codeFileIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(codeFileIdLabel));
        if (projectIdField.getText() == null || projectIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel));
        if (contentField.getText() == null || contentField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(contentLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            codeFileIdField.setPromptText(codeFileIdFieldPrompt);
            codeFileIdField.setEditable(false);
            codeFileIdField.setStyle(codeFileIdFieldBasicStyle);

            projectIdField.setPromptText(projectIdFieldPrompt);
            projectIdField.setEditable(true);
            projectIdField.setStyle(projectIdFieldBasicStyle);

            contentField.setPromptText(contentFieldPrompt);
            contentField.setEditable(true);
            contentField.setStyle(contentFieldBasicStyle);

            codeFileUploadDateField.setPromptText(codeFileUploadDateFieldPrompt);
            codeFileUploadDateField.setEditable(true);
            codeFileUploadDateField.setStyle(codeFileUploadDateFieldBasicStyle);
        } else {
            codeFileIdField.setPromptText("Заполняется автоматически");
            codeFileIdField.setEditable(false);
            codeFileIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            projectIdField.setPromptText("Заполняется автоматически");
            projectIdField.setEditable(false);
            projectIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            contentField.setPromptText("Заполняется автоматически");
            contentField.setEditable(false);
            contentField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            codeFileUploadDateField.setPromptText("Заполняется автоматически");
            codeFileUploadDateField.setEditable(false);
            codeFileUploadDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }

    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;

        if (!roleManagerService.checkIfCurrentUserAdmin()) {
            if (!projectIdField.getPromptText().equals("Заполняется автоматически"))
                projectIdField.setPromptText("Введите имя проекта");
            projectIdLabel.setText("Имя проекта:");
            projectIdFieldPrompt = "Введите имя проекта";
        }
    }
}
