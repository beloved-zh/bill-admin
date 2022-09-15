package com.beloved;

import com.beloved.common.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Slf4j
@SpringBootTest(classes = BillApplicationTests.class)
class BillApplicationTests {

    @Test
    void utils() {
        System.out.println(ObjectUtils.isEmpty(0L));
    }
    
    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        System.out.println(password);

        System.out.println(new Pbkdf2PasswordEncoder().encode("123456"));
    }
    
    
}
