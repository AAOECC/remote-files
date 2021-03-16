package web.servlet;

import utils.DownLoadUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "DownloadServlet", value = "/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        1.设置字节流编码格式
        2.获取要下载的文件路径，加载进输入流
        3.设置 response 头格式
        4.设置缓存区，加载文件到缓存区，响应流读取缓存区
         */
        //设置字节流编码格式
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        //获取要下载的文件路径，加载进输入流
        String filename = request.getParameter("filename");
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/"+filename);
        FileInputStream fileInputStream = new FileInputStream(realPath);

        //设置 response 头格式
        String mimeType = servletContext.getMimeType(filename);
        String agent = request.getHeader("User-Agent");
        filename = DownLoadUtils.getFileName(agent, filename);
        System.out.println(filename);
        response.setContentType(mimeType);
        response.setHeader("content-disposition", "attachment;filename="+filename);

        //设置缓存区，加载文件到缓存区，响应流读取缓存区
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] cat = new byte[1024 * 8];
        int len = 0;
        while((len = fileInputStream.read(cat)) != -1){
            outputStream.write(cat, 0,len);
        }

    }
}
