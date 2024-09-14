package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

import java.sql.SQLException;
import java.util.UUID;

/**
 * DAO для работы с сущностью RequirementType
 */
public interface RequirementTypeDAO {
    /**
     *
     * @param requirementType
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    UUID saveRequirementType(@NonNull RequirementType requirementType) throws SQLException;

    /**
     *
     * @param requirementTypeId
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    DeploymentStatus getRequirementTypeById(@NonNull UUID requirementTypeId) throws SQLException;

    /**
     *
     * @param requirementTypeName
     * @return
     * @throws SQLException Ошибка при работе с БД
     */
    DeploymentStatus getRequirementTypeByName(@NonNull String requirementTypeName) throws SQLException;

    /**
     *
     * @param requirementTypeId
     * @param newRequirementTypeName
     * @throws SQLException Ошибка при работе с БД
     */
    void updateRequirementTypeNameById(@NonNull UUID requirementTypeId, @NonNull String newRequirementTypeName) throws SQLException;

    /**
     *
     * @param requirementTypeId
     * @throws SQLException Ошибка при работе с БД
     */
    void deleteRequirementTypeById(@NonNull UUID requirementTypeId) throws SQLException;
}
