package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FindNameServlet", urlPatterns = "/findName")
public class FindNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");

        String target = "zhangsan";
        ObjectMapper mapper = new ObjectMapper();
        Msg msg = new Msg();
        if (!"".equals(username)){
            if (target.equals(username)){
                msg.setNameMsg("用户名已存在");
                String json = mapper.writeValueAsString(msg);
                response.getWriter().write(json);
                System.out.println(json);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

class Msg{
    private String nameMsg;

    public String getNameMsg() {
        return nameMsg;
    }

    public void setNameMsg(String nameMsg) {
        this.nameMsg = nameMsg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "nameMsg='" + nameMsg + '\'' +
                '}';
    }
}
