package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000007Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.updating.Flk10000000Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class ProjectDAOImpl extends BaseTable implements ProjectDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("project")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public ProjectDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("project", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public ProjectDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    //May be a problem with dates
    @Override
    public UUID saveProject(@NonNull Project project) throws SQLException, CommonFlkException {
        Flk10000007Validator.validate(project);
        UUID projectId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?, ?, ?, ?::jsonb, ?::jsonb, ?::jsonb, ?)");
        ps.setObject(1, projectId);
        ps.setString(2, project.getProjectName());
        ps.setObject(3, project.getLoadDate());
        ps.setObject(4, project.getLastChangeDate());
        ps.setObject(5, project.getStructure().toString());
        ps.setObject(6, project.getContent().toString());
        ps.setObject(7, project.getSettings().toString());
        ps.setString(8, project.getVersionNumber());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return projectId;
    }

    @Override
    public void updateProject(@NonNull Project project) throws SQLException, CommonFlkException {
        Flk10000000Validator.validate(project);
        Flk10000007Validator.validate(project);
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET project_name = ?, load_date = ?, last_change_date = ?, " +
                        "structure = ?::jsonb, content = ?::jsonb, settings = ?::jsonb, version_number = ? " +
                        "WHERE project_id = ?"
        );
        ps.setString(1, project.getProjectName());
        ps.setObject(2, project.getLoadDate());
        ps.setObject(3, project.getLastChangeDate());
        ps.setString(4, project.getStructure().toString());
        ps.setString(5, project.getContent().toString());
        ps.setString(6, project.getSettings().toString());
        ps.setString(7, project.getVersionNumber());
        ps.setObject(8, project.getProjectId());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateProjectNameById(@NonNull UUID projectId, @NonNull String projectName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET project_name = ? WHERE project_id = ?"
        );
        ps.setString(1, projectName);
        ps.setObject(2, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateProjectLastChangeDateById(@NonNull UUID projectId, @NonNull LocalDateTime lastChangeDate) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET last_change_date = ? WHERE project_id = ?"
        );
        ps.setObject(1, lastChangeDate);
        ps.setObject(2, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateProjectStructureById(@NonNull UUID projectId, @NonNull JSONObject structure) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " t SET structure = ? WHERE project_id = ?"
        );
        ps.setString(1, structure.toString());
        ps.setObject(2, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateProjectContentById(@NonNull UUID projectId, @NonNull JSONObject content) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET content = ? WHERE project_id = ?"
        );
        ps.setString(1, content.toString());
        ps.setObject(2, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateProjectSettingsById(@NonNull UUID projectId, @NonNull JSONObject settings) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET settings = ? WHERE project_id = ?"
        );
        ps.setString(1, settings.toString());
        ps.setObject(2, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateProjectVersionNumberById(@NonNull UUID projectId, @NonNull String versionNumber) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET version_number = ? WHERE project_id = ?"
        );
        ps.setString(1, versionNumber);
        ps.setObject(2, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteProjectById(@NonNull UUID projectId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " t WHERE t.project_id = ?"
        );
        ps.setObject(1, projectId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public Project getProjectById(@NonNull UUID projectId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                        "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                        "t WHERE t.project_id = ?"
        );
        ps.setObject(1, projectId);
        return getSingleProject(ps);
    }

    @Override
    public Project getProjectByName(@NonNull String projectName) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                        "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                        "t WHERE t.project_name = ?"
        );
        ps.setString(1, projectName);
        return getSingleProject(ps);
    }

    @Override
    public List<Project> getAllProjects(@NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Project.class.getName(),
                ProjectDAO.class.getName(),
                "getAllProjects",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                        "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                        "t"
        );
        return getProjects(ps);
    }

    @Override
    public List<Project> getProjectsByLoadDate(@NonNull LocalDateTime loadDate, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Project.class.getName(),
                ProjectDAO.class.getName(),
                "getProjectsByLoadDate",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                        "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                        "t WHERE t.load_date = ?"
        );
        ps.setObject(1, loadDate);
        return getProjects(ps);
    }

    @Override
    public List<Project> getProjectsByLoadDateDiapason(LocalDateTime loadDateDiapasonStart, LocalDateTime loadDateDiapasonEnd, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Project.class.getName(),
                ProjectDAO.class.getName(),
                "getProjectsByLoadDateDiapason",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = null;

        if (loadDateDiapasonStart != null && loadDateDiapasonEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t WHERE t.load_date <= ? AND t.load_date >= ?"
            );
            ps.setObject(1, loadDateDiapasonStart);
            ps.setObject(2, loadDateDiapasonEnd);
        }
        else if (loadDateDiapasonStart != null) {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t WHERE t.load_date >= ?"
            );
            ps.setObject(1, loadDateDiapasonStart);
        }
        else if (loadDateDiapasonEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t WHERE t.load_date <= ?"
            );
            ps.setObject(1, loadDateDiapasonEnd);
        }
        else {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t"
            );
        }

        return getProjects(ps);
    }

    @Override
    public List<Project> getProjectsByLastChangeDate(@NonNull LocalDateTime lastChangeDate, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Project.class.getName(),
                ProjectDAO.class.getName(),
                "getProjectsByLastChangeDate",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                        "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                        "t WHERE t.last_change_date = ?"
        );
        ps.setObject(1, lastChangeDate);
        return getProjects(ps);
    }

    @Override
    public List<Project> getProjectsByLastChangeDateDiapason(LocalDateTime lastChangeDateDiapasonStart, LocalDateTime lastChangeDateDiapasonEnd, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Project.class.getName(),
                ProjectDAO.class.getName(),
                "getProjectsByLastChangeDateDiapason",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = null;

        if (lastChangeDateDiapasonStart != null && lastChangeDateDiapasonEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t WHERE t.last_change_date <= ? AND t.last_change_date >= ?"
            );
            ps.setObject(1, lastChangeDateDiapasonStart);
            ps.setObject(2, lastChangeDateDiapasonEnd);
        }
        else if (lastChangeDateDiapasonStart != null) {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t WHERE t.last_change_date >= ?"
            );
            ps.setObject(1, lastChangeDateDiapasonStart);
        }
        else if (lastChangeDateDiapasonEnd != null) {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t WHERE t.last_change_date <= ?"
            );
            ps.setObject(1, lastChangeDateDiapasonEnd);
        }
        else {
            ps = super.prepareStatement(
                    "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                            "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                            "t"
            );
        }

        return getProjects(ps);
    }

    @Override
    public List<Project> getProjectsByVersionNumber(@NonNull String versionNumber, @NonNull SortDirection sortDirection, String sortBy) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                Project.class.getName(),
                ProjectDAO.class.getName(),
                "getProjectsByVersionNumber",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.project_id, t.project_name, t.load_date, t.last_change_date, t.structure, " +
                        "t.content, t.settings, t.version_number FROM " + super.getTableName() + " " +
                        "t WHERE t.version_number = ?"
        );
        ps.setString(1, versionNumber);
        return getProjects(ps);
    }

    /**
     * Внутренний метод для получения одного проекта по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект Project - проект (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private Project getSingleProject(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        Project project = null;
        if (rs.next()) {
            project = new Project(
                    rs.getObject(1, UUID.class),
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getTimestamp(4).toLocalDateTime(),
                    new JSONObject(rs.getString(5)),
                    new JSONObject(rs.getString(6)),
                    new JSONObject(rs.getString(7)),
                    rs.getString(8)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return project;
    }

    /**
     * Внутренний метод для получения списка проектов по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов Project (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<Project> getProjects(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<Project> projects = new ArrayList<>();
        while (rs.next()) {
            projects.add(new Project(
                    rs.getObject(1, UUID.class),
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getTimestamp(4).toLocalDateTime(),
                    new JSONObject(rs.getString(5)),
                    new JSONObject(rs.getString(6)),
                    new JSONObject(rs.getString(7)),
                    rs.getString(8)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return projects;
    }
}
