package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DbUser;

import java.util.ArrayList;
import java.util.List;

//DbUser
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000015 implements CommonFlk {
    private final String entityName = "DbUser";
    private final String code = "10000015";
    private final String message = "При сохранении сущности DbUser обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("authorId", "userLogin", "userPass");
    private final boolean isCritical = true;

    private @NonNull DbUser dbUser;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for secret: {}", dbUser);

        List<String> tempAttributesNames = new ArrayList<>();
        if (dbUser.getAuthorId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("authorId")).toList());
        }
        if (dbUser.getUserLogin() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("userLogin")).toList());
        }
        if (dbUser.getUserPass() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("userPass")).toList());
        }

        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for secret: {} finished with error", dbUser);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for secret: {} finished successfully", dbUser);
    }
}
