package com.springBootExample.mainController;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBootExample.dto.ResponseDTO;
import com.springBootExample.dto.UserDTO;
import com.springBootExample.model.User;
import com.springBootExample.reminderBot.Messaging;
import com.springBootExample.reminderBot.ReminderBot;
import com.springBootExample.service.IUserService;

@Controller
public class DemoController {

	@Autowired
	IUserService userService;

	@RequestMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String index() {
		/* this.userService.getUser(1l) */
		return "index";
	}

	@RequestMapping(value = "/user-data", method = RequestMethod.POST)

	public /* @ResponseBody String */ void InserData(@RequestParam(name = "username") String userName,

			@RequestParam(name = "password") String password) {

		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		this.userService.inserUser(user);
		/* return ""; */

	}

	@RequestMapping(value = "/get-data", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody UserDTO getData() throws Throwable {

		try {
			this.userService.addReminder();

		} catch (Exception e) {
			System.out.println(e);
		}

		return this.userService.getUser(2l);
		/* return this.userService.reminder(); */

	}

	@RequestMapping(value = "/request-data", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void readData(@RequestBody ReminderBot reminderBot) throws Throwable {

		final String PAGE_ACCESS_TOKEN = "EAASFsxXgI4QBALFNkdDfxk3Ej7I0oVbpUfqBfWO1guJkrbvWfRlWPo8WdiZBXplIRABCFbO9wDxLjy3RrdK7MaiUQPkzJTbunVjAjPRc7XjGnMkVEgDatnZCBvQTwVDjDfVkTChzpBShZAMpwm4O9Pj76FTU019LoZBN2UZBZA0QZDZD";
		String sender_Id = String
				.valueOf(reminderBot.getEntry().iterator().next().getMessaging().iterator().next().getSender().getId());
		final String url = "https://graph.facebook.com/v2.6/me/messages?access_token=" + PAGE_ACCESS_TOKEN;

		/*
		 * RestTemplate restTemplate = new RestTemplate(); ResponseDTO rs = new
		 * ResponseDTO(); rs.getRecipient().setId(sender_Id); // String Message
		 * = getTextMessage(reminderBot); rs.getMessage().setText("hi");
		 * restTemplate.postForObject(uri, rs, ResponseDTO.class);
		 */
		// Create a new RestTemplate instance
/*		RestTemplate restTemplate = new RestTemplate();

		ResponseDTO rs = new ResponseDTO();
		rs.getRecipient().setId(sender_Id); // String Message
		String Message = getTextMessage(reminderBot);
		rs.getMessage().setText(Message);

		// Add the Jackson and String message converters
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		// Make the HTTP POST request, marshaling the request to JSON, and the
		// response to a String
		String response = restTemplate.postForObject(url, rs, String.class);
		System.out.println(response);
		*/
		
		
		/*final String PAGE_ACCESS_TOKEN = "EEAABwjPbKcRkBAOgSl5Elrp1Nq7JepWrzkMM9P0ElpZBYhSMFj31fZBXqL2z63oy1Oo9l3996xLsHRVIrBYoYG57f49mquYq4ZCI0jYngXHIWSI8wW12nF2VLyINkUqAYIhlQwAwtMZBr3V586mZCnZA13PcZCe24AHZAqiZCo4bnjgQZDZD";
		
		final String url = "https://graph.facebook.com/v2.6/me/messages?access_token=" + PAGE_ACCESS_TOKEN;*/
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(url);
	    ObjectMapper mapper = new ObjectMapper();
	    ResponseDTO obj = new ResponseDTO();
	    obj.getRecipient().setId(sender_Id);
	    obj.getMessage().setText(getTextMessage(reminderBot));

	    String json = mapper.writeValueAsString(obj);;
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    /*assertThat(response.getStatusLine().getStatusCode());*/
	    System.out.println(response);
	    client.close();
	    
	}

	@RequestMapping(value = "/request-data", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String readData(@RequestParam(name = "hub.mode") String hubMode,

			@RequestParam(name = "hub.verify_token") String hubVerifyToken,

			@RequestParam(name = "hub.challenge") String hubChallenge) throws Throwable {

		System.out.println(hubMode + "  :  " + hubVerifyToken + " :   " + hubChallenge);
		return hubChallenge;

	}

	private String getTextMessage(ReminderBot reminderBot) {
		Set<Messaging> SetOfMessage = reminderBot.getEntry().iterator().next().getMessaging();
		Iterator<Messaging> itr = SetOfMessage.iterator();

		return itr.next().getMessage().getText();
	}

}
