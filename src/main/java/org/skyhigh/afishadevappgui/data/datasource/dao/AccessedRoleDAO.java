package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * DAO для работы с сущностью AccessedRole
 */
public interface AccessedRoleDAO {
    /**
     * Метод создания имеющей доступ к требованию роли
     * @param accessedRole Объект роли с заполненными requirementId и roleName
     * @throws SQLException Ошибка при работе с БД
     */
    void saveAccessedRole(@NonNull AccessedRole accessedRole) throws SQLException;

    /**
     * Метод обновления наименования роли по идентификатору требования и старому имени роли
     * @param requirementId Идентификатор требования, доступ к которому имеет данная роль
     * @param oldAccessedRoleName Старое наименование роли
     * @param newAccessedRoleName Новое наименование роли
     * @throws SQLException Ошибка при работе с БД
     */
    void updateAccessedRoleNameByIdAndName(
            @NonNull UUID requirementId,
            @NonNull String oldAccessedRoleName,
            @NonNull String newAccessedRoleName
    ) throws SQLException;

    /**
     * Метод получения ролей, имеющих доступ к требованию, по идентификатору этого требования
     * @param requirementId Идентификатор требования, доступ к которому имеют искомые роли
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список ролей типа AccessedRole, имеющих доступ к указанному требованию
     * @throws SQLException Ошибка при работе с БД
     */
    List<AccessedRole> getAccessedRolesByRequirementId(@NonNull UUID requirementId,
                                                       @NonNull SortDirection sortDirection,
                                                       String sortBy
    ) throws SQLException, CommonFlkException;

    /**
     * Метод получения всех имеющих доступ к требованиям ролей, которые хранятся в системе
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список ролей типа AccessedRole
     * @throws SQLException Ошибка при работе с БД
     */
    List<AccessedRole> getAllAccessedRoles(@NonNull SortDirection sortDirection, String sortBy) throws SQLException;

    /**
     * Метод получения ролей, имеющих доступ к требованиям, по наименованию этих ролей
     * @param accessedRoleName Наименование искомых ролей
     * @param sortDirection Режим сортировки
     * @param sortBy Наименование поля, по которому осуществляется сортировка. Если сортировка не осуществляется, поле игнорируется. В ином случае должно быть не null
     * @return Список ролей типа AccessedRole, имеющих имя, соответствующее указанному в параметре поиска
     * @throws SQLException Ошибка при работе с БД
     */
    List<AccessedRole> getAccessedRolesByRoleName(@NonNull String accessedRoleName,
                                                  @NonNull SortDirection sortDirection,
                                                  String sortBy
    ) throws SQLException;

    /**
     * Метод получения роли, имеющей доступ к требованию, по идентификатору требования и наименованию роли
     * @param requirementId Идентификатор требования, к которому имеет доступ искомая роль
     * @param accessedRoleName Наименование искомой роли
     * @return Объект роли AccessedRole, удовлетворяющий критериям поиска
     * @throws SQLException Ошибка при работе с БД
     */
    AccessedRole getAccessedRoleByRequirementIdAndName(@NonNull UUID requirementId, @NonNull String accessedRoleName) throws SQLException;

    /**
     * Метод удаления роли, имеющей доступ к требованию, по идентификатору требования и наименованию роли
     * @param requirementId Идентификатор требования, к которому имеет доступ удаляемая роль
     * @param accessedRoleName Наименование удаляемой роли
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteAccessedRoleByRequirementIdAndName(@NonNull UUID requirementId, @NonNull String accessedRoleName) throws SQLException;

    /**
     * Метод удаления всех ролей, имеющей доступ к требованию, по идентификатору требования
     * @param requirementId Идентификатор требования, к которому имеют доступ удаляемые роли
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteAllAccessedRolesByRequirementId(@NonNull UUID requirementId) throws SQLException;
}
