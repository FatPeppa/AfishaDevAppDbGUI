package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface RequirementTypeSearchService {
    /**
     * Метод поиска типов требований. Допустимые комбинации: requirementTypeId / requirementTypeName / all null
     * @param requirementTypeId ID типа требования
     * @param requirementTypeName Наименование типа требования
     * @return Список искомых записей
     * @throws CommonFlkException Ошибка при выполнении проверок ФЛК
     */
    List<RequirementType> searchRequirementType(UUID requirementTypeId, String requirementTypeName) throws CommonFlkException;
}
