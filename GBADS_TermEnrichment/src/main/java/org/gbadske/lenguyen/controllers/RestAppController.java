package org.gbadske.lenguyen.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gbadske.lenguyen.beans.EnrichedTerm;
import org.gbadske.lenguyen.beans.Term;
import org.gbadske.lenguyen.beans.TermDtoInput;
import org.gbadske.lenguyen.beans.TermDtoOutput;
import org.gbadske.lenguyen.database.DatabaseRepository;
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

@RestController
@RequestMapping("/api")
public class RestAppController {
	@Autowired
	DatabaseRepository da;

	@GetMapping("/list")
	public List<String> getAllUniqueSpecies() {
		return da.findAllUniqueSpecies();
	}

	@PostMapping("/postGetEnrichedTerm")
	@ResponseBody
	public TermDtoOutput postGetEnrichedTerm(@RequestBody TermDtoInput term)
			throws JsonMappingException, JsonProcessingException {

		Set<String> speciesSet = term.getSpecies();
		Set<String> countrySet = term.getCountries();
		Set<String> yearSet = term.getYears();
		
		//TODO
		//if TermDto input is empty
		//get all species, countries, years from database
		
		
		List<String> speciesList = new ArrayList<>(speciesSet);
		List<String> countryList = new ArrayList<>(countrySet);
		List<String> yearList = new ArrayList<>(yearSet);
		
		if(speciesList.size() == 0) {
			speciesList = da.findAllUniqueSpecies();
		}
		if(countryList.size() == 0 ) {
			countryList = da.findAllUniqueCountry();
		}
		if(yearList.size()== 0) {
			yearList = da.findAllUniqueYear();
		}
		
		List<String> superClasses = da.findSuperClassWithSpeciesCountryYear(speciesList, countryList, new ArrayList<>(yearList));

		List<String> enrichedTerms = da.findSpeciesWithCountryYearSuperClass(countryList, yearList, superClasses);
		
		TermDtoOutput output = new TermDtoOutput();


		output.setEnrichedSpecies(enrichedTerms);

		return output;
	}

	@PutMapping("/insertSpeciesTerm")
	public String insertSpeciesTerm(@RequestBody Term speciesTerm) {
		da.save(speciesTerm);
		return "Total Records: " + da.count();
	}

	@PutMapping("/insertMultipleSpeciesTerm")
	public String insertMultipleSpeciesTerm(@RequestBody List<Term> speciesTerm) {
		da.saveAll(speciesTerm);
		return "Total Records: " + da.count();
	}

}
