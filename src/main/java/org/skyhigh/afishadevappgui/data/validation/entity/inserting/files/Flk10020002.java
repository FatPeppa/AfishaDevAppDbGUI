package org.skyhigh.afishadevappgui.data.validation.entity.inserting.files;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.skyhigh.afishadevappgui.common.validation.CommonFlk;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;

import java.io.File;

@Slf4j
@AllArgsConstructor
public class Flk10020002 implements CommonFlk {
    private final String code = "10020002";
    private final String message = "Указанный путь к файлу некорректен, или файл по данному пути не существует";

    private String filePath;

    @Override
    public void validate() throws CommonFlkException {
        log.debug("Flk " + code + " started for file path: {}", filePath);
        if (filePath == null) {
            log.debug("Flk " + code + " for file path: {} finished with error", filePath);
            throw new CommonFlkException(
                    code,
                    message,
                    true
            );
        }
        File file = new File(filePath);
        if (!file.exists()
                || !file.isFile()
                || !file.canRead()
        ) {
            log.debug("Flk " + code + " for file path: {} finished with error", filePath);
            throw new CommonFlkException(
                    code,
                    message,
                    true
            );
        }

        log.debug("Flk " + code + " for file path: {} finished successfully", filePath);
    }
}
