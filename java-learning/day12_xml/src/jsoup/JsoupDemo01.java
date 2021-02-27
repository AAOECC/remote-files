package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo01 {
    public static void main(String[] args) throws IOException {
        //1.获取students.xml文件路径
        String xmlPath = JsoupDemo01.class.getClassLoader().getResource("students.xml").getPath();
        //2.加载文件到内存
        Document document = Jsoup.parse(new File(xmlPath), "utf-8");
        //3.读取需要获取的数据标签
        Elements elements = document.getElementsByTag("name");
        System.out.println(elements.size());
        //4.获得所需的数据
        Element element = elements.get(0);
        String name = element.text();
        System.out.println(name);
    }
}
