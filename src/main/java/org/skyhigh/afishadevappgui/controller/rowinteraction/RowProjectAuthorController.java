package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.util.ArrayList;
import java.util.List;

public class RowProjectAuthorController {
    @FXML
    TextField projectIdField;

    @FXML
    TextField authorIdField;

    @FXML
    Label projectIdLabel;

    @FXML
    Label authorIdLabel;

    private String projectIdFieldPrompt;
    private String authorIdFieldPrompt;

    private String projectIdFieldBasicStyle;
    private String authorIdFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        projectIdFieldPrompt = projectIdField.getPromptText();
        authorIdFieldPrompt = authorIdField.getPromptText();

        projectIdFieldBasicStyle = projectIdField.getStyle();
        authorIdFieldBasicStyle = authorIdField.getStyle();
    }

    public void autoFillFields(ProjectAuthor projectAuthor) {
        projectIdField.setText(projectAuthor.getProjectId().toString());
        authorIdField.setText(projectAuthor.getAuthorId().toString());
    }

    public void clearFields() {
        projectIdField.setText(null);
        authorIdField.setText(null);
    }

    public ProjectAuthor getProjectAuthor() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new ProjectAuthor(
                        ControllerUtils.getUUIDFromTextField(
                                projectIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel)
                        ),
                        ControllerUtils.getUUIDFromTextField(
                                authorIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/удалении автора проекта должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        if (projectIdField.getText() == null || projectIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(projectIdLabel));
        if (authorIdField.getText() == null || authorIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        projectIdField.setEditable(editable);
        authorIdField.setEditable(editable);
    }

    public void setFieldsEditableWithStyleAndPrompt(boolean editable) {
        if (editable) {
            projectIdField.setPromptText(projectIdFieldPrompt);
            projectIdField.setEditable(true);
            projectIdField.setStyle(projectIdFieldBasicStyle);

            authorIdField.setPromptText(authorIdFieldPrompt);
            authorIdField.setEditable(true);
            authorIdField.setStyle(authorIdFieldBasicStyle);
        } else {
            projectIdField.setPromptText("Заполняется автоматически");
            projectIdField.setEditable(false);
            projectIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            authorIdField.setPromptText("Заполняется автоматически");
            authorIdField.setEditable(false);
            authorIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
