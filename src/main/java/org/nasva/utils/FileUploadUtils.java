package org.nasva.utils;

import java.io.IOException;
import java.nio.file.Files;

public class FileUploadUtils {
    public String getFileContent(String fileName) throws IOException {
        return new String(FileUploadUtils.class.getClassLoader().getResourceAsStream(fileName).readAllBytes());
    }
}
