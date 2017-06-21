package com.camel.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.camel.domain.DailySalesRecord;

public class SalesRecordInvalidMapper {   
	
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
	String formattedDate = sdf.format(date);
    
    public Map<String, Object> getSalesRecord(DailySalesRecord dailySalesRecord) {
        Map<String, Object> answer = new HashMap<String, Object>();
        answer.put("unitPrice", dailySalesRecord.getUnitPrice());
        answer.put("quantity", dailySalesRecord.getQuantity());
        answer.put("itemCode", dailySalesRecord.getItemCode());
        answer.put("siteRef", dailySalesRecord.getSiteRef());
        answer.put("timestamp", formattedDate);
        return answer;
    }
    

}