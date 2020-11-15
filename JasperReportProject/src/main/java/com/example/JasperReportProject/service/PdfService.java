package com.example.JasperReportProject.service;



import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.JasperReportProject.model.GroupReport;
import com.example.JasperReportProject.model.Member;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Transactional(rollbackFor = Exception.class)
@Service("PdfService")
public class PdfService {
	
	@Autowired
	private HttpServletResponse response;
	
	public void printPdf() throws IOException, JRException {
		// parameter report
		JasperPrint print = parameterReport();
		// field report
//		JasperPrint print = fieldReport();
		// image report
//		JasperPrint print = imageReport();
		// subReport report
//		JasperPrint print = subReport();
		// group report
//		JasperPrint print = groupReport();
		List<JasperPrint> jasperPrintList = new ArrayList<>();
		jasperPrintList.add(print);
		pdfExport(jasperPrintList);
	}
	
	/**
	 * Parameter report 套版
	 * @return
	 * @throws IOException
	 * @throws JRException
	 */
	private JasperPrint parameterReport() throws IOException, JRException {
		// 讀取jasper模板檔案.
		File file = new ClassPathResource("/templates/jasper_lab_parameter.jasper").getFile();
		// 轉為JaperReport物件
		JasperReport report = (JasperReport) JRLoader.loadObject(file);
		// Parameter
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("TITLE", "TTP讀書會");
		// 將資料套入模板
		JasperPrint print = JasperFillManager.fillReport(report, parameter, new JREmptyDataSource());
		return print;
	}
	
	/**
	 * Field report 套版
	 * @return
	 * @throws IOException
	 * @throws JRException
	 */
	private JasperPrint fieldReport() throws IOException, JRException {
		// 讀取jasper模板檔案.
		File file = new ClassPathResource("/templates/jasper_lab_field.jasper").getFile();
		// 轉為JaperReport物件
		JasperReport report = (JasperReport) JRLoader.loadObject(file);
		// Parameter
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("TITLE", "TTP讀書會");
		// Field
		List<Member> memberList = new ArrayList<>();
		memberList.add(new Member("Wayne", "Male", "企業數位創新應用處", "Git淺談"));
		memberList.add(new Member("Simon", "Male", "數位金融暨行動應用處", "Servlet"));
		// 將資料套入模板
		JasperPrint print = JasperFillManager.fillReport(report, parameter, new JRBeanCollectionDataSource(memberList));
		return print;
	}
	
	/**
	 * Image report 套版
	 * @return
	 * @throws IOException
	 * @throws JRException
	 */
	private JasperPrint imageReport() throws IOException, JRException {
		// 讀取jasper模板檔案.
		File file = new ClassPathResource("/templates/jasper_lab_img.jasper").getFile();
		File img = new ClassPathResource("/static/img/test.png").getFile();
		// 轉為JaperReport物件
		JasperReport report = (JasperReport) JRLoader.loadObject(file);
		// Parameter
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("TITLE", "TTP讀書會");
		parameter.put("IMG", img);
		// Field
		List<Member> memberList = new ArrayList<>();
		memberList.add(new Member("Wayne", "Male", "企業數位創新應用處", "Git淺談"));
		memberList.add(new Member("Simon", "Male", "數位金融暨行動應用處", "Servlet"));
		// 將資料套入模板
		JasperPrint print = JasperFillManager.fillReport(report, parameter, new JRBeanCollectionDataSource(memberList));
		return print;
	}
	
	/**
	 * subReport report 套版
	 * @return
	 * @throws IOException
	 * @throws JRException
	 */
	private JasperPrint subReport() throws IOException, JRException {
		// 讀取jasper模板檔案.
		File file = new ClassPathResource("/templates/jasper_lab_subReport.jasper").getFile();
		File subfile = new ClassPathResource("/templates/jasper_lab_subReport_subreport.jasper").getFile();
		File img = new ClassPathResource("/static/img/test.png").getFile();
		// 轉為JaperReport物件
		JasperReport report = (JasperReport) JRLoader.loadObject(file);
		JasperReport subreport = (JasperReport) JRLoader.loadObject(subfile);
		// Parameter
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("TITLE", "TTP讀書會");
		parameter.put("IMG", img);
		// subreport jasper模板
		parameter.put("SUBREPORT", subreport);
		// subreport 資料源
		List<Member> memberList = new ArrayList<>();
		memberList.add(new Member("Wayne", "Male", "企業數位創新應用處", "Git淺談"));
		memberList.add(new Member("Simon", "Male", "數位金融暨行動應用處", "Servlet"));
		parameter.put("TABLE_DATA", new JRBeanCollectionDataSource(memberList));
		// 將資料套入模板
		JasperPrint print = JasperFillManager.fillReport(report, parameter, new JREmptyDataSource());
		return print;
	}
	
	/**
	 * group report 套版
	 * @return
	 * @throws IOException
	 * @throws JRException
	 */
	private JasperPrint groupReport() throws IOException, JRException {
		// 讀取jasper模板檔案.
		File file = new ClassPathResource("/templates/jasper_lab_group.jasper").getFile();
		File subfile = new ClassPathResource("/templates/jasper_lab_subReport_subreport.jasper").getFile();
		File img = new ClassPathResource("/static/img/test.png").getFile();
		// 轉為JaperReport物件
		JasperReport report = (JasperReport) JRLoader.loadObject(file);
		JasperReport subreport = (JasperReport) JRLoader.loadObject(subfile);
		// Parameter
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("TITLE", "TTP讀書會");
		parameter.put("IMG", img);
		// subreport jasper模板
		parameter.put("SUBREPORT", subreport);
		// Field
		List<GroupReport> reportList = new ArrayList<>();
		List<Member> memberList = new ArrayList<>();
		memberList.add(new Member("Wayne", "Male", "企業數位創新應用處", "Git淺談"));
		memberList.add(new Member("Simon", "Male", "數位金融暨行動應用處", "Servlet"));
		reportList.add(new GroupReport("已分享", memberList));
		memberList = new ArrayList<>();
		memberList.add(new Member("Jason", "Male", "企業數位創新應用處", "Session & Cookie"));
		memberList.add(new Member("Dylan", "Male", "企業數位創新應用處", "尚未確定"));
		reportList.add(new GroupReport("未分享", memberList));
		// 將資料套入模板
		JasperPrint print = JasperFillManager.fillReport(report, parameter, new JRBeanCollectionDataSource(reportList));
		return print;
	}
	
	/**
	 * 輸出pdf
	 * @param jasperPrintList
	 * @throws IOException
	 * @throws JRException
	 */
	private void pdfExport(List<JasperPrint> jasperPrintList) throws IOException, JRException {
		// 檔名
		String fileName = URLEncoder.encode("jasper_lab.pdf", "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"" + "; filename*=UTF-8" + fileName);
		// 將套完資料的jasperPrint設定至JRPdfExporter輸出
		JRPdfExporter exporter = new JRPdfExporter();
		
		ExporterInput exporterInput = SimpleExporterInput.getInstance(jasperPrintList);
		exporter.setExporterInput(exporterInput);
		
		OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(response.getOutputStream());
		exporter.setExporterOutput(exporterOutput);
		
		exporter.exportReport();
	}


}
