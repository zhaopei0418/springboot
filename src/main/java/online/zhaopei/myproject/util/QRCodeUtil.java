package online.zhaopei.myproject.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaopei on 18/1/22.
 */
public class QRCodeUtil {
    private static final int width = 300;// 默认二维码宽度
    private static final int height = 300;// 默认二维码高度
    private static int onColor = 0x00000000;     //前景色
    private static int offColor = 0xFFFFFFFF;    //背景色
    private static final String format = "png";// 默认二维码文件格式
    private static final Map<EncodeHintType, Object> hints = new HashMap();// 二维码参数

    static {
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 字符编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.MARGIN, 2);// 二维码与图片边距
    }
    /**
     * 返回一个 BufferedImage 对象
     * @param content 二维码内容
     * @param width   宽
     * @param height  高
     */
    public static BufferedImage toBufferedImage(String content, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    /**
     * 将二维码图片输出到一个流中
     * @param content 二维码内容
     * @param stream  输出流
     * @param width   宽
     * @param height  高
     */
    public static void writeToStream(String content, OutputStream stream, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
    }
    /**
     * 生成二维码图片文件
     * @param content 二维码内容
     * @param path    文件保存路径
     * @param width   宽
     * @param height  高
     */
    public static void createQRCode(String content, String path, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        //toPath() 方法由 jdk1.7 及以上提供
        MatrixToImageWriter.writeToPath(bitMatrix, format, new File(path).toPath());
    }

    /**
     * 二维码绘制logo
     * @param qrCodeImg 二维码图片文件
     * @param logoImg logo图片文件
     * */
    private static BufferedImage encodeImgLogo(BufferedImage qrCodeImg, File logoImg){
        try{
            if(null == qrCodeImg|| !logoImg.isFile()){
                return null;
            }

            //获取画笔
            Graphics2D g = qrCodeImg.createGraphics();
            //读取logo图片
            BufferedImage logo = ImageIO.read(logoImg);
            //设置二维码大小，太大，会覆盖二维码，此处20%
            int logoWidth = logo.getWidth(null) > qrCodeImg.getWidth() * 2 / 10 ?
                    (qrCodeImg.getWidth() * 2 / 10) : logo.getWidth(null);
            int logoHeight = logo.getHeight(null) > qrCodeImg.getHeight() * 2 / 10 ?
                    (qrCodeImg.getHeight() * 2 / 10) : logo.getHeight(null);
            //设置logo图片放置位置
            //中心
            int x = (qrCodeImg.getWidth() - logoWidth) / 2;
            int y = (qrCodeImg.getHeight() - logoHeight) / 2;
            //右下角，15为调整值
//          int x = twodimensioncode.getWidth()  - logoWidth-15;
//          int y = twodimensioncode.getHeight() - logoHeight-15;
            //开始合并绘制图片
            g.drawImage(logo, x, y, logoWidth, logoHeight, null);
            g.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);
            //logo边框大小
            g.setStroke(new BasicStroke(2));
            //logo边框颜色
            g.setColor(Color.white);
            g.drawRect(x, y, logoWidth, logoHeight);
            g.dispose();
            logo.flush();
            qrCodeImg.flush();
        }catch(Exception e){
            System.out.println("二维码绘制logo失败");
        }
        return qrCodeImg;
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix){
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int x = 0;x < width; x++){
            for(int y = 0;y < height; y++){
                image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
            }
        }
        return image;
    }

    /**
     * 生成logo二维码,用Thumbnails
     * @param logoPath logo图片路径
     * @param content 二维码内容
     * @param path    生成二维码路径
     * @param width  宽
     * @param height  高
     * @throws WriterException
     * @throws IOException
     */
    public static void createLogoQRCode(String logoPath, String content, String path, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage bi = toBufferedImage(bitMatrix);
        //使用 Graphics2D，logo宽，高不超过二维码的20%
        bi = encodeImgLogo(bi, new File(logoPath));
        ImageIO.write(bi, path.substring(path.lastIndexOf(".") + 1, path.length()), new File(path));
        //使用Thumbnails
        //Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(bi);
        //BufferedImage logoImage = Thumbnails.of(new File(logoPath)).forceSize((int) (width * 0.2), (int) (height * 0.2)).asBufferedImage();
        //builder.watermark(Positions.CENTER, logoImage, 1F).scale(1F).toFile(new File(path));
    }
}
