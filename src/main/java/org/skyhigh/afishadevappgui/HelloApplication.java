package org.skyhigh.afishadevappgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.skyhigh.afishadevappgui.common.properties.ApplicationPropertiesReader;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepositoryImpl;
import org.skyhigh.afishadevappgui.service.logic.AuthenticationService;
import org.skyhigh.afishadevappgui.service.logic.AuthenticationServiceImpl;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        try {
            AuthenticationService service = new AuthenticationServiceImpl(
                    new DbUserRepositoryImpl(
                            ApplicationPropertiesReader.getApplicationProperties()
                    )
            );
            //DbUser user = service.register("tfff4fffff", "sdfsssssssss2s");
            //int a = 0;
            //DbUser au = service.authenticate("tfff4fffff", "sdfsssssssss2s");
            //int b = 0;
        } catch (CommonFlkException e) {
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