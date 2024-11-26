module org.skyhigh.afishadevappgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires org.json;
    requires org.slf4j;
    requires bcrypt;
    requires java.desktop;
    requires jdk.compiler;

    opens org.skyhigh.afishadevappgui to javafx.fxml;
    exports org.skyhigh.afishadevappgui;
    exports org.skyhigh.afishadevappgui.controller;
    exports org.skyhigh.afishadevappgui.controller.tables;
    exports org.skyhigh.afishadevappgui.controller.filters;
    exports org.skyhigh.afishadevappgui.controller.rowinteraction;
    exports org.skyhigh.afishadevappgui.common.validation;
    exports org.skyhigh.afishadevappgui.common.service;
    exports org.skyhigh.afishadevappgui.common.controller;
    exports org.skyhigh.afishadevappgui.data.datasource.entity;
    exports org.skyhigh.afishadevappgui.service.logic.authentication;
    exports org.skyhigh.afishadevappgui.service.logic.search;
    exports org.skyhigh.afishadevappgui.service.logic.role;
    opens org.skyhigh.afishadevappgui.controller to javafx.fxml;
    opens org.skyhigh.afishadevappgui.common.service to javafx.fxml;
    opens org.skyhigh.afishadevappgui.common.validation to javafx.fxml;
    opens org.skyhigh.afishadevappgui.controller.rowinteraction to javafx.fxml;
    opens org.skyhigh.afishadevappgui.controller.tables to javafx.fxml;
    opens org.skyhigh.afishadevappgui.controller.filters to javafx.fxml;
    opens org.skyhigh.afishadevappgui.common.controller to javafx.fxml;
    opens org.skyhigh.afishadevappgui.data.datasource.entity to javafx.base;
    opens org.skyhigh.afishadevappgui.service.logic.authentication to javafx.base;
    opens org.skyhigh.afishadevappgui.service.logic.search to javafx.base;
    opens org.skyhigh.afishadevappgui.service.logic.role to javafx.base;

}