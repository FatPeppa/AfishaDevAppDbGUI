package org.skyhigh.afishadevappgui.data.validation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;

import java.util.List;

/**
 * Проверка заполнения projectId при обновлении сущности проекта в методе updateProject(@NonNull Project project)
 */
@Setter
@Getter
@AllArgsConstructor
public class Flk10000000 implements CommonFlk {
    private static final String entityName = "Project";
    private static final String code = "10000000";
    private static final String message = "При обновлении сущности Project поле projectId должно быть заполнено";
    private static final List<String> attributesNames = List.of("projectId");
    private static final boolean isCritical = true;

    private Project project;

    @Override
    public void validate() throws CommonFlkException {
        if (project.getProjectId() == null)
            throw new CommonEntityException(
                    entityName,
                    code,
                    message,
                    attributesNames,
                    isCritical
            );
    }
}
