package com.laowengs.mocker.mapper;

import com.laowengs.mocker.po.MockLogHis;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class MockInterfaceHisMapperTest {

    //https://juejin.cn/post/6961721367846715428
    @Autowired
    private MockLogHisMapper mockLogHisMapper;


    @Test
    void name() {
        List<MockLogHis> mockLogHis = mockLogHisMapper.selectList(null);
        mockLogHis.forEach(System.out::println);
    }
}