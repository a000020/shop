package cn.tedu.shop.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Capture;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.Payer;
import com.paypal.orders.PurchaseUnit;
import com.paypal.orders.PurchaseUnitRequest;

import cn.tedu.shop.service.ICartService;
import cn.tedu.shop.service.PayPalClient;
import cn.tedu.shop.vo.CartVO;

@Service
public class PaypalService extends PayPalClient {
	
	@Autowired
	private ICartService cartService;
	
	@Transactional
	private OrderRequest buildMiniRequsetBody(Integer uid){
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.checkoutPaymentIntent("CAPTURE");
		ApplicationContext applicationContext = new ApplicationContext()
				.cancelUrl("http://localhost:8080/web/cart.html").returnUrl("http://localhost:8080/web/paySuccess.html");
		orderRequest.applicationContext(applicationContext);
		List<CartVO> carts = cartService.getByUid(uid);
		Long totalPrice = 0L;
		for (CartVO cartVO : carts) {
			totalPrice += cartVO.getPrice()*cartVO.getNum();
		}
		
		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
		PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
				.amountWithBreakdown(new AmountWithBreakdown().currencyCode("TWD").value(totalPrice.toString()));
		purchaseUnitRequests.add(purchaseUnitRequest);
		orderRequest.purchaseUnits(purchaseUnitRequests);
		return orderRequest.purchaseUnits(purchaseUnitRequests);
		
	}
	
	public HttpResponse<Order> createOrderWithMiniPayload(Integer uid) throws IOException,JSONException{
		boolean debug = true;
		OrdersCreateRequest request = new OrdersCreateRequest();
		request.header("prefer","return=representation");
		request.requestBody(buildMiniRequsetBody(uid));
		HttpResponse<Order> response = client().execute(request);
		if (debug) {
			if (response.statusCode() == 201) {
				System.out.println("Status Code: " + response.statusCode());
				System.out.println("Status: " + response.result().status());
				System.out.println("Order ID: " + response.result().id());
				System.out.println("Intent: " + response.result().checkoutPaymentIntent());
				System.out.println("Links: ");
				for (LinkDescription link : response.result().links()) {
					System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
				}
				System.out.println("Total Amount: " + response.result().purchaseUnits().get(0).amountWithBreakdown().currencyCode()
						+ " " + response.result().purchaseUnits().get(0).amountWithBreakdown().value());
				System.out.println("Full response body:");
				System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
			}
		}
		return response;
	}
	
	public OrderRequest buildRequestBody(){
		return new OrderRequest();
	}
	
	public HttpResponse<Order> captureOrder(OrderRequest res,String orderId, boolean debug) throws IOException,JSONException {
		OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
		request.requestBody(res);
		HttpResponse<Order> response = client().execute(request);
		if (debug) {
			System.out.println("Status Code: " + response.statusCode());
			System.out.println("Status: " + response.result().status());
			System.out.println("Order ID: " + response.result().id());
			System.out.println("Links: ");
			for (LinkDescription link : response.result().links()) {
				System.out.println("\t" +link.rel() + ": " + link.href());
			}
			System.out.println("Capture ids:");
			for (PurchaseUnit purchaseUnit : response.result().purchaseUnits()) {
				for (Capture capture : purchaseUnit.payments().captures()) {
					System.out.println("\t" + capture.id());
				}
			}
			System.out.println("Buyer: ");
			Payer buyer = response.result().payer();
			System.out.println("\tEmail Address: " + buyer.email());
			System.out.println("\tName: " + buyer.name().givenName() + " " + buyer.name().surname());
			System.out.println("Full response body:");
			System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
		}
		return response;
	}
}
