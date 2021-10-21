package ru.itis.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileInfo {

    private Long id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}
