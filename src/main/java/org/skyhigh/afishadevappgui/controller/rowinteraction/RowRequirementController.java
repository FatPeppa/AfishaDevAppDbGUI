package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RowRequirementController {
    @FXML
    TextField requirementIdField;

    @FXML
    TextField requirementTypeIdField;

    @FXML
    TextField contentField;


    @FXML
    TextField uploadDateField;

    @FXML
    TextField lastChangeDateField;

    @FXML
    Label requirementIdLabel;

    @FXML
    Label requirementTypeIdLabel;

    @FXML
    Label contentLabel;

    @FXML
    Label lastChangeDateLabel;

    @FXML
    Label uploadDateLabel;

    public void initialize() throws CommonFlkException {}

    public void autoFillFields(Requirement requirement) {
        requirementIdField.setText(requirement.getRequirementId().toString());
        requirementTypeIdField.setText(requirement.getRequirementTypeId().toString());
        uploadDateField.setText(requirement.getLoadDate().toString());
        lastChangeDateField.setText(requirement.getLastChangeDate().toString());
        contentField.setText(requirement.getContent().toString());
    }

    public void clearFields() {
        requirementIdField.setText(null);
        requirementTypeIdField.setText(null);
        uploadDateField.setText(null);
        lastChangeDateField.setText(null);
        contentField.setText(null);
    }

    public Requirement getRequirement() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        if (emptyField.isEmpty())
            try {
                return new Requirement(
                        requirementIdField.getText() != null && !requirementIdField.getText().isEmpty() ? ControllerUtils.getUUIDFromTextField(
                                requirementIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel)
                        ) : null,
                        ControllerUtils.getUUIDFromTextField(
                                requirementTypeIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(requirementTypeIdLabel)
                        ),
                        ControllerUtils.getLocalDateTimeFromTextField(
                                uploadDateField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel)
                        ),
                        lastChangeDateField.getText() == null || lastChangeDateField.getText().isEmpty() ? null : ControllerUtils.getLocalDateTimeFromTextField(
                                lastChangeDateField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(lastChangeDateLabel)
                        ),
                        ControllerUtils.getJSONFromTextField(
                                contentField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(contentLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении требования должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public UUID getRequirementId() throws CommonUIFormatException {
        return requirementIdField != null && !requirementIdField.getText().isEmpty() ? ControllerUtils.getUUIDFromTextField(
                requirementIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel)
        ) : null;
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (requirementIdField.getText() == null || requirementIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel));
        if (requirementTypeIdField.getText() == null || requirementTypeIdField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(requirementTypeIdLabel));
        //if (uploadDateField.getText() == null || uploadDateField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel));
        //if (lastChangeDateField.getText() == null || lastChangeDateField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(lastChangeDateLabel));
        if (contentField.getText() == null || contentField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(contentLabel));
        return emptyFields;
    }
}
