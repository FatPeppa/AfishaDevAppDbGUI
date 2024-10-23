package org.skyhigh.afishadevappgui.data.repository;

import lombok.NonNull;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.properties.ApplicationProperties;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.AfishaDevAppDb;
import org.skyhigh.afishadevappgui.data.datasource.dao.PasswordGenRuleDAO;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PasswordGenRuleRepositoryImpl implements PasswordGenRuleRepository {
    private final PasswordGenRuleDAO passwordGenRuleDAO;

    public PasswordGenRuleRepositoryImpl(ApplicationProperties applicationProperties) throws CommonFlkException {
        try {
            passwordGenRuleDAO = (new AfishaDevAppDb(applicationProperties)).getPasswordGenRuleDAO();
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public UUID savePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws CommonFlkException {
        try {
            return passwordGenRuleDAO.savePasswordGenRule(passwordGenRule);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void updatePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws CommonFlkException {
        try {
            passwordGenRuleDAO.updatePasswordGenRule(passwordGenRule);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public void deletePasswordGenRuleById(@NonNull UUID ruleId) throws CommonFlkException {
        try {
            passwordGenRuleDAO.deletePasswordGenRuleById(ruleId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public PasswordGenRule getPasswordGenRuleById(@NonNull UUID ruleId) throws CommonFlkException {
        try {
            return passwordGenRuleDAO.getPasswordGenRuleById(ruleId);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<PasswordGenRule> getAllPasswordGenRules(@NonNull SortDirection sortDirection, String sortBy) throws CommonFlkException {
        try {
            return passwordGenRuleDAO.getAllPasswordGenRules(sortDirection, sortBy);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Произошла системная ошибка. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }
}
