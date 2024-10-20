package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.util.ArrayList;
import java.util.List;

//Deployment
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000005 implements CommonFlk {
    private final String entityName = "Deployment";
    private final String code = "10000005";
    private final String message = "При сохранении сущности Deployment обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("deploymentStatusId", "projectId");
    private final boolean isCritical = true;

    private @NonNull Deployment deployment;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for deployment: {}", deployment);

        List<String> tempAttributesNames = new ArrayList<>();
        if (deployment.getDeploymentStatusId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("deploymentStatusId")).toList());
        }
        if (deployment.getProjectId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("projectId")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for deployment: {} finished with error", deployment);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for deployment: {} finished successfully", deployment);
    }
}
