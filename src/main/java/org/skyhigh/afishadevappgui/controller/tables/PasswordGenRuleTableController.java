package org.skyhigh.afishadevappgui.controller.tables;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.skyhigh.afishadevappgui.common.controller.RoleManagedTableController;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;
import org.skyhigh.afishadevappgui.data.repository.PasswordGenRuleRepository;
import org.skyhigh.afishadevappgui.data.repository.PasswordGenRuleRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;

import java.time.LocalDateTime;
import java.util.List;

public class PasswordGenRuleTableController implements RoleManagedTableController {
    private static final boolean isViewableForAnalyst = false;
    private static final boolean isViewableForDeveloper = false;
    private static final boolean isViewableForQA= true;
    private static final boolean isViewableForDevOps = true;
    private static final boolean isEditableForAnalyst = false;
    private static final boolean isEditableForDeveloper = false;
    private static final boolean isEditableForQA= false;
    private static final boolean isEditableForDevOps = true;

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

    private RoleManagerService roleManagerService;

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

    @Override
    public boolean getAccessibilityForViewingByAnalyst() {
        return isViewableForAnalyst;
    }

    @Override
    public boolean getAccessibilityForViewingByDeveloper() {
        return isViewableForDeveloper;
    }

    @Override
    public boolean getAccessibilityForViewingByQA() {
        return isViewableForQA;
    }

    @Override
    public boolean getAccessibilityForViewingByDevOps() {
        return isViewableForDevOps;
    }

    @Override
    public boolean getAccessibilityForEditingByAnalyst() {
        return isEditableForAnalyst;
    }

    @Override
    public boolean getAccessibilityForEditingByDeveloper() {
        return isEditableForDeveloper;
    }

    @Override
    public boolean getAccessibilityForEditingByQA() {
        return isEditableForQA;
    }

    @Override
    public boolean getAccessibilityForEditingByDevOps() {
        return isEditableForDevOps;
    }

    @Override
    public void setRoleManagerService(RoleManagerService roleManagerService) {
        this.roleManagerService = roleManagerService;
    }
}
