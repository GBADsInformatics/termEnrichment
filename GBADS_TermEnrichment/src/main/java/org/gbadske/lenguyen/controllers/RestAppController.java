package org.gbadske.lenguyen.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.gbadske.lenguyen.beans.Term;
import org.gbadske.lenguyen.beans.TermDtoInput;
import org.gbadske.lenguyen.beans.TermDtoOutput;
import org.gbadske.lenguyen.database.DatabaseRepository;
import org.gbadske.lenguyen.database.SparqlEndPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


/**
 * This class provides the web API for the term enrichment.
 */

@RestController
@RequestMapping("/api")
public class RestAppController {
	@Autowired
	DatabaseRepository da;
	@Autowired
	SparqlEndPointRepository  eda;

	
	/**
	 * Delete  a species term in the database based on the id
	 * return number of deleted records
	 * @param Long id
	 * @return String number deleted records
	 */
	@PutMapping("/deleteSpeciesTermById")
	public String deleteSpeciesTermById(@RequestBody Long id) {
		Long c = da.count();
		da.deleteById(id);
		return "Number deleted Records: " + (c - da.count());
	}
	
	
	/**
	 * Delete  species terms in the database based on the id list
	 * return number of deleted records
	 * @param List<Long> idList
	 * @return String number of deleted records
	 */
	@PutMapping("/deleteSpeciesTermByIdList")
	public String deleteSpeciesTermByIdList(@RequestBody List<Long> idList) {
		Long c = da.count();
		da.deleteAllById(idList);
		return "Number deleted Records: " + (c - da.count());
	}
	
	/**
	 * Delete all species term in the database
	 * return number of record on the database
	 */
	@PutMapping("/deleteAllSpeciesTerms")
	public String deleteAllSpeciesTerms() {
		da.deleteAll();
		return "Number of Records: " + da.count();
	}
	
	
	/**
	 * Find a term by id. Return a term otherwise null.
	 * @param id
	 * @return Term 
	 */
	@GetMapping("/findTermById")
	public Term findTermById(@RequestBody Long id) {
		return da.findById(id).get();
	}
	/**
	 * List all terms of all species
	 * @return List<String> allUniqueNames
	 */
	@GetMapping("/listAllTerms")
	public List<Term> findAllTerm(){
		return da.findAll();
	}
	
	 /**
	 * List all uniques name of all species
	 * @return List<String> allUniqueNames
	 */
	
	@GetMapping("/list")
	public List<String> getAllUniqueSpecies() {
		return da.findAllUniqueSpecies();
	}

	/**
	 * This api return the enriched term based on the inputs species, countries, and years
	 * if species or countries or year are empty, it will return records associated with all species and all countries and all years.
	 * @param TermDtoInput term
	 * @return TermDtoOutput termDtoOuput
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@PostMapping("/postGetEnrichedTerm")
	@ResponseBody
	public TermDtoOutput postGetEnrichedTerm(@RequestBody TermDtoInput term)
			throws JsonMappingException, JsonProcessingException {

		//extract species, country, year from post requests
		Set<String> speciesSet = term.getSpecies();
		Set<String> countrySet = term.getCountries();
		Set<String> yearSet = term.getYears();
		
		
		//crate species list, country list, year list
	    	
		List<String> speciesList = new ArrayList<>(speciesSet);
		List<String> countryList = new ArrayList<>(countrySet);
		List<String> yearList = new ArrayList<>(yearSet);
		
		//if users don't specified find all!!
		
		if(speciesList.size() == 0) {
			speciesList = da.findAllUniqueSpecies();
		}
		if(countryList.size() == 0 ) {
			countryList = da.findAllUniqueCountry();
		}
		if(yearList.size()== 0) {
			yearList = da.findAllUniqueYear();
		}
		
		//get all superclass of species list
		List<String> superClasses = da.findSuperClassWithSpeciesCountryYear(speciesList, countryList, new ArrayList<>(yearList));

		//get species based on super classes
		List<String> enrichedTerms = da.findSpeciesWithCountryYearSuperClass(countryList, yearList, superClasses);
		
		TermDtoOutput output = new TermDtoOutput();


		//set enrichedTerm for output
		output.setEnrichedSpecies(enrichedTerms);

		return output;
	}


	
	/**
	 * Insert a species term if the species term exists, it updates the term in the database
	 * return number of record on the database
	 * @param Term speciesTerm
	 * @return String number of records
	 */
	@PutMapping("/insertUpdateSpeciesTerm")
	public String insertUpdateSpeciesTerm(@RequestBody Term speciesTerm) {
		da.save(speciesTerm);
		return "Total Records: " + da.count();
	}

	
	/**
	 * Insert a species term lists if the species term list exists, it updates the list terms in the database
	 * return number of record on the database
	 * @param speciesTerm
	 * @return String number of records
	 */
	@PutMapping("/insertUpdateSpeciesTerms")
	public String insertUpdateSpeciesTerms(@RequestBody List<Term> speciesTerm) {
		da.saveAll(speciesTerm);
		return "Total Records: " + da.count();
	}
	

	
	/**
	 * Insert all species terms into the database from sparql endpoint given the super class.
	 * return number of record on the database
	 * @param String superClass
	 * @return String numberOfRecords
	 */
	@PutMapping("/insertAllSpeciesTerm")
	public String insertAllSpeciesTerm(@RequestBody String superClass) {
		System.out.println("Start inserting ***********************************");
		List<Term> termList = eda.getAllSpecies(superClass);
		da.saveAll(termList);
		System.out.println("Finish inserting ***********************************");
		
		return "Total Records: " + da.count();
	}
	
	

}
