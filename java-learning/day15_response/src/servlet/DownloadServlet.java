package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownloadServlet", urlPatterns = "/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字节流编码
        response.setContentType("utf-8");
        //获取文件名称
        request.setCharacterEncoding("utf-8");
        String filename = request.getParameter("filename");
        //将文件输入流字节流关联文件
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/img/" + filename);
        FileInputStream fis = new FileInputStream(realPath);

        //设置响应头 状态
        String mimeType = context.getMimeType(filename);
        response.setHeader("context-type",mimeType);
        response.setHeader("content-disposition","attachment;filename="+filename);
        //设置缓存区
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while((len = fis.read(buff))!= -1){
            outputStream.write(buff,0,len);
        }
        fis.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
