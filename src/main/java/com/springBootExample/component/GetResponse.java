/**
 * 
 */
package com.springBootExample.component;

import java.io.IOException;

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

/**
 * @author VAIBHAV-PC
 *
 */
@Component
public class GetResponse {

	public String generateResponse(String reminderBot) throws ClientProtocolException, IOException, JSONException {
		ObjectMapper mapper = new ObjectMapper();
		String response1 = "";
		JSONObject jSONObject = new JSONObject(reminderBot);

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
			sendReponse(sender_Id, text, mapper);

		}

		return response1;

	}

	private void sendReponse(String sender_id, String text, ObjectMapper mapper) throws IOException {
		CloseableHttpClient client = null;
		HttpPost httpPost = null;
		try {
			client = HttpClients.createDefault();
			httpPost = new HttpPost(FBCHATPropeties.getProperty("URL")
					+ "EAAC1QXos99sBAAqcK3AkoJ4ehy4e96MfOT3rZBx4iKYQ9Fy0fzsdr6MhcePKaDnXiSII1NuWO1igwZAsjF6n2VCPUEBPZAJcQNiuHUHw30MXwsTqKUaUYaa0DpWCdspnsOsqikmUN2FZBl6ZADlzZBrME3DLH2zWBQRKBftJKsjwZDZD");

			JSONObject recipient = new JSONObject();
			
			recipient.put("id", sender_id);
			JSONObject message = new JSONObject();
			message.put("text", text);

			JSONObject responseJson = new JSONObject();
			responseJson.put("recipient", recipient);
			responseJson.put("message", message);

			String json = responseJson.toString();
			StringEntity entity = new StringEntity(json);

			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			CloseableHttpResponse response = client.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());

		} catch (Exception e) {
			System.out.println("Exception during generating response" + e);
		} finally {
			if (client != null) {
				client.close();
			}
			httpPost.releaseConnection();

		}
	}
	
	
	
	
	
}
