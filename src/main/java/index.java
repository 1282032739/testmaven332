import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import common.MatrixTo;
import common.PoolCache;
import common.ScanPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
         System.out.println("进入首页,先生成UUID");
	   String uuid=UUID.randomUUID().toString();
	   request.setAttribute("uuid", uuid);
	   String text = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx81b2d26db8128f0f&redirect_uri=http://1971ug6163.51mypc.cn:16526/untitled1/wxpayServlet&response_type=code&scope=snsapi_base&state="+uuid;
		int width = 300;   
		int height = 300;   
		String format = "jpg";   
		//将UUID放入缓存
		ScanPool pool = new ScanPool();
		PoolCache.cacheMap.put(uuid, pool);
		try
		{
			
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
		     // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）  
		    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
		    // 内容所使用字符集编码  
		    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");     
		//  hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值  
		//  hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值  
		    hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数  
		      
		    BitMatrix bitMatrix = new MultiFormatWriter().encode(text,//要编码的内容  
		            BarcodeFormat.QR_CODE,  
		            width, //条形码的宽度  
		            height, //条形码的高度  
		            hints);//生成条形码时的一些配置,此项可选  
		    // 生成二维码  
		    String productPath = getServletContext().getRealPath("/img");
			System.out.print(productPath);
		    File outputFile = new File( productPath+ File.separator + "888.jpg");//指定输出路径  
		    MatrixTo img=new MatrixTo();
		   img.writeToFile(bitMatrix, format, outputFile);
		} catch (WriterException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
