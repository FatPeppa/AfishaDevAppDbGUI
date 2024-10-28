package org.skyhigh.afishadevappgui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.skyhigh.afishadevappgui.AfishaDevGUIApplication;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.controller.tables.AccessedRoleTableController;
import org.skyhigh.afishadevappgui.controller.tables.DbUserTableController;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.io.IOException;

public class MainController {
    @FXML private Label currentUserLogin;
    @FXML private Label openedTableName;
    @FXML private Pane filtersPane;
    @FXML private BorderPane tablePane;

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

    @FXML
    private AccessedRoleTableController accessedRoleTableController;

    @FXML
    private DbUserTableController dbUserTableController;

    private DbUser currentDbUser;

    public void setCurrentDbUser(DbUser currentDbUser) {
        this.currentDbUser = currentDbUser;
        this.currentUserLogin.setText(currentDbUser.getUserLogin());
    }

    public void setDefaultTableViewAndFilters() throws IOException, CommonFlkException {
        FXMLLoader centerTableLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("tables/db-user-table-view.fxml"));
        AnchorPane centerTablePane = (AnchorPane) centerTableLoader.load();
        dbUserTableController = (DbUserTableController) centerTableLoader.getController();
        dbUserTableController.fillTable();
        openedTableName.setText("Пользователи");
        tablePane.setCenter(centerTablePane);
        usersBt.setStyle("-fx-background-color: #0031b4; -fx-text-fill: white;");
        //usersBt.setStyle(null);
    }
}
