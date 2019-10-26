package com.faishze.api.fasizheapi.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: ftp模板类
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-06-07 20:46
 */
public class FTPClientTemplate {

    private static final Logger logger = LoggerFactory.getLogger(FTPClientTemplate.class);

    private FTPClientConfig ftpClientConfig;

    private String host;

    private String username;

    private String password;

    private int port = 21;

    public FTPClientTemplate(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    /**
     * 顶层模板方法
     * @param callback FTPClientCallback
     * @throws IOException .
     */
    public void execute(FTPClientCallback callback) throws IOException {
        FTPClient ftp = new FTPClient();
        try {
            if (this.getFtpClientConfig() != null) {
                ftp.configure(this.getFtpClientConfig());
            }

            ftp.connect(host, getPort());
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new IOException("failed to connect to the FTP Server:" + host);
            }

            boolean isLoginSuc = ftp.login(this.getUsername(), this.getPassword());
            if (!isLoginSuc) {
                throw new IOException("wrong username or password, please try to login again.");
            }
            callback.processFTPRequest(ftp);
        } finally {
            if (ftp.isConnected()) {
                ftp.disconnect();
            }
        }
    }

    /**
     * 上传文件
     * @param directoryPath 目录
     * @param file 文件
     * @return 是否成功
     * @throws IOException .
     */
    public boolean uploadFile(final String directoryPath, final File file) throws IOException {
        final boolean[] success = {false};
        execute((ftp) -> {
            changeWorkingDirectory(ftp, directoryPath);
            ftp.enterLocalPassiveMode();
            ftp.setBufferSize(1024);
            ftp.setControlEncoding("UTF-8");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            try (FileInputStream fis = new FileInputStream(file)){
                success[0] = ftp.storeFile(file.getName(), fis);
            }
        });
        return success[0];
    }

    /**
     * 删除文件
     * @param directoryPath 目录
     * @param fileName 文件名
     * @return 是否成功
     * @throws IOException .
     */
    public boolean deleteFile(final String directoryPath, final String fileName) throws IOException {
        final boolean[] success = {false};
        execute((ftp) -> {
            ftp.enterLocalPassiveMode();
            changeWorkingDirectory(ftp, directoryPath);
            success[0] = ftp.deleteFile(fileName);
        });
        return success[0];
    }

    /**
     * 修改文件名称
     * @param directoryPath 目录
     * @param from 原文件名
     * @param to 新文件名
     * @throws IOException .
     */
    public boolean rename(final String directoryPath, String from, String to) throws IOException {
        final boolean[] success = {false};
        execute((ftp) -> {
            ftp.enterLocalPassiveMode();
            changeWorkingDirectory(ftp, directoryPath);
            success[0] = ftp.rename(from, to);
        });
        return success[0];
    }

    /**
     * 列出所有文件的名字
     * @param directoryPath 目录
     * @param fileNamePattern 文件名匹配模式
     * @return 文件名列表
     * @throws IOException .
     */
    public String[] listFileNames(final String directoryPath, final String fileNamePattern) throws IOException {
        final List<String[]> namesList = new ArrayList<>();
        execute((ftp) -> {
            ftp.enterLocalPassiveMode();
            changeWorkingDirectory(ftp, directoryPath);
            namesList.add(ftp.listNames());
        });
        if (namesList.size() > 0) {
            return namesList.get(0);
        }
        return null;
    }

    /**
     * 改变工作目录
     * @param ftp FTPClient
     * @param directoryPath 目录名
     * @throws IOException .
     */
    protected void changeWorkingDirectory(FTPClient ftp, String directoryPath) throws IOException {
        ftp.changeWorkingDirectory(directoryPath);
        if (logger.isDebugEnabled()) {
            logger.debug("working directory:" + ftp.printWorkingDirectory());
        }
    }

    public FTPClientConfig getFtpClientConfig() {
        return ftpClientConfig;
    }

    public void setFtpClientConfig(FTPClientConfig ftpClientConfig) {
        this.ftpClientConfig = ftpClientConfig;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
