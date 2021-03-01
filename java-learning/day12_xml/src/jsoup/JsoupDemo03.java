package jsoup;


import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Jsoup 的快速定位标签：
 *      1.选择器
 *      2. Xpath 方式
 */
public class JsoupDemo03 {

    private static Document document;

    static{
        String xml_path = JsoupDemo03.class.getClassLoader().getResource("students.xml").getPath();
        try {
            document = Jsoup.parse(new File(xml_path), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //selector();
        try {
            xpath_method();
        } catch (XpathSyntaxErrorException e) {
            e.printStackTrace();
        }
    }

    //选择器方式
    public static void selector() {
        //1.查询 name 标签
        Elements ele_name = document.select("name");
        System.out.println(ele_name);
        System.out.println("---------------------------------");
        //2.查询 id 值为 itcast 的元素
        Elements ele_id_itcast = document.select("#itcast");
        System.out.println(ele_id_itcast);
        System.out.println("---------------------------------");
        //3.获取 number 属性值为 “z001”的 student 标签的 age 子标签
        Elements ele_age = document.select("student[number='z001'] age");
        System.out.println(ele_age);
        System.out.println("---------------------------------");
    }

    //XPath 方式
    public static void xpath_method() throws XpathSyntaxErrorException {
        JXDocument jxDocument = new JXDocument(document);
        //1.查询所有 student 标签
        List<JXNode> jx_students = jxDocument.selN("//student");
        for (JXNode jx_student : jx_students) {
            System.out.println(jx_student);
        }
        System.out.println("------------------------------");
        //2.查询所有 student 标签下的 name 标签
        List<JXNode> jx_name = jxDocument.selN("//student/name");
        for (JXNode jxNode : jx_name) {
            System.out.println(jxNode);
        }
        System.out.println("------------------------------");

        //3.查询所有 student 标签下带有 id 属性的 name 标签
        List<JXNode> jx_name_id = jxDocument.selN("//student/name[@id]");
        for (JXNode jxNode : jx_name_id) {
            System.out.println(jxNode);
        }
        System.out.println("------------------------------");
        //4.查询所有 student 标签下带有 id 属性的 name 标签，并且id值为 itcast
        List<JXNode> jx_name_id_itcast = jxDocument.selN("//student[@number='z002']/name");
        for (JXNode jxNode : jx_name_id_itcast) {
            System.out.println(jxNode);
        }
        System.out.println("------------------------------");

    }
}
