package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;

import java.util.ArrayList;
import java.util.List;

//DeploymentStatus
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000006 implements CommonFlk {
    private final String entityName = "DeploymentStatus";
    private final String code = "10000006";
    private final String message = "При сохранении сущности DeploymentStatus обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("statusName");
    private final boolean isCritical = true;

    private @NonNull DeploymentStatus deploymentStatus;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for deploymentStatus: {}", deploymentStatus);

        List<String> tempAttributesNames = new ArrayList<>();
        if (deploymentStatus.getStatusName() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("statusName")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for deploymentStatus: {} finished with error", deploymentStatus);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for deploymentStatus: {} finished successfully", deploymentStatus);
    }
}
