package org.skyhigh.afishadevappgui.data.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeFile {
    private UUID codeFileId;
    private UUID projectId;
    private String fileContent;
    private LocalDateTime loadDate;
}
