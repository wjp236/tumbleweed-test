package com.base.idwork.impl;

import com.base.idwork.IdService;

import java.util.UUID;

/**
 * 描述: uuid
 *
 * @author: mylover
 * @Time: 26/06/2017.
 */
public class UUIDServiceImpl implements IdService {

    @Override
    public String orderId() {
        return UUID.randomUUID().toString();
    }

}
