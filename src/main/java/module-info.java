module org.skyhigh.afishadevappgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires org.json;
    requires org.slf4j;

    opens org.skyhigh.afishadevappgui to javafx.fxml;
    exports org.skyhigh.afishadevappgui;
}