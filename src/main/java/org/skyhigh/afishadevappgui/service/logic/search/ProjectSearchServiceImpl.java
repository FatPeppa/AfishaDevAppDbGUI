package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.service.ServiceUtils;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Project;
import org.skyhigh.afishadevappgui.data.repository.ProjectRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProjectSearchServiceImpl implements ProjectSearchService {
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> searchProject(
            UUID projectId,
            String projectName,
            String versionNumber,
            LocalDateTime loadDateDiapasonStart,
            LocalDateTime loadDateDiapasonEnd,
            LocalDateTime lastChangeDateDiapasonStart,
            LocalDateTime lastChangeDateDiapasonEnd
    ) throws CommonFlkException {
        List<Object> args = new ArrayList<>();
        args.add(projectId);
        args.add(projectName);
        args.add(versionNumber);
        args.add(loadDateDiapasonStart);
        args.add(loadDateDiapasonEnd);
        args.add(lastChangeDateDiapasonStart);
        args.add(lastChangeDateDiapasonEnd);

        if (ServiceUtils.countNotNull(args) > 2)
            return null;
        if (projectId != null)
            return List.of(projectRepository.getProjectById(projectId));
        if (projectName != null && !projectName.isEmpty()) {
            ArrayList<Project> projects = new ArrayList<>();
            projects.add(projectRepository.getProjectByName(projectName));
            return projects;
        }
        if ((loadDateDiapasonStart != null || loadDateDiapasonEnd != null) && (lastChangeDateDiapasonEnd != null || lastChangeDateDiapasonStart != null))
            return null;
        if (loadDateDiapasonStart != null || loadDateDiapasonEnd != null)
            return projectRepository.getProjectsByLoadDateDiapason(
                    loadDateDiapasonStart,
                    loadDateDiapasonEnd,
                    SortDirection.NONE,
                    null
            );
        if (lastChangeDateDiapasonStart != null || lastChangeDateDiapasonEnd != null)
            return projectRepository.getProjectsByLastChangeDateDiapason(
                    lastChangeDateDiapasonStart,
                    lastChangeDateDiapasonEnd,
                    SortDirection.NONE,
                    null
            );
        if (versionNumber != null && !versionNumber.isEmpty())
            return projectRepository.getProjectsByVersionNumber(
                    versionNumber,
                    SortDirection.NONE,
                    null
            );
        return projectRepository.getAllProjects(
                SortDirection.NONE,
                null
        );
    }
}
