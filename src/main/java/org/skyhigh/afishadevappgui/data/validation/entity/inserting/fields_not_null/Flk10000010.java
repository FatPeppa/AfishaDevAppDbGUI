package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.util.ArrayList;
import java.util.List;

//RequirementAuthor
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000010 implements CommonFlk {
    private final String entityName = "RequirementAuthor";
    private final String code = "10000010";
    private final String message = "При сохранении сущности RequirementAuthor обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("requirementId", "authorId");
    private final boolean isCritical = true;

    private @NonNull RequirementAuthor requirementAuthor;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for requirementAuthor: {}", requirementAuthor);

        List<String> tempAttributesNames = new ArrayList<>();
        if (requirementAuthor.getRequirementId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("requirementId")).toList());
        }
        if (requirementAuthor.getAuthorId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("authorId")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for requirementAuthor: {} finished with error", requirementAuthor);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for requirementAuthor: {} finished successfully", requirementAuthor);
    }
}
