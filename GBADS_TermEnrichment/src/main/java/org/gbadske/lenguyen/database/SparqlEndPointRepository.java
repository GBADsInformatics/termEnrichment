package org.gbadske.lenguyen.database;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryResults;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.gbadske.lenguyen.beans.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class SparqlEndPointRepository {
	//prefix of the ontology
	@Value("${ontology.gbads.prefix}")
	private String prefix;
	//SPARQL end point url
	@Value("${sparql.endpoint.url}")
	private String sparqlEndpoint;
	
	@Autowired
	SPARQLCreateQuery query;
	
	public List<Term> getAllSpecies(String superClass){
		List<Term> termList = new ArrayList<>();
		String queryString = query.createQueryGetAllSpecies(superClass);
		
		org.eclipse.rdf4j.repository.Repository repo = new SPARQLRepository(sparqlEndpoint);
		
		RepositoryConnection conn = repo.getConnection();	
		
		TupleQuery query = conn.prepareTupleQuery(queryString);
		
		List<BindingSet> result = QueryResults.asList(query.evaluate());
		result.forEach(e-> {
			Term term = new Term();
		    term.setSpecies(e.getValue("species").stringValue());
		    term.setCountry(e.getValue("country").stringValue());
		    term.setTermYear(e.getValue("year").stringValue());
		    term.setDataSource(e.getValue("datasource").stringValue());
		    term.setSuperClass(superClass);
		    term.setOntologyVersion("version 1");
		    term.setTermId(0L);
		    termList.add(term);
		});
		conn.close();
				
		return termList;
	}
}
