package com.laowengs.mocker.mapper;

import com.laowengs.mocker.po.MockInterface;

import java.util.List;

public interface MockInterfaceDao {
    int deleteByPrimaryKey(Long interfaceId);

    Long insert(MockInterface record);

    int insertSelective(MockInterface record);

    MockInterface selectByPrimaryKey(Long interfaceId);

    int updateByPrimaryKeySelective(MockInterface record);

    int updateByPrimaryKey(MockInterface record);

    List<MockInterface> selectByMockInterface(MockInterface mockInterface);

    List<MockInterface> selectByPath(String urlPath);
}