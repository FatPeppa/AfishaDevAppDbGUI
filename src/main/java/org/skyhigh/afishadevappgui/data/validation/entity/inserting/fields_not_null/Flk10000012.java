package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.util.ArrayList;
import java.util.List;

//Secret
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000012 implements CommonFlk {
    private final String entityName = "Secret";
    private final String code = "10000012";
    private final String message = "При сохранении сущности Secret обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("deploymentId", "address", "login");
    private final boolean isCritical = true;

    private @NonNull Secret secret;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for secret: {}", secret);

        List<String> tempAttributesNames = new ArrayList<>();
        if (secret.getDeploymentId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("deploymentId")).toList());
        }
        if (secret.getAddress() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("address")).toList());
        }
        if (secret.getLogin() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("login")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for secret: {} finished with error", secret);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for secret: {} finished successfully", secret);
    }
}
