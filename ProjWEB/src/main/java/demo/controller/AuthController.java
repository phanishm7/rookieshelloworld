package demo.controller;


import demo.domain.PhonePin;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.datastax.driver.core.*;


import java.security.MessageDigest;


import sun.misc.BASE64Encoder;

@RestController
public class AuthController {



    @RequestMapping("/GiftCardLogin")
    public PhonePin checkDecryption(@RequestParam(value="phone", defaultValue="") String phone,
                                    @RequestParam(value="pin", defaultValue="") String pin) {

        //connect to cassandra
        Cluster cluster = Cluster.builder().withSocketOptions(new SocketOptions().setKeepAlive(true))
				.withPort(9042)															//DEV
				.addContactPoints("localhost".split(",")).build(); //DEV
        Session session = cluster.connect("demo");

        ResultSet results = session.execute("select * from xmbl_gfcd_acct where mbl_num_i='"+phone+"'");
        System.err.println("Sahana"); 
         

        System.err.println("m71");

        String enryptedDbPin ="";

        //get the encrypted pin
        for (Row row : results) {
            System.out.format("%s %s\n", row.getString("email_addr_i"), row.getString("mbl_num_i"));

            enryptedDbPin = row.getString("pin_num_hash_t");

        }

        //encrypt the request pin
        String plainText = pin;
        MessageDigest msgDigest = null;
        String hashValue = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(plainText.getBytes("UTF-8"));
            byte rawByte[] = msgDigest.digest();
            hashValue = (new BASE64Encoder()).encode(rawByte);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.err.println("Printing the key : " + hashValue);

        //compare encrypted request with the cassandra value
        return new PhonePin(phone, hashValue.equals(enryptedDbPin) ? "Success" : "Failed");
    }
}
