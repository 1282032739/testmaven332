package common;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class MatrixTo {
	private static final int BLACK = 0xFF000000;//��������ͼ������ɫ  
    private static final int WHITE = 0xFFFFFFFF; //���ڱ���ɫ  
  
    private void MatrixToImageWriter() {  
    }  
  
    public static BufferedImage toBufferedImage(BitMatrix matrix) {  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));  
//              image.setRGB(x, y,  (matrix.get(x, y) ? Color.YELLOW.getRGB() : Color.CYAN.getRGB()));  
            }  
        }  
        return image;  
    }  
  
    public void writeToFile(BitMatrix matrix, String format, File file) throws IOException {  
        BufferedImage image = toBufferedImage(matrix);  
        //����logoͼ��  
        LogoConfig logoConfig = new LogoConfig();  
        image = logoConfig.LogoMatrix(image);    
        if (!ImageIO.write(image, format, file)) {  
            throw new IOException("Could not write an image of format " + format + " to " + file);  
        }else{  
            System.out.println("生成二维码");  
        }  
    }  
  
    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {  
        BufferedImage image = toBufferedImage(matrix);  
       
        LogoConfig logoConfig = new LogoConfig();  
        image = logoConfig.LogoMatrix(image);  
          
        if (!ImageIO.write(image, format, stream)) {  
            throw new IOException("Could not write an image of format " + format);  
        }  
    }  
}
