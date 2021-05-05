package cn.itcast.travel.web.servlet;


import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category/*")
public class CategoryServlet extends BaseServlet {
    private final CategoryService categoryService = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> all = categoryService.findAll();
        writeValueByJson(all, response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cid_str = request.getParameter("cid");
        int cid = -1;
        if (cid_str != null && cid_str.length() > 0 && !"null".equals(cid_str)) {
            cid = Integer.parseInt(cid_str);
        }

        String category = categoryService.findByCid(cid);
        if (!"error".equals(category)){
            response.getWriter().write(category);
        }

    }

}
