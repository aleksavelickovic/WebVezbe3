package com.ftn.OWPVezba3.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import com.ftn.OWPVezba3.bean.Osoba;

@Controller
@RequestMapping("login")
public class LoginController implements ServletContextAware {
	
	
	@Autowired
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	
	}
	
	@GetMapping
	public String ulogujSe(@RequestParam String korIme, @RequestParam String lozinka) { //TODO zasto ne radi preko POST?
		List<Osoba> osobe = (List<Osoba>) this.servletContext.getAttribute("osobe");
		for (Osoba osoba : osobe) {
			if (osoba.getKorIme().equals(korIme) && osoba.getLozinka().equals(lozinka)) {
				return "dodaj-osobu.html";
			}
		}
		return "uloguj-se.html";
	}
}
