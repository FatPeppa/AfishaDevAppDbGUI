package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;

import java.util.ArrayList;
import java.util.List;

public class RowAccessedRoleController {
    @FXML
    TextField requirementIdField;

    @FXML
    TextField roleNameInputField;

    @FXML
    Label requirementIdLabel;

    @FXML
    Label roleNameLabel;

    private String requirementIdPrompt;
    private String roleNamePrompt;

    private String requirementIdBasicStyle;
    private String roleNameBasicStyle;

    public void initialize() throws CommonFlkException {
        requirementIdPrompt = requirementIdField.getPromptText();
        roleNamePrompt = roleNameInputField.getPromptText();
        requirementIdBasicStyle = requirementIdField.getStyle();
        roleNameBasicStyle = roleNameInputField.getStyle();
    }

    public void autoFillFields(AccessedRole accessedRole) {
        requirementIdField.setText(accessedRole.getRequirementId().toString());
        requirementIdField.setEditable(false);
        requirementIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        if (accessedRole.getRoleName() != null) {roleNameInputField.setText(accessedRole.getRoleName());}
    }

    public void clearFields() {
        requirementIdField.setText(null);
        requirementIdField.setEditable(true);
        requirementIdField.setStyle(null);
        roleNameInputField.setText(null);
    }

    public AccessedRole getRole() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new AccessedRole(
                        ControllerUtils.getUUIDFromTextField(
                                requirementIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel)
                        ),
                        roleNameInputField.getText()
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении/удалении роли должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        if (requirementIdField.getText() == null || requirementIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel));
        if (roleNameInputField.getText() == null || roleNameInputField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(roleNameLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            requirementIdField.setPromptText(requirementIdPrompt);
            requirementIdField.setEditable(false);
            requirementIdField.setStyle(requirementIdBasicStyle);

            roleNameInputField.setPromptText(roleNamePrompt);
            roleNameInputField.setEditable(true);
            roleNameInputField.setStyle(roleNameBasicStyle);
        } else {
            requirementIdField.setPromptText("Заполняется автоматически");
            requirementIdField.setEditable(false);
            requirementIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            roleNameInputField.setPromptText("Заполняется автоматически");
            roleNameInputField.setEditable(false);
            roleNameInputField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
