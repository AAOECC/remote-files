package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FindNameServlet", value = "/findName")
public class FindNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //response.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        ObjectMapper mapper = new ObjectMapper();
        Msg msg;
        String target = "zhangsan";
        if (!("".equals(username))){
            if (target.equals(username)){
                msg = new Msg(1, "用户名已存在");
            }else{
                msg = new Msg(0, "该用户名可用");
            }
            mapper.writeValue(response.getWriter(),msg);
        }
    }
}

class Msg{
    private int userExist;
    private String msg;

    public Msg(){}

    public Msg(int userExist, String msg) {
        this.userExist = userExist;
        this.msg = msg;
    }

    public int getUserExist() {
        return userExist;
    }

    public void setUserExist(int userExist) {
        this.userExist = userExist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "userExist=" + userExist +
                ", msg='" + msg + '\'' +
                '}';
    }


}
