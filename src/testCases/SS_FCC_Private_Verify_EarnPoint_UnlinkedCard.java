package testCases;



import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.BaseClass;
import pageObjects.MiniCart_Page;
import pageObjects.ProductDetails_Page;
import pageObjects.ProductListing_Page;
import appModules.FCC_Action;
import appModules.HomePage_Action;
import appModules.Login_App;
import appModules.PDP_Action;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import utility.JyperionListener;

/**
 * 
 * <h2 style="text-align:center;">SS_FCC_Private_Verify_EarnPoint_UnlinkedCard</h2>
 * <p style="font-size:19px"><b>Description -</b>This Test Case checks for the earn point message displayed on cart page when FCC card no (not linked to the account)is entered.</p>
 * <TABLE width="100%" border="1">
 * <caption style="font-size:17px">List of columns used from excel file</caption>
 * <tr><th>Parameters</th><th>Description</th></tr>
 * <tr><td>browser</td><td>Browser name in which test execution starts</td></tr>
 * <tr><td>emailId</td><td>User email Id</td></tr>
 * <tr><td>password</td><td>User password</td></tr> 
 * <tr><td>productCategory</td><td>Product category available on main menu</td></tr>
 * <tr><td>productSubCategory</td><td>Product sub category available under the main menu</td></tr>
 * <tr><td>giftCardEGVFCC</td><td>Enter the required payment option on checkout page(Like EGV, GC, FCC)</td></tr>
 * <tr><td>cardNumberEGVGiftFCC</td><td>FCC card number to be entered.</td></tr>
 * </table>
 * <br>
 * <br>
 * 
 */ 
@Listeners(JyperionListener.class)
public class SS_FCC_Private_Verify_EarnPoint_UnlinkedCard {
	
	public WebDriver Driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	
	@BeforeSuite
	public void BeforeSuite() throws Exception{
		Utils.setSnapshotFolder();
	}

	@BeforeMethod
	public void BeforeMethod() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		sTestCaseName = Utils.getTestCaseName(this.toString());
		Log.info("Test case to be executed: "+ sTestCaseName);
		ExcelUtils.setExcelFile(Utils.ReadProperties(Constant.Path_ConfigProperties).getProperty("Path_TestData")
				+ Constant.File_TestData, "Sheet1");
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.testCaseName);

		Log.startTestCase(sTestCaseName);
		Driver = Utils.OpenBrowser(iTestCaseRow);
		new BaseClass(Driver);
	}

	@Test
	public void main() throws Exception {
		try {
			
		Login_App.execute(iTestCaseRow);
		Log.info("User login successfull");
		
		HomePage_Action.selectProductCategoryfromMenu(iTestCaseRow);
		Log.info("Product categaory and subcategory selected successfull");
		
		ProductListing_Page.product().click();
		Log.info("Product clicked on PLP");
		PDP_Action.product_selectSize(ProductDetails_Page.Product.size_variant_buttonlist());
		ProductDetails_Page.Product.Product_AddToCart().click();
		Log.info("Product added to cart on PDP");
		
		MiniCart_Page.MiniCartProductDetails.MiniCartViewBag().click();
		Log.info("View bag button clicked on minicart pop up");
		
		FCC_Action.FCC_Verify_EarnPoint_UnlinkedCard_Functionality(iTestCaseRow);
		Log.info("FCC points earning message displayed on cart page for registered user");
		
		if(BaseClass.bResult==true){
			Log.info("Verification for FCC points earning on cart page for registered user with unlinked card successfull");
			ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.result);
		}else{
			
			throw new Exception("Test Case Failed because of Verification");
		}
		
		} catch (Exception e) {
			Log.error("Verification for Buying FCC from Header Link for guest user failed");
			ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.result);
			Utils.captureScreenshot(sTestCaseName, "Fail", "Failed");
			Log.error(e.getMessage());
			throw (e);
		}
	}

	@AfterMethod
	public void afterMethod() {

		Log.endTestCase(sTestCaseName);

		Driver.close();
		Driver.quit();

	}

}