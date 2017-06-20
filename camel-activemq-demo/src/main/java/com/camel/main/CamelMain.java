package com.camel.main;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CamelMain {

    public static void main(String[] args) {

        try {
            ApplicationContext springCtx = new ClassPathXmlApplicationContext("database-context.xml");

            CamelContext context = springCtx.getBean("salesRecordContext", CamelContext.class);

            context.start();
            
            
            Thread.sleep(10000);

            // stop the CamelContext
            context.stop();
            

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

  
}
