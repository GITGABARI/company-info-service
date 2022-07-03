package com.intelcia.test.companyInfoService.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyInfo{	
	
	private Long id;

	private String nic;
    
	private String fullAdress;
	
	private LocalDate  dateCreation;
	
	private String fullName;
	
	private String TvaNumber;
	
    
}
