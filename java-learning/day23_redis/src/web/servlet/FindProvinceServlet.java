package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Province;
import web.service.ProvinceService;
import web.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindProvinceServlet", urlPatterns = "/findProvince")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Province> provinceList = provinceService.findAllProvince();
//
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(provinceList));
//        mapper.writeValue(response.getWriter(),provinceList);
        response.setContentType("application/json;charset=utf-8");

        ProvinceService provinceService = new ProvinceServiceImpl();
        String json = provinceService.finaAllJson();
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
