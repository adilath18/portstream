package com.portstream.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portstream.model.CommercialMarket;
import com.portstream.model.LoadCondition;
import com.portstream.model.Port;
import com.portstream.model.PortCall;
import com.portstream.model.VesselDetail;
import com.portstream.repository.CommercialMarketRepository;
import com.portstream.repository.LoadConditionRepository;
import com.portstream.repository.PortRepository;
import com.portstream.repository.VesselDetailRepository;
import com.portstream.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ExportService {

	@Autowired
	private PortRepository portRepository;

	@Autowired
	private LoadConditionRepository loadConditionRepository;

	@Autowired
	private CommercialMarketRepository commercialMarketRepository;

	@Autowired
	private VesselDetailRepository vesselDetailRepository;
	

	/**
	 * \ Read and Save Excel Sheet
	 * 
	 * @param file
	 * @return Saved Vessel Detail
	 */
	public List<VesselDetail> readExcelSheet(InputStream fis) {
		//File file = new File("C:\\Users\\Ongraph\\Downloads\\MarineTraffic_VesselExport_2018-10-07.xlsx");

		List<List<Cell>> sheetRow = new ArrayList<>();
 
		try (Workbook workbook = new XSSFWorkbook(fis);) {
			Sheet sheet = workbook.getSheetAt(1);

			Iterator<Row> rows = sheet.iterator();
			
			boolean sheetHeader = true;
			while (rows.hasNext()) {
				
				Row row = rows.next();
				
				if(sheetHeader || row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) == null) {
					sheetHeader = false;
					continue;
				}
				
				int lastColumn = Math.max(row.getLastCellNum(), Constants.IMPORT_CELL_SIZE);

				List<Cell> cells = new ArrayList<>();
				for(int i = 0 ; i < lastColumn ; i++) {
					cells.add(row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
				}

				sheetRow.add(cells);

			}

		} catch (Exception e) {
			log.error("export Sheet ", e);
		}

		return saveVessel(sheetRow);

	}

	/**
	 * Import Excel Sheet to DB
	 * 
	 * @param sheetData
	 * @return
	 */
	private List<VesselDetail> saveVessel(List<List<Cell>> sheetData) {
		List<VesselDetail> vds = new ArrayList<>();

			for (int i = 0; i < sheetData.size(); i++) {
				try {
				List<Cell> data = sheetData.get(i);

				VesselDetail vd = new VesselDetail();
				PortCall poc = new PortCall();

				vd.setImo(Math.round(getNumericValue(data.get(0))));
				vd.setCapacityDWT(Math.round(getNumericValue(data.get(1))));
				vd.setName(getStringValue(data.get(2)));

				poc.setDestinationPort(getPort(getStringValue(data.get(3)), getStringValue(data.get(4))));

				//poc.setEta((Date) data.get(5));
				poc.setOriginPort(getPort(getStringValue(data.get(6)), getStringValue(data.get(7))));

				vd.setDraught(getNumericValue(data.get(8)));
				vd.setLoadCondition(getLoadCondition(getStringValue(data.get(9))));

				poc.setPreviousOriginPort(getPort(getStringValue(data.get(10)), getStringValue(data.get(11))));

				poc.setCurrentPortCountry(getStringValue(data.get(12)));
				vd.setCommercialMarket(getCommercialMarket(getStringValue(data.get(13)), getStringValue(data.get(14))));

				vd.setPortCall(poc);
				
				vds.add(vd);
				
				} catch (Exception e) {
					log.error("Error in creating Object from Sheet Data " + i + " to Database ",  e);
				}
			}
		
			vds = vesselDetailRepository.saveAll(vds);
		return vds;

	}
	
	@SuppressWarnings("deprecation")
	private String getStringValue(Cell cell) {
		if(cell != null && cell.getCellTypeEnum() == CellType.STRING) {
				return cell.getStringCellValue();
		}
		return "";
	}
	
	@SuppressWarnings("deprecation")
	private Double getNumericValue(Cell cell) {
		if(cell != null && cell.getCellTypeEnum() == CellType.NUMERIC) {
				return cell.getNumericCellValue();
		}
		return null;
	}

	private Port getPort(String name, String country) {
		Port port = null;
		try {
			port = portRepository.findByPortName(name);
		} catch (NullPointerException n) {

		}
		if (port == null) {
			port = new Port(name, country);
			port = portRepository.saveAndFlush(port);
		}

		return port;
	}

	private CommercialMarket getCommercialMarket(String marketType, String sizeClass) {
		CommercialMarket comm = null;
		try {
			comm = commercialMarketRepository.findByMarketTypeAndSizeClass(marketType, sizeClass);
		} catch (NullPointerException n) {
		}
		if (comm == null) {
			comm = new CommercialMarket(marketType, sizeClass);
			comm = commercialMarketRepository.saveAndFlush(comm);
		}

		return comm;
	}

	private LoadCondition getLoadCondition(String condition) {
		LoadCondition lc = null;
		try {
			lc = loadConditionRepository.findByCondition(condition);
		} catch (NullPointerException n) {
		}
		if (lc == null) {
			lc = new LoadCondition(condition);
			lc = loadConditionRepository.saveAndFlush(lc);
		}

		return lc;
	}

}
