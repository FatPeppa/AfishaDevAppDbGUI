package org.skyhigh.afishadevappgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.controller.tables.*;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.io.IOException;
import java.util.List;

public class MainController {
    // Controlled labels
    @FXML private Label currentUserLogin;
    @FXML private Label openedTableName;

    // Nested fxml panes
    @FXML private Pane filtersPane;
    @FXML private BorderPane tablePane;

    // Choosing table buttons
    @FXML private Button usersBt;
    @FXML private Button authorsBt;
    @FXML private Button requirementsBt;
    @FXML private Button requirementTypesBt;
    @FXML private Button projectsBt;
    @FXML private Button codeFilesBt;
    @FXML private Button deploymentsBt;
    @FXML private Button deploymentStatusesBt;
    @FXML private Button projectAuthorsBt;
    @FXML private Button requirementAuthorsBt;
    @FXML private Button accessibleRolesBt;
    @FXML private Button secretsBt;
    @FXML private Button passGenRuleBt;

    // Table interaction buttons
    @FXML private Button saveTableChangesBt;
    @FXML private Button addNewRowToTableBt;
    @FXML private Button deleteChosenRowFromTableBt;

    // Other buttons
    @FXML private Button logOutBt;

    // Nested controllers
    @FXML
    private AccessedRoleTableController accessedRoleTableController;

    @FXML
    private AuthorTableController authorTableController;

    @FXML
    private CodeFileTableController codeFileTableController;

    @FXML
    private DbUserTableController dbUserTableController;

    @FXML
    private DeploymentStatusTableController deploymentStatusTableController;

    @FXML
    private DeploymentTableController deploymentTableController;

    @FXML
    private PasswordGenRuleTableController passwordGenRuleTableController;

    @FXML
    private ProjectAuthorTableController projectAuthorTableController;

    @FXML
    private ProjectTableController projectTableController;

    @FXML
    private RequirementAuthorTableController requirementAuthorTableController;

    @FXML
    private RequirementTableController requirementTableController;

    @FXML
    private RequirementTypeTableController requirementTypeTableController;

    @FXML
    private SecretTableController secretTableController;

    private final String chosenTableButtonStyle = "-fx-background-color: #000002; -fx-text-fill: white;";

    private DbUser currentDbUser;

    public void setCurrentDbUser(DbUser currentDbUser) {
        this.currentDbUser = currentDbUser;
        this.currentUserLogin.setText(currentDbUser.getUserLogin());
    }

    public void initialize() throws IOException {
        setOnActionForUsersBt();
        setOnActionForAuthorsBt();
        setOnActionForRequirementsBt();
        setOnActionForRequirementTypesBt();
        setOnActionForProjectsBt();
        setOnActionForCodeFilesBt();
        setOnActionForDeploymentsBt();
        setOnActionForDeploymentStatusesBt();
        setOnActionForProjectAuthorsBt();
        setOnActionForRequirementAuthorsBt();
        setOnActionForAccessibleRolesBt();
        setOnActionForSecretsBt();
        setOnActionForPassGenRuleBt();
        setOnActionLogOutBt();
    }

    public void setDefaultTableViewAndFilters() throws IOException, CommonFlkException {
        makeAllTableChoosingButtonsAbleToBeClicked();
        clearAllButtonsStyles();
        FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/db-user-table-view.fxml"));
        AnchorPane centerTablePane = (AnchorPane) centerTableLoader.load();
        dbUserTableController = (DbUserTableController) centerTableLoader.getController();
        dbUserTableController.initialize();
        dbUserTableController.fillTable();
        openedTableName.setText(usersBt.getText());
        tablePane.setCenter(centerTablePane);
        usersBt.setDisable(true);
        usersBt.setStyle(chosenTableButtonStyle);
    }

    /**
     * Внутренний метод контроллера главного окна, предназначенный для сброса цвета всех кнопок выбора просматриваемой таблички.
     * Должен вызываться перед установкой цвета для новой выбранной пользователем кнопки выбора просматриваемой таблички
     */
    private void clearAllButtonsStyles() {
        usersBt.setStyle(null);
        authorsBt.setStyle(null);
        requirementsBt.setStyle(null);
        requirementTypesBt.setStyle(null);
        projectsBt.setStyle(null);
        codeFilesBt.setStyle(null);
        deploymentsBt.setStyle(null);
        deploymentStatusesBt.setStyle(null);
        projectAuthorsBt.setStyle(null);
        requirementAuthorsBt.setStyle(null);
        accessibleRolesBt.setStyle(null);
        secretsBt.setStyle(null);
        passGenRuleBt.setStyle(null);
    }

    /**
     * Внутренний метод контроллера главного окна, предназначенный для включения всех кнопок выбора просматриваемой таблички.
     * Должен вызываться перед установкой цвета для новой выбранной пользователем кнопки выбора просматриваемой таблички
     */
    private void makeAllTableChoosingButtonsAbleToBeClicked() {
        usersBt.setDisable(false);
        authorsBt.setDisable(false);
        requirementsBt.setDisable(false);
        requirementTypesBt.setDisable(false);
        projectsBt.setDisable(false);
        codeFilesBt.setDisable(false);
        deploymentsBt.setDisable(false);
        deploymentStatusesBt.setDisable(false);
        projectAuthorsBt.setDisable(false);
        requirementAuthorsBt.setDisable(false);
        accessibleRolesBt.setDisable(false);
        secretsBt.setDisable(false);
        passGenRuleBt.setDisable(false);
    }

    private Button getCurrentChosenTableButton() {
        List<Button> choosingTableButtons = List.of(
            usersBt, authorsBt, requirementsBt, requirementTypesBt,
            projectsBt, codeFilesBt, deploymentsBt, deploymentStatusesBt,
            projectAuthorsBt, requirementAuthorsBt, accessibleRolesBt,
            secretsBt, passGenRuleBt
        );

        return choosingTableButtons.stream().filter(Node::isDisable).findFirst().get();
    }

    private void setOnActionForUsersBt() {
        usersBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/db-user-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                dbUserTableController = (DbUserTableController) centerTableLoader.getController();
                dbUserTableController.initialize();
                dbUserTableController.fillTable();

                openedTableName.setText(usersBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                usersBt.setDisable(true);
                usersBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForAuthorsBt() {
        authorsBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/author-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                authorTableController = (AuthorTableController) centerTableLoader.getController();
                authorTableController.initialize();
                authorTableController.fillTable();

                openedTableName.setText(authorsBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                authorsBt.setDisable(true);
                authorsBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForRequirementsBt() {
        requirementsBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/requirement-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                requirementTableController = (RequirementTableController) centerTableLoader.getController();
                requirementTableController.initialize();
                requirementTableController.fillTable();

                openedTableName.setText(requirementsBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                requirementsBt.setDisable(true);
                requirementsBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForRequirementTypesBt() {
        requirementTypesBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/requirement-type-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                requirementTypeTableController = (RequirementTypeTableController) centerTableLoader.getController();
                requirementTypeTableController.initialize();
                requirementTypeTableController.fillTable();

                openedTableName.setText(requirementTypesBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                requirementTypesBt.setDisable(true);
                requirementTypesBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForProjectsBt() {
        projectsBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/project-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                projectTableController = (ProjectTableController) centerTableLoader.getController();
                projectTableController.initialize();
                projectTableController.fillTable();

                openedTableName.setText(projectsBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                projectsBt.setDisable(true);
                projectsBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForCodeFilesBt() {
        codeFilesBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/code-file-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                codeFileTableController = (CodeFileTableController) centerTableLoader.getController();
                codeFileTableController.initialize();
                codeFileTableController.fillTable();

                openedTableName.setText(codeFilesBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                codeFilesBt.setDisable(true);
                codeFilesBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForDeploymentsBt() {
        deploymentsBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/deployment-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                deploymentTableController = (DeploymentTableController) centerTableLoader.getController();
                deploymentTableController.initialize();
                deploymentTableController.fillTable();

                openedTableName.setText(deploymentsBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                deploymentsBt.setDisable(true);
                deploymentsBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForDeploymentStatusesBt() {
        deploymentStatusesBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/deployment-status-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                deploymentStatusTableController = (DeploymentStatusTableController) centerTableLoader.getController();
                deploymentStatusTableController.initialize();
                deploymentStatusTableController.fillTable();

                openedTableName.setText(deploymentStatusesBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                deploymentStatusesBt.setDisable(true);
                deploymentStatusesBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForProjectAuthorsBt() {
        projectAuthorsBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/project-author-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                projectAuthorTableController = (ProjectAuthorTableController) centerTableLoader.getController();
                projectAuthorTableController.initialize();
                projectAuthorTableController.fillTable();

                openedTableName.setText(projectAuthorsBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                projectAuthorsBt.setDisable(true);
                projectAuthorsBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForRequirementAuthorsBt() {
        requirementAuthorsBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/requirement-author-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                requirementAuthorTableController = (RequirementAuthorTableController) centerTableLoader.getController();
                requirementAuthorTableController.initialize();
                requirementAuthorTableController.fillTable();

                openedTableName.setText(requirementAuthorsBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                requirementAuthorsBt.setDisable(true);
                requirementAuthorsBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForAccessibleRolesBt() {
        accessibleRolesBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/accessed-role-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                accessedRoleTableController = (AccessedRoleTableController) centerTableLoader.getController();
                accessedRoleTableController.initialize();
                accessedRoleTableController.fillTable();

                openedTableName.setText(accessibleRolesBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                accessibleRolesBt.setDisable(true);
                accessibleRolesBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForSecretsBt() {
        secretsBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/secret-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                secretTableController = (SecretTableController) centerTableLoader.getController();
                secretTableController.initialize();
                secretTableController.fillTable();

                openedTableName.setText(secretsBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                secretsBt.setDisable(true);
                secretsBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionForPassGenRuleBt() {
        passGenRuleBt.setOnAction(event -> {
            try {
                FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/password-gen-rule-table-view.fxml"));
                AnchorPane centerTablePane = null;
                centerTablePane = (AnchorPane) centerTableLoader.load();

                passwordGenRuleTableController = (PasswordGenRuleTableController) centerTableLoader.getController();
                passwordGenRuleTableController.initialize();
                passwordGenRuleTableController.fillTable();

                openedTableName.setText(passGenRuleBt.getText());
                tablePane.setCenter(centerTablePane);
                makeAllTableChoosingButtonsAbleToBeClicked();
                clearAllButtonsStyles();
                passGenRuleBt.setDisable(true);
                passGenRuleBt.setStyle(chosenTableButtonStyle);
            } catch (IOException | CommonFlkException e) {
                Button currentChosenTableButton = getCurrentChosenTableButton();
                currentChosenTableButton.setDisable(true);
                currentChosenTableButton.setStyle(chosenTableButtonStyle);
                throw new RuntimeException(e);
            }
        });
    }

    private void setOnActionLogOutBt() {
        logOutBt.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("hello-view.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) logOutBt.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
