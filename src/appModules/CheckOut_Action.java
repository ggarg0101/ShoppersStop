package appModules;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.Checkout_Page;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

// This is called Modularization, when we club series of actions in to one Module

public class CheckOut_Action {
	static WebElement element;

	public static void LoginAsRegisteredUser(int iTestCaseRow) throws Exception {

		try {
			Checkout_Page.LoginDetails.Optionbtn_RegisteredUser().click();
			Log.info("Registered user option is selected successfully");

			String sUsername = ExcelUtils.getCellData(iTestCaseRow, Constant.emailId);
			Checkout_Page.LoginDetails.LoginEmailRegisteredUser().sendKeys(sUsername);
			Log.info("Email id is entered successfully");

			String sPassword = ExcelUtils.getCellData(iTestCaseRow, Constant.password);
			Checkout_Page.LoginDetails.LoginPwdRegisteredUser().sendKeys(sPassword);
			Log.info("Password is entered successfully");

			Checkout_Page.LoginDetails.ContinueBtn().click();
			Log.info("Continue button is clicked successfully");

		} catch (Exception e) {
			Log.error("Exception in Class CheckOut_Action | Method LoginAsRegisteredUser");
			Log.error("Login not successful");
			throw (e);

		}

	}

	public static void ProceedwithNewAddress(int iTestCaseRow) throws Exception {

		try {
			if (Checkout_Page.DeliveryAddress.DeliveryAddressNew().getAttribute("style").contains("none")) {
				Checkout_Page.DeliveryAddress.AddNewAddressBtn().click();
				Log.info("Add new address button is clicked successfully");
			}

			String firstName = ExcelUtils.getCellData(iTestCaseRow, Constant.firstName);
			Checkout_Page.DeliveryAddress.firstName().sendKeys(firstName);
			Log.info("First name is entered successfully");

			String lastName = ExcelUtils.getCellData(iTestCaseRow, Constant.lastName);
			Checkout_Page.DeliveryAddress.lastName().sendKeys(lastName);
			Log.info("Last name is entered successfully");

			String phone = ExcelUtils.getCellData(iTestCaseRow, Constant.mobileNumber);
			Checkout_Page.DeliveryAddress.phone().sendKeys(phone);
			Log.info("Phone number is entered successfully");

			String address1 = ExcelUtils.getCellData(iTestCaseRow, Constant.address);
			Checkout_Page.DeliveryAddress.address1().sendKeys(address1);
			Log.info("Address 1 is entered successfully");

			String address2 = ExcelUtils.getCellData(iTestCaseRow, Constant.landmark);
			Checkout_Page.DeliveryAddress.address2().sendKeys(address2);
			Log.info("Address 2 is entered successfully");

			String postcode = ExcelUtils.getCellData(iTestCaseRow, Constant.postCode);
			Checkout_Page.DeliveryAddress.Postcode().sendKeys(postcode);
			Log.info("Postcode is entered successfully");

			Checkout_Page.DeliveryAddress.city().click();

			Checkout_Page.DeliveryAddress.TermsandConditionsCheckBox().sendKeys(Keys.PAGE_DOWN);
			Thread.sleep(1000);
			Checkout_Page.DeliveryAddress.TermsandConditionsCheckBox().click();
			Log.info("Terms and conditions option is selected successfully");

			Checkout_Page.DeliveryAddress.SaveandContinueBtn().click();
			Log.info("Save and continue button is clicked successfully");
			Log.info("Address is entered successfully");

		} catch (Exception e) {
			Log.error("Exception in Class CheckOut_Action | Method ProceedwithNewAddress");
			Log.error("Address is not entered successfully");
			throw e;
		}
	}

	public static void RedeemGiftCard_EGV_FCC(int iTestCaseRow) throws Exception {

		try {

			String paymentMode = ExcelUtils.getCellData(iTestCaseRow, Constant.giftCardEGVFCC);

			if (paymentMode.equals("GiftCard")) {
				Checkout_Page.Paymentinfo.RedeemGiftCard().click();
				Log.info("GiftCard/EGV field is clicked successfully");
				Checkout_Page.Paymentinfo.RedeemCardTypeDiv().click();
				System.out.println(Checkout_Page.Paymentinfo.RedeemCardType().getOptions().get(0).getText());
				System.out.println(Checkout_Page.Paymentinfo.RedeemCardType().getOptions().get(1).getText());
				System.out.println(Checkout_Page.Paymentinfo.RedeemCardType().getOptions().get(2).getText());
				Checkout_Page.Paymentinfo.RedeemCardType().getOptions().get(1).click();
//				Checkout_Page.Paymentinfo.RedeemCardType().selectByVisibleText("Gift Card");
				Log.info("Gift card option is selected from the dropdown");

				String cardNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.cardNumberEGVGiftFCC);
				String pin = ExcelUtils.getCellData(iTestCaseRow, Constant.pinEGVGiftFCC);
				String amtToBeRedeem = ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem);
				Checkout_Page.Paymentinfo.RedeemCardNumber().sendKeys(cardNumber);
				Log.info("Card number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardPin().sendKeys(pin);
				Log.info("Pin number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCard_AmtToRedeem().sendKeys(amtToBeRedeem);
				Log.info("Amount to be redeeemed is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardBtn().click();
				Log.info("Redeem button is clicked successfully");
				Log.info("EGV/Gift Card details entered successfully");
			}

			if (paymentMode.equals("EGV")) {
				Checkout_Page.Paymentinfo.RedeemGiftCard().click();
				Log.info("GiftCard/EGV field is clicked successfully");

				Checkout_Page.Paymentinfo.RedeemCardTypeDiv().click();
				Checkout_Page.Paymentinfo.RedeemCardType().selectByVisibleText("Electronic Gift Voucher");
				Log.info("Electronic Gift Voucher option is selected from the dropdown");

				String cardNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.cardNumberEGVGiftFCC);
				String pin = ExcelUtils.getCellData(iTestCaseRow, Constant.pinEGVGiftFCC);
				String amtToBeRedeem = ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem);
				Checkout_Page.Paymentinfo.RedeemCardNumber().sendKeys(cardNumber);
				Log.info("Card number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardPin().sendKeys(pin);
				Log.info("Pin number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCard_AmtToRedeem().sendKeys(amtToBeRedeem);
				Log.info("Amount to be redeeemed is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardBtn().click();
				Log.info("Redeem button is clicked successfully");
				Log.info("EGV/Gift Card details entered successfully");
			}

			if (paymentMode.equals("FCC")) {
				double fCCBalance;
				double pointsRedeemedBefore;
				double pointsRedeemedAfter;
				double fCCBalanceAfterRedemption;
				double pointsToBeRedeemed;

				Checkout_Page.Paymentinfo.RedeemFCC().click();

				pointsRedeemedBefore = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedBefore);
				fCCBalance = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalance);

				pointsToBeRedeemed = Double.parseDouble(ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem));
				System.out.println(pointsToBeRedeemed);

				Log.info("First Citizen Points drop down field is clicked successfully");
				Checkout_Page.Paymentinfo.FCCRedeemAmount()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem));
				Log.info("FCC amount to be redemed is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemPassword()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.pinEGVGiftFCC));
				Log.info("FCC password is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemButton().click();
				Log.info("First Citizen Points Redeem button is clicked successfully");

				pointsRedeemedAfter = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedAfter);

				if (!(pointsRedeemedAfter == (pointsRedeemedBefore + pointsToBeRedeemed))) {
					BaseClass.errorValidation += "Redemeed point count not increase by amount to be redeemed\n";
				}

				fCCBalanceAfterRedemption = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalanceAfterRedemption);
				if (!(fCCBalanceAfterRedemption == (fCCBalance - pointsToBeRedeemed))) {
					BaseClass.errorValidation += "FCC balance not decreased by amount to be redeemed\n";
				}

				Checkout_Page.Paymentinfo.FCCRedeemAmount()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem));
				Log.info("FCC amount to be redemed is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemPassword()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.pinEGVGiftFCC));
				Log.info("FCC password is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemButton().click();
				Log.info("First Citizen Points Redeem button is clicked successfully");

				pointsRedeemedAfter = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedAfter);
				if (!(pointsRedeemedAfter == (pointsRedeemedBefore + pointsToBeRedeemed + pointsToBeRedeemed))) {
					BaseClass.errorValidation += "Redemeed point count not increase by amount to be redeemed subsequently\n";
				}
				fCCBalanceAfterRedemption = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalanceAfterRedemption);
				if (!(fCCBalanceAfterRedemption == (fCCBalance - (pointsToBeRedeemed + pointsToBeRedeemed)))) {
					BaseClass.errorValidation += "FCC Balance not decreased by amount to be redeemed subsequently\n";
				}

				Checkout_Page.Paymentinfo.RemoveFCC().click();
				Log.info("Remove FCC button clicked");

				pointsRedeemedAfter = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedAfter);
				if (!(pointsRedeemedAfter == pointsRedeemedBefore)) {
					BaseClass.errorValidation += "Redeemed point not reset to zero after deleting FCC\n";
				}
				fCCBalanceAfterRedemption = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalanceAfterRedemption);
				if (!(fCCBalanceAfterRedemption == fCCBalance)) {
					BaseClass.errorValidation += "FCC balance not reset to original amount after deleting FCC\n";
				}

				try {
					if (!(Checkout_Page.Paymentinfo.PaymentAlerts().get(0).getText()
							.equals("Loyalty points are released successfully")))
						;

				} catch (Exception e) {
					BaseClass.errorValidation += "Alert -' Loyalty points are released successfully' missing after deleting FCC \n";
				}
				Log.info("FCC details entered successfully");
			}

		} catch (Exception e) {
			Log.error("Exception in Class CheckOut_Action | Method RedeemGift_EGV");
			Log.error("Error in entering details for the redeem card");
			throw e;
		}
		if (!BaseClass.errorValidation.isEmpty()) {
			Log.error("Exception in Class CheckOut_Action | Method RedeemGift_EGV");
			Log.error("Error in entering details for the redeem card");
			throw new Exception(BaseClass.errorValidation);
		}

	}

	public static void PaymentOption(int iTestCaseRow) throws Exception {

		try {
			String paymentMode = ExcelUtils.getCellData(iTestCaseRow, Constant.paymentOption);

			if (paymentMode.equals("CreditCard")) {

				Checkout_Page.Paymentinfo.CreditCardOption().click();
				Log.info("Credit card option is clicked successfully");
				String cardNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.cardNumber);
				String month = ExcelUtils.getCellData(iTestCaseRow, Constant.cardExpiryMonthYear).split("/")[0];
				String year = ExcelUtils.getCellData(iTestCaseRow, Constant.cardExpiryMonthYear).split("/")[1];
				String cvvNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.CVV);
				String cardType = ExcelUtils.getCellData(iTestCaseRow, Constant.bank);

				Checkout_Page.Paymentinfo.CreditCard_CardNumber().sendKeys(cardNumber);
				Log.info("Card number is entered successfully");
				Checkout_Page.Paymentinfo.CreditCard_ExpiryMonth().selectByValue(month);
				Log.info("Month is selected successfully");
				Checkout_Page.Paymentinfo.CreditCard_ExpiryYear().selectByValue(year);
				Log.info("Year is selected successfully");
				Checkout_Page.Paymentinfo.CreditCard_CVV().sendKeys(cvvNumber);
				Log.info("CVV number is entered successfully");
				Checkout_Page.Paymentinfo.CreditCard_Name().selectByValue(cardType);
				Log.info("Card name is selected successfully");

				Log.info("CreditCard details entered successfully");

				// Checkout_Page.Paymentinfo.CreditCard_PlaceOrderBtn().click();

			}

			if (paymentMode.equals("DebitCard")) {

				Checkout_Page.Paymentinfo.DebitCardOption().click();
				Log.info("Debit card option is clicked successfully");
				String cardNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.cardNumber);
				String month = ExcelUtils.getCellData(iTestCaseRow, Constant.cardExpiryMonthYear).split("/")[0];
				String year = ExcelUtils.getCellData(iTestCaseRow, Constant.cardExpiryMonthYear).split("/")[1];
				String cvvNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.CVV);
				String cardType = ExcelUtils.getCellData(iTestCaseRow, Constant.bank);

				Checkout_Page.Paymentinfo.DebitCard_CardNumber().sendKeys(cardNumber);
				Log.info("Card number is entered successfully");
				Checkout_Page.Paymentinfo.DebitCard_ExpiryMonth().selectByValue(month);
				Log.info("Month is selected successfully");
				Checkout_Page.Paymentinfo.DebitCard_ExpiryYear().selectByValue(year);
				Log.info("Year is selected successfully");
				Checkout_Page.Paymentinfo.DebitCard_CVV().sendKeys(cvvNumber);
				Log.info("CVV number is entered successfully");
				Checkout_Page.Paymentinfo.DebitCard_Name().selectByValue(cardType);
				Log.info("Card name is selected successfully");

				Log.info("DebitCard details entered successfully");

				// Checkout_Page.Paymentinfo.DebitCard_PlaceOrderBtn().click();

			}

			if (paymentMode.equals("NetBanking")) {

				Checkout_Page.Paymentinfo.NetBankingOption().click();
				Log.info("NetBanking option is clicked successfully");
				String bankSelection = ExcelUtils.getCellData(iTestCaseRow, Constant.netbankingBankSelection);

				if (bankSelection.equals("SelectBankFromOptionList")) {
					String bankName = ExcelUtils.getCellData(iTestCaseRow, Constant.bank);
					Checkout_Page.Paymentinfo.NetBanking_SelectFromVisibleList(bankName).click();
					Log.info("Bank name selected from the options displayed");
				} else if (bankSelection.equals("SelectBankFromDropdown")) {
					String bankName = ExcelUtils.getCellData(iTestCaseRow, Constant.bank);
					// Checkout_Page.Paymentinfo.NetBanking_SelectFromDropdownDiv().click();
					List<WebElement> options = Checkout_Page.Paymentinfo.NetBanking_SelectFromDropdown().getOptions();

					for (WebElement option : options) {

						if ((bankName.trim()).equals(option.getText().trim()))
							option.click();
						Log.info("Bank name selected from the dropdown list");
					}

					Checkout_Page.Paymentinfo.NetBanking_SelectFromDropdown().selectByValue(bankName);
				} else {
					throw new Exception("Invalid Bank selection criteria");
				}

				Log.info("Bank selected successfully");
				// Checkout_Page.Paymentinfo.NetBanking_PlaceOrderBtn().click();

			}

		} catch (Exception e) {
			Log.error("Exception in Class CheckOut_Action | Method PaymentOption");
			Log.error("Issue with Payment process");
			throw e;
		}
	}

	public static void RegisterAtCheckout(int iTestCaseRow) throws Exception {

		try {

			Checkout_Page.LoginDetails.DoNotHaveAccountBtn().click();

			Log.info("Sign up button is clicked for registration");

			String fName = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeFirstName);
			Checkout_Page.LoginDetails.First_name().sendKeys(fName);
			Log.info("First Name entered successfully");

			String lName = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeLastName);
			Checkout_Page.LoginDetails.last_name().sendKeys(lName);
			Log.info("Last Name entered successfully");

			String emailAddress = ExcelUtils.getCellData(iTestCaseRow, Constant.emailId);
			Checkout_Page.LoginDetails.email().sendKeys(emailAddress);
			Log.info("Email id entered successfully");

			String password = ExcelUtils.getCellData(iTestCaseRow, Constant.password);
			Checkout_Page.LoginDetails.password().sendKeys(password);
			Log.info("Password entered successfully");

			String cPassword = ExcelUtils.getCellData(iTestCaseRow, Constant.confirmPassword);
			Checkout_Page.LoginDetails.Confirm_Password().sendKeys(cPassword);
			Log.info("Confirm Password entered successfully");

			String pNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeMobile);
			Checkout_Page.LoginDetails.mobileNumber().sendKeys(pNumber);
			Log.info("Mobile number entered successfully");

			String gender = ExcelUtils.getCellData(iTestCaseRow, Constant.gender);

			if (gender.equals("Male")) {
				Checkout_Page.LoginDetails.genderMale().click();
				Log.info("Gender Male selected successfully");
			} else if (gender.equals("Female")) {
				Checkout_Page.LoginDetails.genderFemale().click();
				Log.info("Gender Female selected successfully");
			}

			Checkout_Page.LoginDetails.SignUpBtn().click();
			Log.info("Sign up button is clicked successfully");

			Reporter.log("Registration action is successfully performed");

		} catch (Exception e) {
			Log.error("Exception in Class CheckOut_Action | Method RegisterAtCheckout");
			Log.error("Registration action is not successful");
			throw e;
		}

	}
	
	public static void MultipleRedeemGift_EGV(int iTestCaseRow) throws Exception {

		try {

			String paymentMode = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeGiftCardEGVFCC);

			if (paymentMode.equals("GiftCard")) {
//				Checkout_Page.Paymentinfo.RedeemGiftCard().click();
				Log.info("GiftCard/EGV field is clicked succesfully");
				Checkout_Page.Paymentinfo.RedeemCardTypeDiv().click();
				
				System.out.println(Checkout_Page.Paymentinfo.RedeemCardType().getOptions().get(0).getText());
				System.out.println(Checkout_Page.Paymentinfo.RedeemCardType().getOptions().get(1).getText());
//				Checkout_Page.Paymentinfo.RedeemCardType().selectByIndex(1);
				Checkout_Page.Paymentinfo.RedeemCardType().selectByVisibleText("Gift Card");
				Checkout_Page.Paymentinfo.RedeemCardType().selectByVisibleText("Gift Card");
				Log.info("Gift card option is selected from the dropdown");

				String cardNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeCardNumberEGVGiftFCC);
				String pin = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativePinEGVGiftFCC);
				String amtToBeRedeem = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeAmountRedeem);
				Checkout_Page.Paymentinfo.RedeemCardNumber().sendKeys(cardNumber);
				Log.info("Card number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardPin().sendKeys(pin);
				Log.info("Pin number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCard_AmtToRedeem().sendKeys(amtToBeRedeem);
				Log.info("Amount to be redeeemed is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardBtn().click();
				Log.info("Redeem button is clicked successfully");
			}

			if (paymentMode.equals("EGV")) {
				Checkout_Page.Paymentinfo.RedeemGiftCard().click();
				Log.info("GiftCard/EGV field is clicked successfully");

				Checkout_Page.Paymentinfo.RedeemCardTypeDiv().click();
				Checkout_Page.Paymentinfo.RedeemCardType().selectByVisibleText("Electronic Gift Voucher");
				Log.info("Electronic Gift Voucher option is selected from the dropdown");

				String cardNumber = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeCardNumberEGVGiftFCC);
				String pin = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativePinEGVGiftFCC);
				String amtToBeRedeem = ExcelUtils.getCellData(iTestCaseRow, Constant.alternativeAmountRedeem);
				Checkout_Page.Paymentinfo.RedeemCardNumber().sendKeys(cardNumber);
				Log.info("Card number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardPin().sendKeys(pin);
				Log.info("Pin number is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCard_AmtToRedeem().sendKeys(amtToBeRedeem);
				Log.info("Amount to be redeeemed is entered successfully");

				Checkout_Page.Paymentinfo.RedeemCardBtn().click();
				Log.info("Redeem button is clicked successfully");
				Log.info("EGV/Gift Card details entered successfully");
			}

			if (paymentMode.equals("FCC")) {
				double fCCBalance;
				double pointsRedeemedBefore;
				double pointsRedeemedAfter;
				double fCCBalanceAfterRedemption;
				double pointsToBeRedeemed;

				Checkout_Page.Paymentinfo.RedeemFCC().click();

				pointsRedeemedBefore = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedBefore);
				fCCBalance = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalance);

				pointsToBeRedeemed = Double.parseDouble(ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem));
				System.out.println(pointsToBeRedeemed);

				Log.info("First Citizen Points drop down field is clicked successfully");
				Checkout_Page.Paymentinfo.FCCRedeemAmount()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem));
				Log.info("FCC amount to be redemed is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemPassword()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.pinEGVGiftFCC));
				Log.info("FCC password is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemButton().click();
				Log.info("First Citizen Points Redeem button is clicked successfully");

				pointsRedeemedAfter = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedAfter);

				if (!(pointsRedeemedAfter == (pointsRedeemedBefore + pointsToBeRedeemed))) {
					BaseClass.errorValidation += "Redemeed point count not increase by amount to be redeemed\n";
				}

				fCCBalanceAfterRedemption = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalanceAfterRedemption);
				if (!(fCCBalanceAfterRedemption == (fCCBalance - pointsToBeRedeemed))) {
					BaseClass.errorValidation += "FCC balance not decreased by amount to be redeemed\n";
				}

				Checkout_Page.Paymentinfo.FCCRedeemAmount()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.amountRedeem));
				Log.info("FCC amount to be redemed is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemPassword()
						.sendKeys(ExcelUtils.getCellData(iTestCaseRow, Constant.pinEGVGiftFCC));
				Log.info("FCC password is entered successfully");
				Checkout_Page.Paymentinfo.FCCRedeemButton().click();
				Log.info("First Citizen Points Redeem button is clicked successfully");

				pointsRedeemedAfter = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedAfter);
				if (!(pointsRedeemedAfter == (pointsRedeemedBefore + pointsToBeRedeemed + pointsToBeRedeemed))) {
					BaseClass.errorValidation += "Redemeed point count not increase by amount to be redeemed subsequently\n";
				}
				fCCBalanceAfterRedemption = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalanceAfterRedemption);
				if (!(fCCBalanceAfterRedemption == (fCCBalance - (pointsToBeRedeemed + pointsToBeRedeemed)))) {
					BaseClass.errorValidation += "FCC Balance not decreased by amount to be redeemed subsequently\n";
				}

				Checkout_Page.Paymentinfo.RemoveFCC().click();
				Log.info("Remove FCC button clicked");

				pointsRedeemedAfter = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCRedeemdAmountText().getText());
				System.out.println(pointsRedeemedAfter);
				if (!(pointsRedeemedAfter == pointsRedeemedBefore)) {
					BaseClass.errorValidation += "Redeemed point not reset to zero after deleting FCC\n";
				}
				fCCBalanceAfterRedemption = 
						Utils.decimalReaderFromString(Checkout_Page.Paymentinfo.FCCAvailableAmountText().getText());
				System.out.println(fCCBalanceAfterRedemption);
				if (!(fCCBalanceAfterRedemption == fCCBalance)) {
					BaseClass.errorValidation += "FCC balance not reset to original amount after deleting FCC\n";
				}

				try {
					if (!(Checkout_Page.Paymentinfo.PaymentAlerts().get(0).getText()
							.equals("Loyalty points are released successfully")))
						;

				} catch (Exception e) {
					BaseClass.errorValidation += "Alert -' Loyalty points are released successfully' missing after deleting FCC \n";
				}
				Log.info("FCC details entered successfully");
			}

		} catch (Exception e) {
			Log.error("Exception in Class CheckOut_Action | Method RedeemGift_EGV");
			Log.error("Error in entering details for the redeem card");
			throw e;
		}
		if (!BaseClass.errorValidation.isEmpty()) {
			Log.error("Exception in Class CheckOut_Action | Method RedeemGift_EGV");
			Log.error("Error in entering details for the redeem card");
			throw new Exception(BaseClass.errorValidation);
		}

	}
}