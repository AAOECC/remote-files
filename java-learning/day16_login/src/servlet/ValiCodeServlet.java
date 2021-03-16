package servlet;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ValiCodeServlet", urlPatterns = "/valiCode")
public class ValiCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("valiCode....");
        HttpSession session = request.getSession();
        session.removeAttribute("statue");
        //绘制验证码
        ServletOutputStream out = response.getOutputStream();
        int height = 60;
        int width = 120;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics gra = bufferedImage.getGraphics();

        gra.setColor(Color.PINK);
        gra.fillRect(0,0,width,height);
        gra.setColor(Color.BLUE);
        gra.drawRect(0,0,width-1,height-1);

        //绘制字符 及 干扰线
        String set = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Font times_new_roman = new Font("Times New Roman", Font.PLAIN, 20);
        gra.setFont(times_new_roman);
        Random random = new Random();
        StringBuilder right_code = new StringBuilder();
        for (int i = 1; i <= 4 ; i++) {
            int index = random.nextInt(set.length());
            char ch = set.charAt(index);
            right_code.append(ch);
            gra.drawString(ch+"",width/5*i,height/2);
        }
        gra.setColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            gra.drawLine(x1,y1,x2,y2);
        }
        ImageIO.write(bufferedImage,"jpeg",out);


        //封装 生成的字符串到 session 中
        String rightStr = right_code.toString();
        System.out.println("rightStr : "+rightStr);
        session.setAttribute("valiCode", rightStr);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
