package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

import java.util.List;

/**
 * Проверка заполнения requirementId при обновлении сущности проекта в методе updateRequirement(@NonNull Requirement requirement)
 */
@Setter
@Getter
@AllArgsConstructor
public class Flk10000001 implements CommonFlk {
    private final String entityName = "Requirement";
    private final String code = "10000001";
    private final String message = "При обновлении сущности Requirement поле requirementId должно быть заполнено";
    private final List<String> attributesNames = List.of("requirementId");
    private final boolean isCritical = true;

    private Requirement requirement;

    @Override
    public void validate() throws CommonFlkException {
        if (requirement.getRequirementId() == null)
            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
    }
}
