package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.data.datasource.entity.SystemRole;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Slf4j
public class SystemRoleDAOImpl extends BaseTable implements SystemRoleDAO {
    private static final String tableSchemaInitializationQuery =
            "CREATE TABLE public.system_role(" +
                    "system_role_id BIGINT NOT NULL constraint pk_system_rule PRIMARY KEY," +
                    "role_name varchar(255) NOT NULL" +
                    ");";

    private static final String idSequenceInitializationQuery =
            "CREATE SEQUENCE system_role_id START 1 INCREMENT 1 MINVALUE 1 OWNED BY system_role.system_role_id;";

    private static final String userReferenceInitializationQuery =
            "ALTER TABLE db_user ADD CONSTRAINT fk_db_user_system_role FOREIGN KEY (system_role_id) REFERENCES system_role ON UPDATE CASCADE ON DELETE CASCADE;";

    private static final List<String> basicRoles = new ArrayList<>(List.of(
            "ADMIN",
            "ANALYST",
            "DEVELOPER",
            "DEVOPS",
            "QA"
    ));


    public SystemRoleDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    public SystemRoleDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("system_role", dbConnector);
    }

    @Override
    public boolean initializeTable() throws SQLException {
        DatabaseMetaData dbm = super.getConnection().getMetaData();
        ResultSet tables = dbm.getTables(null, null, "system_role", null);
        if (tables.next())
            return false;
        else {
            super.executeSqlStatementUpdate(super.prepareStatement(tableSchemaInitializationQuery));
            super.executeSqlStatementUpdate(super.prepareStatement(idSequenceInitializationQuery));
            super.executeSqlStatementUpdate(super.prepareStatement(userReferenceInitializationQuery));
            return true;
        }
    }

    @Override
    public boolean initializeBasicRoles() throws SQLException {
        boolean basicRolesInitialized = false;
        List<SystemRole> systemRoles = getAllSystemRoles();
        for (String s : basicRoles)
            if (systemRoles.stream().noneMatch(r -> r.getRoleName().equals(s))) {
                saveSystemRole(new SystemRole(null, s));
                basicRolesInitialized = true;
            }
        return basicRolesInitialized;
    }

    private Integer saveSystemRole(@NonNull SystemRole systemRole) throws SQLException {
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (nextval('system_role_id'), ?)");
        ps.setString(1, systemRole.getRoleName());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.debug("database logging: " + stRes + " rows inserted into " + super.getTableName(), Level.INFO);
        Integer systemRoleId = null;
        List<SystemRole> systemRoles = getAllSystemRoles();
        for (SystemRole sr : systemRoles)
            if (sr.getRoleName().equals(systemRole.getRoleName())) systemRoleId = sr.getSystemRoleId();
        return systemRoleId;
    }

    @Override
    public SystemRole getSystemRole(@NonNull Integer systemRoleId) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.system_role_id system_role_id, t.role_name role_name FROM " + super.getTableName() + " t WHERE t.system_role_id=?",
                SortDirection.NONE,
                null
        );
        ps.setObject(1, systemRoleId);
        return getSingleSystemRole(ps);
    }

    @Override
    public SystemRole getSystemRoleByName(@NonNull String roleName) throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.system_role_id system_role_id, t.role_name role_name FROM " + super.getTableName() + " t WHERE t.role_name=?",
                SortDirection.NONE,
                null
        );
        ps.setString(1, roleName);
        return getSingleSystemRole(ps);
    }

    @Override
    public List<SystemRole> getAllSystemRoles() throws SQLException {
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.system_role_id system_role_id, t.role_name role_name FROM " + super.getTableName() + " t",
                SortDirection.NONE,
                null
        );
        return getSystemRoleList(ps);
    }

    private SystemRole getSingleSystemRole(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        SystemRole systemRole = null;
        while (rs.next()) {
            systemRole = new SystemRole(
                    Integer.valueOf(rs.getInt(1)),
                    rs.getString(2)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return systemRole;
    }

    private List<SystemRole> getSystemRoleList(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<SystemRole> systemRoles = new ArrayList<>();
        while (rs.next()) {
            systemRoles.add(new SystemRole(
                    Integer.valueOf(rs.getInt(1)),
                    rs.getString(2)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return systemRoles;
    }
}
