package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;

import java.util.ArrayList;
import java.util.List;

//CodeFile
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000004 implements CommonFlk {
    private final String entityName = "CodeFile";
    private final String code = "10000004";
    private final String message = "При сохранении сущности CodeFile обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("projectId", "fileContent");
    private final boolean isCritical = true;

    private @NonNull CodeFile codeFile;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for codeFile: {}", codeFile);

        List<String> tempAttributesNames = new ArrayList<>();
        if (codeFile.getProjectId() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("projectId")).toList());
        }
        if (codeFile.getFileContent() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("fileContent")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for codeFile: {} finished with error", codeFile);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for codeFile: {} finished successfully", codeFile);
    }
}
