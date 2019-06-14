package com.tetherfi.test.fax;

import java.util.Map;

import org.testng.annotations.Test;

import com.tetherfi.model.fax.FaxAddressBookDetails;
import com.tetherfi.pages.FaxAddressBookPage;
import com.tetherfi.test.BaseTest;
import com.tetherfi.utility.ExcelReader;
import com.tetherfi.utility.PageFactory;

public class sqlQueryTest extends BaseTest{
	
	@Test
	   public void Database() throws Exception {
		for(int i=0;i<5;i++) {
			String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\RecipientData.xlsx";
		   	Map<String, String> map = new ExcelReader(filePath,"TestTfax").getTestData().get(i);
			FaxAddressBookDetails faxAddressBoookDetails = new FaxAddressBookDetails(map);
			FaxAddressBookPage faxAddressBookPage = PageFactory.createPageInstance(driver, FaxAddressBookPage.class);
			faxAddressBookPage.InsertQuery("Insert Into [TestTfax].[dbo].[Fax_Dnis] (DNIS,Description,SendEnabled,ReceiveEnabled,Enabled,LastChangedBy,LastChangedOn) values("+faxAddressBoookDetails.getNumber()+",'test',1,1,1,'Palak','06/13/2019');");
		}
	   }

}
