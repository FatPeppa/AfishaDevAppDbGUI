package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;

import java.util.ArrayList;
import java.util.List;

//AccessedRole
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000002 implements CommonFlk {
    private final String entityName = "AccessedRole";
    private final String code = "10000002";
    private final String message = "При сохранении сущности AccessedRole обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("requirementId", "roleName");
    private final boolean isCritical = true;

    private @NonNull AccessedRole accessedRole;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for accessedRole: {}", accessedRole);

        List<String> tempAttributesNames = new ArrayList<>();
        if (accessedRole.getRoleName() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("roleName")).toList());
        }
        if (accessedRole.getRequirementId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("requirementId")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for accessedRole: {} finished with error", accessedRole);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }
        log.debug("Flk " + code + " for accessedRole: {} finished successfully", accessedRole);
    }
}
