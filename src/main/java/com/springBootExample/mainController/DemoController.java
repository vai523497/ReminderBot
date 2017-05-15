package com.springBootExample.mainController;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

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
		final String PAGE_ACCESS_TOKEN = "EAASawSn52GsBAK2I8XBNoaoPRD3cZB87VzNzZCcKZB5TZAVCv8wdYCeVzTzS5DOzOZBC7JFZAO5XCe4lk7KRaFhdd9afXxNTwblZAzq70jR9s9Id4dM1LZA1scmATv9oWFtgLU4wWvn9KRC0FwxgAAB2avVnV5zJbTPJH6JpAory1wZDZD";
		String sender_Id = String
				.valueOf(reminderBot.getEntry().iterator().next().getMessaging().iterator().next().getSender().getId());
		final String uri = "https://graph.facebook.com/v2.6/me/messages?access_token=" + PAGE_ACCESS_TOKEN;
		RestTemplate restTemplate = new RestTemplate();
		ResponseDTO rs = new ResponseDTO();
		rs.getRecipient().setId(sender_Id);
		String Message = getTextMessage(reminderBot);
		rs.getMessage().setText(Message);
		restTemplate.postForObject(uri, rs, ResponseDTO.class);
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
