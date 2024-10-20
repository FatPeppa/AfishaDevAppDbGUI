package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10020001 implements CommonFlk {
    private final String entityName = "Deployment";
    private final String code = "10020001";
    private final String message = "При обновлении сборки в сущности Deployment поле built должно содержать существующий файл или быть null";
    private final List<String> attributesNames = List.of("built");
    private final boolean isCritical = true;

    private File deploymentBuilt;
    private @NonNull UUID deploymentId;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for deployment with id: {}", deploymentId);

        if (deploymentBuilt != null
                && (!deploymentBuilt.exists()
                || !deploymentBuilt.isFile()
                || !deploymentBuilt.canRead())
        ) {
            log.debug("Flk " + code + " for deployment with id: {} finished with error", deploymentId);
            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for deployment with id: {} finished successfully", deploymentId);
    }
}
