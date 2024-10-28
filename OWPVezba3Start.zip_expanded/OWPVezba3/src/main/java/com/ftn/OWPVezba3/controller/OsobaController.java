package com.ftn.OWPVezba3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.ftn.OWPVezba3.bean.Osoba;

@Controller
@RequestMapping("osoba")
public class OsobaController implements ServletContextAware {
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	
	}
	
	@PostConstruct
	public void init() {
		List<Osoba> osobe = new ArrayList<Osoba>();
		this.servletContext.setAttribute("osobe", osobe);
	}
	
	@GetMapping
	public String getDodajOsobu() {
		return "/dodaj-osobu.html";
	}
	
	@GetMapping("prikazOsobe")
	@ResponseBody
	public String prikaziOsobu(@RequestParam int index) {
		List<Osoba> osobe = (List<Osoba>) this.servletContext.getAttribute("osobe");
		return "<html><body>" + "<p>" + osobe.get(index).getIme() + "</p></body></html>";
	}
	
	@PostMapping("dodajOsobu")
	@ResponseBody
	public String dodajOsobu(@RequestParam String ime, @RequestParam String prezime) {
		Osoba osoba = new Osoba(ime, prezime);
		
		List<Osoba> osobe = (List<Osoba>) this.servletContext.getAttribute("osobe");
		osobe.add(osoba);
		
		String htmlPrikaz = "<html> "
				+ "<body>";
		for(int i = 0; i < osobe.size(); i++) {
			Osoba osobaPrikaz = osobe.get(i);
		 htmlPrikaz += "<p>" + (i+1) + "</p>" + "<p>Ime: " + osobaPrikaz.getIme() + "</p>" + "<p>Prezime: " + osobaPrikaz.getPrezime() + "</p>"
				 + "<a href=\"/osoba/prikazOsobe?index=" + i +"\">Prikazi Osobu</a>"
				 + "<a href=\"/osoba/obrisiOsobu?index=" + i +"\">Obrisi Osobu</a>"
				 +" <form action=\"obrisiOsobu\" method=\"post\">\r\n"
				 + "    <button name=\"index\" value=\"" + i + "\">Obrisi osobu</button>\r\n"
				 + "</form>";
		 		
		};
		
		htmlPrikaz += "</body></html>";
		
		return htmlPrikaz;
			
	}
	
	@GetMapping("ispisiOsobe")
	@ResponseBody
	public String ispisiOsobe() {
		
		
		List<Osoba> osobe = (List<Osoba>) this.servletContext.getAttribute("osobe");
		
		
		String htmlPrikaz = "<html> "
				+ "<body>";
		for(int i = 0; i < osobe.size(); i++) {
			Osoba osobaPrikaz = osobe.get(i);
		 htmlPrikaz += "<p>" + (i+1) + "</p>" + "<p>Ime: " + osobaPrikaz.getIme() + "</p>" + "<p>Prezime: " + osobaPrikaz.getPrezime() + "</p>"
				 + "<a href=\"/osoba/prikazOsobe?index=" + i +"\">Prikazi Osobu</a>"
				 +" <form action=\"obrisiOsobu\" method=\"post\">\r\n"
				 + "    <button name=\"index\" value=\"" + i + "\">Obrisi osobu</button>\r\n"
				 + "</form>";
		 		
		};
		
		htmlPrikaz += "</body></html>";
		
		return htmlPrikaz;
			
	}
	
	@PostMapping("obrisiOsobu")
	@ResponseBody
	public String obrisiOsobu(@RequestParam int index, HttpServletResponse response) {
		List<Osoba> osobe = (List<Osoba>) this.servletContext.getAttribute("osobe");
		osobe.remove(index);
		String htmlPrikaz = "<html> "
				+ "<body>";
		for(int i = 0; i < osobe.size(); i++) {
			Osoba osobaPrikaz = osobe.get(i);
		 htmlPrikaz += "<p>" + (i+1) + "</p>" + "<p>Ime: " + osobaPrikaz.getIme() + "</p>" + "<p>Prezime: " + osobaPrikaz.getPrezime() + "</p>"
				 + "<a href=\"/osoba/prikazOsobe?index=" + i +"\">Prikazi Osobu</a>"
				 + " <form action=\"obrisiOsobu\" method=\"post\">\r\n"
				 + "    <button name=\"index\" value=\"" + i + "\">Obrisi osobu</button>\r\n"
				 + "</form>";
		 		
		};
		
		htmlPrikaz += "</body></html>";
		
		return htmlPrikaz;
//		return "<html><body><form action=\"ispisiOsobe	\"  method=\"GET\">"
//				+ "<button type=\"submit\">Nazad</button> "
//				+ "</form></body></html>";
//		try {
//			response.sendRedirect("/osobe/dodajOsobu");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		return "/dodaj-osobu.html";
	}

	
	
}
