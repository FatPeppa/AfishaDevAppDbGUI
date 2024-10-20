package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

public class Flk10000013Validator {
    public static void validate(PasswordGenRule passwordGenRule) throws CommonFlkException {
        Flk10000013 flk10000013 = new Flk10000013(passwordGenRule);
        flk10000013.validate();
    }
}
