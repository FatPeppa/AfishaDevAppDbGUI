package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10030006Validator {
    public static void validate(String password) throws CommonFlkException {
        Flk10030006 flk10030006 = new Flk10030006(password);
        flk10030006.validate();
    }
}
