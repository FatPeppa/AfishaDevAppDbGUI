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
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.controller.filters.*;
import org.skyhigh.afishadevappgui.controller.tables.*;
import org.skyhigh.afishadevappgui.data.datasource.entity.*;
import org.skyhigh.afishadevappgui.data.repository.*;
import org.skyhigh.afishadevappgui.service.logic.search.*;

import java.io.IOException;
import java.util.List;

@Slf4j
public class MainController {
    // Controlled labels
    @FXML private Label currentUserLogin;
    @FXML private Label openedTableName;

    // Nested fxml panes
    @FXML private BorderPane filtersPane;
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
    @FXML private Button filterTableRowsBt;

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

    @FXML
    AccessedRoleFiltersController accessedRoleFiltersController;

    @FXML
    AuthorFiltersController authorFiltersController;

    @FXML
    CodeFileFiltersController codeFileFiltersController;

    @FXML
    DbUserFiltersController dbUserFiltersController;

    @FXML
    DeploymentFiltersController deploymentFiltersController;

    @FXML
    DeploymentStatusFiltersController deploymentStatusFiltersController;

    @FXML
    PasswordGenRuleFiltersController passwordGenRuleFiltersController;

    @FXML
    ProjectAuthorFiltersController projectAuthorFiltersController;

    @FXML
    ProjectFiltersController projectFiltersController;

    @FXML
    RequirementAuthorFiltersController requirementAuthorFiltersController;

    @FXML
    RequirementFiltersController requirementFiltersController;

    @FXML
    RequirementTypeFiltersController requirementTypeFiltersController;

    @FXML
    SecretFiltersController secretFiltersController;

    // Services
    private AccessedRoleSearchService accessedRoleSearchService;

    private AuthorSearchService authorSearchService;

    private CodeFileSearchService codeFileSearchService;

    private DbUserSearchService dbUserSearchService;

    private DeploymentSearchService deploymentSearchService;

    private DeploymentStatusSearchService deploymentStatusSearchService;

    private PasswordGenRuleSearchService passwordGenRuleSearchService;

    private ProjectAuthorSearchService projectAuthorSearchService;

    private ProjectSearchService projectSearchService;

    private RequirementSearchService requirementSearchService;

    private RequirementTypeSearchService requirementTypeSearchService;

    private SecretSearchService secretSearchService;

    private RequirementAuthorSearchService requirementAuthorSearchService;

    private final String chosenTableButtonStyle = "-fx-background-color: #000002; -fx-text-fill: white;";

    private DbUser currentDbUser;

    public void setCurrentDbUser(DbUser currentDbUser) {
        this.currentDbUser = currentDbUser;
        this.currentUserLogin.setText(currentDbUser.getUserLogin());
    }

    public void initialize() throws IOException, CommonFlkException {
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

        AccessedRoleRepository accessedRoleRepository = new AccessedRoleRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        AuthorRepository authorRepository = new AuthorRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        CodeFileRepository codeFileRepository = new CodeFileRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        DbUserRepository dbUserRepository = new DbUserRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        DeploymentRepository deploymentRepository = new DeploymentRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        DeploymentStatusRepository deploymentStatusRepository = new DeploymentStatusRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        PasswordGenRuleRepository passwordGenRuleRepository = new PasswordGenRuleRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        ProjectAuthorRepository projectAuthorRepository = new ProjectAuthorRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        ProjectRepository projectRepository = new ProjectRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        RequirementAuthorRepository requirementAuthorRepository = new RequirementAuthorRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        RequirementRepository requirementRepository = new RequirementRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        RequirementTypeRepository requirementTypeRepository = new RequirementTypeRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        SecretRepository secretRepository = new SecretRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );

        accessedRoleSearchService = new AccessedRoleSearchServiceImpl(
                accessedRoleRepository
        );
        authorSearchService = new AuthorSearchServiceImpl(
                authorRepository
        );
        codeFileSearchService = new CodeFileSearchServiceImpl(
                codeFileRepository
        );
        dbUserSearchService = new DbUserSearchServiceImpl(
                dbUserRepository
        );
        deploymentSearchService = new DeploymentSearchServiceImpl(
                deploymentRepository
        );
        deploymentStatusSearchService = new DeploymentStatusSearchServiceImpl(
                deploymentStatusRepository
        );
        passwordGenRuleSearchService = new PasswordGenRuleSearchServiceImpl(
                passwordGenRuleRepository
        );
        projectAuthorSearchService = new ProjectAuthorSearchServiceImpl(
                projectAuthorRepository
        );
        projectSearchService = new ProjectSearchServiceImpl(
                projectRepository
        );
        requirementAuthorSearchService = new RequirementAuthorSearchServiceImpl(
                requirementAuthorRepository
        );
        requirementSearchService = new RequirementSearchServiceImpl(
                requirementRepository
        );
        requirementTypeSearchService = new RequirementTypeSearchServiceImpl(
                requirementTypeRepository
        );
        secretSearchService = new SecretSearchServiceImpl(
                secretRepository
        );
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
        FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-db-user-view.fxml"));
        AnchorPane filterPane = (AnchorPane) filtersLoader.load();
        dbUserFiltersController = (DbUserFiltersController) filtersLoader.getController();
        filtersPane.setCenter(filterPane);
        setOnActionFilterTableRowsBtForDbUsers();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-db-user-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                dbUserFiltersController = (DbUserFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForDbUsers();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-author-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                authorFiltersController = (AuthorFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForAuthors();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-requirement-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                requirementFiltersController = (RequirementFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForRequirements();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-requirement-type-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                requirementTypeFiltersController = (RequirementTypeFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForRequirementTypes();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-project-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                projectFiltersController = (ProjectFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForProjects();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-code-file-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                codeFileFiltersController = (CodeFileFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForCodeFiles();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-deployment-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                deploymentFiltersController = (DeploymentFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForDeployments();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-deployment-status-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                deploymentStatusFiltersController = (DeploymentStatusFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForDeploymentsStatuses();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-project-author-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                projectAuthorFiltersController = (ProjectAuthorFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForProjectAuthors();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-requirement-author-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                requirementAuthorFiltersController = (RequirementAuthorFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForRequirementAuthors();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-accessed-role-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                accessedRoleFiltersController = (AccessedRoleFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);

                setOnActionFilterTableRowsBtForAccessedRoles();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-secret-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                secretFiltersController = (SecretFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForSecrets();
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

                FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-password-gen-rule-view.fxml"));
                AnchorPane filterPane = (AnchorPane) filtersLoader.load();
                passwordGenRuleFiltersController = (PasswordGenRuleFiltersController) filtersLoader.getController();
                filtersPane.setCenter(filterPane);
                setOnActionFilterTableRowsBtForPasswordGenRules();
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

    private void setOnActionFilterTableRowsBtForAccessedRoles() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<AccessedRole> foundAccessedRoles = accessedRoleSearchService.searchAccessedRoles(
                        accessedRoleFiltersController.getRequirementId(),
                        accessedRoleFiltersController.getRoleName()
                );
                accessedRoleTableController.fillTable(foundAccessedRoles);
            } catch (CommonFlkException e) {
                log.debug("Failed to search accessed roles for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForAuthors() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<Author> foundAuthor = authorSearchService.searchAuthors(
                        authorFiltersController.getAuthorId(),
                        authorFiltersController.getAuthorLogin()
                );
                authorTableController.fillTable(foundAuthor);
            } catch (CommonFlkException e) {
                log.debug("Failed to search authors for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForCodeFiles() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<CodeFile> foundCodeFiles = codeFileSearchService.searchCodeFiles(
                        codeFileFiltersController.getCodeFileId(),
                        codeFileFiltersController.getProjectId()
                );
                codeFileTableController.fillTable(foundCodeFiles);
            } catch (CommonFlkException e) {
                log.debug("Failed to search code files for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForDbUsers() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<DbUser> foundDbUsers = dbUserSearchService.searchDbUsers(
                        dbUserFiltersController.getUserId(),
                        dbUserFiltersController.getAuthorId(),
                        dbUserFiltersController.getUserLogin()
                );
                dbUserTableController.fillTable(foundDbUsers);
            } catch (CommonFlkException e) {
                log.debug("Failed to search db-users for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForDeployments() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<Deployment> foundDeployments = deploymentSearchService.searchDeployment(
                        deploymentFiltersController.getDeploymentId(),
                        deploymentFiltersController.getProjectId(),
                        deploymentFiltersController.getDeploymentStatusId(),
                        deploymentFiltersController.getBuiltVersion(),
                        deploymentFiltersController.getDeploymentPath()
                );
                deploymentTableController.fillTable(foundDeployments);
            } catch (CommonFlkException e) {
                log.debug("Failed to search deployments for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForDeploymentsStatuses() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<DeploymentStatus> foundDeploymentStatuses = deploymentStatusSearchService.searchDeploymentStatus(
                        deploymentStatusFiltersController.getDeploymentStatusId(),
                        deploymentStatusFiltersController.getDeploymentStatusName()
                );
                deploymentStatusTableController.fillTable(foundDeploymentStatuses);
            } catch (CommonFlkException e) {
                log.debug("Failed to search deployment statuses for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForPasswordGenRules() throws CommonFlkException {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<PasswordGenRule> foundPasswordGenRules = passwordGenRuleSearchService.searchPasswordGenRule(
                        passwordGenRuleFiltersController.getPasswordGenRuleId(),
                        passwordGenRuleFiltersController.getActualizationDate()
                );
                passwordGenRuleTableController.fillTable(foundPasswordGenRules);
            } catch (CommonFlkException e) {
                log.debug("Failed to search password generation rules for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForProjectAuthors() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<ProjectAuthor> foundProjectAuthors = projectAuthorSearchService.searchProjectAuthor(
                        projectAuthorFiltersController.getAuthorId(),
                        projectAuthorFiltersController.getProjectId()
                );
                projectAuthorTableController.fillTable(foundProjectAuthors);
            } catch (CommonFlkException e) {
                log.debug("Failed to search project authors for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForProjects() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<Project> foundProjects = projectSearchService.searchProject(
                        projectFiltersController.getProjectId(),
                        projectFiltersController.getProjectName(),
                        projectFiltersController.getProjectVersion(),
                        projectFiltersController.getLoadDateStart(),
                        projectFiltersController.getLoadDateEnd(),
                        projectFiltersController.getLastChangeDateStart(),
                        projectFiltersController.getLastChangeDateEnd()
                );
                projectTableController.fillTable(foundProjects);
            } catch (CommonFlkException e) {
                log.debug("Failed to search projects for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForRequirementAuthors() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<RequirementAuthor> foundRequirementAuthors = requirementAuthorSearchService.searchRequirementAuthor(
                        requirementAuthorFiltersController.getAuthorId(),
                        requirementAuthorFiltersController.getRequirementId()
                );
                requirementAuthorTableController.fillTable(foundRequirementAuthors);
            } catch (CommonFlkException e) {
                log.debug("Failed to search requirement authors for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForRequirements() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<Requirement> foundRequirements = requirementSearchService.searchRequirement(
                        requirementFiltersController.getRequirementId(),
                        requirementFiltersController.getRequirementTypeId(),
                        requirementFiltersController.getLoadDateStart(),
                        requirementFiltersController.getLoadDateEnd(),
                        requirementFiltersController.getLastChangeDateStart(),
                        requirementFiltersController.getLastChangeDateEnd()
                );
                requirementTableController.fillTable(foundRequirements);
            } catch (CommonFlkException e) {
                log.debug("Failed to search requirements for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForRequirementTypes() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<RequirementType> foundRequirementTypes = requirementTypeSearchService.searchRequirementType(
                        requirementTypeFiltersController.getRequirementTypeId(),
                        requirementTypeFiltersController.getRequirementTypeName()
                );
                requirementTypeTableController.fillTable(foundRequirementTypes);
            } catch (CommonFlkException e) {
                log.debug("Failed to search requirement types for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionFilterTableRowsBtForSecrets() {
        filterTableRowsBt.setOnAction(event -> {
            try {
                List<Secret> foundSecrets = secretSearchService.searchSecret(
                        secretFiltersController.getSecretId(),
                        secretFiltersController.getDeploymentId(),
                        secretFiltersController.getAddress(),
                        secretFiltersController.getLogin()
                );
                secretTableController.fillTable(foundSecrets);
            } catch (CommonFlkException e) {
                log.debug("Failed to search secrets for filter table rows: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }
}
