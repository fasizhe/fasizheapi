package com.faishze.api.fasizheapi.manager.impl;

import com.faishze.api.fasizheapi.manager.WeChatManager;
import com.faishze.api.fasizheapi.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09
 */
@Component("weCharManager")
public class WeChatManagerImpl implements WeChatManager {
    @Override
    public Result login(String code) {
        return null;
    }
}
