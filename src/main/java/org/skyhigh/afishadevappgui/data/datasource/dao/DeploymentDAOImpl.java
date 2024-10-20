package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.db.validation.CommonEntityException;
import org.skyhigh.afishadevappgui.common.db.validation.CommonSystemException;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Deployment;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000005Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.files.Flk10020000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.updating.Flk10020001Validator;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class DeploymentDAOImpl extends BaseTable implements DeploymentDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("deployment")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public DeploymentDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("deployment", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public DeploymentDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public UUID saveDeployment(@NonNull Deployment deployment) throws SQLException, CommonFlkException {
        Flk10000005Validator.validate(deployment);
        Flk10020000Validator.validate(deployment);
        UUID deploymentId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setObject(1, deploymentId);
        ps.setObject(2, deployment.getDeploymentStatusId());
        if (deployment.getDeploymentPath() == null)
            ps.setNull(3, Types.VARCHAR);
        else
            ps.setString(3, deployment.getDeploymentPath());
        if (deployment.getSettings() == null)
            ps.setNull(4, Types.OTHER);
        else
            ps.setString(4, deployment.getSettings().toString());
        if (deployment.getBuiltVersion() == null)
            ps.setNull(5, Types.VARCHAR);
        else
            ps.setString(5, deployment.getBuiltVersion());
        if (deployment.getBuiltSettings() == null)
           ps.setNull(6, Types.OTHER);
        else
            ps.setString(6, deployment.getBuiltSettings().toString());
        if (deployment.getBuilt() == null)
            ps.setNull(7, Types.BINARY);
        else
            try {
                ps.setBinaryStream(
                        7,
                        new FileInputStream(deployment.getBuilt()),
                        deployment.getBuilt().length()
                );
            } catch (FileNotFoundException e) {
                throw new CommonEntityException(
                        "Deployment",
                        "10020000",
                        e.getMessage(),
                        List.of("built"),
                        true,
                        e.getCause()
                );
            }
        ps.setObject(8, deployment.getProjectId());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return deploymentId;
    }

    @Override
    public void deleteDeploymentById(@NonNull UUID deploymentId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " WHERE deployment_id = ?1"
        );
        ps.setObject(1, deploymentId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public void updateDeploymentStatusById(
            @NonNull UUID deploymentId,
            @NonNull UUID deploymentStatusId
    ) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET deployment_status_id = ?1 WHERE deployment_id = ?2"
        );
        ps.setObject(1, deploymentStatusId);
        ps.setObject(2, deploymentId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateDeploymentBuiltVersionById(
            @NonNull UUID deploymentId,
            String builtVersion
    ) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET built_version = ?1 WHERE deployment_id = ?2"
        );
        if (builtVersion == null)
            ps.setNull(1, Types.VARCHAR);
        else
            ps.setString(1, builtVersion);
        ps.setObject(2, deploymentId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateDeploymentBuiltSettingsById(
            @NonNull UUID deploymentId,
            JSONObject builtSettings
    ) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET built_settings = ?1 WHERE deployment_id = ?2"
        );
        if (builtSettings == null)
            ps.setNull(1, Types.OTHER);
        else
            ps.setString(1, builtSettings.toString());
        ps.setObject(2, deploymentId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateDeploymentBuiltById(
            @NonNull UUID deploymentId,
            File built
    ) throws SQLException, CommonFlkException {
        Flk10020001Validator.validate(deploymentId, built);
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET built = ?1 WHERE deployment_id = ?2"
        );

        if (built == null)
            ps.setNull(7, Types.BINARY);
        else
            try {
                ps.setBinaryStream(
                        1,
                        new FileInputStream(built),
                        built.length()
                );
            } catch (FileNotFoundException e) {
                throw new CommonEntityException(
                        "Deployment",
                        "10020001",
                        e.getMessage(),
                        List.of("built"),
                        true,
                        e.getCause()
                );
            }

        ps.setObject(2, deploymentId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateDeploymentProjectIdById(
            @NonNull UUID deploymentId,
            @NonNull UUID projectId
    ) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET project_id = ?1 WHERE deployment_id = ?2"
        );
        ps.setObject(1, projectId);
        ps.setObject(2, deploymentId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateDeploymentParamsById(
            @NonNull UUID deploymentId,
            @NonNull UUID deploymentStatusId,
            String deploymentPath,
            JSONObject settings,
            String builtVersion,
            JSONObject builtSettings,
            File built,
            @NonNull UUID projectId
    ) throws SQLException, CommonFlkException {
        Flk10020001Validator.validate(deploymentId, built);
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " " +
                        "SET deployment_status_id = ?1" +
                        "deployment_path = ?2, " +
                        "settings = ?3, " +
                        "built_version = ?4, " +
                        "built_settings = ?5, " +
                        "built = ?6, " +
                        "project_id = ?7 " +
                        "WHERE deployment_id = ?8"
        );

        ps.setObject(1, deploymentStatusId);
        if (deploymentPath == null)
            ps.setNull(2, Types.VARCHAR);
        else
            ps.setString(2, deploymentPath);
        if (settings == null)
            ps.setNull(3, Types.OTHER);
        else
            ps.setString(3, settings.toString());
        if (builtVersion == null)
            ps.setNull(4, Types.VARCHAR);
        else
            ps.setString(4, builtVersion);
        if (builtSettings == null)
            ps.setNull(5, Types.OTHER);
        else
            ps.setString(5, builtSettings.toString());
        if (built == null)
            ps.setNull(6, Types.BINARY);
        else
            try {
                ps.setBinaryStream(
                        6,
                        new FileInputStream(built),
                        built.length()
                );
            } catch (FileNotFoundException e) {
                throw new CommonEntityException(
                        "Deployment",
                        "10020001",
                        e.getMessage(),
                        List.of("built"),
                        true,
                        e.getCause()
                );
            }
        ps.setObject(7, projectId);
        ps.setObject(8, deploymentId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public Deployment getDeploymentById(@NonNull UUID deploymentId) throws SQLException, CommonFlkException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.deployment_id deployment_id, t.deployment_status_id deployment_status_id, " +
                        "t.deployment_path deployment_path, t.settings settings, t.built_version built_version, " +
                        "t.built_settings built_settings, t.built built, t.project_id project_id " +
                        "FROM " + super.getTableName() + " t WHERE t.deployment_id = ?1"
        );
        ps.setObject(1, deploymentId);
        return getSingleDeployment(ps);
    }

    @Override
    public List<Deployment> getDeploymentsByProjectId(
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Deployment.class.getName(),
                DeploymentDAO.class.getName(),
                "getDeploymentsByProjectId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.deployment_id deployment_id, t.deployment_status_id deployment_status_id, " +
                        "t.deployment_path deployment_path, t.settings settings, t.built_version built_version, " +
                        "t.built_settings built_settings, t.built built, t.project_id project_id " +
                        "FROM " + super.getTableName() + " t WHERE t.project_id = ?1",
                sortDirection,
                sortBy
        );
        ps.setObject(1, projectId);
        return getDeployments(ps);
    }

    @Override
    public List<Deployment> getDeploymentsByStatusId(
            @NonNull UUID deploymentStatusId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Deployment.class.getName(),
                DeploymentDAO.class.getName(),
                "getDeploymentsByStatusId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.deployment_id deployment_id, t.deployment_status_id deployment_status_id, " +
                        "t.deployment_path deployment_path, t.settings settings, t.built_version built_version, " +
                        "t.built_settings built_settings, t.built built, t.project_id project_id " +
                        "FROM " + super.getTableName() + " t WHERE t.deployment_status_id = ?1",
                sortDirection,
                sortBy
        );
        ps.setObject(1, deploymentStatusId);
        return getDeployments(ps);
    }

    @Override
    public List<Deployment> getAllDeployments(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Deployment.class.getName(),
                DeploymentDAO.class.getName(),
                "getAllDeployments",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.deployment_id deployment_id, t.deployment_status_id deployment_status_id, " +
                        "t.deployment_path deployment_path, t.settings settings, t.built_version built_version, " +
                        "t.built_settings built_settings, t.built built, t.project_id project_id " +
                        "FROM " + super.getTableName(),
                sortDirection,
                sortBy
        );
        return getDeployments(ps);
    }

    @Override
    public List<Deployment> getDeploymentsByBuiltVersion(
            String builtVersion,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Deployment.class.getName(),
                DeploymentDAO.class.getName(),
                "getDeploymentsByBuiltVersion",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.deployment_id deployment_id, t.deployment_status_id deployment_status_id, " +
                        "t.deployment_path deployment_path, t.settings settings, t.built_version built_version, " +
                        "t.built_settings built_settings, t.built built, t.project_id project_id " +
                        "FROM " + super.getTableName() + " t WHERE t.built_version = ?1",
                sortDirection,
                sortBy
        );
        ps.setString(1, builtVersion);
        return getDeployments(ps);
    }

    @Override
    public List<Deployment> getDeploymentsByPath(
            String deploymentPath,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Deployment.class.getName(),
                DeploymentDAO.class.getName(),
                "getDeploymentsByPath",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.deployment_id deployment_id, t.deployment_status_id deployment_status_id, " +
                        "t.deployment_path deployment_path, t.settings settings, t.built_version built_version, " +
                        "t.built_settings built_settings, t.built built, t.project_id project_id " +
                        "FROM " + super.getTableName() + " t WHERE t.deployment_path = ?1",
                sortDirection,
                sortBy
        );
        ps.setString(1, deploymentPath);
        return getDeployments(ps);
    }

    @Override
    public List<Deployment> getDeploymentsByStatusIdAndProjectId(
            @NonNull UUID deploymentStatusId,
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Deployment.class.getName(),
                DeploymentDAO.class.getName(),
                "getDeploymentsByStatusIdAndProjectId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.deployment_id deployment_id, t.deployment_status_id deployment_status_id, " +
                        "t.deployment_path deployment_path, t.settings settings, t.built_version built_version, " +
                        "t.built_settings built_settings, t.built built, t.project_id project_id " +
                        "FROM " + super.getTableName() + " t WHERE t.deployment_status_id = ?1 AND t.projectId = ?2",
                sortDirection,
                sortBy
        );
        ps.setObject(1, deploymentStatusId);
        ps.setObject(1, projectId);
        return getDeployments(ps);
    }

    /**
     * Внутренний метод для получения одного развертывания по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект Deployment - развертывание (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private Deployment getSingleDeployment(@NonNull PreparedStatement ps) throws SQLException, CommonFlkException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        Deployment deployment = null;
        if (rs.next()) {
            UUID deploymentId = rs.getObject(1, UUID.class);
            File tempBuiltFile = null;
            InputStream iSBuilt = rs.getBinaryStream(7);
            try {
                tempBuiltFile = File.createTempFile("B" + (int) ((Math.random() * 98) + 1), ".tmp");
                tempBuiltFile.deleteOnExit();
                FileOutputStream out = new FileOutputStream(tempBuiltFile);
                iSBuilt.transferTo(out);
                iSBuilt.close();
                out.close();
            } catch (IOException e) {
                throw new CommonSystemException(
                        "Ошибка при создании временного файла для сборки в сущности Deployment с id: " +
                                deploymentId.toString(),
                        e.getCause(),
                        false
                );
            }
            deployment = new Deployment(
                    deploymentId,
                    rs.getObject(2, UUID.class),
                    rs.getString(3),
                    new JSONObject(rs.getString(4)),
                    rs.getString(5),
                    new JSONObject(rs.getString(6)),
                    tempBuiltFile,
                    rs.getObject(8, UUID.class)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return deployment;
    }

    /**
     * Внутренний метод для получения списка развертываний по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов Deployment (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<Deployment> getDeployments(@NonNull PreparedStatement ps) throws SQLException, CommonFlkException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<Deployment> deployments = new ArrayList<>();
        while (rs.next()) {
            UUID deploymentId = rs.getObject(1, UUID.class);
            File tempBuiltFile = null;
            InputStream iSBuilt = rs.getBinaryStream(7);
            try {
                tempBuiltFile = File.createTempFile("B" + (int) ((Math.random() * 98) + 1), ".tmp");
                tempBuiltFile.deleteOnExit();
                FileOutputStream out = new FileOutputStream(tempBuiltFile);
                iSBuilt.transferTo(out);
                iSBuilt.close();
                out.close();
            } catch (IOException e) {
                throw new CommonSystemException(
                        "Ошибка при создании временного файла для сборки в сущности Deployment с id: " +
                                deploymentId.toString(),
                        e.getCause(),
                        false
                );
            }
            deployments.add(
                new Deployment(
                    deploymentId,
                    rs.getObject(2, UUID.class),
                    rs.getString(3),
                    new JSONObject(rs.getString(4)),
                    rs.getString(5),
                    new JSONObject(rs.getString(6)),
                    tempBuiltFile,
                    rs.getObject(8, UUID.class)
                )
            );
        }

        rs.close();
        ps.close();
        super.close();
        return deployments;
    }
}