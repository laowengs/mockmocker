package com.laowengs.mocker.env;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

//@Component
public class TestConfig {
    @Value("${wengjp}")
    private Integer id;

    @Value("${wengjp2}")
    private Integer id2;

    @Value("${wengjp3:22}")
    private Integer id3;

    @Value("${wengjp3:22,23,24}")
    private List<Integer> id4;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public Integer getId3() {
        return id3;
    }

    public void setId3(Integer id3) {
        this.id3 = id3;
    }

    public List<Integer> getId4() {
        return id4;
    }

    public void setId4(List<Integer> id4) {
        this.id4 = id4;
    }

    @Override
    public String toString() {
        return "TestConfig{" +
                "id=" + id +
                ", id2=" + id2 +
                ", id3=" + id3 +
                ", id4=" + id4 +
                '}';
    }
}
