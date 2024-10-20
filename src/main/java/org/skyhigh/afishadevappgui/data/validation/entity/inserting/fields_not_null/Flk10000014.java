package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.util.ArrayList;
import java.util.List;

//PasswordGenRule
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000014 implements CommonFlk {
    private final String entityName = "PasswordGenRule";
    private final String code = "10000014";
    private final String message = "При сохранении сущности PasswordGenRule обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("repeatableCharactersAmount", "capitalLettersAmount", "specSymbolsAmount", "createDate", "numericSymbolsAmount");
    private final boolean isCritical = true;

    private @NonNull PasswordGenRule passwordGenRule;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for secret: {}", passwordGenRule);

        List<String> tempAttributesNames = new ArrayList<>();
        if (passwordGenRule.getRepeatableCharactersAmount() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("repeatableCharactersAmount")).toList());
        }
        if (passwordGenRule.getCapitalLettersAmount() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("capitalLettersAmount")).toList());
        }
        if (passwordGenRule.getSpecSymbolsAmount() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("specSymbolsAmount")).toList());
        }
        if (passwordGenRule.getCreateDate() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("createDate")).toList());
        }
        if (passwordGenRule.getNumericSymbolsAmount() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("numericSymbolsAmount")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for secret: {} finished with error", passwordGenRule);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for secret: {} finished successfully", passwordGenRule);
    }
}
