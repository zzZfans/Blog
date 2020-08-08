package com.zfans;

import com.zfans.dao.TypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private TypeRepository typeRepository;

    @Test
    void Test() {
        System.out.println(typeRepository.findAll());
    }

}
