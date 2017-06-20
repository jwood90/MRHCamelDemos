package com.camel.domain;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import java.io.Serializable;


@CsvRecord(separator = ",", crlf = "MAC")
public class DailySalesRecord implements Serializable {

   //     @NotNull	
     //   @Size(min = 3, max = 5) 
	    @DataField(pos = 1)
	    private String siteRef;
	 
   //     @NotNull
   //     @Size(min = 3, max = 5) 
	    @DataField(pos = 2)
	    private String itemCode;
       
   //     @NotNull
	    @DataField(pos = 3)
	    private String quantity;
	    
   //     @NotNull
	    @DataField(pos = 4)
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
