package com.changyue.oauth;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author Linmo
 * @create 2020/4/13 16:01
 */
public class KeyStoreDemo {
    private KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("changyue.jks"), "changyue".toCharArray()).getKeyPair("changyue");

    @Test
    public void buildKey() {

        System.out.println(keyPair.getPublic().getFormat());
        Jwt sjh = JwtHelper.encode("孙俊华", new RsaSigner((RSAPrivateKey) keyPair.getPrivate()));
        System.out.println(sjh.getEncoded());
    }

    @Test
    public void parseKey() throws UnsupportedEncodingException {
        Jwt decode = JwtHelper.decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.5a2Z5L-K5Y2O.SCiF_WSptWF_cDROrehiPw5AO_jQn3FVL_Paj0PyWmfr62sJtIHJLUk6lRexawBBt9gryXyQBVCXW1WBpUix8lFt2tVPOvZCdU8ip1AIUTqtJq8ToqXedL0n42EVYjrONg2wK5hdyP8UIa7aQLbanYZnd8DavyMbq5KFT9-jzecBG9YtrKIlgkDOzMyOwEMJeBbJoNvclyPO6W3uJ8kXIchqbRzK1B8LCp5fkOvOqt1qWXX9OaIm09lcvI-4M0weSzGxX_LUzTmdgENVGo_1H_YtZfvInP0eyDxDVGxj4g6Zeb2Tg1YtWj0N7EM9DrulUOhnwQFq6Yu6GibNskSUGA");
        decode.verifySignature(new RsaVerifier((RSAPublicKey) keyPair.getPublic()));
        System.out.println(keyPair.getPublic().getFormat());
    }
}
