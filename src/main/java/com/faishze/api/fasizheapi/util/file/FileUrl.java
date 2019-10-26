package com.faishze.api.fasizheapi.util.file;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-08-11 15:56
 */
public class FileUrl {

    private String host;
    private String directoryPath;
    private String fileName;

    public FileUrl() {
    }

    public FileUrl(String host, String directoryPath, String fileName) {
        this.host = host;
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileUrl{" +
                "host='" + host + '\'' +
                ", directoryPath='" + directoryPath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
