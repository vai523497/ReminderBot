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

import com.springBootExample.dto.UserDTO;
import com.springBootExample.model.User;
import com.springBootExample.service.IUserService;

@Controller
public class DemoController {

	@Autowired
	IUserService userService;

	@RequestMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/user-data", method = RequestMethod.POST)
	public void InserData(@RequestParam(name = "username") String userName,
			@RequestParam(name = "password") String password) {

		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		this.userService.inserUser(user);

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
	public void readData(@RequestBody String reminderBot) throws Throwable {
		String response = userService.sendResponse(reminderBot);
		System.out.println(response);
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
