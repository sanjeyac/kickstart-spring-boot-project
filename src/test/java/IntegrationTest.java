import eu.sanprojects.kickstart.Application;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest("server.port:0")
public class IntegrationTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() { System.out.println("Tests setup"); }

    @Test
    public void sampleTest() {
        System.out.println("Tests sample");
    }


}
