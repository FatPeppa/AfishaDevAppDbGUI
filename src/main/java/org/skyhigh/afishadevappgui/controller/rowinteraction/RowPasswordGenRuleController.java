package org.skyhigh.afishadevappgui.controller.rowinteraction;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIFormatException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RowPasswordGenRuleController {
    @FXML
    TextField ruleIdField;

    @FXML
    TextField minCapLetField;

    @FXML
    TextField maxRepSymField;

    @FXML
    TextField minSpecSymField;

    @FXML
    TextField beginDateField;

    @FXML
    TextField endDateField;

    @FXML
    TextField uploadDateField;

    @FXML
    TextField minNumField;

    @FXML
    Label ruleIdLabel;

    @FXML
    Label maxRepSymLabel;

    @FXML
    Label minCapLetLabel;

    @FXML
    Label minSpecSymLabel;

    @FXML
    Label endDateLabel;

    @FXML
    Label beginDateLabel;

    @FXML
    Label uploadDateLabel;

    @FXML
    Label minNumLabel;

    private String ruleIdFieldPrompt;
    private String minCapLetFieldPrompt;
    private String maxRepSymFieldPrompt;
    private String minSpecSymFieldPrompt;
    private String beginDateFieldPrompt;
    private String endDateFieldPrompt;
    private String uploadDateFieldPrompt;
    private String minNumFieldPrompt;

    private String ruleIdFieldBasicStyle;
    private String minCapLetFieldBasicStyle;
    private String maxRepSymFieldBasicStyle;
    private String minSpecSymFieldBasicStyle;
    private String beginDateFieldBasicStyle;
    private String endDateFieldBasicStyle;
    private String uploadDateFieldBasicStyle;
    private String minNumFieldBasicStyle;

    public void initialize() throws CommonFlkException {
        ruleIdFieldPrompt = ruleIdField.getPromptText();
        minCapLetFieldPrompt = minCapLetField.getPromptText();
        maxRepSymFieldPrompt = maxRepSymField.getPromptText();
        minSpecSymFieldPrompt = minSpecSymField.getPromptText();
        beginDateFieldPrompt = beginDateField.getPromptText();
        endDateFieldPrompt = endDateField.getPromptText();
        uploadDateFieldPrompt = uploadDateField.getPromptText();
        minNumFieldPrompt = minNumField.getPromptText();

        ruleIdFieldBasicStyle = ruleIdField.getStyle();
        minCapLetFieldBasicStyle = minCapLetField.getStyle();
        maxRepSymFieldBasicStyle = maxRepSymField.getStyle();
        minSpecSymFieldBasicStyle = minSpecSymField.getStyle();
        beginDateFieldBasicStyle = beginDateField.getStyle();
        endDateFieldBasicStyle = endDateField.getStyle();
        uploadDateFieldBasicStyle = uploadDateField.getStyle();
        minNumFieldBasicStyle = minNumField.getStyle();
    }

    //2023-01-01T22:22:22
    //2023-01-01T22:22:23
    public void autoFillFields(PasswordGenRule passwordGenRule) {
        ruleIdField.setText(passwordGenRule.getRuleId().toString());
        minCapLetField.setText(passwordGenRule.getCapitalLettersAmount().toString());
        maxRepSymField.setText(passwordGenRule.getRepeatableCharactersAmount().toString());
        minSpecSymField.setText(passwordGenRule.getSpecSymbolsAmount().toString());
        if (passwordGenRule.getBeginDate() != null)
            beginDateField.setText(passwordGenRule.getBeginDate().toString());
        if (passwordGenRule.getEndDate() != null)
            endDateField.setText(passwordGenRule.getEndDate().toString());
        uploadDateField.setText(passwordGenRule.getCreateDate().toString());
        minNumField.setText(passwordGenRule.getNumericSymbolsAmount().toString());
    }

    public void clearFields() {
        ruleIdField.setText(null);
        minCapLetField.setText(null);
        maxRepSymField.setText(null);
        minSpecSymField.setText(null);
        beginDateField.setText(null);
        endDateField.setText(null);
        uploadDateField.setText(null);
        minNumField.setText(null);
    }

    public PasswordGenRule getPasswordGenRule() throws CommonFlkException {
        List<String> emptyField = getEmptyNecessaryFields();
        LocalDateTime beginDate = beginDateField.getText() != null && !beginDateField.getText().isEmpty() ?
                ControllerUtils.getLocalDateTimeFromTextField(
                        beginDateField,
                        ControllerUtils.getFieldLocalNameFromItsLabel(beginDateLabel)
                ) : null;
        LocalDateTime endDate = endDateField.getText() != null && !endDateField.getText().isEmpty() ?
                ControllerUtils.getLocalDateTimeFromTextField(
                        endDateField,
                        ControllerUtils.getFieldLocalNameFromItsLabel(endDateLabel)
                ) : null;
        try {
            ControllerUtils.checkDateDiapasonPositive(
                    beginDate,
                    endDate,
                    ControllerUtils.getFieldLocalNameFromItsLabel(beginDateLabel),
                    ControllerUtils.getFieldLocalNameFromItsLabel(endDateLabel)
            );
        } catch (CommonFlkException e) {
            ControllerUtils.showCommonFlkExceptionAlert(e);
        }
        if (emptyField.isEmpty())
            try {
                return new PasswordGenRule(
                        ControllerUtils.getIntegerFromTextField(
                                ruleIdField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(ruleIdLabel)
                        ),
                        ControllerUtils.getIntegerFromTextField(
                                maxRepSymField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(maxRepSymLabel)
                        ),
                        ControllerUtils.getIntegerFromTextField(
                                minCapLetField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(minCapLetLabel)
                        ),
                        ControllerUtils.getIntegerFromTextField(
                                minSpecSymField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(minSpecSymLabel)
                        ),
                        beginDate,
                        endDate,
                        ControllerUtils.getLocalDateTimeFromTextField(
                                uploadDateField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel)
                        ),
                        ControllerUtils.getIntegerFromTextField(
                                minNumField,
                                ControllerUtils.getFieldLocalNameFromItsLabel(minNumLabel)
                        )
                );
            } catch (CommonUIFormatException e) {
                return null;
            }
        else {
            throw new CommonUIException(
                    "1004000",
                    "При сохранении/изменении правила генерации (заполнения) паролей должны быть заполнены обязательные атрибуты",
                    emptyField
            );
        }
    }

    public Integer getPasswordGenRuleId() throws CommonUIFormatException {
        return ControllerUtils.getIntegerFromTextField(
                ruleIdField,
                ControllerUtils.getFieldLocalNameFromItsLabel(ruleIdLabel)
        );
    }

    private List<String> getEmptyNecessaryFields() {
        List<String> emptyFields = new ArrayList<>();
        //if (ruleIdField.getText() == null || ruleIdField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(ruleIdLabel));
        if (maxRepSymField.getText() == null || maxRepSymField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(maxRepSymLabel));
        if (minCapLetField.getText() == null || minCapLetField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(minCapLetLabel));
        if (minSpecSymField.getText() == null || minSpecSymField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(minSpecSymLabel));
        //if (uploadDateField.getText() == null || uploadDateField.getText().isEmpty())
        //    emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(uploadDateLabel));
        if (minNumField.getText() == null || minNumField.getText().isEmpty())
            emptyFields.add(ControllerUtils.getFieldLocalNameFromItsLabel(minNumLabel));
        return emptyFields;
    }

    public void setFieldsEditable(boolean editable) {
        if (editable) {
            ruleIdField.setPromptText(ruleIdFieldPrompt);
            ruleIdField.setEditable(false);
            ruleIdField.setStyle(ruleIdFieldBasicStyle);

            minCapLetField.setPromptText(minCapLetFieldPrompt);
            minCapLetField.setEditable(true);
            minCapLetField.setStyle(minCapLetFieldBasicStyle);

            maxRepSymField.setPromptText(maxRepSymFieldPrompt);
            maxRepSymField.setEditable(true);
            maxRepSymField.setStyle(maxRepSymFieldBasicStyle);

            minSpecSymField.setPromptText(minSpecSymFieldPrompt);
            minSpecSymField.setEditable(true);
            minSpecSymField.setStyle(minSpecSymFieldBasicStyle);

            beginDateField.setPromptText(beginDateFieldPrompt);
            beginDateField.setEditable(true);
            beginDateField.setStyle(beginDateFieldBasicStyle);

            endDateField.setPromptText(endDateFieldPrompt);
            endDateField.setEditable(true);
            endDateField.setStyle(endDateFieldBasicStyle);

            uploadDateField.setPromptText(uploadDateFieldPrompt);
            uploadDateField.setEditable(true);
            uploadDateField.setStyle(uploadDateFieldBasicStyle);

            minNumField.setPromptText(minNumFieldPrompt);
            minNumField.setEditable(true);
            minNumField.setStyle(minNumFieldBasicStyle);
        } else {
            ruleIdField.setPromptText("Заполняется автоматически");
            ruleIdField.setEditable(false);
            ruleIdField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            minCapLetField.setPromptText("Заполняется автоматически");
            minCapLetField.setEditable(false);
            minCapLetField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            maxRepSymField.setPromptText("Заполняется автоматически");
            maxRepSymField.setEditable(false);
            maxRepSymField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            minSpecSymField.setPromptText("Заполняется автоматически");
            minSpecSymField.setEditable(false);
            minSpecSymField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            beginDateField.setPromptText("Заполняется автоматически");
            beginDateField.setEditable(false);
            beginDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            endDateField.setPromptText("Заполняется автоматически");
            endDateField.setEditable(false);
            endDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            uploadDateField.setPromptText("Заполняется автоматически");
            uploadDateField.setEditable(false);
            uploadDateField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");

            minNumField.setPromptText("Заполняется автоматически");
            minNumField.setEditable(false);
            minNumField.setStyle("-fx-background-color: #e6e6e6; -fx-border-color: black;");
        }
    }
}
