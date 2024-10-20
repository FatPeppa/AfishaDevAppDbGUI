package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.ProjectAuthor;

import java.util.ArrayList;
import java.util.List;

//ProjectAuthor
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000008 implements CommonFlk {
    private final String entityName = "ProjectAuthor";
    private final String code = "10000008";
    private final String message = "При сохранении сущности ProjectAuthor обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("projectId", "authorId");
    private final boolean isCritical = true;

    private @NonNull ProjectAuthor projectAuthor;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for projectAuthor: {}", projectAuthor);

        List<String> tempAttributesNames = new ArrayList<>();
        if (projectAuthor.getProjectId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("projectId")).toList());
        }
        if (projectAuthor.getAuthorId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("authorId")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for projectAuthor: {} finished with error", projectAuthor);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for projectAuthor: {} finished successfully", projectAuthor);
    }
}
