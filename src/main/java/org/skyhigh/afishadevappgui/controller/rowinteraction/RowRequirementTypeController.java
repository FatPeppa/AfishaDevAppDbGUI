package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowRequirementTypeController {
    @FXML
    TextField requirementTypeIdField;

    @FXML
    TextField nameField;

    @FXML
    Label requirementTypeIdLabel;

    @FXML
    Label nameLabel;

    private String requirementTypeIdFieldPrompt;
    private String nameFieldPrompt;

    private String requirementTypeIdFieldBasicStyle;
    private String nameFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        requirementTypeIdFieldPrompt = requirementTypeIdField.getPromptText();
        nameFieldPrompt = nameField.getPromptText();

        requirementTypeIdFieldBasicStyle = requirementTypeIdField.getStyle();
        nameFieldBasicStyle = nameField.getStyle();
    }

    public void autoFillFields(RequirementType requirementType) {
        requirementTypeIdField.setText(requirementType.getRequirementTypeId().toString());
        nameField.setText(requirementType.getRequirementTypeName());
    }

    public void clearFields() {
        requirementTypeIdField.setText(null);
        nameField.setText(null);
    }

    public RequirementType getRequirementType() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new RequirementType(
                        ControllerUtils.getUUIDFromTextField(
                                requirementTypeIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(requirementTypeIdLabel)
                        ),
                        nameField.getText()
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении типа требования должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getRequirementTypeId() throws CommonUIFormatException {
        return ControllerUtils.getUUIDFromTextField(
                requirementTypeIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(requirementTypeIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (requirementTypeIdField.getText() == null || requirementTypeIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(requirementTypeIdLabel));
        if (nameField.getText() == null || nameField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(nameLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            requirementTypeIdField.setPromptText(requirementTypeIdFieldPrompt);
            requirementTypeIdField.setEditable(false);
            requirementTypeIdField.setStyle(requirementTypeIdFieldBasicStyle);

            nameField.setPromptText(nameFieldPrompt);
            nameField.setEditable(true);
            nameField.setStyle(nameFieldBasicStyle);
        } else {
            requirementTypeIdField.setPromptText("Заполняется автоматически");
            requirementTypeIdField.setEditable(false);
            requirementTypeIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            nameField.setPromptText("Заполняется автоматически");
            nameField.setEditable(false);
            nameField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
