package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Person;

public class PersonTest {
    
    @Test
    public void testAdd(){
        Person p = new Person();
        int result = p.add(1, 2);
        assertEquals(3, result);
    }
}
