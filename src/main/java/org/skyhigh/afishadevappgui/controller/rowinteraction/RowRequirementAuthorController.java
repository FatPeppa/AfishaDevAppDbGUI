package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.util.ArrayList;
import java.util.List;

public class RowRequirementAuthorController {
    @FXML
    TextField requirementIdField;

    @FXML
    TextField authorIdField;

    @FXML
    Label requirementIdLabel;

    @FXML
    Label authorIdLabel;

    private String requirementIdFieldPrompt;
    private String authorIdFieldPrompt;

    private String requirementIdFieldBasicStyle;
    private String authorIdFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        requirementIdFieldPrompt = requirementIdField.getPromptText();
        authorIdFieldPrompt = authorIdField.getPromptText();

        requirementIdFieldBasicStyle = requirementIdField.getStyle();
        authorIdFieldBasicStyle = authorIdField.getStyle();
    }

    public void autoFillFields(RequirementAuthor requirementAuthor) {
        requirementIdField.setText(requirementAuthor.getRequirementId().toString());
        authorIdField.setText(requirementAuthor.getAuthorId().toString());
    }

    public void clearFields() {
        requirementIdField.setText(null);
        authorIdField.setText(null);
    }

    public RequirementAuthor getRequirementAuthor() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new RequirementAuthor(
                        ControllerUtils.getUUIDFromTextField(
                                requirementIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel)
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
                    "При сохранении/удалении автора требования должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        if (requirementIdField.getText() == null || requirementIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel));
        if (authorIdField.getText() == null || authorIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(authorIdLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        requirementIdField.setEditable(editable);
        authorIdField.setEditable(editable);
    }

    public void setFieldsEditableWithStyleAndPrompt(boolean editable) {
        if (editable) {
            requirementIdField.setPromptText(requirementIdFieldPrompt);
            requirementIdField.setEditable(false);
            requirementIdField.setStyle(requirementIdFieldBasicStyle);

            authorIdField.setPromptText(authorIdFieldPrompt);
            authorIdField.setEditable(true);
            authorIdField.setStyle(authorIdFieldBasicStyle);
        } else {
            requirementIdField.setPromptText("Заполняется автоматически");
            requirementIdField.setEditable(false);
            requirementIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            authorIdField.setPromptText("Заполняется автоматически");
            authorIdField.setEditable(false);
            authorIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
