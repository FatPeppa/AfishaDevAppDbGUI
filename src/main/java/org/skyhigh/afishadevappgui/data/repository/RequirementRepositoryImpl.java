package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.RequirementDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RequirementRepositoryImpl implements RequirementRepository {
    private final RequirementDAO requirementDAO;

    public RequirementRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            requirementDAO = (new AfishaDevAppDb(applicationProperties)).getRequirementDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID saveRequirement(@NonNull Requirement requirement) throws CommonFlkException {
        try {
            return requirementDAO.saveRequirement(requirement);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateRequirement(@NonNull Requirement requirement) throws CommonFlkException {
        try {
            requirementDAO.updateRequirement(requirement);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateRequirementTypeIdById(@NonNull UUID requirementId, @NonNull UUID requirementTypeId) throws CommonFlkException {
        try {
            requirementDAO.updateRequirementTypeIdById(requirementId, requirementTypeId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateLastChangeDateById(@NonNull UUID requirementId, @NonNull LocalDateTime lastChangeDate) throws CommonFlkException {
        try {
            requirementDAO.updateLastChangeDateById(requirementId, lastChangeDate);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateContentById(@NonNull UUID requirementId, @NonNull JSONObject content) throws CommonFlkException {
        try {
            requirementDAO.updateContentById(requirementId, content);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteRequirementById(@NonNull UUID requirementId) throws CommonFlkException {
        try {
            requirementDAO.deleteRequirementById(requirementId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public Requirement getRequirementById(@NonNull UUID requirementId) throws CommonFlkException {
        try {
            return requirementDAO.getRequirementById(requirementId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Requirement> getRequirementsByTypeId(@NonNull UUID requirementTypeId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementDAO.getRequirementsByTypeId(requirementTypeId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Requirement> getRequirementsByLoadDate(@NonNull LocalDateTime loadDate, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementDAO.getRequirementsByLoadDate(loadDate, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Requirement> getRequirementsByLoadDateDiapason(LocalDateTime loadDateStart, LocalDateTime loadDateEnd, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementDAO.getRequirementsByLoadDateDiapason(loadDateStart, loadDateEnd, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Requirement> getRequirementsByLastChangeDate(@NonNull LocalDateTime lastChangeDate, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementDAO.getRequirementsByLastChangeDate(lastChangeDate, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Requirement> getRequirementsByLastChangeDateDiapason(LocalDateTime lastChangeDateStart, LocalDateTime lastChangeDateEnd, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementDAO.getRequirementsByLastChangeDateDiapason(lastChangeDateStart, lastChangeDateEnd, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<Requirement> getAllRequirements(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementDAO.getAllRequirements(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
