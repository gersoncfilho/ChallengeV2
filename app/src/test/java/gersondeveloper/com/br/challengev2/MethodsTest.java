package gersondeveloper.com.br.challengev2;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import gersondeveloper.com.br.challengev2.Util.ChallengeUtil;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MethodsTest {

    @Test
    public void emailValidatorTest()
    {
        assertThat(ChallengeUtil.emailValidator("gersoncfilho@mac.com"), is(true));
    }

    @Test
    public void formatCurrencyTest()
    {
        assertThat(ChallengeUtil.formatPrice(1999.00), is("R$ 1.999,00"));
    }
}