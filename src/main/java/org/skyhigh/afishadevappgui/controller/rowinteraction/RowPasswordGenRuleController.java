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

    public void initialize() throws CommonFlkException {}

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
}
