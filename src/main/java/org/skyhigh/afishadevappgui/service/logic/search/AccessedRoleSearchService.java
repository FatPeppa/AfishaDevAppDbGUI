package org.skyhigh.afishadevappgui.service.logic.search;

import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;

import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface AccessedRoleSearchService {
    /**
     * Метод поиска ролей к требованию. Допустимые комбинации: ВСЕ
     * @param requirementId Идентификатор требования
     * @param roleName Наименование роли
     * @return Список искомых значений
     * @throws CommonFlkException Ошибка при проверках ФЛК
     */
    List<AccessedRole> searchAccessedRoles(UUID requirementId, String roleName) throws CommonFlkException;
}
