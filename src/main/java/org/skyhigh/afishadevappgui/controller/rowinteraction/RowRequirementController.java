package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepository;
import org.skyhigh.afishadevappgui.data.repository.RequirementTypeRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

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

    private RoleManagerService roleManagerService;

    private String requirementIdFieldPrompt;
    private String requirementTypeIdFieldPrompt;
    private String contentFieldPrompt;
    private String uploadDateFieldPrompt;
    private String lastChangeDateFieldPrompt;

    private String requirementIdFieldBasicStyle;
    private String requirementTypeIdFieldBasicStyle;
    private String contentFieldBasicStyle;
    private String uploadDateFieldBasicStyle;
    private String lastChangeDateFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        requirementIdFieldPrompt = requirementIdField.getPromptText();
        requirementTypeIdFieldPrompt = requirementTypeIdField.getPromptText();
        contentFieldPrompt = contentField.getPromptText();
        uploadDateFieldPrompt = uploadDateField.getPromptText();
        lastChangeDateFieldPrompt = lastChangeDateField.getPromptText();

        requirementIdFieldBasicStyle = requirementIdField.getStyle();
        requirementTypeIdFieldBasicStyle = requirementTypeIdField.getStyle();
        contentFieldBasicStyle = contentField.getStyle();
        uploadDateFieldBasicStyle = uploadDateField.getStyle();
        lastChangeDateFieldBasicStyle = lastChangeDateField.getStyle();
    }

    public void autoFillFields(Requirement requirement) throws CommonFlkException {
        RequirementTypeRepository requirementTypeRepository = new RequirementTypeRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        requirementIdField.setText(requirement.getRequirementId().toString());
        if (roleManagerService.checkIfCurrentUserAdmin())
            requirementTypeIdField.setText(requirement.getRequirementTypeId().toString());
        else
            requirementTypeIdField.setText(requirementTypeRepository.getRequirementTypeById(requirement.getRequirementTypeId()).getRequirementTypeName());
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
        RequirementTypeRepository requirementTypeRepository = new RequirementTypeRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        if (emptyField.isEmpty())
            try {
                UUID requirementTypeId = null;
                if (!roleManagerService.checkIfCurrentUserAdmin()) {
                    if (requirementTypeRepository.getRequirementTypeByName(requirementTypeIdField.getText()) == null)
                        throw new CommonFlkException(
                                "Тип требования '" + requirementTypeIdField.getText() + "' не найден"
                        );
                    requirementTypeId = requirementTypeRepository.getRequirementTypeByName(requirementTypeIdField.getText()).getRequirementTypeId();
                } else {
                    requirementTypeId = ControllerUtils.getUUIDFromTextField(
                            requirementTypeIdField,
                            ControllerUtils.getFieldLocalNameFromItsLabel(requirementTypeIdLabel)
                    );
                }
                return new Requirement(
                        requirementIdField.getText() != null && !requirementIdField.getText().isEmpty() ? ControllerUtils.getUUIDFromTextField(
                                requirementIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(requirementIdLabel)
                        ) : null,
                        requirementTypeId,
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

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            requirementIdField.setPromptText(requirementIdFieldPrompt);
            requirementIdField.setEditable(false);
            requirementIdField.setStyle(requirementIdFieldBasicStyle);

            requirementTypeIdField.setPromptText(requirementTypeIdFieldPrompt);
            requirementTypeIdField.setEditable(true);
            requirementTypeIdField.setStyle(requirementTypeIdFieldBasicStyle);

            lastChangeDateField.setPromptText(lastChangeDateFieldPrompt);
            lastChangeDateField.setEditable(true);
            lastChangeDateField.setStyle(lastChangeDateFieldBasicStyle);

            uploadDateField.setPromptText(uploadDateFieldPrompt);
            uploadDateField.setEditable(true);
            uploadDateField.setStyle(uploadDateFieldBasicStyle);

            contentField.setPromptText(contentFieldPrompt);
            contentField.setEditable(true);
            contentField.setStyle(contentFieldBasicStyle);
        } else {
            requirementIdField.setPromptText("Заполняется автоматически");
            requirementIdField.setEditable(false);
            requirementIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            requirementTypeIdField.setPromptText("Заполняется автоматически");
            requirementTypeIdField.setEditable(false);
            requirementTypeIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            lastChangeDateField.setPromptText("Заполняется автоматически");
            lastChangeDateField.setEditable(false);
            lastChangeDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            uploadDateField.setPromptText("Заполняется автоматически");
            uploadDateField.setEditable(false);
            uploadDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            contentField.setPromptText("Заполняется автоматически");
            contentField.setEditable(false);
            contentField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }

    public void setRoleManagerService(RoleManagerService roleManagerService) throws CommonFlkException {
        this.roleManagerService = roleManagerService;

        if (!roleManagerService.checkIfCurrentUserAdmin()) {
            if (!requirementTypeIdField.getPromptText().equals("Заполняется автоматически"))
                requirementTypeIdField.setPromptText("Введите тип требования");
            requirementTypeIdLabel.setText("Тип требования:");
            requirementTypeIdFieldPrompt = "Введите тип требования";
        }
    }
}
