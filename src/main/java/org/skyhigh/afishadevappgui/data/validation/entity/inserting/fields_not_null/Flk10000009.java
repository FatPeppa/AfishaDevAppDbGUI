package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

import java.util.ArrayList;
import java.util.List;

//Requirement
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000009 implements CommonFlk {
    private final String entityName = "Requirement";
    private final String code = "10000009";
    private final String message = "При сохранении сущности Requirement обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("requirementType", "loadDate", "lastChangeDate", "content");
    private final boolean isCritical = true;

    private @NonNull Requirement requirement;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for requirement: {}", requirement);

        List<String> tempAttributesNames = new ArrayList<>();
        if (requirement.getRequirementTypeId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("requirementType")).toList());
        }
        if (requirement.getLoadDate() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("loadDate")).toList());
        }
        if (requirement.getLastChangeDate() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("lastChangeDate")).toList());
        }
        if (requirement.getContent() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("content")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for requirement: {} finished with error", requirement);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for requirement: {} finished successfully", requirement);
    }
}
