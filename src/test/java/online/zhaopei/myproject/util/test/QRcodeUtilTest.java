package online.zhaopei.myproject.util.test;

import online.zhaopei.myproject.util.QRCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhaopei on 18/1/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QRcodeUtilTest {

    @Test
    public void testCreateQRCode() throws Exception {
        //QRCodeUtil.createQRCode("zhaopei", "/Users/zhaopei/Desktop/qrtest.png", 200, 200);
        QRCodeUtil.createLogoQRCode("/Users/zhaopei/Documents/myn2.jpeg", "https://www.baidu.com",
                "/Users/zhaopei/Desktop/logoqr.jpeg", 200, 200);
        //QRCodeUtil.drawTwoImage("/Users/zhaopei/Desktop/logoqr.jpeg", "/Users/zhaopei/Documents/vim.jpg",
         //       "/Users/zhaopei/Desktop/1.jpg");
    }
}
