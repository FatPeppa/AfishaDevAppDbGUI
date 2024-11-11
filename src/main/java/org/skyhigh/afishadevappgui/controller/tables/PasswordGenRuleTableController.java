package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;
import org.skyhigh.afishadevappgui.data.repository.PasswordGenRuleRepository;
import org.skyhigh.afishadevappgui.data.repository.PasswordGenRuleRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

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

    private final PasswordGenRuleRepository passwordGenRuleRepository = new PasswordGenRuleRepositoryImpl(ApplicationPropertiesReader.getApplicationProperties());

    @Getter
    private PasswordGenRule selectedPasswordGenRule;

    public PasswordGenRuleTableController() throws CommonFlkException {}

    public void initialize() {
        ruleIdPasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("ruleId"));
        repCharAmPasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("repeatableCharactersAmount"));
        capLetAmPasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("capitalLettersAmount"));
        specSymAmPasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("specSymbolsAmount"));
        beginDatePasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        endDatePasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        createDatePasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        numSymAmPasswordGenRuleTableColumn.setCellValueFactory(new PropertyValueFactory<>("numericSymbolsAmount"));
        setPasswordGenRuleTableSelectedItemPropertyListener();
    }

    public void fillTable() throws CommonFlkException {
        ObservableList<PasswordGenRule> passwordGenRulesListView = FXCollections.observableArrayList();
        List<PasswordGenRule> passwordGenRules = passwordGenRuleRepository.getAllPasswordGenRules(
                SortDirection.NONE,
                null
        );
        passwordGenRulesListView.addAll(passwordGenRules);
        passwordGenRuleTable.setItems(passwordGenRulesListView);
    }

    public void fillTable(List<PasswordGenRule> passwordGenRules) {
        ObservableList<PasswordGenRule> passwordGenRuleObservableList = FXCollections.observableArrayList();
        passwordGenRuleObservableList.addAll(passwordGenRules);
        passwordGenRuleTable.setItems(passwordGenRuleObservableList);
    }

    private void setPasswordGenRuleTableSelectedItemPropertyListener() {
        passwordGenRuleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                selectedPasswordGenRule = newSelection;
        });
    }

    public void clearSelection() {
        passwordGenRuleTable.getSelectionModel().clearSelection();
        selectedPasswordGenRule = null;
    }

    public ObservableValue<PasswordGenRule> getObservableSelectedPasswordGenRule() {
        return passwordGenRuleTable.getSelectionModel().selectedItemProperty();
    }
}
