package org.skyhigh.afishadevappgui.service.validation.authentication;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

public class Flk10030004Validator {
    public static void validate(String password) throws CommonFlkException {
        Flk10030004 flk10030004 = new Flk10030004(password);
        flk10030004.validate();
    }
}
