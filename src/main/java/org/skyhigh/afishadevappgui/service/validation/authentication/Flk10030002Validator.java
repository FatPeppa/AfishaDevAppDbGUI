package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10030002Validator {
    public static void validate(String login) throws CommonFlkException {
        Flk10030002 flk10030002 = new Flk10030002(login);
        flk10030002.validate();
    }
}
