package by.onlinepharmacy.logic.ImplLogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PriceValidatorTest {

        private String price;
        private boolean expectedResult;

    public PriceValidatorTest(String price, boolean expectedResult) {
            this.price = price;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> date(){
            return Arrays.asList(new Object[][]{
                    {"2.22", true},
                    {"2.2", false},
                    {null, false},
                    {"2.2" , false},
                    {"2.222", false},
                    {"22", false},
             });

        }

    @Test
    public void isPriceValid() throws Exception {
        AdministratorLogic administratorLogic=new AdministratorLogic();
        boolean actual=administratorLogic.isPriceValid(price);
        assertEquals("Result for string = " + price + " is wrong: ", expectedResult, actual);

    }
    }




