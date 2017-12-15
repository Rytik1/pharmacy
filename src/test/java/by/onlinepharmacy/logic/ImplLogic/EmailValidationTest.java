package by.onlinepharmacy.logic.ImplLogic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class EmailValidationTest {
    private String email;
    private boolean expectedResult;

    public EmailValidationTest(String email, boolean expectedResult) {
        this.email = email;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> date(){
        return Arrays.asList(new Object[][]{
                {"Rytik_andrey@mail.ru", true},
                {"Rytik_andrey@", false},
                {null, false},
                {"" , false},
                {"abcd@", false},
                {"abcd12345@mail", false},
                {"duduKLM_123ob@gmail.ru", true}
        });

    }
    @Test
    public void isEmailValid() throws Exception {
        AuthenticationLogic authenticationLogic=new AuthenticationLogic();
        boolean actual=authenticationLogic.isEmailValid(email);
        assertEquals("Result for string = " + email + " is wrong: ", expectedResult, actual);

    }

    }

