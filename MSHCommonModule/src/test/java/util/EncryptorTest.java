package util;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Kray on 2016/12/21.
 */
public class EncryptorTest {

    @Test
    public void testEncryptor(){
        String s = "123456";
        assertEquals("e10adc3949ba59abbe56e057f20f883e", Encryptor.encrypt(s));
    }
}
