package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.RequirementTypeDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RequirementTypeRepositoryImpl implements RequirementTypeRepository {
    private final RequirementTypeDAO requirementTypeDAO;

    public RequirementTypeRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            requirementTypeDAO = (new AfishaDevAppDb(applicationProperties)).getRequirementTypeDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveRequirementType(@NonNull RequirementType requirementType) throws CommonFlkException {
        try {
            return requirementTypeDAO.saveRequirementType(requirementType);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public RequirementType getRequirementTypeById(@NonNull UUID requirementTypeId) throws CommonFlkException {
        try {
            return requirementTypeDAO.getRequirementTypeById(requirementTypeId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public RequirementType getRequirementTypeByName(@NonNull String requirementTypeName) throws CommonFlkException {
        try {
            return requirementTypeDAO.getRequirementTypeByName(requirementTypeName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<RequirementType> getAllRequirementTypes(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementTypeDAO.getAllRequirementTypes(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateRequirementTypeNameById(@NonNull UUID requirementTypeId, @NonNull String newRequirementTypeName) throws CommonFlkException {
        try {
            requirementTypeDAO.updateRequirementTypeNameById(requirementTypeId, newRequirementTypeName);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteRequirementTypeById(@NonNull UUID requirementTypeId) throws CommonFlkException {
        try {
            requirementTypeDAO.deleteRequirementTypeById(requirementTypeId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
