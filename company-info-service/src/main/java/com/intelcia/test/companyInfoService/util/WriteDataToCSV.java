package com.intelcia.test.companyInfoService.util;

import java.io.PrintWriter;

import com.intelcia.test.companyInfoService.dto.CompanyInfo;
import com.opencsv.CSVWriter;

public class WriteDataToCSV {
	
	
	public static void writeDataToCsvUsingStringArray(PrintWriter writer,CompanyInfo companyInfo) {
		
		String[] CSV_HEADER = { "id", "nic", "full address", "creation date", "Full name", "TVA number" };
		try (
			CSVWriter csvWriter = new CSVWriter(writer,
		                CSVWriter.DEFAULT_SEPARATOR,
		                CSVWriter.NO_QUOTE_CHARACTER,
		                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
		                CSVWriter.DEFAULT_LINE_END);
		){
			csvWriter.writeNext(CSV_HEADER);

			
				String[] data = {
						String.valueOf(companyInfo.getId()),
						companyInfo.getNic(),
						companyInfo.getFullAdress(),
						companyInfo.getDateCreation().toString(),
						companyInfo.getFullName(),
						companyInfo.getTvaNumber()
				};
				
				csvWriter.writeNext(data);

			
			System.out.println("Write CSV using CSVWriter successfully!");
		}catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

}
