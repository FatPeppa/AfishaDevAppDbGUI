package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;

import java.util.ArrayList;
import java.util.List;

//Author
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000003 implements CommonFlk {
    private final String entityName = "Author";
    private final String code = "10000003";
    private final String message = "При сохранении сущности Author обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("login");
    private final boolean isCritical = true;

    private @NonNull Author author;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for author: {}", author);

        List<String> tempAttributesNames = new ArrayList<>();
        if (author.getLogin() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("login")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for author: {} finished with error", author);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for author: {} finished successfully", author);
    }
}
