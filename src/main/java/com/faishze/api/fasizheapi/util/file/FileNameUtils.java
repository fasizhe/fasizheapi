package com.faishze.api.fasizheapi.util.file;

import java.util.UUID;

/**
 * 描述: 对文件名的处理类
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-08-11 14:09
 */
public final class FileNameUtils {

    private FileNameUtils() {}

    /**
     * 获取文件的扩展名
     * @param fileName String
     * @return fileExtensionName
     */
    public static String getFileExtensionName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 随机生成一个文件名，可指定扩展名
     *
     * @param extensionName String
     * @return randomFileName
     */
    public static String getRandomFileName(String extensionName) {
        String randomName = UUID.randomUUID().toString();
        return randomName + "." + extensionName;
    }

    /**
     * 随机生成一个文件名，从originalFileName获取扩展名
     *
     * @param originalFileName 原文件名
     * @return randomFileName
     */
    public static String getRandomFileNameByOriginalFileName(String originalFileName) {
        String extensionName = getFileExtensionName(originalFileName);
        return getRandomFileName(extensionName);
    }

    /**
     * 从文件的url获取文件名
     *
     * @param url 地址
     * @return fileName
     */
    public static String getFileNameByUrl(String url) {
        return url.substring(getFileNameIndexByUrl(url));
    }

    /**
     * 从文件的url获取文件的目录路径
     *
     * @param url 地址
     * @return 目录路径
     */
    public static String getDirectoryPathByUrl(String url) {
        int hostIndex = getHostLength(url);
        int fileNameIndex = getFileNameIndexByUrl(url);
        return url.substring(hostIndex, fileNameIndex);
    }

    /**
     * 获取host
     *
     * @param url 地址
     * @return host
     */
    public static String getHost(String url) {
        return url.substring(0, getHostLength(url));
    }

    /**
     * 对文件url进行解析
     * 成为FileUrl类，包含host，directoryPath，fileName
     *
     * @param url 文件地址
     * @return FileUrl
     */
    public static FileUrl parseFileUrl(String url) {
        int hostIndex = getHostLength(url);
        int fileNameIndex = getFileNameIndexByUrl(url);
        String host = url.substring(0, hostIndex);
        String directoryPath = url.substring(hostIndex, fileNameIndex);
        String fileName = url.substring(fileNameIndex);
        return new FileUrl(host, directoryPath, fileName);
    }

    /**
     * 获取url的host的长度
     *
     * @param url 地址
     * @return host的长度
     */
    private static int getHostLength(String url) {
        return url.indexOf("/", url.indexOf("/") + 2);
    }

    /**
     * 获取文件名的下标，通过url
     *
     * @param url 地址
     * @return 下标
     */
    private static int getFileNameIndexByUrl(String url) {
        return url.lastIndexOf("/") + 1;
    }

}
