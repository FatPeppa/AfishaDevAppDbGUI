package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Requirement;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000009Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.updating.Flk10000001Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class RequirementDAOImpl extends BaseTable implements RequirementDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("requirement")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public RequirementDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("requirement", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public RequirementDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public UUID saveRequirement(@NonNull Requirement requirement) throws SQLException, CommonFlkException {
        Flk10000009Validator.validate(requirement);
        UUID requirementId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?, ?, ?, ?)");
        ps.setObject(1, requirementId);
        ps.setObject(2, requirement.getRequirementTypeId());
        ps.setObject(3, requirement.getLoadDate());
        ps.setObject(4, requirement.getLastChangeDate());
        ps.setString(5, requirement.getContent().toString());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return requirementId;
    }

    @Override
    public void updateRequirement(@NonNull Requirement requirement) throws SQLException, CommonFlkException {
        Flk10000001Validator.validate(requirement);
        Flk10000009Validator.validate(requirement);
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.requirement_type_id = ?1, t.load_date = ?2, t.last_change_date = ?3, " +
                        "t.content = ?4 " +
                        "WHERE t.requirement_id = ?5"
        );
        ps.setObject(1, requirement.getRequirementTypeId());
        ps.setObject(2, requirement.getLoadDate());
        ps.setObject(3, requirement.getLastChangeDate());
        ps.setString(4, requirement.getContent().toString());
        ps.setObject(5, requirement.getRequirementId());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateRequirementTypeIdById(@NonNull UUID requirementId, @NonNull UUID requirementTypeId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.requirement_type_id = ?1 WHERE t.requirement_id = ?2"
        );
        ps.setObject(1, requirementTypeId);
        ps.setObject(2, requirementId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateLastChangeDateById(@NonNull UUID requirementId, @NonNull LocalDateTime lastChangeDate) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.last_change_date = ?1 WHERE t.requirement_id = ?2"
        );
        ps.setObject(1, lastChangeDate);
        ps.setObject(2, requirementId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateContentById(@NonNull UUID requirementId, @NonNull JSONObject content) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.content = ?1 WHERE t.requirement_id = ?2"
        );
        ps.setString(1, content.toString());
        ps.setObject(2, requirementId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteRequirementById(@NonNull UUID requirementId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.requirement_id = ?1"
        );
        ps.setObject(1, requirementId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public Requirement getRequirementById(@NonNull UUID requirementId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                        "FROM " + super.getTableName() + " " +
                        "t WHERE t.requirement_id = ?1"
        );
        ps.setObject(1, requirementId);
        return getSingleRequirement(ps);
    }

    @Override
    public List<Requirement> getRequirementsByTypeId(@NonNull UUID requirementTypeId, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Requirement.class.getName(),
                RequirementDAO.class.getName(),
                "getRequirementsByTypeId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                        "FROM " + super.getTableName() + " " +
                        "t WHERE t.requirement_type_id = ?1"
        );
        ps.setObject(1, requirementTypeId);

        return getRequirements(ps);
    }

    @Override
    public List<Requirement> getRequirementsByLoadDate(@NonNull LocalDateTime loadDate, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Requirement.class.getName(),
                RequirementDAO.class.getName(),
                "getRequirementsByLoadDate",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                        "FROM " + super.getTableName() + " " +
                        "t WHERE t.load_date = ?1"
        );
        ps.setObject(1, loadDate);

        return getRequirements(ps);
    }

    @Override
    public List<Requirement> getRequirementsByLoadDateDiapason(LocalDateTime loadDateStart, LocalDateTime loadDateEnd, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Requirement.class.getName(),
                RequirementDAO.class.getName(),
                "getRequirementsByLoadDateDiapason",
                sortDirection,
                sortBy
        );

        PreparedStatement ps = null;

        if (loadDateStart != null && loadDateEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t WHERE t.load_date <= ?1 AND t.load_date >= ?2"
            );
            ps.setObject(1, loadDateEnd);
            ps.setObject(2, loadDateStart);
        }
        else if (loadDateStart != null) {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t WHERE t.load_date >= ?2"
            );
            ps.setObject(2, loadDateStart);
        }
        else if (loadDateEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t WHERE t.load_date <= ?1"
            );
            ps.setObject(1, loadDateEnd);
        }
        else {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t"
            );
        }

        return getRequirements(ps);
    }

    @Override
    public List<Requirement> getRequirementsByLastChangeDate(@NonNull LocalDateTime lastChangeDate, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Requirement.class.getName(),
                RequirementDAO.class.getName(),
                "getRequirementsByLastChangeDate",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                        "FROM " + super.getTableName() + " " +
                        "t WHERE t.last_change_date = ?1"
        );
        ps.setObject(1, lastChangeDate);

        return getRequirements(ps);
    }

    @Override
    public List<Requirement> getRequirementsByLastChangeDateDiapason(LocalDateTime lastChangeDateStart, LocalDateTime lastChangeDateEnd, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Requirement.class.getName(),
                RequirementDAO.class.getName(),
                "getRequirementsByLastChangeDateDiapason",
                sortDirection,
                sortBy
        );

        PreparedStatement ps = null;

        if (lastChangeDateStart != null && lastChangeDateEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t WHERE t.last_change_date <= ?1 AND t.last_change_date >= ?2"
            );
            ps.setObject(1, lastChangeDateEnd);
            ps.setObject(2, lastChangeDateStart);
        }
        else if (lastChangeDateStart != null) {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t WHERE t.last_change_date >= ?2"
            );
            ps.setObject(2, lastChangeDateStart);
        }
        else if (lastChangeDateEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t WHERE t.last_change_date <= ?1"
            );
            ps.setObject(1, lastChangeDateEnd);
        }
        else {
            ps = super.prepareStatement(
                    "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                            "FROM " + super.getTableName() + " " +
                            "t"
            );
        }

        return getRequirements(ps);
    }

    @Override
    public List<Requirement> getAllRequirements(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Requirement.class.getName(),
                RequirementDAO.class.getName(),
                "getAllRequirements",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_id, t.requirement_type_id, t.load_date, t.last_change_date, t.content " +
                        "FROM " + super.getTableName() + " " +
                        "t"
        );
        return getRequirements(ps);
    }

    /**
     * Внутренний метод для получения одного требования по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект Requirement - требование (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private Requirement getSingleRequirement(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        Requirement requirement = null;
        if (rs.next()) {
            requirement = new Requirement(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getTimestamp(4).toLocalDateTime(),
                    new JSONObject(rs.getString(5))
            );
        }

        rs.close();
        ps.close();
        super.close();
        return requirement;
    }

    /**
     * Внутренний метод для получения списка требований по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов Requirement (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<Requirement> getRequirements(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<Requirement> requirements = new ArrayList<>();
        while (rs.next()) {
            requirements.add(new Requirement(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getTimestamp(4).toLocalDateTime(),
                    new JSONObject(rs.getString(5))
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return requirements;
    }
}
