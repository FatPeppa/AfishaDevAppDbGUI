package org.skyhigh.afishadevappgui.controller.tables;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.time.LocalDateTime;

public class PasswordGenRuleTableController {
    @FXML
    private TableView<PasswordGenRule> passwordGenRuleTable;

    @FXML
    private TableColumn<PasswordGenRule, Integer> ruleIdPasswordGenRuleTableColumn;

    @FXML
    private TableColumn<PasswordGenRule, Integer> repCharAmPasswordGenRuleTableColumn;

    @FXML
    private TableColumn<PasswordGenRule, Integer> capLetAmPasswordGenRuleTableColumn;

    @FXML
    private TableColumn<PasswordGenRule, Integer> specSymAmPasswordGenRuleTableColumn;

    @FXML
    private TableColumn<PasswordGenRule, LocalDateTime> beginDatePasswordGenRuleTableColumn;

    @FXML
    private TableColumn<PasswordGenRule, LocalDateTime> endDatePasswordGenRuleTableColumn;

    @FXML
    private TableColumn<PasswordGenRule, LocalDateTime> createDatePasswordGenRuleTableColumn;

    @FXML
    private TableColumn<PasswordGenRule, Integer> numSymAmPasswordGenRuleTableColumn;

}
