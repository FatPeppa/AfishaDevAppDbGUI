package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.AccessedRole;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000002Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class AccessedRoleDAOImpl extends BaseTable implements AccessedRoleDAO {

    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("accessed_role")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public AccessedRoleDAOImpl(@NonNull DbConnector dbConnector) throws SQLException {
        super("accessed_role", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public AccessedRoleDAOImpl(@NonNull String tableName, @NonNull DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public void saveAccessedRole(@NonNull AccessedRole accessedRole) throws SQLException, CommonFlkException {
        Flk10000002Validator.validate(accessedRole);
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?)");
        ps.setObject(1, accessedRole.getRequirementId());
        ps.setString(2, accessedRole.getRoleName());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
    }

    @Override
    public void updateAccessedRoleNameByIdAndName(
            @NonNull UUID requirementId,
            @NonNull String oldAccessedRoleName,
            @NonNull String newAccessedRoleName
    ) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() +
                " SET role_name=1 WHERE requirement_id=? AND role_name=?"
        );
        ps.setString(1, newAccessedRoleName);
        ps.setObject(2, requirementId);
        ps.setString(3, oldAccessedRoleName);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public List<AccessedRole> getAccessedRolesByRequirementId(@NonNull UUID requirementId,
                                                              @NonNull SortDirection sortDirection,
                                                              String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                AccessedRole.class.getName(),
                AccessedRoleDAO.class.getName(),
                "getAccessedRolesByRequirementId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.requirement_id requirement_id, t.role_name role_name " +
                " FROM " + super.getTableName() + " t WHERE t.requirement_id=?1",
                sortDirection,
                sortBy
        );
        ps.setObject(1, requirementId);
        return getAccessedRoles(ps);
    }

    @Override
    public List<AccessedRole> getAllAccessedRoles(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                AccessedRole.class.getName(),
                AccessedRoleDAO.class.getName(),
                "getAllAccessedRoles",
                sortDirection,
                sortBy
        );

        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.requirement_id requirement_id, t.role_name role_name FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );
        return getAccessedRoles(ps);
    }

    @Override
    public List<AccessedRole> getAccessedRolesByRoleName(@NonNull String accessedRoleName,
                                                         @NonNull SortDirection sortDirection,
                                                         String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                AccessedRole.class.getName(),
                AccessedRoleDAO.class.getName(),
                "getAccessedRolesByRoleName",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.requirement_id requirement_id, t.role_name role_name FROM " + super.getTableName()
                + " t WHERE t.role_name=?",
                sortDirection,
                sortBy
        );
        ps.setString(1, accessedRoleName);
        return getAccessedRoles(ps);
    }

    @Override
    public AccessedRole getAccessedRoleByRequirementIdAndName(@NonNull UUID requirementId, @NonNull String accessedRoleName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.requirement_id requirement_id, t.role_name role_name FROM " + super.getTableName()
                        + " t WHERE t.requirement_id=? AND t.role_name=?"
        );
        ps.setObject(1, requirementId);
        ps.setString(2, accessedRoleName);
        return getSingleAccessedRole(ps);
    }

    @Override
    public void deleteAccessedRoleByRequirementIdAndName(@NonNull UUID requirementId, @NonNull String accessedRoleName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " WHERE requirement_id=? AND role_name=?"
        );
        ps.setObject(1, requirementId);
        ps.setString(2, accessedRoleName);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void deleteAllAccessedRolesByRequirementId(@NonNull UUID requirementId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " WHERE requirement_id=?"
        );
        ps.setObject(1, requirementId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    /**
     * Внутренний метод для получения одной роли, имеющей доступ к требованию, по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект AccessedRole - роль, имеющая доступ к требованию (первая из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private AccessedRole getSingleAccessedRole(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        AccessedRole accessedRole = null;
        if (rs.next()) {
            accessedRole = new AccessedRole(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return accessedRole;
    }

    /**
     * Внутренний метод для получения списка ролей, имеющих доступ к требованию, по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов AccessedRole (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<AccessedRole> getAccessedRoles(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<AccessedRole> accessedRoles = new ArrayList<>();
        while (rs.next()) {
            accessedRoles.add(new AccessedRole(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return accessedRoles;
    }
}
