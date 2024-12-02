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
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;
import org.skyhigh.afishadevappgui.controller.filters.*;
import org.skyhigh.afishadevappgui.controller.rowinteraction.*;
import org.skyhigh.afishadevappgui.controller.tables.*;
import org.skyhigh.afishadevappgui.data.datasource.entity.*;
import org.skyhigh.afishadevappgui.data.repository.*;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerService;
import org.skyhigh.afishadevappgui.service.logic.role.RoleManagerServiceImpl;
import org.skyhigh.afishadevappgui.service.logic.search.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
public class MainController {
    // Controlled labels
    @FXML private Label currentUserLogin;
    @FXML private Label openedTableName;

    // Nested fxml panes
    @FXML private BorderPane filtersPane;
    @FXML private BorderPane tablePane;
    @FXML private BorderPane rowInteractionPane;

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
    @FXML private Button saveRowChangesBt;
    @FXML private Button addNewRowToTableBt;
    @FXML private Button deleteChosenRowFromTableBt;
    @FXML private Button filterTableRowsBt;
    @FXML private Button stopSelectingRowBt;

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

    @FXML
    RowAccessedRoleController rowAccessedRoleController;

    @FXML
    RowAuthorController rowAuthorController;

    @FXML
    RowCodeFileController rowCodeFileController;

    @FXML
    RowDbUserController rowDbUserController;

    @FXML
    RowDeploymentController rowDeploymentController;

    @FXML
    RowDeploymentStatusController rowDeploymentStatusController;

    @FXML
    RowPasswordGenRuleController rowPasswordGenRuleController;

    @FXML
    RowProjectAuthorController rowProjectAuthorController;

    @FXML
    RowProjectController rowProjectController;

    @FXML
    RowRequirementAuthorController rowRequirementAuthorController;

    @FXML
    RowRequirementController rowRequirementController;

    @FXML
    RowRequirementTypeController rowRequirementTypeController;

    @FXML
    RowSecretController rowSecretController;


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

    private RoleManagerService roleManagerService;

    // Repositories
    private AccessedRoleRepository accessedRoleRepository;

    private AuthorRepository authorRepository;

    private CodeFileRepository codeFileRepository;

    private DbUserRepository dbUserRepository;

    private DeploymentRepository deploymentRepository;

    private DeploymentStatusRepository deploymentStatusRepository;

    private PasswordGenRuleRepository passwordGenRuleRepository;

    private ProjectAuthorRepository projectAuthorRepository;

    private ProjectRepository projectRepository;

    private RequirementAuthorRepository requirementAuthorRepository;

    private RequirementRepository requirementRepository;

    private RequirementTypeRepository requirementTypeRepository;

    private SecretRepository secretRepository;

    private final String chosenTableButtonStyle = "-fx-background-color: #000002; -fx-text-fill: white;";

    @Setter
    private DbUser currentDbUser;

    private Object selectedRow;

    public void initialize() throws IOException, CommonFlkException {
        if (currentDbUser != null)
            this.currentUserLogin.setText(currentDbUser.getUserLogin());

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

        accessedRoleRepository = new AccessedRoleRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        authorRepository = new AuthorRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        codeFileRepository = new CodeFileRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        dbUserRepository = new DbUserRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        deploymentRepository = new DeploymentRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        deploymentStatusRepository = new DeploymentStatusRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        passwordGenRuleRepository = new PasswordGenRuleRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        projectAuthorRepository = new ProjectAuthorRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        projectRepository = new ProjectRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        requirementAuthorRepository = new RequirementAuthorRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        requirementRepository = new RequirementRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        requirementTypeRepository = new RequirementTypeRepositoryImpl(
                ApplicationPropertiesReader.getApplicationProperties()
        );
        secretRepository = new SecretRepositoryImpl(
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
        roleManagerService = new RoleManagerServiceImpl();
        if (currentDbUser != null) roleManagerService.setCurrentUserRole(currentDbUser);
    }

    public void setDefaultTableViewAndFilters() throws IOException, CommonFlkException {
        makeAllTableChoosingButtonsAbleToBeClicked();
        clearAllButtonsStyles();
        FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/author-table-view.fxml"));
        AnchorPane centerTablePane = null;
        centerTablePane = (AnchorPane) centerTableLoader.load();

        authorTableController = (AuthorTableController) centerTableLoader.getController();
        authorTableController.initialize();
        authorTableController.fillTable();
        authorTableController.setRoleManagerService(roleManagerService);

        openedTableName.setText(authorsBt.getText());
        tablePane.setCenter(centerTablePane);
        authorsBt.setDisable(true);
        authorsBt.setStyle(chosenTableButtonStyle);

        FXMLLoader filtersLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("filters/filters-author-view.fxml"));
        AnchorPane filterPane = (AnchorPane) filtersLoader.load();
        authorFiltersController = (AuthorFiltersController) filtersLoader.getController();
        filtersPane.setCenter(filterPane);
        setOnActionFilterTableRowsBtForAuthors();

        FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-author-view.fxml"));
        AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
        rowAuthorController = (RowAuthorController) rowInteractionLoader.getController();
        rowInteractionPane.setCenter(rowInteractionAnchorPane);

        addNewRowToTableBt.setDisable(true);
        saveRowChangesBt.setDisable(true);
        deleteChosenRowFromTableBt.setDisable(true);
        rowAuthorController.setFieldsEditable(false);
        setOnActionStopSelectingRowBtForAuthors();
        setOnActionAddNewRowToTableBtForAuthors();
        setOnChangedSelectedAuthor();

        setOnActionSaveRowChangesBtForAuthors();
        setOnDeleteChosenRowFromTableBtForAuthors();
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
    private void makeAllTableChoosingButtonsAbleToBeClicked() throws CommonFlkException {
        usersBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new DbUserTableController(), currentDbUser));
        authorsBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new AuthorTableController(), currentDbUser));
        requirementsBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new RequirementTableController(), currentDbUser));
        requirementTypesBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new RequirementTypeTableController(), currentDbUser));
        projectsBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new ProjectTableController(), currentDbUser));
        codeFilesBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new CodeFileTableController(), currentDbUser));
        deploymentsBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new DeploymentTableController(), currentDbUser));
        deploymentStatusesBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new DeploymentStatusTableController(), currentDbUser));
        projectAuthorsBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new ProjectAuthorTableController(), currentDbUser));
        requirementAuthorsBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new RequirementAuthorTableController(), currentDbUser));
        accessibleRolesBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new AccessedRoleTableController(), currentDbUser));
        secretsBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new SecretTableController(), currentDbUser));
        passGenRuleBt.setDisable(!roleManagerService.checkIfUserCanViewTableByItsEntityClass(new PasswordGenRuleTableController(), currentDbUser));
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
                dbUserTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-db-user-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowDbUserController = (RowDbUserController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                rowDbUserController.setFieldsEditable(false);

                setOnActionStopSelectingRowBtForDbUsers();
                setOnActionAddNewRowToTableBtForDbUsers();
                setOnChangedSelectedDbUser();
                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(true);
                deleteChosenRowFromTableBt.setDisable(true);

                setOnActionSaveRowChangesBtForDbUsers();
                setOnDeleteChosenRowFromTableBtForDbUsers();
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
                authorTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-author-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowAuthorController = (RowAuthorController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                rowAuthorController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(true);
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForAuthors();
                setOnActionAddNewRowToTableBtForAuthors();
                setOnChangedSelectedAuthor();

                setOnActionSaveRowChangesBtForAuthors();
                setOnDeleteChosenRowFromTableBtForAuthors();
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
                requirementTableController.setRoleManagerService(roleManagerService);

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
                requirementFiltersController.setRoleManagerService(roleManagerService);
                setOnActionFilterTableRowsBtForRequirements();

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-requirement-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowRequirementController = (RowRequirementController) rowInteractionLoader.getController();
                rowRequirementController.setRoleManagerService(roleManagerService);
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTableController, currentDbUser)) rowRequirementController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForRequirements();
                setOnActionAddNewRowToTableBtForRequirements();
                setOnChangedSelectedRequirement();

                setOnActionSaveRowChangesBtForRequirements();
                setOnDeleteChosenRowFromTableBtForRequirements();
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
                requirementTypeTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-requirement-type-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowRequirementTypeController = (RowRequirementTypeController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTypeTableController, currentDbUser)) rowRequirementTypeController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTypeTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForRequirementTypes();
                setOnActionAddNewRowToTableBtForRequirementTypes();
                setOnChangedSelectedRequirementType();

                setOnActionSaveRowChangesBtForRequirementTypes();
                setOnDeleteChosenRowFromTableBtForRequirementTypes();
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
                projectTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-project-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowProjectController = (RowProjectController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectTableController, currentDbUser)) rowProjectController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForProjects();
                setOnActionAddNewRowToTableBtForProjects();
                setOnChangedSelectedProject();

                setOnActionSaveRowChangesBtForProjects();
                setOnDeleteChosenRowFromTableBtForProjects();
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
                codeFileTableController.setRoleManagerService(roleManagerService);

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
                codeFileFiltersController.setRoleManagerService(roleManagerService);
                setOnActionFilterTableRowsBtForCodeFiles();

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-code-file-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowCodeFileController = (RowCodeFileController) rowInteractionLoader.getController();
                rowCodeFileController.setRoleManagerService(roleManagerService);
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(codeFileTableController, currentDbUser)) rowCodeFileController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(codeFileTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForCodeFiles();
                setOnActionAddNewRowToTableBtForCodeFiles();
                setOnChangedSelectedCodeFile();

                setOnActionSaveRowChangesBtForCodeFiles();
                setOnDeleteChosenRowFromTableBtForCodeFiles();
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
                deploymentTableController.setRoleManagerService(roleManagerService);

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
                deploymentFiltersController.setRoleManagerService(roleManagerService);
                setOnActionFilterTableRowsBtForDeployments();

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-deployment-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowDeploymentController = (RowDeploymentController) rowInteractionLoader.getController();
                rowDeploymentController.setRoleManagerService(roleManagerService);
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentTableController, currentDbUser)) rowDeploymentController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForDeployments();
                setOnActionAddNewRowToTableBtForDeployments();
                setOnChangedSelectedDeployment();

                setOnActionSaveRowChangesBtForDeployments();
                setOnDeleteChosenRowFromTableBtForDeployments();
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
                deploymentStatusTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-deployment-status-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowDeploymentStatusController = (RowDeploymentStatusController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentStatusTableController, currentDbUser)) rowDeploymentStatusController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentStatusTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForDeploymentStatuses();
                setOnActionAddNewRowToTableBtForDeploymentStatuses();
                setOnChangedSelectedDeploymentStatus();

                setOnActionSaveRowChangesBtForDeploymentStatuses();
                setOnDeleteChosenRowFromTableBtForDeploymentStatuses();
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
                projectAuthorTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-project-author-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowProjectAuthorController = (RowProjectAuthorController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectAuthorTableController, currentDbUser)) rowProjectAuthorController.setFieldsEditableWithStyleAndPrompt(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectAuthorTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForProjectAuthors();
                setOnActionAddNewRowToTableBtForProjectAuthors();
                setOnChangedSelectedProjectAuthor();

                setOnDeleteChosenRowFromTableBtForProjectAuthors();
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
                requirementAuthorTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-requirement-author-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowRequirementAuthorController = (RowRequirementAuthorController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementAuthorTableController, currentDbUser)) rowRequirementAuthorController.setFieldsEditableWithStyleAndPrompt(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementAuthorTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForRequirementAuthors();;
                setOnActionAddNewRowToTableBtForRequirementAuthors();
                setOnChangedSelectedRequirementAuthor();

                setOnDeleteChosenRowFromTableBtForRequirementAuthors();
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
                accessedRoleTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-accessed-role-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowAccessedRoleController = (RowAccessedRoleController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(accessedRoleTableController, currentDbUser)) rowAccessedRoleController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(accessedRoleTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForAccessedRoles();
                setOnActionAddNewRowToTableBtForAccessedRoles();
                setOnChangedSelectedAccessedRole();

                setOnActionSaveRowChangesBtForAccessedRoles();
                setOnDeleteChosenRowFromTableBtForAccessedRoles();
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
                secretTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-secret-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowSecretController = (RowSecretController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(secretTableController, currentDbUser)) rowSecretController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(secretTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForSecrets();
                setOnActionAddNewRowToTableBtForSecrets();
                setOnChangedSelectedSecret();

                setOnActionSaveRowChangesBtForSecrets();
                setOnDeleteChosenRowFromTableBtForSecrets();
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
                passwordGenRuleTableController.setRoleManagerService(roleManagerService);

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

                FXMLLoader rowInteractionLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("rowinteraction/row-password-gen-rule-view.fxml"));
                AnchorPane rowInteractionAnchorPane = (AnchorPane) rowInteractionLoader.load();
                rowPasswordGenRuleController = (RowPasswordGenRuleController) rowInteractionLoader.getController();
                rowInteractionPane.setCenter(rowInteractionAnchorPane);
                if (!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(passwordGenRuleTableController, currentDbUser)) rowPasswordGenRuleController.setFieldsEditable(false);

                saveRowChangesBt.setDisable(true);
                addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(passwordGenRuleTableController, currentDbUser));
                deleteChosenRowFromTableBt.setDisable(true);
                setOnActionStopSelectingRowBtForPasswordGenRules();
                setOnActionAddNewRowToTableBtForPasswordGenRules();
                setOnChangedSelectedPasswordGenRule();

                setOnActionSaveRowChangesBtForPasswordGenRules();
                setOnDeleteChosenRowFromTableBtForPasswordGenRules();
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

    private void setOnActionStopSelectingRowBtForAccessedRoles() {
        stopSelectingRowBt.setOnAction(event -> accessedRoleTableController.clearSelection());
    }

    private void setOnActionStopSelectingRowBtForAuthors() {
        stopSelectingRowBt.setOnAction(event -> authorTableController.clearSelection());
    }

    private void setOnActionStopSelectingRowBtForCodeFiles() {
        stopSelectingRowBt.setOnAction(event -> {
            codeFileTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForDbUsers() {
        stopSelectingRowBt.setOnAction(event -> {
            dbUserTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForDeploymentStatuses() {
        stopSelectingRowBt.setOnAction(event -> {
            deploymentStatusTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForDeployments() {
        stopSelectingRowBt.setOnAction(event -> {
            deploymentTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForPasswordGenRules() {
        stopSelectingRowBt.setOnAction(event -> {
            passwordGenRuleTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForProjectAuthors() {
        stopSelectingRowBt.setOnAction(event -> {
            projectAuthorTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForProjects() {
        stopSelectingRowBt.setOnAction(event -> {
            projectTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForRequirementAuthors() {
        stopSelectingRowBt.setOnAction(event -> {
            requirementAuthorTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForRequirements() {
        stopSelectingRowBt.setOnAction(event -> {
            requirementTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForRequirementTypes() {
        stopSelectingRowBt.setOnAction(event -> {
            requirementTypeTableController.clearSelection();
        });
    }

    private void setOnActionStopSelectingRowBtForSecrets() {
        stopSelectingRowBt.setOnAction(event -> {
            secretTableController.clearSelection();
        });
    }

    private void setOnActionAddNewRowToTableBtForAccessedRoles() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    AccessedRole accessedRole = rowAccessedRoleController.getRole();
                    if (accessedRole == null) return;
                    accessedRoleRepository.saveAccessedRole(accessedRole);
                    accessedRoleTableController.fillTable();
                    rowAccessedRoleController.clearFields();
                    if (requirementAuthorRepository.getRequirementAuthorByIds(accessedRole.getRequirementId(), currentDbUser.getAuthorId()) == null)
                        requirementAuthorRepository.saveRequirementAuthor(new RequirementAuthor(accessedRole.getRequirementId(), currentDbUser.getAuthorId()));
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Роль {%s, %s} успешно сохранена", accessedRole.getRequirementId(), accessedRole.getRoleName()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save accessed role: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForAuthors() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    Author author = rowAuthorController.getAuthor();
                    if (author == null) return;
                    UUID id = authorRepository.saveAuthor(author);
                    authorTableController.fillTable();
                    rowAuthorController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Автор успешно сохранен с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save author: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForCodeFiles() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    CodeFile codeFile = rowCodeFileController.getCodeFile();
                    if (codeFile == null) return;
                    if (codeFile.getLoadDate() == null) codeFile.setLoadDate(LocalDateTime.now());
                    UUID id = codeFileRepository.saveCodeFile(codeFile);
                    codeFileTableController.fillTable();
                    rowCodeFileController.clearFields();
                    if (projectAuthorRepository.getProjectAuthorByIds(codeFile.getProjectId(), currentDbUser.getAuthorId()) == null)
                        projectAuthorRepository.saveProjectAuthor(new ProjectAuthor(codeFile.getProjectId(), currentDbUser.getAuthorId()));
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Файл с кодом успешно сохранен с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save code file: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForDbUsers() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    DbUser dbUser = rowDbUserController.getDbUser();
                    if (dbUser == null) return;
                    Integer id = dbUserRepository.saveDbUser(dbUser);
                    dbUserTableController.fillTable();
                    rowDbUserController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Пользователь успешно сохранен с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save db user: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForDeploymentStatuses() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    DeploymentStatus deploymentStatus = rowDeploymentStatusController.getDeploymentStatus();
                    if (deploymentStatus == null) return;
                    UUID id = deploymentStatusRepository.saveDeploymentStatus(deploymentStatus);
                    deploymentStatusTableController.fillTable();
                    rowDeploymentStatusController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Статус развертывания успешно сохранен с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save deployment status: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForDeployments() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    Deployment deployment = rowDeploymentController.getDeployment();
                    if (deployment == null) return;
                    UUID id = deploymentRepository.saveDeployment(deployment);
                    deploymentTableController.fillTable();
                    rowDeploymentController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Развертывание успешно сохранено с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save deployment: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForPasswordGenRules() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    PasswordGenRule passwordGenRule = rowPasswordGenRuleController.getPasswordGenRule();
                    if (passwordGenRule == null) return;
                    if (passwordGenRule.getCreateDate() == null) passwordGenRule.setCreateDate(LocalDateTime.now());
                    Integer id = passwordGenRuleRepository.savePasswordGenRule(passwordGenRule);
                    passwordGenRuleTableController.fillTable();
                    rowPasswordGenRuleController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Правило генерации (заполнения) паролей успешно сохранено с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save password generation rule: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForProjectAuthors() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    ProjectAuthor projectAuthor = rowProjectAuthorController.getProjectAuthor();
                    if (projectAuthor == null) return;
                    projectAuthorRepository.saveProjectAuthor(projectAuthor);
                    projectAuthorTableController.fillTable();
                    rowProjectAuthorController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog("Автор проекта успешно сохранен");
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save project author: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForProjects() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    Project project = rowProjectController.getProject();
                    if (project == null) return;
                    if (project.getLoadDate() == null) project.setLoadDate(LocalDateTime.now());
                    if (project.getLastChangeDate() == null) project.setLastChangeDate(LocalDateTime.now());
                    UUID id = projectRepository.saveProject(project);
                    if (authorRepository.getAuthorById(currentDbUser.getAuthorId()) != null)
                        projectAuthorRepository.saveProjectAuthor(new ProjectAuthor(
                                id,
                                currentDbUser.getAuthorId()
                        ));
                    projectTableController.fillTable();
                    rowProjectController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Проект успешно сохранен с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save project: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForRequirementAuthors() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    RequirementAuthor requirementAuthor = rowRequirementAuthorController.getRequirementAuthor();
                    if (requirementAuthor == null) return;
                    requirementAuthorRepository.saveRequirementAuthor(requirementAuthor);
                    requirementAuthorTableController.fillTable();
                    rowRequirementAuthorController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog("Автор требования успешно сохранен");
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save requirement author: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForRequirements() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    Requirement requirement = rowRequirementController.getRequirement();
                    if (requirement == null) return;
                    if (requirement.getLoadDate() == null) requirement.setLoadDate(LocalDateTime.now());
                    if (requirement.getLastChangeDate() == null) requirement.setLastChangeDate(LocalDateTime.now());
                    UUID id = requirementRepository.saveRequirement(requirement);
                    if (authorRepository.getAuthorById(currentDbUser.getAuthorId()) != null)
                        requirementAuthorRepository.saveRequirementAuthor(new RequirementAuthor(
                                id,
                                currentDbUser.getAuthorId()
                        ));
                    requirementTableController.fillTable();
                    rowRequirementController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Требование успешно сохранено с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save requirement: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForRequirementTypes() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    RequirementType requirementType = rowRequirementTypeController.getRequirementType();
                    if (requirementType == null) return;
                    UUID id = requirementTypeRepository.saveRequirementType(requirementType);
                    requirementTypeTableController.fillTable();
                    rowRequirementTypeController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Тип требований успешно сохранен с id: %s", id));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save requirement type: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionAddNewRowToTableBtForSecrets() {
        addNewRowToTableBt.setOnAction(event -> {
            try {
                if (selectedRow == null) {
                    Secret secret = rowSecretController.getSecret();
                    if (secret == null) return;
                    secretRepository.saveSecret(secret);
                    secretTableController.fillTable();
                    rowSecretController.clearFields();
                    ControllerUtils.showSuccessfulEntitySaveDialog(String.format("Доступ к развертыванию успешно сохранен с id: %s", secret.getSecretId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для добавления новой записи выйдите из режима редактирования существующей (снимите выделение)"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to save secret: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnChangedSelectedAccessedRole() {
        accessedRoleTableController.getObservableSelectedAccessedRole().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                addNewRowToTableBt.setDisable(false);
                saveRowChangesBt.setDisable(true);
                deleteChosenRowFromTableBt.setDisable(true);
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(accessedRoleTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowAccessedRoleController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(accessedRoleTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(accessedRoleTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowAccessedRoleController.autoFillFields(newSelection);
            }
        });
    }

    private void setOnChangedSelectedAuthor() {
        authorTableController.getObservableSelectedAuthor().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                addNewRowToTableBt.setDisable(true);
                saveRowChangesBt.setDisable(true);
                deleteChosenRowFromTableBt.setDisable(true);
                rowAuthorController.clearFields();
            } else {
                selectedRow = newSelection;
                addNewRowToTableBt.setDisable(true);
                saveRowChangesBt.setDisable(true);
                deleteChosenRowFromTableBt.setDisable(true);
                rowAuthorController.autoFillFields(newSelection);
            }
        });
    }

    private void setOnChangedSelectedCodeFile() {
        codeFileTableController.getObservableSelectedCodeFile().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(codeFileTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowCodeFileController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(codeFileTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(codeFileTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                try {
                    rowCodeFileController.autoFillFields(newSelection);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setOnChangedSelectedDbUser() {
        dbUserTableController.getObservableSelectedDbUser().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                rowDbUserController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    rowDbUserController.autoFillFields(newSelection);
                } catch (CommonFlkException e) {
                    ControllerUtils.showCommonFlkExceptionAlert(e);
                }
            }
        });
    }

    private void setOnChangedSelectedDeploymentStatus() {
        deploymentStatusTableController.getObservableSelectedDeploymentStatus().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentStatusTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowDeploymentStatusController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentStatusTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentStatusTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowDeploymentStatusController.autoFillFields(newSelection);
            }
        });
    }

    private void setOnChangedSelectedDeployment() {
        deploymentTableController.getObservableSelectedDeployment().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowDeploymentController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(deploymentTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                try {
                    rowDeploymentController.autoFillFields(newSelection);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setOnChangedSelectedPasswordGenRule() {
        passwordGenRuleTableController.getObservableSelectedPasswordGenRule().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(passwordGenRuleTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowPasswordGenRuleController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(passwordGenRuleTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(passwordGenRuleTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowPasswordGenRuleController.autoFillFields(newSelection);
            }
        });
    }

    private void setOnChangedSelectedProjectAuthor() {
        projectAuthorTableController.getObservableSelectedProjectAuthor().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectAuthorTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowProjectAuthorController.clearFields();
                rowProjectAuthorController.setFieldsEditable(true);
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectAuthorTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowProjectAuthorController.autoFillFields(newSelection);
                rowProjectAuthorController.setFieldsEditable(false);
            }
        });
    }

    private void setOnChangedSelectedProject() {
        projectTableController.getObservableSelectedProject().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowProjectController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(projectTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowProjectController.autoFillFields(newSelection);
            }
        });
    }

    private void setOnChangedSelectedRequirementAuthor() {
        requirementAuthorTableController.getObservableSelectedRequirementAuthor().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementAuthorTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowRequirementAuthorController.clearFields();
                rowRequirementAuthorController.setFieldsEditable(true);
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementAuthorTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowRequirementAuthorController.autoFillFields(newSelection);
                rowRequirementAuthorController.setFieldsEditable(false);
            }
        });
    }

    private void setOnChangedSelectedRequirement() {
        requirementTableController.getObservableSelectedRequirement().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowRequirementController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                try {
                    rowRequirementController.autoFillFields(newSelection);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void setOnChangedSelectedRequirementType() {
        requirementTypeTableController.getObservableSelectedRequirementType().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTypeTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowRequirementTypeController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTypeTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(requirementTypeTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowRequirementTypeController.autoFillFields(newSelection);
            }
        });
    }

    private void setOnChangedSelectedSecret() {
        secretTableController.getObservableSelectedSecret().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null) {
                selectedRow = null;
                try {
                    addNewRowToTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(secretTableController, currentDbUser));
                    saveRowChangesBt.setDisable(true);
                    deleteChosenRowFromTableBt.setDisable(true);
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowSecretController.clearFields();
            } else {
                selectedRow = newSelection;
                try {
                    addNewRowToTableBt.setDisable(true);
                    saveRowChangesBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(secretTableController, currentDbUser));
                    deleteChosenRowFromTableBt.setDisable(!roleManagerService.checkIfUserCanEditTableDataByItsEntityClass(secretTableController, currentDbUser));
                } catch (CommonFlkException e) {
                    throw new RuntimeException(e);
                }
                rowSecretController.autoFillFields(newSelection);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForAccessedRoles() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    AccessedRole accessedRole = rowAccessedRoleController.getRole();
                    if (accessedRole == null) return;
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить роль {%s}?", accessedRole.toString())
                    );
                    if (isDeletionAssigned) {
                        accessedRoleRepository.deleteAccessedRoleByRequirementIdAndName(
                                accessedRole.getRequirementId(),
                                accessedRole.getRoleName()
                        );
                        accessedRoleTableController.fillTable();
                        rowAccessedRoleController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Роль {%s, %s} удалена успешно", accessedRole.getRequirementId(), accessedRole.getRoleName()));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete accessed role: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForAuthors() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID authorId = rowAuthorController.getAuthorId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить автора с id '%s'?", authorId.toString())
                    );
                    if (isDeletionAssigned) {
                        authorRepository.deleteAuthorById(
                                authorId
                        );
                        authorTableController.fillTable();
                        rowAuthorController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Автор с id '%s' удален успешно", authorId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete author: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForCodeFiles() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID codeFileId = rowCodeFileController.getCodeFileId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить файл с кодом с id '%s'?", codeFileId.toString())
                    );
                    if (isDeletionAssigned) {
                        codeFileRepository.deleteCodeFileById(
                                codeFileId
                        );
                        codeFileTableController.fillTable();
                        rowCodeFileController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Файл с кодом с id '%s' удален успешно", codeFileId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete code file: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForDbUsers() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    Integer dbUserId = rowDbUserController.getUserId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить пользователя с id '%s'?", dbUserId.toString())
                    );
                    if (isDeletionAssigned) {
                        dbUserRepository.deleteDbUserById(
                                dbUserId
                        );
                        dbUserTableController.fillTable();
                        rowDbUserController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Пользователь с id '%s' удален успешно", dbUserId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete db user: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForDeployments() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID deploymentId = rowDeploymentController.getDeploymentId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить развертывание с id '%s'?", deploymentId)
                    );
                    if (isDeletionAssigned) {
                        deploymentRepository.deleteDeploymentById(
                                deploymentId
                        );
                        deploymentTableController.fillTable();
                        rowDeploymentController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Развертывание с id '%s' удалено успешно", deploymentId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete deployment: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForDeploymentStatuses() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID deploymentStatusId = rowDeploymentStatusController.getDeploymentStatusId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить статус развертываний с id '%s'?", deploymentStatusId)
                    );
                    if (isDeletionAssigned) {
                        deploymentStatusRepository.deleteDeploymentStatusById(
                                deploymentStatusId
                        );
                        deploymentStatusTableController.fillTable();
                        rowDeploymentStatusController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Статус развертываний с id '%s' удален успешно", deploymentStatusId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete deployment status: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForPasswordGenRules() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    Integer passwordGenRuleId = rowPasswordGenRuleController.getPasswordGenRuleId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить правило генерации (заполнения) паролей с id '%s'?", passwordGenRuleId)
                    );
                    if (isDeletionAssigned) {
                        passwordGenRuleRepository.deletePasswordGenRuleById(
                                passwordGenRuleId
                        );
                        passwordGenRuleTableController.fillTable();
                        rowPasswordGenRuleController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Правило генерации (заполнения) паролей с id '%s' удалено успешно", passwordGenRuleId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete password generation rule: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForProjectAuthors() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    ProjectAuthor projectAuthor = rowProjectAuthorController.getProjectAuthor();
                    if (projectAuthor == null) return;
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить связь автора с проектом {%s}?", projectAuthor.toString())
                    );
                    if (isDeletionAssigned) {
                        projectAuthorRepository.deleteProjectAuthorByIds(
                                projectAuthor.getProjectId(),
                                projectAuthor.getAuthorId()
                        );
                        projectAuthorTableController.fillTable();
                        rowProjectAuthorController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Связь автора (id '%s') с проектом (id '%s') удалена успешно", projectAuthor.getAuthorId(), projectAuthor.getProjectId()));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete project author: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForProjects() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID projectId = rowProjectController.getProjectId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить проект с id '%s'?", projectId)
                    );
                    if (isDeletionAssigned) {
                        projectRepository.deleteProjectById(
                                projectId
                        );
                        projectTableController.fillTable();
                        rowProjectController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Проект с id '%s' удален успешно", projectId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete project: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForRequirementAuthors() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    RequirementAuthor requirementAuthor = rowRequirementAuthorController.getRequirementAuthor();
                    if (requirementAuthor == null) return;
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить связь автора с требованием {%s}?", requirementAuthor.toString())
                    );
                    if (isDeletionAssigned) {
                        requirementAuthorRepository.deleteRequirementAuthorByIds(
                                requirementAuthor.getRequirementId(),
                                requirementAuthor.getAuthorId()
                        );
                        projectAuthorTableController.fillTable();
                        rowRequirementAuthorController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Связь автора (id '%s') с требованием (id '%s') удалена успешно", requirementAuthor.getAuthorId(), requirementAuthor.getRequirementId()));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete requirement author: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForRequirements() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID requirementId = rowRequirementController.getRequirementId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить требование с id '%s'?", requirementId)
                    );
                    if (isDeletionAssigned) {
                        requirementRepository.deleteRequirementById(
                                requirementId
                        );
                        requirementTableController.fillTable();
                        rowRequirementController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Требование с id '%s' удалено успешно", requirementId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete requirement: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForRequirementTypes() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID requirementTypeId = rowRequirementTypeController.getRequirementTypeId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить тип требований с id '%s'?", requirementTypeId)
                    );
                    if (isDeletionAssigned) {
                        requirementTypeRepository.deleteRequirementTypeById(
                                requirementTypeId
                        );
                        requirementTypeTableController.fillTable();
                        rowRequirementTypeController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Тип требований с id '%s' удален успешно", requirementTypeId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete requirement type: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnDeleteChosenRowFromTableBtForSecrets() {
        deleteChosenRowFromTableBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    UUID secretId = rowSecretController.getSecretId();
                    Boolean isDeletionAssigned = ControllerUtils.showModalForAssigningOperation(
                            String.format("Вы точно хотите удалить доступ к развертыванию с id '%s'?", secretId)
                    );
                    if (isDeletionAssigned) {
                        secretRepository.deleteSecretById(
                                secretId
                        );
                        secretTableController.fillTable();
                        rowSecretController.clearFields();
                        ControllerUtils.showSuccessfulEntityDeletionDialog(String.format("Доступ к развертыванию с id '%s' удален успешно", secretId));
                    }
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для удаления записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to delete secret: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForAccessedRoles() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    AccessedRole accessedRole = rowAccessedRoleController.getRole();
                    if (accessedRole == null) return;
                    accessedRoleRepository.updateAccessedRoleNameByIdAndName(
                            accessedRole.getRequirementId(),
                            ((AccessedRole) selectedRow).getRoleName(),
                            accessedRole.getRoleName()
                    );
                    accessedRoleTableController.fillTable();
                    rowAccessedRoleController.clearFields();
                    if (requirementAuthorRepository.getRequirementAuthorByIds(accessedRole.getRequirementId(), currentDbUser.getAuthorId()) == null)
                        requirementAuthorRepository.saveRequirementAuthor(new RequirementAuthor(accessedRole.getRequirementId(), currentDbUser.getAuthorId()));
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Роль {%s, %s} изменена успешно", ((AccessedRole) selectedRow).getRequirementId(), ((AccessedRole) selectedRow).getRoleName()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update accessed role: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForAuthors() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    Author author = rowAuthorController.getAuthor();
                    if (author == null) return;
                    authorRepository.updateAuthorLoginById(
                            author.getAuthorId(),
                            author.getLogin()
                    );
                    authorTableController.fillTable();
                    rowAuthorController.clearFields();
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Автор с id '%s' изменен успешно", author.getAuthorId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update author: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForCodeFiles() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    CodeFile codeFile = rowCodeFileController.getCodeFile();
                    if (codeFile == null) return;
                    codeFileRepository.updateCodeFileContentById(
                            codeFile.getCodeFileId(),
                            codeFile.getFileContent()
                    );
                    codeFileTableController.fillTable();
                    rowCodeFileController.clearFields();
                    if (projectAuthorRepository.getProjectAuthorByIds(codeFile.getProjectId(), currentDbUser.getAuthorId()) == null)
                        projectAuthorRepository.saveProjectAuthor(new ProjectAuthor(codeFile.getProjectId(), currentDbUser.getAuthorId()));
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Файл с кодом с id '%s' изменен успешно", codeFile.getCodeFileId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update code file: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForDbUsers() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    DbUser dbUser = rowDbUserController.getDbUser();
                    if (dbUser == null) return;
                    dbUserRepository.updateDbUserPassById(
                            dbUser.getUserId(),
                            dbUser.getUserPass()
                    );
                    dbUserTableController.fillTable();
                    rowDbUserController.clearFields();
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Пользователь с id '%s' изменен успешно", dbUser.getUserId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update db user: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForDeployments() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    Deployment deployment = rowDeploymentController.getDeployment();
                    if (deployment == null) return;
                    deploymentRepository.updateDeploymentParamsById(
                            deployment.getDeploymentId(),
                            deployment.getDeploymentStatusId(),
                            deployment.getDeploymentPath(),
                            deployment.getSettings(),
                            deployment.getBuiltVersion(),
                            deployment.getBuiltSettings(),
                            deployment.getBuilt(),
                            deployment.getProjectId()
                    );
                    deploymentTableController.fillTable();
                    rowDeploymentController.clearFields();
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Развертывание с id '%s' изменено успешно", deployment.getDeploymentId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update deployment: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForDeploymentStatuses() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    DeploymentStatus deploymentStatus = rowDeploymentStatusController.getDeploymentStatus();
                    if (deploymentStatus == null) return;
                    deploymentStatusRepository.updateDeploymentStatusNameById(
                            deploymentStatus.getDeploymentStatusId(),
                            deploymentStatus.getStatusName()
                    );
                    deploymentStatusTableController.fillTable();
                    rowDeploymentStatusController.clearFields();
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Статус развертываний с id '%s' изменен успешно", deploymentStatus.getDeploymentStatusId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update deployment status: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForPasswordGenRules() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    PasswordGenRule passwordGenRule = rowPasswordGenRuleController.getPasswordGenRule();
                    if (passwordGenRule == null) return;
                    passwordGenRuleRepository.updatePasswordGenRule(
                            passwordGenRule
                    );
                    passwordGenRuleTableController.fillTable();
                    rowPasswordGenRuleController.clearFields();
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Правило генерации (заполнения) паролей с id '%s' изменено успешно", passwordGenRule.getRuleId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update password generation rule: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForProjects() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    Project project = rowProjectController.getProject();
                    if (project == null) return;
                    if (project.getLastChangeDate() == null) project.setLastChangeDate(LocalDateTime.now());
                    projectRepository.updateProject(
                            project
                    );
                    projectTableController.fillTable();
                    rowProjectController.clearFields();
                    if (projectAuthorRepository.getProjectAuthorByIds(project.getProjectId(), currentDbUser.getAuthorId()) == null)
                        projectAuthorRepository.saveProjectAuthor(new ProjectAuthor(project.getProjectId(), currentDbUser.getAuthorId()));
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Проект с id '%s' изменен успешно", project.getProjectId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update project: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForRequirements() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    Requirement requirement = rowRequirementController.getRequirement();
                    if (requirement == null) return;
                    if (requirement.getLastChangeDate() == null) requirement.setLastChangeDate(LocalDateTime.now());
                    requirementRepository.updateRequirement(
                            requirement
                    );
                    requirementTableController.fillTable();
                    rowRequirementController.clearFields();
                    if (requirementAuthorRepository.getRequirementAuthorByIds(requirement.getRequirementId(), currentDbUser.getAuthorId()) == null)
                        requirementAuthorRepository.saveRequirementAuthor(new RequirementAuthor(requirement.getRequirementId(), currentDbUser.getAuthorId()));
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Требование с id '%s' изменен успешно", requirement.getRequirementId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update requirement: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForRequirementTypes() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    RequirementType requirementType = rowRequirementTypeController.getRequirementType();
                    if (requirementType == null) return;
                    requirementTypeRepository.updateRequirementTypeNameById(
                            requirementType.getRequirementTypeId(),
                            requirementType.getRequirementTypeName()
                    );
                    requirementTypeTableController.fillTable();
                    rowRequirementTypeController.clearFields();
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Тип требований с id '%s' изменен успешно", requirementType.getRequirementTypeId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update requirement type: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }

    private void setOnActionSaveRowChangesBtForSecrets() {
        saveRowChangesBt.setOnAction(event -> {
            try {
                if (selectedRow != null) {
                    Secret secret = rowSecretController.getSecret();
                    if (secret == null) return;
                    secretRepository.updateSecret(
                            secret
                    );
                    secretTableController.fillTable();
                    rowSecretController.clearFields();
                    ControllerUtils.showSuccessfulEntityUpdatingDialog(String.format("Доступ (id '%s') к развертыванию изменен успешно", secret.getSecretId()));
                } else ControllerUtils.showCommonFlkExceptionAlert(new CommonUIException(
                        "1004001",
                        "Для изменения записи выберите соответствующую строку таблицы"
                ));
            } catch (CommonFlkException e) {
                log.debug("Failed to update secret: {}", e.getMessage());
                ControllerUtils.showCommonFlkExceptionAlert(e);
            }
        });
    }
}