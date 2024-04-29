package org.gbadske.lenguyen.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SPARQLCreateQueryTest {
	@Autowired
	SPARQLCreateQuery query;
	@Test
	void createQueryGetAllSpeciesTest(){
		
		System.out.println(query.createQueryGetAllSpecies("All_Cattle"));
	}

}
