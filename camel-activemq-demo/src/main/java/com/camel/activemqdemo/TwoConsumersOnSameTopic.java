package com.camel.activemqdemo;

import javax.sql.DataSource;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
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
	
		//camelContext.addComponent("activemq", ActiveMQComponent
				//.activeMQComponent("vm://localhost?broker.persistent=false"));
		
				public void configure() {
					
			        final DataFormat bindy = new BindyCsvDataFormat(DailySalesRecord.class);
					
					from("file:src/data/csv?noop=true")
					.log("CSV file received into route")
			    	.split(body(String.class).tokenize("\n"))
			    	.log("CSV line has been split")
			        .unmarshal(bindy)
			        //.to("bean-validator:DailySalesRecord.class)")
			        .log("CSV record has been transformed into POJO")
			        //.beanRef("salesRecordMapper", "getSalesRecord")
			        .to("activemq:topic:foo")
			        .log("Record sent to queue");
					
					
					
					from("activemq:topic:foo").routeId("a")
					.beanRef("salesRecordMapper", "getSalesRecord")
		            .to("sqlComponent:{{sql.insertSalesRecord}}")
					.to("log:a","direct:a");

					from("activemq:topic:foo").routeId("b").to("log:b",
							"direct:b");

					from("direct:a").transform(
							simple("direct:a output: ${body}"))
							.to("stream:out");
					from("direct:b").transform(
							simple("direct:b output: ${body}"))
							.to("stream:out");
				}
			
		
	}
	
