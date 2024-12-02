package org.skyhigh.afishadevappgui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.controller.ControllerUtils;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.controller.FlkExceptionMessageController;
import org.skyhigh.afishadevappgui.common.validation.CommonUIException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class AfishaDevGUIApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("hello-view.fxml")); //main-view.fxml
        Scene scene = new Scene(fxmlLoader.load());

        Thread.setDefaultUncaughtExceptionHandler(AfishaDevGUIApplication::showError);

        stage.setTitle("AfishaDevAppGUI");
        stage.setScene(scene);
        stage.show();
    }

    private static void showError(Thread t, Throwable e) {
        log.debug("Default exception handler initialized with exception: ", e);
        if (Platform.isFxApplicationThread()) {
            showErrorDialog(e);
        } else {
            log.debug("An unexpected error occurred in {}", t);
        }
    }

    private static void showErrorDialog(Throwable e) {
        StringWriter errorMsg = new StringWriter();
        if (e.getCause() != null && e.getCause().getCause() != null && e.getCause().getCause() instanceof CommonFlkException) {
            if (e.getCause().getCause() instanceof CommonUIException) {
                ControllerUtils.showCommonFlkExceptionAlert((CommonFlkException) e.getCause().getCause());
                return;
            }
            errorMsg.write(e.getCause().getCause().getMessage());
        } else {
            e.printStackTrace(new PrintWriter(errorMsg));
        }
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(AfishaDevGUIApplication.class.getResource("baseviews/exception-flk-message-view.fxml"));
        try {
            Parent root = loader.load();
            ((FlkExceptionMessageController) loader.getController()).setErrorText(errorMsg.toString());
            dialog.setScene(new Scene(root, 250, 400));
            dialog.show();
        } catch (IOException exc) {
            log.debug("Error dialog did not opened for unknown reason", exc);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}