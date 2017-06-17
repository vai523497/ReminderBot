/**
 * 
 */
package com.springBootExample.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBootExample.dto.ResponseDTO;
import com.springBootExample.reminderBot.Messaging;
import com.springBootExample.reminderBot.ReminderBot;

/**
 * @author VAIBHAV-PC
 *
 */
@Component
public class GetResponse {

	public String generateResponse(ReminderBot reminderBot) throws ClientProtocolException, IOException, JSONException {
		ObjectMapper mapper = new ObjectMapper();
		String response1 = "";
		String reminderBot1 = mapper.writeValueAsString(reminderBot);
		JSONObject jSONObject = new JSONObject(reminderBot1);
		JSONArray entry = (JSONArray) jSONObject.get("entry");
		for (int i = 0; i < entry.length(); i++) {
			System.out.println(i);
			JSONObject jsonobj = (JSONObject) entry.get(i);
			JSONArray messaging = (JSONArray) jsonobj.get("messaging");
			JSONObject messagingObject = (JSONObject) messaging.get(0);
			JSONObject sender = (JSONObject) messagingObject.get("sender");
			JSONObject message = (JSONObject) messagingObject.get("message");

			String sender_Id = (String) String.valueOf(sender.get("id"));
			String text = (String) message.get("text");
			System.out.println(sender_Id + " : " + text);
			sendReponse(sender_Id,text,mapper);

		}
		/*
		 * JSONObject jsonobj = (JSONObject)entry.get(0); JSONArray messaging
		 * =(JSONArray)jsonobj.get("messaging"); JSONObject messagingObject =
		 * (JSONObject)messaging.get(0); JSONObject sender =
		 * (JSONObject)messagingObject.get("sender"); JSONObject message
		 * =(JSONObject)messagingObject.get("message");
		 */

		/*
		 * String sender_Id= (String)String.valueOf(sender.get("id")); String
		 * text = (String)message.get("text"); String sender_Id = String
		 * .valueOf(reminderBot.getEntry().iterator().next().getMessaging().
		 * iterator().next().getSender().getId());
		 * System.out.println(sender_Id+" : "+text); CloseableHttpClient client
		 * = HttpClients.createDefault(); HttpPost httpPost = new HttpPost(
		 * FBCHATPropeties.getProperty("URL")
		 * +"EAAaNuhGbwD4BAJooVbNuZAzJdg1ZAh6d7wZCq4RqhvrEwmRXx2UcroqM5PUgcUT4gOAX6fZAeReJKKiKW85qh7ohiqq10ZBdJZCSxEu5mIeGZCjW72p2jMZA9hCzWscwdaCPZCcLZCXluDfJq8PBBI2ZCZC2oE6wzMqTioZACbqoJrifYSAZDZD"
		 * ); System.out.println(
		 * "EAAaNuhGbwD4BAJooVbNuZAzJdg1ZAh6d7wZCq4RqhvrEwmRXx2UcroqM5PUgcUT4gOAX6fZAeReJKKiKW85qh7ohiqq10ZBdJZCSxEu5mIeGZCjW72p2jMZA9hCzWscwdaCPZCcLZCXluDfJq8PBBI2ZCZC2oE6wzMqTioZACbqoJrifYSAZDZD"
		 * ); ResponseDTO obj = new ResponseDTO();
		 * obj.getRecipient().setId(sender_Id); obj.getMessage().setText(text);
		 * 
		 * String json = mapper.writeValueAsString(obj); StringEntity entity =
		 * new StringEntity(json); httpPost.setEntity(entity);
		 * httpPost.setHeader("Accept", "application/json");
		 * httpPost.setHeader("Content-type", "application/json");
		 * 
		 * CloseableHttpResponse response = client.execute(httpPost);
		 * System.out.println(response.getStatusLine().getStatusCode());
		 * client.close(); httpPost.releaseConnection();
		 */
		return response1;

	}

	private void sendReponse(String sender_id,String text,ObjectMapper mapper) throws IOException {
		CloseableHttpClient client = null;
		HttpPost httpPost=null;
		try {
			client = HttpClients.createDefault();
			 httpPost = new HttpPost(FBCHATPropeties.getProperty("URL")
					+ "EAAHCrYCu9jIBAH0ur8TuHPHqmZAlXbilfJL9ZAQp7gBYiouNvfF58ewWHt0Nd6T8DeX32ZCH6INYM2qhjqVY4yLI7i49owzNYX5N7ZAtMUzTneNrsG3tKPWZB9ASdZAnwj3JPbMxldE0jZA2vBkC5jpBoB79hvPoygtISH7g8lqvgZDZD");
			ResponseDTO obj = new ResponseDTO();
			obj.getRecipient().setId(sender_id);
			obj.getMessage().setText(text);
			JSONObject recipient = new JSONObject();
			recipient.put("id",sender_id);
			JSONObject message = new JSONObject();
			message.put("text",text);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("recipient", recipient);
			responseJson.put("message", message);
			
			

			String json = responseJson.toString();
			StringEntity entity = new StringEntity(json);
			/*{"recipient":{"id":"1804149342945278"},"message":{"text":"hello"}}*/
			 
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			CloseableHttpResponse response = client.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());
			
			
		} catch (Exception e) {
			System.out.println("Exception during generating response"+e);
		} finally {
			if(client!=null){
				client.close();
			}
			httpPost.releaseConnection();
			

		}
	}

	private String getTextMessage(ReminderBot reminderBot) {
		Set<Messaging> SetOfMessage = reminderBot.getEntry().iterator().next().getMessaging();
		Iterator<Messaging> itr = SetOfMessage.iterator();

		return itr.next().getMessage().getText();

	}

}
