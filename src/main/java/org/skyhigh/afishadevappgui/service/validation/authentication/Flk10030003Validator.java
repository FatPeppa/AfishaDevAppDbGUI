package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10030003Validator {
    public static void validate(String login) throws CommonFlkException {
        Flk10030003 flk10030003 = new Flk10030003(login);
        flk10030003.validate();
    }
}
