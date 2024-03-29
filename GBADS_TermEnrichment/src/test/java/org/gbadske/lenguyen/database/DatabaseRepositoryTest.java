package org.gbadske.lenguyen.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.gbadske.lenguyen.beans.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This bean class is to capture information in Term table. `term_id` BIGINT NOT
 * NULL AUTO_INCREMENT, `species` VARCHAR(45) NOT NULL, `superClass` VARCHAR(45)
 * NOT NULL, `country` VARCHAR(45) NULL, `t_year` VARCHAR(45) NULL,
 * `description` VARCHAR(400) NULL, `ontologyVersion` VARCHAR(200) NOT NULL,
 */

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseRepositoryTest {
	@Autowired
	DatabaseRepository dr;

	@BeforeEach
	public void setup() {
		dr.deleteAll();

		Term term0 = new Term(null, "CattleA", "All_Cattle", "Canada", "2020", "SourceA", "Display all cattle",
				"version 1");
		Term term1 = new Term(null, "CattleB", "All_Cattle", "USA", "2010", "SourceB", "Display all cattle",
				"version 1");
		Term term2 = new Term(null, "CattleC", "All_CattleA", "Denmark", "2023", "SourceC", "Display all cattle",
				"version 1");
		Term term3 = new Term(null, "CattleE", "All_CattleB", "Japan", "2024", "SourceD", "Display all cattle",
				"version 1");

		Term term4 = new Term(null, "ChickenA", "All_Poultry", "Canada", "2020", "SourceA", "Display all cattle",
				"version 1");
		Term term5 = new Term(null, "ChickenB", "All_Poultry", "USA", "2010", "SourceB", "Display all cattle",
				"version 1");
		Term term6 = new Term(null, "ChickenC", "All_PoultryA", "Australia", "2022", "SourceC", "Display all cattle",
				"version 1");
		Term term7 = new Term(null, "ChickenD", "All_PoultryB", "Denmark", "2023", "SourceC", "Display all cattle",
				"version 1");
		Term term8 = new Term(null, "ChickenE", "All_PoultryC", "Japan", "2024", "SourceD", "Display all cattle",
				"version 1");

		List<Term> terms = new ArrayList<>();
		terms.add(term0);
		terms.add(term1);
		terms.add(term2);
		terms.add(term3);
		terms.add(term4);
		terms.add(term5);
		terms.add(term6);
		terms.add(term7);
		terms.add(term8);

		dr.saveAll(terms);

	}

	@Test
	public void testGetSuperClass() {

		List<String> species = new ArrayList<>();
		species.add("CattleA");
		species.add("CattleB");
		species.add("CattleC");
		species.add("ChickenB");
		species.add("ChickenC");
		species.add("ChickenD");
		List<String> countries = new ArrayList<>();
		countries.add("USA");
		countries.add("Canada");
		countries.add("Australia");
		countries.add("Denmark");
		List<String> years = new ArrayList<>();

		years.add("2010");
		years.add("2020");
		years.add("2022");
		years.add("2023");

		List<String> superClasses = dr.findSuperClassWithSpeciesCountryYear(species, countries, years);
		superClasses.forEach(System.out::println);
		assertEquals(superClasses.size(), 5);
		// All_Cattle
		// All_CattleA
		// All_Poultry
		// All_PoultryA
		// All_PoultryB

		List<Term> terms = dr.findTermWithCountryYearSuperClass(countries, years, superClasses);
		terms.forEach(t -> System.out.println(t.getSpecies()));

		/*
		 * CattleA CattleB CattleC ChickenA ChickenB ChickenC ChickenD
		 */

	}

}
