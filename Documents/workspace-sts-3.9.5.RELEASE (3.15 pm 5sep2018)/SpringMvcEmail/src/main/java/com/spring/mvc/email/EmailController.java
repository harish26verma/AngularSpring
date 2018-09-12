package com.spring.mvc.email;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
public class EmailController {

	static String emailToRecipient, emailSubject, emailMessage;
	static String Reservation_No = "";
	static String Go_MMT_ID = "";
	static String Guest_name = "";
	static String Phone_no = "";
	static String Room_type = "";
	static String Meal_plan = "";
	static String No_of_rooms = "";
	static String No_of_persons = "";
	static String Arrival_Date = "";
	static String Departure_Date = "";
	static String No_of_nights = "";
	static String Costs_per_night_per_room = "";
	static String Hotel_taxes_to_be_collected_from_guest = "";
	static String Billing_Type = "";

	static String emailFromRecipient = "harish26verma@gmail.com";
	static ModelAndView modelViewObj;

	@Autowired
	private JavaMailSender mailSenderObj;

	
	
	@Autowired
	Configuration freemarkerConfig;  

	@RequestMapping(value = { "/", "emailForm" }, method = RequestMethod.GET)
	public ModelAndView showEmailForm(Model modeltemplate) {

		AxisRoom roomList = new AxisRoom("0039001170", "NH7003094126197", "Pravin sahu", "919006633325",
				"Standard Room", "CP = Room+Breakfast	", "1", "2", "10/9/2018", "11/9/2018", "1", "3099.00 + Tax",
				"null", "Bill to company");

		/*
		 * AxisRoom roomList = new AxisRoom(Reservation_No, Go_MMT_ID, Guest_name,
		 * Phone_no, Room_type, Meal_plan, No_of_rooms, No_of_persons, Arrival_Date,
		 * Departure_Date, No_of_nights, Costs_per_night_per_room,
		 * Hotel_taxes_to_be_collected_from_guest, Billing_Type);
		 */
		modeltemplate.addAttribute("Reservation_No", roomList.getReservation_No());
		modeltemplate.addAttribute("Go_MMT_ID", roomList.getGo_MMT_ID());
		modeltemplate.addAttribute("Guest_name", roomList.getGuest_name());
		modeltemplate.addAttribute("Phone_no", roomList.getPhone_no());
		modeltemplate.addAttribute("Room_type", roomList.getRoom_type());
		modeltemplate.addAttribute("Meal_plan", roomList.getMeal_plan());
		modeltemplate.addAttribute("No_of_rooms", roomList.getNo_of_rooms());
		modeltemplate.addAttribute("No_of_persons", roomList.getNo_of_persons());
		modeltemplate.addAttribute("Arrival_Date", roomList.getArrival_Date());
		modeltemplate.addAttribute("Departure_Date", roomList.getDeparture_Date());
		modeltemplate.addAttribute("No_of_nights", roomList.getNo_of_nights());
		modeltemplate.addAttribute("Costs_per_night_per_room", roomList.getCosts_per_night_per_room().toString());
		modeltemplate.addAttribute("Hotel_taxes_to_be_collected_from_guest",
				roomList.getHotel_taxes_to_be_collected_from_guest().toString());
		modeltemplate.addAttribute("Billing_Type", roomList.getBilling_Type());

		modelViewObj = new ModelAndView("templateflt");
		return modelViewObj;
	}

	@RequestMapping(value = { "/", "addAxisHotel" }, method = RequestMethod.POST)
	public ModelAndView addRoom(HttpServletRequest request, Model modeltemplate) {

		Reservation_No = request.getParameter("Reservation_No");
		Go_MMT_ID = request.getParameter("Go_MMT_ID");
		Guest_name = request.getParameter("Guest_name");
		Phone_no = request.getParameter("Phone_no");
		Room_type = request.getParameter("Room_type");
		Meal_plan = request.getParameter("Meal_plan");
		No_of_rooms = request.getParameter("No_of_rooms");
		No_of_persons = request.getParameter("No_of_persons");
		Arrival_Date = request.getParameter("Arrival_Date");
		Departure_Date = request.getParameter("Departure_Date");
		No_of_nights = request.getParameter("No_of_nights");
		Costs_per_night_per_room = request.getParameter("Costs_per_night_per_room");
		Hotel_taxes_to_be_collected_from_guest = request.getParameter("Hotel_taxes_to_be_collected_from_guest");
		Billing_Type = request.getParameter("Billing_Type");

		modelViewObj = new ModelAndView("addAxisHotel");
		return modelViewObj;

	}

	// This Method Is Used To Prepare The Email Message And Send It To The Client
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST)
	public ModelAndView sendEmailToClient(HttpServletRequest request,
			final @RequestParam CommonsMultipartFile attachFileObj) {
		// Reading Email Form Input Parameters
		emailSubject = request.getParameter("subject");
		emailMessage = request.getParameter("message");
		emailToRecipient = request.getParameter("mailTo");

		// Logging The Email Form Parameters For Debugging Purpose
		System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= "
				+ emailMessage + "\n");

		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMsgHelperObj.setTo(emailToRecipient);
				mimeMsgHelperObj.setFrom(emailFromRecipient);
				mimeMsgHelperObj.setText(emailMessage);
				mimeMsgHelperObj.setSubject(emailSubject);

				
				
				
				
				Map<String, Object> model = new HashMap();
				model.put("emailUser", emailFromRecipient);

				Template t = freemarkerConfig.getTemplate("templateflt.ftl");
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
				mimeMsgHelperObj.setText(html, true);

				// Determine If There Is An File Upload. If Yes, Attach It To The Client Email
				if ((attachFileObj != null) && (attachFileObj.getSize() > 0) && (!attachFileObj.equals(""))) {
					System.out.println("\nAttachment Name?= " + attachFileObj.getOriginalFilename() + "\n");
					mimeMsgHelperObj.addAttachment(attachFileObj.getOriginalFilename(), new InputStreamSource() {
						public InputStream getInputStream() throws IOException {
							return attachFileObj.getInputStream();
						}
					});
				} else {
					System.out.println("\nNo Attachment Is Selected By The User. Sending Text Email!\n");
				}
			}
		});
		System.out.println("\nMessage Send Successfully.... Hurrey!\n");

		modelViewObj = new ModelAndView("success", "messageObj", "Thank You! Your Email Has Been Sent!");
		return modelViewObj;
	}
}
