package org.github.omnbmh.cobra;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringSecurityPasswordGenTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        System.out.println(encoder.encode("123456"));
    }
}
