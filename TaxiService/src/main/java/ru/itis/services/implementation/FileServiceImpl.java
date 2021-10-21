package ru.itis.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.CustomerRepository;
import ru.itis.dao.FilesRepository;
import ru.itis.dto.CustomerDto;
import ru.itis.exceptions.FileSizeException;
import ru.itis.exceptions.NotFoundException;
import ru.itis.models.FileInfo;
import ru.itis.services.FileService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    String path = "C:/Users/incor/Pictures/";

    FilesRepository filesRepository;
    CustomerRepository customerRepository;


    @Autowired
    public FileServiceImpl(FilesRepository filesRepository, CustomerRepository customerRepository) {
        this.filesRepository = filesRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public FileInfo saveFileToStorage(CustomerDto customer, InputStream inputStream, String originalFileName, String contentType, Long size) {
            if(size > 10000000) {
                throw new FileSizeException("File is too large");
            }
            FileInfo fileInfo = new FileInfo(
                    null,
                    originalFileName,
                    UUID.randomUUID().toString(),
                    size,
                    contentType
            );
        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = filesRepository.save(fileInfo);
            customerRepository.putAvatar(customer.getId(), fileInfo.getId());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return fileInfo;
    }

    @Override
    public void readFileFromStorage(Long fileId, OutputStream outputStream) {
        FileInfo fileInfo = filesRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException("File not found"));

        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return filesRepository.findById(fileId).orElseThrow(() -> new NotFoundException("File not found"));
    }
}
