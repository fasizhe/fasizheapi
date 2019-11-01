package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.exception.ProcessingException;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.service.FileService;
import com.faishze.api.fasizheapi.service.constant.FileConstant;
import com.faishze.api.fasizheapi.util.file.FileNameUtils;
import com.faishze.api.fasizheapi.util.file.FileUrl;
import com.faishze.api.fasizheapi.util.ftp.FTPClientTemplate;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FTPClientTemplate ftpClientTemplate;

    @Value("${ftp.buf.path}")
    private String bufferPath;

    /**
     * 上传文件到ftp服务器
     *
     * @param file          文件
     * @param fileName      文件名
     * @param directoryPath 文件目录路径
     * @return boolean 是否上传成功
     */
    @Override
    public boolean save(MultipartFile file, String fileName, String directoryPath) {
        //将文件缓存到本地文件夹
        File bufFile = bufferFileOnLocal(file, fileName);
        return save(bufFile, directoryPath);
    }

    @Override
    public boolean save(File file, String directoryPath) {
        //上传是否成功
        boolean success;
        try {
            //将file上传到ftp服务器
            success = ftpClientTemplate.uploadFile(directoryPath, file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ProcessingException(ErrorCode.INTERNAL_ERROR, "Upload file failed.");
        }
        //上传成功
        return success;
    }


    @Override
    public boolean saveAndDeleteFile(File file, String directoryPath) {
        //上传是否成功
        boolean success = save(file, directoryPath);
        //删除本地缓存文件
        deleteLocalFile(file);
        //上传成功
        return success;
    }

    @Override
    public String saveAndGetUrl(File file, String directoryPath, boolean deleteFile) {
        boolean success;
        if (deleteFile) {
            success = saveAndDeleteFile(file, directoryPath);
        } else {
            success = save(file, directoryPath);
        }
        if (success) {
            return FileConstant.HOST + File.separator + directoryPath + File.separator + file.getName();
        } else {
            throw new ProcessingException(ErrorCode.INTERNAL_ERROR, "Upload file failed.");
        }
    }

    /**
     * 上传文件，会随机生成文件名
     *
     * @param file          文件
     * @param directoryPath 文件目录路径
     * @return 文件名
     */
    @Override
    public String save(MultipartFile file, String directoryPath) {
        //随机生成文件名
        String fileName = FileNameUtils.getRandomFileNameByOriginalFileName(file.getOriginalFilename());
        //上传文件
        boolean success = save(file, fileName, directoryPath);
        //上传失败
        if (!success) {
            throw new ProcessingException(ErrorCode.INTERNAL_ERROR, "Upload file failed.");
        }
        return fileName;
    }

    /**
     * 上传文件，并获得文件的Url
     *
     * @param file          文件
     * @param directoryPath 文件目录路径
     * @return 文件名
     */
    @Override
    public String saveAndGetUrl(MultipartFile file, String directoryPath) {
        String fileName = save(file, directoryPath);
        //文件url
        return FileConstant.HOST + directoryPath + fileName;
    }

    /**
     * 删除文件，通过文件目录路径和文件名
     *
     * @param fileName      文件名
     * @param directoryPath 文件目录路径
     */
    @Override
    public void delete(String fileName, String directoryPath) {
        try {
            ftpClientTemplate.deleteFile(directoryPath, fileName);
        } catch (IOException e) {
            throw new ProcessingException(ErrorCode.INTERNAL_ERROR, "Delete file failed.");
        }
    }

    /**
     * 删除文件，通过文件url
     *
     * @param fileUrl 文件url
     */
    @Override
    public void delete(String fileUrl) {
        //解析url
        FileUrl remoteDirAndFileName = FileNameUtils.parseFileUrl(fileUrl);
        //删除文件
        delete(remoteDirAndFileName.getFileName(), remoteDirAndFileName.getDirectoryPath());
    }

    /**
     * 更新文件，需要MultipartFile和原文件Url
     *
     * @param file          文件
     * @param oldUrl        原文件Url
     * @param directoryPath 文件目录路径
     * @return 新url
     */
    public String updateFile(MultipartFile file, String oldUrl, String directoryPath) {
        //上传文件
        String newUrl = saveAndGetUrl(file, directoryPath);
        //删除旧文件
        if (oldUrl != null) {
            delete(oldUrl);
        }
        return newUrl;
    }

    /**
     * 将输入流保存在缓冲中
     *
     * @param inputStream 输入流
     * @param fileName    文件名
     * @return 保存的路径
     */
    @Override
    public String saveToBuffer(InputStream inputStream, String fileName) throws IOException {
        String filePath = bufferPath + File.separator + fileName;
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        int bufferSize = 1024 * 1024;
        int readCount = 0;
        byte[] buffer = new byte[bufferSize];
        while ((readCount = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, readCount);
        }
        fileOutputStream.close();
        return filePath;
    }

    /**
     * 从一个url中获取文件，保存在缓冲区中
     * @param urlPath url
     * @return 保存的路径
     * @throws IOException 打开URL失败
     * @throws MimeTypeException 获取不到MIME TYPE
     */
    @Override
    public String saveToBuffer(String urlPath) throws IOException, MimeTypeException {
        URL url = new URL(urlPath);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        // 获取文件后缀
        String contentType = connection.getContentType();
        MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
        MimeType type = allTypes.forName(contentType);
        String fileName = UUID.randomUUID().toString() + type.getExtension();
        // 获得文件保存的路径
        String filePath = saveToBuffer(inputStream, fileName);
        if(inputStream != null){
            inputStream.close();
        }
        return filePath;
    }

    /**
     * 删除保存在本地的文件
     *
     * @param file File
     */
    private void deleteLocalFile(File file) {
        //删除本地文件夹里面的file
        boolean delSuccess = file.delete();
        //删除失败
        if (!delSuccess) {
            throw new ProcessingException(ErrorCode.INTERNAL_ERROR, "Delete file failed.");
        }
    }

    /**
     * 将文件缓存到本地
     *
     * @param file     MultipartFile
     * @param fileName String
     * @return File
     */
    private File bufferFileOnLocal(MultipartFile file, String fileName) {
        //将文件缓存到本地文件夹
        File bufFile = new File(FileConstant.BUF_PATH, fileName);
        try {
            //保存文件
            file.transferTo(bufFile);
        } catch (IOException e) {
            throw new ProcessingException(ErrorCode.INTERNAL_ERROR, "Upload file failed.");
        }
        return bufFile;
    }
}
