package org.skyhigh.afishadevappgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.properties.enums.DbmsType;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.dao.DeploymentDAO;
import org.skyhigh.afishadevappgui.data.datasource.dao.DeploymentDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        ApplicationProperties pr = new ApplicationProperties(
                "jdbc:postgresql://localhost:6200/afisha_dev_app_db",
                "root",
                "123",
                DbmsType.POSTGRESQL,
                "org.postgresql.Driver",
                false
        );

        DbConnector dbConnector = new DbConnector(pr);

        try {
            DeploymentDAO dao = new DeploymentDAOImpl(dbConnector);
            /*dao.saveDeployment(
                    new Deployment(
                            null,
                            UUID.fromString("923b475d-4e83-40c8-95f2-58bbfadddfc3"),
                            null,
                            null,
                            null,
                            null,
                            new File("D:\\test_built_file.txt"),
                            UUID.fromString("ff16517f-8b07-4e78-8fa9-1f1c2992bb42")
                    )
            );*/
            dao.deployBuilt(
                UUID.fromString("5ce9b8c5-29c5-460c-9111-0649e0225b4f"),
                "test_addddd",
                "dfsa",
                null
            );
        } catch (SQLException | CommonFlkException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}