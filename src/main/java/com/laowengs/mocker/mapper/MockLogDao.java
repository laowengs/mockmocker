package com.laowengs.mocker.mapper;

import com.laowengs.mocker.po.MockLog;

import java.util.List;

public interface MockLogDao {
    int deleteByPrimaryKey(Long logId);

    int insert(MockLog record);

    int insertSelective(MockLog record);

    MockLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(MockLog record);

    int updateByPrimaryKey(MockLog record);

    List<MockLog> listByInterfaceId(Long interfaceId);
}