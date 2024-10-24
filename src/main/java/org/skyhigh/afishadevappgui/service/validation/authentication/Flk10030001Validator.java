package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10030001Validator {
    public static void validate(String login) throws CommonFlkException {
        Flk10030001 flk10030001 = new Flk10030001(login);
        flk10030001.validate();
    }
}
