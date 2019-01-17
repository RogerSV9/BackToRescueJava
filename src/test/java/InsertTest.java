import edu.upc.eetac.dsa.BTRManager;
import edu.upc.eetac.dsa.BTRManagerImpl;
import edu.upc.eetac.dsa.User;
import org.junit.Test;

public class InsertTest {
    private BTRManager btr;

    @Test
    public void insertEmployeeDB(){
        this.btr = BTRManagerImpl.getInstance();
        User user = new User("Roger","Sanchez");
        this.btr.UserRegistration(user);
        //this.btr.addEmployee("Laia","Munoz",9000);
        //this.btr.clear();
    }
}
