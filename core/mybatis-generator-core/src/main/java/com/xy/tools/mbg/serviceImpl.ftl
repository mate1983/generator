package com.humpback.service.wlx.service;

import com.humpback.service.wlx.model.UserDeviceHistory;

@Service
public interface UserDeviceHistoryService implements UserDeviceHistoryService{
    @Autowired
    private  UserDeviceHistoryMapper userDeviceHistoryMapper;

    @Override
    public int deleteByPrimaryKey(Long id){
        return userDeviceHistoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserDeviceHistory userDeviceHistory){
        return userDeviceHistoryMapper.insert(userDeviceHistory);
    }
    @Override
    public int insertSelective(UserDeviceHistory userDeviceHistory){
        return userDeviceHistoryMapper.insertSelective(userDeviceHistory);
    }

    @Override
    public UserDeviceHistory selectByPrimaryKey(Long id){
         return userDeviceHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserDeviceHistory userDeviceHistory){
         return updateByPrimaryKeySelective.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UserDeviceHistory userDeviceHistory){
        return updateByPrimaryKeySelective.updateByPrimaryKey(userDeviceHistory);
    }
}