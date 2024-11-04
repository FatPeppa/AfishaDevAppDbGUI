package org.skyhigh.afishadevappgui.common.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class ControllerUtils {
    public static UUID getUUIDFromTextField(
            TextField textField,
            String textFieldLogicName
    ) {
        if (textField.getText().isEmpty())
            return null;
        try {
            return UUID.fromString(textField.getText());
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText(String.format("Недопустимый формат поля '%s'", textFieldLogicName));
            alert.setContentText("Тип поля должен быть UUID");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            return null;
        }
    }

    public static Integer getIntegerFromTextField(
            TextField textField,
            String textFieldLogicName
    ) {
        if (textField.getText().isEmpty())
            return null;
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Недопустимый формат поля");
            alert.setContentText(String.format("Тип поля '%s' должен быть Integer", textFieldLogicName));
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            return null;
        }
    }

    //2024-10-01 23:00:00
    //2024-10-01T23:00:00
    public static LocalDateTime getLocalDateTimeFromTextField(
            TextField textField,
            String textFieldLogicName
    ) {
        String textFieldValue = textField.getText();
        if (textFieldValue.isEmpty())
            return null;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            format.setLenient(false);
            format.parse(textFieldValue);
            DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                    .toFormatter();
            return LocalDateTime.parse(textFieldValue, dateTimeFormatter);
        } catch(ParseException | DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Недопустимый формат поля");
            alert.setContentText(String.format("Значение поля '%s' должно соответствовать маске 'ГГГГ-ММ-ДДTЧЧ-МИ-СС', " +
                    "где ГГГГ - год, ММ - месяц, ДД - день, ЧЧ - час, МИ - минута и СС - секунда, а латинская T - разделитель", textFieldLogicName));
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

            alert.showAndWait();

            return null;
        }
    }

    public static String getFieldLocalNameFromItsLabel(Label label) {
        if (label == null || label.getText().isEmpty())
            return null;
        return label.getText().replaceAll(":$", "");
    }

    public static void showCommonFlkExceptionAlert(CommonFlkException e) {
        StringWriter errorMsg = new StringWriter();
        if (e.getCause() != null && e.getCause().getCause() != null && e.getCause().getCause() instanceof CommonFlkException) {
            errorMsg.write(e.getCause().getCause().getMessage());
        } else {
            e.printStackTrace(new PrintWriter(errorMsg));
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Ошибка при проверке ФЛК");
        alert.setContentText(String.format(errorMsg.toString()));
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);

        alert.showAndWait();
    }
}
