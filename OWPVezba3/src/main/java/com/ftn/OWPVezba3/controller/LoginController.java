package com.ftn.OWPVezba3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import com.ftn.OWPVezba3.bean.Osoba;

@Controller
@RequestMapping(value = "login", method = RequestMethod.POST)
public class LoginController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}

	@PostMapping
	public void ulogujSe(@RequestParam String korIme, @RequestParam String lozinka, HttpServletResponse response)
			throws IOException, ServletException {
		List<Osoba> osobe = (List<Osoba>) this.servletContext.getAttribute("osobe");
		boolean ulogovan = false;
		for (Osoba osoba : osobe) {
			if (osoba.getKorIme().equals(korIme) && osoba.getLozinka().equals(lozinka)) {
				ulogovan = true;
				break;
			}
		}
		if (ulogovan) {
			response.sendRedirect("./dodaj-osobu.html");
		} else {
			response.sendRedirect("./uloguj-se.html");
		}
	}

}
