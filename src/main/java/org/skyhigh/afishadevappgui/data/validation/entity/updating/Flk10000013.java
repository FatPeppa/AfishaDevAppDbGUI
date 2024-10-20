package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.util.List;

/**
 * Проверка заполнения ruleId при обновлении сущности правила генерации паролей в методе updatePasswordGenRule(@NonNull PasswordGenRule passwordGenRule)
 */
@Setter
@Getter
@AllArgsConstructor
public class Flk10000013 implements CommonFlk {
    private final String entityName = "PasswordGenRule";
    private final String code = "10000013";
    private final String message = "При обновлении сущности PasswordGenRule поле ruleId должно быть заполнено";
    private final List<String> attributesNames = List.of("ruleId");
    private final boolean isCritical = true;

    private PasswordGenRule passwordGenRule;

    @Override
    public void validate() throws CommonFlkException {
        if (passwordGenRule.getRuleId() == null)
            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
    }
}
