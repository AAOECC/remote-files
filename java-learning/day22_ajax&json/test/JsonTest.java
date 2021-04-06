import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {

    @Test
    public void test1() throws Exception {
        Person person = new Person();
        person.setAge(23);
        person.setName("zhangsan");
        person.setGender(true);


        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(person);
        System.out.println(json);
    }

    @Test
    public void test2() throws IOException {
        Person person = new Person();
        person.setAge(23);
        person.setName("zhangsan");
        person.setGender(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("d://a.txt"),person);

    }

    @Test
    public void test3() throws IOException {
        Person person = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        person.setAge(23);
        person.setName("zhangsan");
        person.setGender(true);

        List<Person> ps = new ArrayList<Person>();
        ps.add(person);

        person2.setAge(23);
        person2.setName("lisi");
        person2.setGender(true);
        ps.add(person2);
        person3.setAge(23);
        person3.setName("wangwu");
        person3.setGender(false);
        ps.add(person3);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(ps);
        System.out.println(json);

    }

    @Test
    public void test4() throws JsonProcessingException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "zhangsan");
        map.put("age",23);
        map.put("gender",true);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println(json);

    }
}
