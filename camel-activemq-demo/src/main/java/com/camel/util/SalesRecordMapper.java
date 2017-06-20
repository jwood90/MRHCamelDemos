package com.camel.util;


import java.util.HashMap;
import java.util.Map;

import com.camel.domain.DailySalesRecord;

public class SalesRecordMapper {    
    
    public Map<String, Object> getSalesRecord(DailySalesRecord dailySalesRecord) {
        Map<String, Object> answer = new HashMap<String, Object>();
        answer.put("unitPrice", dailySalesRecord.getUnitPrice());
        answer.put("quantity", dailySalesRecord.getQuantity());
        answer.put("itemCode", dailySalesRecord.getItemCode());
        answer.put("siteRef", dailySalesRecord.getSiteRef());
        return answer;
    }
    

}