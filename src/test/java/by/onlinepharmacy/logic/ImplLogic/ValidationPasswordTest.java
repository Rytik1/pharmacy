package by.onlinepharmacy.logic.ImplLogic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class ValidationPasswordTest {
    private String password;
    private boolean expectedResult;

    public ValidationPasswordTest(String password, boolean expectedResult) {
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> date(){
        return Arrays.asList(new Object[][]{
                {"Qabcd12345", true},
                {"OLAKSKD1lf4949bnd", true},
                {null, false},
                {"" , false},
                {"abcd", false},
                {"abcd12345", false},
                {"duduKLM_123ob", false}
        });

    }
    @Test
    public void isPasswordValid() throws Exception {
        AuthenticationLogic authenticationLogic=new AuthenticationLogic();
        boolean actual=authenticationLogic.isPasswordValid(password);
        assertEquals("Result for string = " + password + " is wrong: ", expectedResult, actual);

    }

    }

