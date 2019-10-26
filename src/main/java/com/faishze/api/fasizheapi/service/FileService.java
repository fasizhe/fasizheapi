package com.faishze.api.fasizheapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean save(MultipartFile file, String fileName, String directoryPath);

    String save(MultipartFile file, String directoryPath);

    String saveAndGetUrl(MultipartFile file, String directoryPath);

    void delete(String fileName, String directoryPath);

    void delete(String fileUrl);

    String updateFile(MultipartFile file, String oldUrl, String directoryPath);
}
