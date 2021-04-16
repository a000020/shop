package cn.tedu.shop.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.http.HttpResponse;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;

import cn.tedu.shop.service.ICartService;
import cn.tedu.shop.service.IOrderService;
import cn.tedu.shop.service.impl.PaypalService;
import cn.tedu.shop.util.JsonResult;

@RestController
@RequestMapping("paypal")
public class PaypalController extends BaseController {
	
	@Autowired private PaypalService paypalService;
	@Autowired private IOrderService orderService;
	@Autowired private ICartService cartService;
	
	@RequestMapping("createPay")
	public JsonResult<String> createPaypal(Integer aid,HttpSession session)throws IOException,JSONException{
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		HttpResponse<Order> data = paypalService.createOrderWithMiniPayload(uid);
		orderService.create(aid, uid, username);
		cartService.deleteByUid(uid);
		for(LinkDescription link:data.result().links()){
			System.err.println("\t"+link.rel()+"\tCall Type:" +link.method());
		}
		session.setAttribute("orderSession", data.result().id());
		return new JsonResult<>(SUCCESS,data.result().links().get(1).href());
	}
	
	@RequestMapping("capturePay")
	public JsonResult<String> capturePaypal(OrderRequest res,HttpSession session) throws IOException,JSONException{
		String orderId = session.getAttribute("orderSession").toString();
		System.err.println(orderId);
		HttpResponse<Order> data = paypalService.captureOrder(res, orderId, true);
		return new JsonResult<>(SUCCESS,"capture pay successfully");
	}
	
}
