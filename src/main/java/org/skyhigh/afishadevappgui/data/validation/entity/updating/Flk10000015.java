package org.skyhigh.afishadevappgui.data.validation.entity.updating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Secret;

import java.util.List;

/**
 * Проверка заполнения secretId при обновлении сущности доступа к развертыванию в методе updateSecret(@NonNull Secret secret)
 */
@Setter
@Getter
@AllArgsConstructor
public class Flk10000015 implements CommonFlk {
    private final String entityName = "Secret";
    private final String code = "10000015";
    private final String message = "При обновлении сущности Secret поле secretId должно быть заполнено";
    private final List<String> attributesNames = List.of("secretId");
    private final boolean isCritical = true;

    private Secret secret;

    @Override
    public void validate() throws CommonFlkException {
        if (secret.getSecretId() == null)
            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
    }
}
