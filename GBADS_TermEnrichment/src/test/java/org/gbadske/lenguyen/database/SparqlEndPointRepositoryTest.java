package org.gbadske.lenguyen.database;

import java.util.List;

import org.gbadske.lenguyen.beans.Term;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SparqlEndPointRepositoryTest {
	@Autowired
	SparqlEndPointRepository  eda;
	
	@Test
	void getAllSpeciesTest(){
		
		List<Term> termList = eda.getAllSpecies("All_Cattle");
		
		System.out.println("Total size of terms = " + termList.size());
		
	}

}
