package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowProjectController {
    @FXML
    TextField projectIdField;

    @FXML
    TextField structureField;

    @FXML
    TextField nameField;

    @FXML
    TextField contentField;

    @FXML
    TextField settingsField;

    @FXML
    TextField uploadDateField;

    @FXML
    TextField lastChangeDateField;

    @FXML
    TextField versionNumberField;

    @FXML
    Label projectIdLabel;

    @FXML
    Label nameLabel;

    @FXML
    Label contentLabel;

    @FXML
    Label settingsLabel;

    @FXML
    Label structureLabel;

    @FXML
    Label lastChangeDateLabel;

    @FXML
    Label uploadDateLabel;

    @FXML
    Label versionNumberLabel;

    private String projectIdFieldPrompt;
    private String structureFieldPrompt;
    private String nameFieldPrompt;
    private String contentFieldPrompt;
    private String settingsFieldPrompt;
    private String lastChangeDateFieldPrompt;
    private String uploadDateFieldPrompt;
    private String versionNumberFieldPrompt;

    private String projectIdFieldBasicStyle;
    private String structureFieldBasicStyle;
    private String nameFieldBasicStyle;
    private String contentFieldBasicStyle;
    private String settingsFieldBasicStyle;
    private String lastChangeDateFieldBasicStyle;
    private String uploadDateFieldBasicStyle;
    private String versionNumberFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        projectIdFieldPrompt = projectIdField.getPromptText();
        structureFieldPrompt = structureField.getPromptText();
        nameFieldPrompt = nameField.getPromptText();
        contentFieldPrompt = contentField.getPromptText();
        settingsFieldPrompt = settingsField.getPromptText();
        lastChangeDateFieldPrompt = lastChangeDateField.getPromptText();
        uploadDateFieldPrompt = uploadDateField.getPromptText();
        versionNumberFieldPrompt = versionNumberField.getPromptText();

        projectIdFieldBasicStyle = projectIdField.getStyle();
        structureFieldBasicStyle = structureField.getStyle();
        nameFieldBasicStyle = nameField.getStyle();
        contentFieldBasicStyle = contentField.getStyle();
        settingsFieldBasicStyle = settingsField.getStyle();
        lastChangeDateFieldBasicStyle = lastChangeDateField.getStyle();
        uploadDateFieldBasicStyle = uploadDateField.getStyle();
        versionNumberFieldBasicStyle = versionNumberField.getStyle();
    }

    public void autoFillFields(Project project) {
        projectIdField.setText(project.getProjectId().toString());
        nameField.setText(project.getProjectName());
        uploadDateField.setText(project.getLoadDate().toString());
        lastChangeDateField.setText(project.getLastChangeDate().toString());
        structureField.setText(project.getStructure().toString());
        contentField.setText(project.getContent().toString());
        settingsField.setText(project.getSettings().toString());
        versionNumberField.setText(project.getVersionNumber());
    }

    public void clearFields() {
        projectIdField.setText(null);
        nameField.setText(null);
        uploadDateField.setText(null);
        lastChangeDateField.setText(null);
        structureField.setText(null);
        contentField.setText(null);
        settingsField.setText(null);
        versionNumberField.setText(null);
    }

    public Project getProject() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new Project(
                        ControllerUtils.getUUIDFromTextField(
                                projectIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel)
                        ),
                        nameField.getText(),
                        ControllerUtils.getLocalDateTimeFromTextField(
                                uploadDateField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel)
                        ),
                        lastChangeDateField.getText() == null || lastChangeDateField.getText().isEmpty() ? null : ControllerUtils.getLocalDateTimeFromTextField(
                                lastChangeDateField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(lastChangeDateLabel)
                        ),
                        ControllerUtils.getJSONFromTextField(
                                structureField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(structureLabel)
                        ),
                        ControllerUtils.getJSONFromTextField(
                                contentField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(contentLabel)
                        ),
                        ControllerUtils.getJSONFromTextField(
                                settingsField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(settingsLabel)
                        ),
                        versionNumberField.getText()
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении проекта должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getProjectId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                projectIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (projectIdField.getText() == null || projectIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel));
        if (nameField.getText() == null || nameField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(nameLabel));
        //if (uploadDateField.getText() == null || uploadDateField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel));
        //if (lastChangeDateField.getText() == null || lastChangeDateField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(lastChangeDateLabel));
        if (structureField.getText() == null || structureField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(structureLabel));
        if (contentField.getText() == null || contentField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(contentLabel));
        if (settingsField.getText() == null || settingsField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(settingsLabel));
        if (versionNumberField.getText() == null || versionNumberField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(versionNumberLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            projectIdField.setPromptText(projectIdFieldPrompt);
            projectIdField.setEditable(false);
            projectIdField.setStyle(projectIdFieldBasicStyle);

            structureField.setPromptText(structureFieldPrompt);
            structureField.setEditable(true);
            structureField.setStyle(structureFieldBasicStyle);

            nameField.setPromptText(nameFieldPrompt);
            nameField.setEditable(true);
            nameField.setStyle(nameFieldBasicStyle);

            contentField.setPromptText(contentFieldPrompt);
            contentField.setEditable(true);
            contentField.setStyle(contentFieldBasicStyle);

            settingsField.setPromptText(settingsFieldPrompt);
            settingsField.setEditable(true);
            settingsField.setStyle(settingsFieldBasicStyle);

            lastChangeDateField.setPromptText(lastChangeDateFieldPrompt);
            lastChangeDateField.setEditable(true);
            lastChangeDateField.setStyle(lastChangeDateFieldBasicStyle);

            uploadDateField.setPromptText(uploadDateFieldPrompt);
            uploadDateField.setEditable(true);
            uploadDateField.setStyle(uploadDateFieldBasicStyle);

            versionNumberField.setPromptText(versionNumberFieldPrompt);
            versionNumberField.setEditable(true);
            versionNumberField.setStyle(versionNumberFieldBasicStyle);
        } else {
            projectIdField.setPromptText("Заполняется автоматически");
            projectIdField.setEditable(false);
            projectIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            structureField.setPromptText("Заполняется автоматически");
            structureField.setEditable(false);
            structureField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            nameField.setPromptText("Заполняется автоматически");
            nameField.setEditable(false);
            nameField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            contentField.setPromptText("Заполняется автоматически");
            contentField.setEditable(false);
            contentField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            settingsField.setPromptText("Заполняется автоматически");
            settingsField.setEditable(false);
            settingsField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            lastChangeDateField.setPromptText("Заполняется автоматически");
            lastChangeDateField.setEditable(false);
            lastChangeDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            uploadDateField.setPromptText("Заполняется автоматически");
            uploadDateField.setEditable(false);
            uploadDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            versionNumberField.setPromptText("Заполняется автоматически");
            versionNumberField.setEditable(false);
            versionNumberField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
