package org.skyhigh.afishadevappgui.data.datasource.dao;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.skyhigh.afishadevappgui.common.db.BaseTable;
import org.skyhigh.afishadevappgui.common.db.DbConnector;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.CodeFile;
import org.skyhigh.afishadevappgui.data.validation.args.Flk10010000Validator;
import org.skyhigh.afishadevappgui.data.validation.entity.inserting.fields_not_null.Flk10000004Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class CodeFileDAOImpl extends BaseTable implements CodeFileDAO {
    /**
     * Конструктор для DAO с использованием имени таблицы по умолчанию ("code_file")
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public CodeFileDAOImpl(@NonNull final DbConnector dbConnector) throws SQLException {
        super("code_file", dbConnector);
    }

    /**
     * Конструктор для DAO с использованием передаваемого имени таблицы
     * @param tableName Имя таблицы
     * @param dbConnector Объект-коннектор к БД
     * @throws SQLException Ошибка при работе с БД
     */
    public CodeFileDAOImpl(String tableName, DbConnector dbConnector) throws SQLException {
        super(tableName, dbConnector);
    }

    @Override
    public UUID saveCodeFile(@NonNull CodeFile codeFile) throws SQLException, CommonFlkException {
        Flk10000004Validator.validate(codeFile);
        UUID codeFileId = UUID.randomUUID();
        PreparedStatement ps = super.prepareStatement("INSERT INTO " + super.getTableName() + " VALUES (?, ?, ?)");
        ps.setObject(1, codeFileId);
        ps.setObject(2, codeFile.getProjectId());
        ps.setString(3, codeFile.getFileContent());
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows inserted into " + super.getTableName());
        return codeFileId;
    }

    @Override
    public void updateCodeFileContentById(@NonNull UUID codeFileId, @NonNull String content) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET file_content = ?1 WHERE code_file_id = ?2"
        );
        ps.setString(1, content);
        ps.setObject(2, codeFileId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void updateCodeFileProjectId(@NonNull UUID codeFileId, @NonNull UUID projectId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "UPDATE " + super.getTableName() + " SET project_id = ?1 WHERE code_file_id = ?2"
        );
        ps.setObject(1, projectId);
        ps.setObject(2, codeFileId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows updated in " + super.getTableName());
    }

    @Override
    public void deleteCodeFileById(@NonNull UUID codeFileId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "DELETE FROM " + super.getTableName() + " WHERE code_file_id = ?1"
        );
        ps.setObject(1, codeFileId);
        int stRes = super.executeSqlStatementUpdate(ps);
        log.log(Level.INFO, "database logging: " + stRes + " rows deleted from " + super.getTableName());
    }

    @Override
    public CodeFile getCodeFileById(@NonNull UUID codeFileId) throws SQLException {
        PreparedStatement ps = super.prepareStatement(
                "SELECT t.code_file_id code_file_id, t.project_id project_id " +
                        "t.file_content file_content FROM " + super.getTableName() + " t WHERE t.code_file_id = ?1"
        );
        ps.setObject(1, codeFileId);
        return getSingleCodeFile(ps);
    }

    @Override
    public List<CodeFile> getAllCodeFilesByProjectId(
            @NonNull UUID projectId,
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                CodeFile.class.getName(),
                CodeFileDAO.class.getName(),
                "getAllCodeFilesByProjectId",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.code_file_id code_file_id, t.project_id project_id " +
                        "t.file_content file_content FROM " + super.getTableName() + " t WHERE t.project_id = ?1",
                sortDirection,
                sortBy
        );
        ps.setObject(1, projectId);
        return getCodeFiles(ps);
    }

    @Override
    public List<CodeFile> getAllCodeFiles(
            @NonNull SortDirection sortDirection,
            String sortBy
    ) throws SQLException, CommonFlkException {
        Flk10010000Validator.validate(
                CodeFile.class.getName(),
                CodeFileDAO.class.getName(),
                "getAllCodeFiles",
                sortDirection,
                sortBy
        );
        PreparedStatement ps = super.prepareReadStatement(
                "SELECT t.code_file_id code_file_id, t.project_id project_id " +
                        "t.file_content file_content FROM " + super.getTableName() + " t",
                sortDirection,
                sortBy
        );
        return getCodeFiles(ps);
    }

    /**
     * Внутренний метод для получения одного файла с кодом по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Объект CodeFile - файл с кодом (первый из массива, если получен массив), или null, если данные не были найдены
     * @throws SQLException Ошибка при работе с БД
     */
    private CodeFile getSingleCodeFile(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        CodeFile codeFile = null;
        if (rs.next()) {
            codeFile = new CodeFile(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class),
                    rs.getString(3)
            );
        }

        rs.close();
        ps.close();
        super.close();
        return codeFile;
    }

    /**
     * Внутренний метод для получения списка файлов с кодом по SQL-выражению
     * @param ps Подготовленное к выполнению SQL-выражение
     * @return Список найденных по ps объектов CodeFile (если данные не были найдены, то список пуст)
     * @throws SQLException Ошибка при работе с БД
     */
    private List<CodeFile> getCodeFiles(@NonNull PreparedStatement ps) throws SQLException {
        ResultSet rs = super.executeSqlStatementRead(ps);
        List<CodeFile> codeFiles = new ArrayList<>();
        while (rs.next()) {
            codeFiles.add(new CodeFile(
                    rs.getObject(1, UUID.class),
                    rs.getObject(2, UUID.class),
                    rs.getString(3)
            ));
        }

        rs.close();
        ps.close();
        super.close();
        return codeFiles;
    }
}
