package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.RequirementAuthorDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementAuthor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RequirementAuthorRepositoryImpl implements RequirementAuthorRepository {
    private final RequirementAuthorDAO requirementAuthorDAO;

    public RequirementAuthorRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            requirementAuthorDAO = (new AfishaDevAppDb(applicationProperties)).getRequirementAuthorDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void saveRequirementAuthor(@NonNull RequirementAuthor requirementAuthor) throws CommonFlkException {
        try {
            requirementAuthorDAO.saveRequirementAuthor(requirementAuthor);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws CommonFlkException {
        try {
            requirementAuthorDAO.deleteRequirementAuthorByIds(requirementId, authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteRequirementAuthorsByRequirementId(@NonNull UUID requirementId) throws CommonFlkException {
        try {
            requirementAuthorDAO.deleteRequirementAuthorsByRequirementId(requirementId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deleteRequirementAuthorsByAuthorId(@NonNull UUID authorId) throws CommonFlkException {
        try {
            requirementAuthorDAO.deleteRequirementAuthorsByAuthorId(authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateRequirementAuthorAuthorId(@NonNull UUID oldAuthorId, @NonNull UUID requirementId, @NonNull UUID newAuthorId) throws CommonFlkException {
        try {
            requirementAuthorDAO.updateRequirementAuthorAuthorId(oldAuthorId, requirementId, newAuthorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updateRequirementAuthorRequirementId(@NonNull UUID authorId, @NonNull UUID oldRequirementId, @NonNull UUID newRequirementId) throws CommonFlkException {
        try {
            requirementAuthorDAO.updateRequirementAuthorRequirementId(authorId, oldRequirementId, newRequirementId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public RequirementAuthor getRequirementAuthorByIds(@NonNull UUID requirementId, @NonNull UUID authorId) throws CommonFlkException {
        try {
            return requirementAuthorDAO.getRequirementAuthorByIds(authorId, authorId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<RequirementAuthor> getAllRequirementAuthors(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementAuthorDAO.getAllRequirementAuthors(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<RequirementAuthor> getRequirementAuthorsByRequirementId(@NonNull UUID requirementId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementAuthorDAO.getRequirementAuthorsByRequirementId(requirementId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<RequirementAuthor> getRequirementAuthorsByAuthorId(@NonNull UUID authorId, @NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return requirementAuthorDAO.getRequirementAuthorsByAuthorId(authorId, sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
