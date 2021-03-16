package web.servlet;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckCodeServlet", value = "/checkCodeServlet")
public class CheckcodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载图片对象
        int width = 100;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        //美化图片
        Graphics gra = bufferedImage.getGraphics();//获取画笔工具
        //填充背景色 及 边框
        gra.setColor(Color.PINK);
        gra.fillRect(0, 0, width, height);
        gra.setColor(Color.BLUE);
        gra.drawRect(0,0,width-1,height-1);
        //绘制字符
        String codeSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        gra.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(codeSet.length());
            char ch = codeSet.charAt(index);
            sb.append(ch);
            gra.drawString(ch+"",width/5*i, height/2);
        }
        String rightStr = sb.toString();//正确的验证码内容
        //绘制干扰线
        gra.setColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            gra.drawLine(x1,y1,x2,y2);
        }

        //进行页面输出
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
        System.out.println(rightStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
