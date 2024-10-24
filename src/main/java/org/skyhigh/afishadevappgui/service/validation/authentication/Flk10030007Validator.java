package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.repository.DbUserRepository;

public class Flk10030007Validator {
    public static void validate(String login, DbUserRepository dbUserRepository) throws CommonFlkException {
        Flk10030007 flk10030007 = new Flk10030007(login, dbUserRepository);
        flk10030007.validate();
    }
}
