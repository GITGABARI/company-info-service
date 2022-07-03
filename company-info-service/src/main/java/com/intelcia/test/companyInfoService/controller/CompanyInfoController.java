package com.intelcia.test.companyInfoService.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.intelcia.test.companyInfoService.dto.CompanyInfo;
import com.intelcia.test.companyInfoService.util.WriteDataToCSV;

@RestController
@RequestMapping("/company")
public class CompanyInfoController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.url}")
	private String apiUrl;
	
	@GetMapping(value = "/{siret}")
	public void getCompanyInfo(@PathVariable("siret") String siret, HttpServletResponse response) throws IOException {

		ResponseEntity<JsonNode> infoResponse =
		        restTemplate.exchange( apiUrl+ siret,
		                    HttpMethod.GET, null, JsonNode.class);
		JsonNode ir = infoResponse.getBody();
		
	    response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; file=customers.csv");
	    WriteDataToCSV.writeDataToCsvUsingStringArray(response.getWriter(), mapData(ir));
	}
	
	private CompanyInfo mapData(JsonNode jn) {
		
		JsonNode innerNode = jn.get("etablissement"); 
	    // get an element in that node
	    JsonNode idNode = innerNode.get("id");
	    JsonNode nicNode = innerNode.get("nic");
	    JsonNode addressNode = innerNode.get("geo_adresse");
	    JsonNode creationDateNode = innerNode.get("date_creation");
	    
	    JsonNode uniteLegaleNode = innerNode.get("unite_legale");
	    JsonNode fullNameNode = uniteLegaleNode.get("nom");
	    JsonNode tvaNode = uniteLegaleNode.get("numero_tva_intra");
	    //the customerSessionId has a String value
	    Long id = idNode.asLong();
	    return new CompanyInfo(idNode.asLong(), nicNode.asText(), addressNode.asText(), LocalDate.parse(creationDateNode.asText()),fullNameNode.asText(),tvaNode.asText());
	}
	
	

}
