package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

public class Flk10000014Validator {
    public static void validate(PasswordGenRule passwordGenRule) throws CommonFlkException {
        Flk10000014 flk10000014 = new Flk10000014(passwordGenRule);
        flk10000014.validate();
    }
}
