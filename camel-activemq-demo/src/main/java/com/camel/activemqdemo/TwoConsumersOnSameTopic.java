package com.camel.activemqdemo;

import javax.sql.DataSource;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.camel.domain.DailySalesRecord;

public class TwoConsumersOnSameTopic extends RouteBuilder {

		
				public void configure() {
					
			        final DataFormat bindy = new BindyCsvDataFormat(DailySalesRecord.class);
			        
			        //Exception handling
			        //If record fails validation, then write invalid record to the invalid-daily-sales-records table. 
			        
			        onException(ValidationException.class)
			        .log("A record has failed validation, see table 'invalid-daily-sales-records' for details")
			        .handled(true)
			        .beanRef("salesRecordInvalidMapper", "getSalesRecord")
			        .to("sqlComponent:{{sql.insertInvalidSalesRecord}}");
			        
			        //Camel Entry Route - CSV file is consumed from the file system and is then processed.
			        //If successful, sends the successful record to a queue.  
					
					from("file:src/data/csv?noop=true")
					.log("CSV file received into route")
			    	.split(body(String.class).tokenize("\n"))			    
			    	.filter(header("CamelSplitIndex").isGreaterThan(0))
			    	.log("CSV line has been split")
			        .unmarshal(bindy)
			        .log("CSV record has been transformed into a POJO and is being validated")
			        .to("bean-validator:DailySalesRecord.class")
			        .log("Record has been successfully validated")
			        .to("activemq:topic:validRecords")
			        .log("Record sent to queue");
					
					//Subscriber one - writes the successfully transformed and validated record to the Database
					
					from("activemq:topic:validRecords").routeId("a")
					.beanRef("salesRecordMapper", "getSalesRecord")
		            .to("sqlComponent:{{sql.insertSalesRecord}}")
		            .log("Record has been inserted into table 'daily-sales-records' successfully");
					//.to("log:a","direct:a");

					
					//Subscriber two - tbc
					
					//from("activemq:topic:validRecords").routeId("b").to("log:b",
						//	"direct:b");
					
					
					
					
					
				}
				
				
					
	}
	
