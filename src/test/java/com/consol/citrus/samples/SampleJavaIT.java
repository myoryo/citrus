package com.consol.citrus.samples;

import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;


@Test
public class SampleJavaIT extends TestNGCitrusTestDesigner {


    @CitrusTest(name = "SendMessageTest")
    public void sendMessageTest() { //готово

        variable("text", "Hello Citrus!");
        variable("messageId", "Mx1x123456789");
        send("endpoint")
                .name("helloMessage")
                .payload(new ClassPathResource("com/consol/citrus/samples/message.xml"))
                .header("Operation", "sayHello")
                .header("RequestTag", "${messageId}");
    }

    @CitrusTest(name = "SampleJavaTest.receiveMessageTest")
    public void receiveMessageTest() {

        receive("endpoint")
                .validate("bookstore//book[last()]/date", "17.12.22")
                .validate("/bookstore/book/title[@lang='fr']","Notre-Dame de Paris" );
                //.xsd("testSchema");
    }
}
