import edu.upc.eetac.dsa.BTRManager;
import edu.upc.eetac.dsa.BTRManagerImpl;
import org.junit.Assert;
import org.junit.Test;

public class SelectTest {
    private BTRManager btr;

    @Test
    public void selectEmployeeDB(){
        this.btr = BTRManagerImpl.getInstance();
        //Assert.assertEquals("Roger",this.btr.getEm(1).getName());
    }
}
