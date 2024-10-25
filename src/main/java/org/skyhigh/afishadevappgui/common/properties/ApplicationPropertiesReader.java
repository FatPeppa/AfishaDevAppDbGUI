package org.skyhigh.afishadevappgui.common.properties;

import lombok.Getter;
import org.skyhigh.afishadevappgui.common.properties.enums.DbmsType;

public class ApplicationPropertiesReader {
    @Getter
    private static final ApplicationProperties applicationProperties = new ApplicationProperties(
            "jdbc:postgresql://localhost:6200/afisha_dev_app_db",
            "root",
            "123",
            DbmsType.POSTGRESQL,
            "org.postgresql.Driver",
            false
    );
}
