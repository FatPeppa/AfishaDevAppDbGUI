package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10030000Validator {
    public static void validate(String login, String password) throws CommonFlkException {
        Flk10030000 flk10030000 = new Flk10030000(login, password);
        flk10030000.validate();
    }
}
