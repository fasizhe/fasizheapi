package com.faishze.api.fasizheapi.service;

import org.apache.tika.mime.MimeTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    boolean save(MultipartFile file, String fileName, String directoryPath);

    boolean save(File file, String directoryPath);

    boolean saveAndDeleteFile(File file, String directoryPath);

    String saveAndGetUrl(File file, String directoryPath, boolean deleteFile);

    String save(MultipartFile file, String directoryPath);

    String saveAndGetUrl(MultipartFile file, String directoryPath);

    void delete(String fileName, String directoryPath);

    void delete(String fileUrl);

    String updateFile(MultipartFile file, String oldUrl, String directoryPath);

    String saveToBuffer(InputStream inputStream, String fileName) throws IOException;

    String saveToBuffer(String url) throws IOException, MimeTypeException;
}
