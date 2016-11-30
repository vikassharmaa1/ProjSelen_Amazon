package testcases;

import org.openqa.selenium.*;
import org.testng.annotations.*;


import actions.OrderActions;
import base.LoadProperties;
import enums.Products;
import pages.ShoppingCartReviewPage;
import pojo.Book;
import utils.DriverUtils;

public class PurchaseOrderTest {
	public WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = DriverUtils.getDriver();

    }

//    @Test()
//    public void test_Login(){
//        OrderActions orderActions = new OrderActions();
//        String username = LoadProperties.user.getProperty("tester23.username");
//        String password = LoadProperties.user.getProperty("tester23.password");
//        orderActions.navigateToHomePage();
//        orderActions.loginAs(username, password);
//        orderActions.initializeLogin();
//    }

    @Test()
    public void test_createPurchaseOrderForSingleProduct(){
        Products testBook = Products.HITCHHIKERS_GUIDE;
        String username = LoadProperties.user.getProperty("tester23.username");
        String password = LoadProperties.user.getProperty("tester23.password");
        OrderActions orderActions = new OrderActions();
        ShoppingCartReviewPage shoppingCartReviewPage = new ShoppingCartReviewPage();

        orderActions.initializeLogin();
        orderActions.navigateToHomePage();
        orderActions.loginAs(username, password);
        orderActions.initializeCart();

        Book bookProductPage = orderActions.loadProductPageDataIntoProductObject(testBook);
        orderActions.addProductToShoppingCartReview(testBook);
        String actualCartSubtotalPrice = shoppingCartReviewPage.getCartSubtotal();
        String expectedBookPrice =  bookProductPage.getOfferPrice();
        orderActions.checkMatchingValues("Verify the Price Listed for the book:", actualCartSubtotalPrice, expectedBookPrice);
        assertEquals(actualCartSubtotalPrice, expectedBookPrice, "SHOPPING_CART_REVIEW: Cart Subtotal not what is expected!");
        //Book bookShoppingCart = orderActions.loadShoppingCartDataIntoProductObject(testBook);
    }

    private void assertEquals(String actualCartSubtotalPrice, String expectedBookPrice, String string) {
		// TODO Auto-generated method stub
		
	}

	@AfterClass
    public void tearDown(){
        driver.quit();
    }

}
