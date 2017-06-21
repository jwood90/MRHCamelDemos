package com.camel.domain;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@CsvRecord(separator = ",", crlf = "MAC")

public class DailySalesRecord implements Serializable {
   
	    @DataField(pos = 1)
	    @NotNull
	    private String siteRef;
	      
	    @DataField(pos = 2)
	    @NotNull
	    @Size(min = 3, max = 5) 
	    private String itemCode;
       
	    @DataField(pos = 3)
	    @NotNull
	    private String quantity;
	           
	    @DataField(pos = 4)
	    @NotNull
	    private String unitPrice;
	   
	    public String getSiteRef() {
	        return siteRef;
	    }

	    public void setSiteRef(String siteRef) {
	        this.siteRef = siteRef;
	    }

	    public String getItemCode() {
	        return itemCode;
	    }

	    public void setItemCode(String itemCode) {
	        this.itemCode = itemCode;
	    }

	    public String getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(String quantity) {
	        this.quantity = quantity;
	    }
	    
	    public String getUnitPrice() {
	        return unitPrice;
	    }

	    public void setUnitPrice(String unitPrice) {
	        this.unitPrice = unitPrice;
	    }
	    
	   
	    
	
}
