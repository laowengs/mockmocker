package com.laowengs.mocker;

import com.laowengs.mocker.mapper.MockInterfaceDao;
import com.laowengs.mocker.po.MockInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/interface")
@RestController
public class MockerController {
    private MockInterfaceDao mockInterfaceDao;

    @Autowired
    public MockerController(MockInterfaceDao mockInterfaceDao) {
        this.mockInterfaceDao = mockInterfaceDao;
    }

    @GetMapping
    public List<MockInterface> list(String urlPath) {
        List<MockInterface> mockInterfaces = mockInterfaceDao.selectByPath(urlPath);
        return mockInterfaces;
    }

    @GetMapping("/{interfaceId}")
    public MockInterface select(@PathVariable("interfaceId") Long interfaceId) {
        return mockInterfaceDao.selectByPrimaryKey(interfaceId);
    }

    @PutMapping
    public MockInterface insert(@RequestBody MockInterface mockInterface) {
        Long interfaceId = mockInterfaceDao.insert(mockInterface);
        return mockInterface;
    }


    @PatchMapping
    public MockInterface update(@RequestBody  MockInterface mockInterface) {
        int number = mockInterfaceDao.updateByPrimaryKeySelective(mockInterface);
        return mockInterface;
    }

    @DeleteMapping("/{interfaceId}")
    public Integer delete(@PathVariable("interfaceId") Long interfaceId) {
        return mockInterfaceDao.deleteByPrimaryKey(interfaceId);
    }
}
