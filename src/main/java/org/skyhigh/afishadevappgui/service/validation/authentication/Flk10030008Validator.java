package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

public class Flk10030008Validator {
    public static void validate(String login, String password, DbUser foundDbUser) throws CommonFlkException {
        Flk10030008 flk10030008 = new Flk10030008(login, password, foundDbUser);
        flk10030008.validate();
    }
}
