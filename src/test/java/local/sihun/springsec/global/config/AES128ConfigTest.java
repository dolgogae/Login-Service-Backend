package local.sihun.springsec.global.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AES128ConfigTest {

    @Autowired
    AES128Config aes128Config;

    @Test
    @DisplayName("Aes128 암호화 테스트")
    void aes128Test(){
        String text = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSIsImlhdCI6MTY5NTI5MjA5NywiZXhwIjoxNjk1NTUxMjk3fQ.ATsRbF_qa94fHOVABPszkWckyPYhMJnAg1I7Ix_kUWlbnw1ZBQOUKsJt0V9lIxLX1CC-TvGi-aKHK_5SNwsFHw";
        String enc = aes128Config.encryptAes(text);
        String dec = aes128Config.decryptAes(enc);
        System.out.println("enc = " + enc);
        System.out.println("dec = " + dec);

        assertThat(dec).isEqualTo(text);
    }
}