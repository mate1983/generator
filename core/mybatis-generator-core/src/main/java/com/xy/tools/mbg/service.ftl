package com.humpback.service.wlx.service;

import com.humpback.service.wlx.model.UserDeviceHistory;

public interface UserDeviceHistoryService {
    int deleteByPrimaryKey(Long id);

    int insert(UserDeviceHistory userDeviceHistory);

    int insertSelective(UserDeviceHistory userDeviceHistory);

    UserDeviceHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDeviceHistory userDeviceHistory);

    int updateByPrimaryKey(UserDeviceHistory userDeviceHistory);
}