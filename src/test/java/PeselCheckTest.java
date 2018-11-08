import org.junit.Assert;
import org.junit.Test;

public class PeselCheckTest {
    @Test
    public void peselC(){
    A t= new A();
        Assert.assertTrue(t.checkpesel("00220812559"));
    }
}
