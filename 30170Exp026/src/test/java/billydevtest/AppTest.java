package billydevtest;

import billydev.MultiValueMap;
import billydev.MultiValueMapImpl;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AppTest {

    static MultiValueMap<String, String> multiValueMap;

    @BeforeAll
    static void initAll() {
        System.out.println("this is Before All method !");
        multiValueMap=new MultiValueMapImpl();
    }

    @BeforeEach
    void init() {
        System.out.println("this is before each method .");
        multiValueMap.add("header1", "header1A");
        multiValueMap.add("header1", "header1B");
        multiValueMap.add("header1", "header1C");
        multiValueMap.add("header2", "header2A");
        multiValueMap.add("header2", "header2B");
        multiValueMap.add("header2", "header2C");
    }

    @Test
    void getFirstTest() {
        assertEquals("header1A",multiValueMap.getFirst("header1"),"first tag in header failed test");
        assertEquals("header2A",multiValueMap.getFirst("header2"),"first tag in header failed test");
        System.out.println("this method should be succeed");
    }
    @Test
    void addAllTest(){
        MultiValueMap<String, String> multiValueMap2 = new MultiValueMapImpl();
        multiValueMap2.add("headerNew1", "headerNew1A");
        multiValueMap2.add("headerNew1", "headerNew1B");
        multiValueMap2.add("headerNew1", "headerNew1C");
        multiValueMap2.add("headerNew2", "headerNew2A");
        multiValueMap2.add("headerNew2", "headerNew2B");
        multiValueMap2.add("headerNew2", "headerNew2C");
        multiValueMap.addAll(multiValueMap2);
        assertEquals(4,multiValueMap.size(),"size of sum of the map not match");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
        System.out.println("this  message should be ignore and not display");
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        System.out.println("this is after each.");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("this is after all");
    }
}
