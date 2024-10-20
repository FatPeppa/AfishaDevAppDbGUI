package org.skyhigh.afishadevappgui.data.validation.entity.inserting.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;

import java.util.List;

//Проверка, что файл сборки по искомому пути существует
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10020000 implements CommonFlk {
    private final String entityName = "Deployment";
    private final String code = "10020000";
    private final String message = "При сохранении сборки в сущности Deployment поле built должно содержать существующий файл или быть null";
    private final List<String> attributesNames = List.of("built");
    private final boolean isCritical = true;

    private @NonNull Deployment deployment;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for deployment: {}", deployment);

        if (deployment.getBuilt() != null
                && (!deployment.getBuilt().exists()
                || !deployment.getBuilt().isFile()
                || !deployment.getBuilt().canRead())
        ) {
            log.debug("Flk " + code + " for deployment: {} finished with error", deployment);
            throw new CommonEntityException(
                entityName,
                code,
                message,
                attributesNames,
                isCritical
            );
        }

        log.debug("Flk " + code + " for deployment: {} finished successfully", deployment);
    }
}
