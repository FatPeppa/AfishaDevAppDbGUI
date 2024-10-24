package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10030005Validator {
    public static void validate(String password) throws CommonFlkException {
        Flk10030005 flk10030005 = new Flk10030005(password);
        flk10030005.validate();
    }
}
