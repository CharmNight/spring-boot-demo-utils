import com.night.RabbitMQApplication;
import com.night.service.DirectSenderSerivice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitMQDirectTest {
    @Autowired
    private DirectSenderSerivice directSenderSerivice;
    @Test
    public void directSend(){
        directSenderSerivice.sender(1);
    }
}
