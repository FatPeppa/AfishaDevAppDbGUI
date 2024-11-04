package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.common.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.data.datasource.entity.PasswordGenRule;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000014Validator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class PasswordGenRuleDAOImpl extends BaseTable implements PasswordGenRuleDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("password_gen_rule")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public PasswordGenRuleDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("password_gen_rule", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public PasswordGenRuleDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public Integer savePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws SQLException, CommonFlkException {
        Flk10000014Validator.validate(passwordGenRule);
        Integer ruleId = null;
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (nextval('password_gen_rule_id'), ?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, passwordGenRule.getRepeatableCharactersAmount().intValue());
        ps.setInt(2, passwordGenRule.getCapitalLettersAmount().intValue());
        ps.setInt(3, passwordGenRule.getSpecSymbolsAmount().intValue());
        if (passwordGenRule.getBeginDate() == null)
            ps.setNull(4, Types.TIMESTAMP);
        else
            ps.setObject(4, passwordGenRule.getBeginDate());
        if (passwordGenRule.getEndDate() == null)
            ps.setNull(5, Types.TIMESTAMP);
        else
            ps.setObject(5, passwordGenRule.getEndDate());
        ps.setObject(6, passwordGenRule.getCreateDate());
        ps.setInt(7, passwordGenRule.getNumericSymbolsAmount().intValue());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        List<PasswordGenRule> passwordGenRules = getAllPasswordGenRules(SortDirection.NONE, null);
        for (PasswordGenRule pg : passwordGenRules)
            if (pg.getRepeatableCharactersAmount().equals(passwordGenRule.getRepeatableCharactersAmount())
                    && pg.getCapitalLettersAmount().equals(passwordGenRule.getCapitalLettersAmount())
                    && pg.getSpecSymbolsAmount().equals(passwordGenRule.getSpecSymbolsAmount())
                    && pg.getNumericSymbolsAmount().equals(passwordGenRule.getNumericSymbolsAmount())
                    && pg.getBeginDate().equals(passwordGenRule.getBeginDate())
                    && pg.getEndDate().equals(passwordGenRule.getEndDate())
                    && pg.getCreateDate().equals(passwordGenRule.getCreateDate())
            ) ruleId = pg.getRuleId();
        return ruleId;
    }

    @Override
    public void updatePasswordGenRule(@NonNull PasswordGenRule passwordGenRule) throws SQLException, CommonFlkException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.repeatable_characters_amount = ?, t.capital_letters_amount = ?, " +
                        "t.spec_symbols_amount = ?, t.begin_date = ?, t.end_date = ?, t.create_date = ?, t.numeric_symbols_amount = ?" +
                        " WHERE t.rule_id = ?"
        );
        ps.setInt(1, passwordGenRule.getRepeatableCharactersAmount().intValue());
        ps.setInt(2, passwordGenRule.getCapitalLettersAmount().intValue());
        ps.setInt(3, passwordGenRule.getSpecSymbolsAmount().intValue());
        if (passwordGenRule.getBeginDate() == null)
            ps.setNull(4, Types.TIMESTAMP);
        else
            ps.setObject(4, passwordGenRule.getBeginDate());
        if (passwordGenRule.getEndDate() == null)
            ps.setNull(5, Types.TIMESTAMP);
        else
            ps.setObject(5, passwordGenRule.getEndDate());
        ps.setObject(6, passwordGenRule.getCreateDate());
        ps.setInt(7, passwordGenRule.getNumericSymbolsAmount().intValue());
        ps.setObject(8, passwordGenRule.getRuleId());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deletePasswordGenRuleById(@NonNull Integer ruleId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.rule_id = ?"
        );
        ps.setObject(1, ruleId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public PasswordGenRule getPasswordGenRuleById(@NonNull Integer ruleId) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.rule_id, t.repeatable_characters_amount, t.capital_letters_amount, " +
                        "t.spec_symbols_amount, t.begin_date, t.end_date, t.create_date, t.numeric_symbols_amount" +
                        " FROM " + super.getTableName() + " t WHERE t.rule_id=?",
                SortDirection.NONE,
                null
        );
        ps.setObject(1, ruleId);
        return getSinglePasswordGenRule(ps);
    }

    @Override
    public PasswordGenRule getActualPasswordGenRuleByDate(LocalDateTime actualizationDate) throws SQLException, CommonFlkException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT rule_id, repeatable_characters_amount, capital_letters_amount, " +
                "spec_symbols_amount, begin_date, end_date, create_date, numeric_symbols_amount" +
                        " FROM get_password_gen_rule_by_date(?)",
                SortDirection.NONE,
                null
        );
        if (actualizationDate == null)
            ps.setNull(1, Types.TIMESTAMP);
        else
            ps.setObject(1, actualizationDate);
        try {
            return getSinglePasswordGenRule(ps);
        } catch (SQLException e) {
            throw new CommonSystemException(
                    "Системная ошибка при получении актуального правила генерации паролей по дате актуальности: '" +
                            actualizationDate + "'. Текст ошибки: '" + e.getMessage() + "'",
                    e.getCause(),
                    false
            );
        }
    }

    @Override
    public List<PasswordGenRule> getAllPasswordGenRules(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                PasswordGenRule.class.getName(),
                PasswordGenRuleDAO.class.getName(),
                "getAllPasswordGenRules",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.rule_id, t.repeatable_characters_amount, t.capital_letters_amount, " +
                        "t.spec_symbols_amount, t.begin_date, t.end_date, t.create_date, t.numeric_symbols_amount" +
                        " FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );
        return getAuthors(ps);
    }

    /**
     * Внутренний метод для получения одного правила заполнения паролей по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект PasswordGenRule - правило заполнения (генерации) паролей (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private PasswordGenRule getSinglePasswordGenRule(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        PasswordGenRule passwordGenRule = null;
        if (rs.next()) {
            passwordGenRule = new PasswordGenRule(
                    Integer.valueOf(rs.getInt(1)),
                    Integer.valueOf(rs.getInt(2)),
                    Integer.valueOf(rs.getInt(3)),
                    Integer.valueOf(rs.getInt(4)),
                    rs.getTimestamp(5) == null ? null : rs.getTimestamp(5).toLocalDateTime(),
                    rs.getTimestamp(6) == null ? null : rs.getTimestamp(6).toLocalDateTime(),
                    rs.getTimestamp(7).toLocalDateTime(),
                    Integer.valueOf(rs.getInt(8))
            );
        }

        rs.close();
        ps.close();
        super.close();
        return passwordGenRule;
    }

    /**
     * Внутренний метод для получения списка правил генерации паролей по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов PasswordGenRule (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<PasswordGenRule> getAuthors(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<PasswordGenRule> passwordGenRules = new ArrayList<>();
        while (rs.next()) {
            passwordGenRules.add(new PasswordGenRule(
                    Integer.valueOf(rs.getInt(1)),
                    Integer.valueOf(rs.getInt(2)),
                    Integer.valueOf(rs.getInt(3)),
                    Integer.valueOf(rs.getInt(4)),
                    rs.getTimestamp(5) == null ? null : rs.getTimestamp(5).toLocalDateTime(),
                    rs.getTimestamp(6) == null ? null : rs.getTimestamp(6).toLocalDateTime(),
                    rs.getTimestamp(7).toLocalDateTime(),
                    Integer.valueOf(rs.getInt(8))
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return passwordGenRules;
    }
}
