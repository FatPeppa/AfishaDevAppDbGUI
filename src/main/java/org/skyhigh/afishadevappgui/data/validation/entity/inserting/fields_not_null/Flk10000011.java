package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

import java.util.ArrayList;
import java.util.List;

//RequirementType
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000011 implements CommonFlk {
    private final String entityName = "RequirementType";
    private final String code = "10000011";
    private final String message = "При сохранении сущности RequirementType обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("requirementTypeName");
    private final boolean isCritical = true;

    private @NonNull RequirementType requirementType;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for requirementType: {}", requirementType);

        List<String> tempAttributesNames = new ArrayList<>();
        if (requirementType.getRequirementTypeName() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("requirementTypeName")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for requirementType: {} finished with error", requirementType);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for requirementType: {} finished successfully", requirementType);
    }
}
