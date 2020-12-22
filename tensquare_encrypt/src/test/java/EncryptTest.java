import com.tensquare.encrypt.EncryptApplication;
import com.tensquare.encrypt.rsa.RsaKeys;
import com.tensquare.encrypt.service.RsaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EncryptApplication.class)
public class EncryptTest {

    @Autowired
    private RsaService rsaService;

    @Before
    public void before() throws Exception{
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void genEncryptDataByPubKey() {
        String data = "{" +
                "        \"columnid\": \"1\"," +
                "        \"userid\": \"1\"," +
                "        \"title\": \"tset2\"," +
                "        \"content\": \"test2\"," +
                "        \"image\": \"tset\"," +
                "        \"createtime\": \"2020-07-21T16:40:52.000+0000\"," +
                "        \"updatetime\": \"2020-07-21T16:40:56.000+0000\"," +
                "        \"ispublic\": \"f\"," +
                "        \"istop\": \"1\"," +
                "        \"visits\": 1," +
                "        \"thumbup\": 1," +
                "        \"comment\": 1," +
                "        \"state\": \"1\"," +
                "        \"channelid\": \"1\"," +
                "        \"url\": \"1\"," +
                "        \"type\": \"1\"" +
                "}";

        try {

            String encData = rsaService.RSAEncryptDataPEM(data, RsaKeys.getServerPubKey());

            System.out.println("data: " + data);
            System.out.println("encData: " + encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        String data = "TkI7OQW0GGgzDiw6h1U7kNTY9PaDeikCKXkews/qBk13o08U8lN7IWoXNZ6gpXNQvI2+BdWeAPzLfu5QNtkEwjhlr7CjcTEb8hWLovEybeXGqFl8Eknk3sLF7BiWDpKXERDJYed5RflEn3tLbMmbf9rH6XwMgrBUJInq3R+IkThA4rsP0bu6+QpDYm8QAil2qgAojBpZMhTIXVkTBlyQfmCdMcwJbxUYOlzFRg5NIxqx4+FLwxh12CXKrTax4c7nEAptmkIGllLgAIWklkVglScyDli0C7HuTQDN1/oGWszYdjHHIs1kTigEPUdJRYjs2Itz+4FcMoWFM+XgoQ3H8g==";
        try {
            String s = rsaService.RSADecryptDataPEM(data, RsaKeys.getServerPrvKeyPkcs8());
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
