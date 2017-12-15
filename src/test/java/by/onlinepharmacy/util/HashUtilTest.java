package by.onlinepharmacy.util;

import by.onlinepharmacy.util.hash.HashUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HashUtilTest {
    private String password;
    private String passwordMd5;

    public HashUtilTest(String password, String passwordMd5) {
        this.password = password;
        this.passwordMd5 = passwordMd5;
    }

@Parameterized.Parameters
public static Collection<Object[]> date(){
        return Arrays.asList(new Object[][]{
            {"" , "d41d8cd98f00b204e9800998ecf8427e"},
            {"abc", "900150983cd24fb0d6963f7d28e17f72"},
            {"rytik", "762afff6c94603660bd9c474ac0cd2e5"},
            {"ewdwdwdw",
                    "173a639bfe71d3b645f41a458a50183a"}


        });

    }


    @Test
    public void computeHash() throws Exception {
        String actual = HashUtil.computeHash(password);
        assertEquals("Md5 hash for string = " + password + " is wrong: ", passwordMd5, actual);

    }



}
