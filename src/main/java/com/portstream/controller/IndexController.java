package com.portstream.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portstream.model.VesselDetail;
import com.portstream.repository.VesselDetailRepository;
import com.portstream.service.ExportService;

@Controller
public class IndexController {

	@Autowired
	private ExportService exportService;
	
	@Autowired
	private VesselDetailRepository vesselDetailRepository;

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) throws Exception {
		get(model);
		return "index";
	}

	@PostMapping("/importExcel")
	public String importFile(@RequestParam MultipartFile file, Model model) throws Exception {

		List<VesselDetail> vesselData = new ArrayList<>();
		if (!file.isEmpty()) {
			vesselData = exportService.readExcelSheet(file.getInputStream());
		}
		
		model.addAttribute("vesselData", vesselData);
		return "excelData";
	}
	
	
	@PostMapping("/get")
	public String get(Model model) throws Exception {
		model.addAttribute("vesselData", vesselDetailRepository.findAll());
		return "excelData";
	}

}
