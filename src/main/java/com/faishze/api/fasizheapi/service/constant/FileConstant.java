package com.faishze.api.fasizheapi.service.constant;

import com.faishze.api.fasizheapi.util.PropertiesUtils;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-26
 */
public final class FileConstant {

    /**
     * 缓存文件的文件夹
     */
    public final static String BUF_PATH;
    static {
        BUF_PATH = PropertiesUtils.getProperty("ftp.buf.path", "application.properties");
    }

    /**
     * ftp文件的前缀
     */
    public final static String HOST;
    static {
        HOST = PropertiesUtils.getProperty("ftp.prefix.host", "application.properties");
    }

}
