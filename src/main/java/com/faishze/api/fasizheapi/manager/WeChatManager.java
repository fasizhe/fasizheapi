package com.faishze.api.fasizheapi.manager;

import com.faishze.api.fasizheapi.manager.impl.WeChatManagerImpl;
import com.faishze.api.fasizheapi.result.Result;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09 15:07
 */
public interface WeChatManager {
    Result login(String code);
}
