package cn.tedu.shop.service;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mockito.internal.util.StringUtil;
import org.springframework.util.StringUtils;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;


public class PayPalClient {
	
	String clientId = "AZpa9A3o1yKSFJpXdRXh9LP72bp1OUrOFGYaHlioWBwQtLa-H3zNKkrEFwrInzn1C-LBF-odQACsyunQ";
	String secret = "EBAFeq5fyRrgxRBNA2k_gfm_JolzR-4yLGBA-plQG6U6aoNWVchKqEva_RzgvTW1hmRDDr6sFbyuKNcZ";
	
	public  PayPalHttpClient client(){
		PayPalEnvironment environment = new PayPalEnvironment.Live(clientId, secret);
		environment = new PayPalEnvironment.Sandbox(clientId, secret);
		PayPalHttpClient client = new PayPalHttpClient(environment);
		client.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(900));
		return client;
	}
	
	public String prettyPrint(JSONObject jo,String pre) throws JSONException{
		Iterator<?> keys = jo.keys();
		StringBuilder pretty = new StringBuilder();
		while(keys.hasNext()){
			String key = (String) keys.next();
			pretty.append(String.format("%s%s: ", pre, StringUtils.capitalize(key)));
			if(jo.get(key) instanceof JSONObject){
				pretty.append(prettyPrint(jo.getJSONObject(key), pre+"\t"));
			}else if(jo.get(key) instanceof JSONArray){
				for(int i=0;i<jo.getJSONArray(key).length();i++){
					prettyPrint((JSONObject)jo.getJSONArray(key).get(i), pre+"\t\t");
				}
			}else{
				pretty.append(String.format("%s\n", jo.getString(key)));
			}
		}
		return pretty.toString();
	}
}
