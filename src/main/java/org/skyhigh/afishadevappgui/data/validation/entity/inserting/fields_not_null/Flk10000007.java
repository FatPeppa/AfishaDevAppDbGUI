package org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.util.ArrayList;
import java.util.List;

//Project
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class Flk10000007 implements CommonFlk {
    private final String entityName = "Project";
    private final String code = "10000007";
    private final String message = "При сохранении сущности Project обязательные поля должны быть заполнены";
    private final List<String> attributesNames = List.of("projectName", "loadDate", "lastChangeDate", "structure", "content", "settings", "versionNumber");
    private final boolean isCritical = true;

    private @NonNull Project project;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for project: {}", project);

        List<String> tempAttributesNames = new ArrayList<>();
        if (project.getProjectName() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("projectName")).toList());
        }
        if (project.getLoadDate() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("loadDate")).toList());
        }
        if (project.getLastChangeDate() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("lastChangeDate")).toList());
        }
        if (project.getStructure() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("structure")).toList());
        }
        if (project.getContent() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("content")).toList());
        }
        if (project.getSettings() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("settings")).toList());
        }
        if (project.getVersionNumber() == null) {
            tempAttributesNames.addAll(attributesNames.stream().filter(x -> !x.equals("versionNumber")).toList());
        }
        if (!tempAttributesNames.isEmpty()) {
            log.debug("Flk " + code + " for project: {} finished with error", project);

            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    tempAttributesNames,
                    isCritical
            );
        }

        log.debug("Flk " + code + " for project: {} finished successfully", project);
    }
}
