package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.DeploymentStatus;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000006Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class DeploymentStatusDAOImpl extends BaseTable implements DeploymentStatusDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("deployment_status")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public DeploymentStatusDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("deployment_status", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public DeploymentStatusDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public UUID saveDeploymentStatus(@NonNull DeploymentStatus deploymentStatus) throws SQLException, CommonFlkException {
        Flk10000006Validator.validate(deploymentStatus);
        UUID deploymentStatusId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?)");
        ps.setObject(1, deploymentStatusId);
        ps.setString(2, deploymentStatus.getStatusName());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return deploymentStatusId;
    }

    @Override
    public DeploymentStatus getDeploymentStatusById(@NonNull UUID deploymentStatusId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.deployment_status_id, t.status_name FROM " + super.getTableName() + " t WHERE t.deployment_status_id = ?"
        );
        ps.setObject(1, deploymentStatusId);
        return getSingleDeploymentStatus(ps);
    }

    @Override
    public DeploymentStatus getDeploymentStatusByName(@NonNull String statusName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.deployment_status_id, t.status_name FROM " + super.getTableName() + " t WHERE t.status_name = ?"
        );
        ps.setString(1, statusName);
        return getSingleDeploymentStatus(ps);
    }

    @Override
    public List<DeploymentStatus> getAllDeploymentStatuses(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                DeploymentStatus.class.getName(),
                DeploymentStatusDAO.class.getName(),
                "getAllDeploymentStatuses",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.deployment_status_id, t.status_name FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );

        return getCodeFiles(ps);
    }

    @Override
    public void updateDeploymentStatusNameById(@NonNull UUID deploymentStatusId, @NonNull String newStatusName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET status_name = ? WHERE deployment_status_id = ?"
        );
        ps.setString(1, newStatusName);
        ps.setObject(2, deploymentStatusId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteDeploymentStatusById(@NonNull UUID deploymentStatusId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " WHERE deployment_status_id = ?"
        );
        ps.setObject(1, deploymentStatusId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    /**
     * Внутренний метод для получения одного статуса развертывания по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект CodeFile - статус развертывания (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private DeploymentStatus getSingleDeploymentStatus(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        DeploymentStatus deploymentStatus = null;
        if (rs.next()) {
            deploymentStatus = new DeploymentStatus(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return deploymentStatus;
    }

    /**
     * Внутренний метод для получения списка статусов развертываний по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов DeploymentStatus (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<DeploymentStatus> getCodeFiles(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<DeploymentStatus> deploymentStatuses = new ArrayList<>();
        while (rs.next()) {
            deploymentStatuses.add(new DeploymentStatus(
                    rs.getObject(1, UUID.class),
                    rs.getString(2)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return deploymentStatuses;
    }
}
