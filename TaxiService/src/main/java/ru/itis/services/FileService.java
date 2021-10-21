package ru.itis.services;

import ru.itis.dto.CustomerDto;
import ru.itis.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    FileInfo saveFileToStorage(CustomerDto user, InputStream file, String originalFileName, String contentType, Long size);
    void readFileFromStorage(Long fileId, OutputStream outputStream);
    FileInfo getFileInfo(Long fileId);
}
