package com.example.JasperReportProject.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.JasperReportProject.service.PdfService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class PdfController {
	
	@Autowired
	PdfService pdfService;
	
	@RequestMapping(value = { "/printPdf" }, method = { RequestMethod.POST }, 
    		produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
	public void printPdf(HttpServletRequest request) throws IOException, JRException {
		pdfService.printPdf();
	}

}
