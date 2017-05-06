package com.springBootExample.mainController;

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
		String PAGE_ACCESS_TOKEN = "EAATQXuGjJdEBAEfNDSzZCr71oaPZAsn49b53TxgBAlzqJZBAZAJJjJ9GOIaBVrZBOW4QF3Mqpw3REoEo8ZCAg4SdjTtAfVUpH5ImNcrWcy3X0RdRxpk5rGH8bTJrTavFkVBrlZA23JZAoD5NwpNZC6Wk31aZC0sIosY0VzjlAzwoUgTAZDZD";

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.getRecipient().setId("");
		responseDTO.getMessage().setText("hi");
		responseDTO.getMessage().setMetadata("");

		final String uri = "https://graph.facebook.com/v2.6/me/messages?access_token=" + PAGE_ACCESS_TOKEN;

		System.out.println("Test wit github");

		

	}

	@RequestMapping(value = "/request-data", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String readData(@RequestParam(name = "hub.mode") String hubMode,

			@RequestParam(name = "hub.verify_token") String hubVerifyToken,

			@RequestParam(name = "hub.challenge") String hubChallenge) throws Throwable {

		System.out.println(hubMode + "  :  " + hubVerifyToken + " :   " + hubChallenge);
		return hubChallenge;

	}

}
