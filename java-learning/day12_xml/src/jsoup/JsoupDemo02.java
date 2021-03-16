package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Jsoup 对象 方法
 */
public class JsoupDemo02 {
    public static void main(String[] args) throws IOException {

        String xmlPath = JsoupDemo02.class.getClassLoader().getResource("students.xml").getPath();

        //Document document = Jsoup.parse(new File(xmlPath), "utf-8");
        /*
        URL url = new URL("https://www.baidu.com");
        Document document = Jsoup.parse(url, 3000);

         */
        Document document = Jsoup.parse("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<!--<!DOCTYPE students SYSTEM \"students.dtd\">-->\n" +
                "\n" +
                "<students>\n" +
                "    <student number=\"z001\">\n" +
                "        <name>zhangsan</name>\n" +
                "        <age>23</age>\n" +
                "        <sex>male</sex>\n" +
                "    </student>\n" +
                "\n" +
                "    <student number=\"z002\">\n" +
                "        <name>bob</name>\n" +
                "        <age>21</age>\n" +
                "        <sex>female</sex>\n" +
                "    </student>\n" +
                "</students>");

        System.out.println(document);
    }
}
