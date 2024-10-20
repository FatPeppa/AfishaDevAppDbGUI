package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.RequirementType;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000011Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class RequirementTypeDAOImpl extends BaseTable implements RequirementTypeDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("requirement_type")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public RequirementTypeDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("requirement_type", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public RequirementTypeDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public UUID saveRequirementType(@NonNull RequirementType requirementType) throws SQLException, CommonFlkException {
        Flk10000011Validator.validate(requirementType);
        UUID requirementTypeId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?)");
        ps.setObject(1, requirementTypeId);
        ps.setString(2, requirementType.getRequirementTypeName());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return requirementTypeId;
    }

    @Override
    public RequirementType getRequirementTypeById(@NonNull UUID requirementTypeId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_type_id, t.requirement_type_name FROM " + super.getTableName() + " t WHERE t.requirement_type_id = ?1"
        );
        ps.setObject(1, requirementTypeId);
        return getSingleRequirementType(ps);
    }

    @Override
    public RequirementType getRequirementTypeByName(@NonNull String requirementTypeName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_type_id, t.requirement_type_name FROM " + super.getTableName() + " t WHERE t.requirement_type_name = ?1"
        );
        ps.setString(1, requirementTypeName);
        return getSingleRequirementType(ps);
    }

    @Override
    public List<RequirementType> getAllRequirementTypes(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                RequirementType.class.getName(),
                RequirementTypeDAO.class.getName(),
                "getAllRequirementTypes",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.requirement_type_id, t.requirement_type_name FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );

        return getRequirementTypes(ps);
    }

    @Override
    public void updateRequirementTypeNameById(@NonNull UUID requirementTypeId, @NonNull String newRequirementTypeName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET t.requirement_type_name = ?1 WHERE t.requirement_type_id = ?2"
        );
        ps.setString(1, newRequirementTypeName);
        ps.setObject(2, requirementTypeId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteRequirementTypeById(@NonNull UUID requirementTypeId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.requirement_type_id = ?1"
        );
        ps.setObject(1, requirementTypeId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    /**
     * Внутренний метод для получения одного типа требования по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект RequirementType - тип требования (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private RequirementType getSingleRequirementType(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        RequirementType requirementType = null;
        if (rs.next()) {
            requirementType = new RequirementType(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return requirementType;
    }

    /**
     * Внутренний метод для получения списка типов требований по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов RequirementType (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<RequirementType> getRequirementTypes(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<RequirementType> requirementTypes = new ArrayList<>();
        while (rs.next()) {
            requirementTypes.add(new RequirementType(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return requirementTypes;
    }
}
