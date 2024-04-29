package org.gbadske.lenguyen.database;

import org.eclipse.rdf4j.sparqlbuilder.constraint.Expression;
import org.eclipse.rdf4j.sparqlbuilder.constraint.Expressions;
import org.eclipse.rdf4j.sparqlbuilder.core.Prefix;
import org.eclipse.rdf4j.sparqlbuilder.core.SparqlBuilder;
import org.eclipse.rdf4j.sparqlbuilder.core.Variable;
import org.eclipse.rdf4j.sparqlbuilder.core.query.Queries;
import org.eclipse.rdf4j.sparqlbuilder.core.query.SelectQuery;
import org.eclipse.rdf4j.sparqlbuilder.rdf.Rdf;
import org.eclipse.rdf4j.sparqlbuilder.rdf.RdfLiteral;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import static org.eclipse.rdf4j.sparqlbuilder.rdf.Rdf.iri;

import java.util.ArrayList;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.XSD;

import lombok.Data;


/**
 * This class is used to generate SPARQL queries
 */
@Service
@Data
public class SPARQLCreateQuery {
	

	//prefix of the onotology
	@Value("${ontology.gbads.prefix}")
	private String prefix;
	  
	
	public String createQueryGetAllSpecies(String superClass) {
		
		SelectQuery selectQuery = Queries.SELECT();
		//create prefix
		Prefix queryPrefix = SparqlBuilder.prefix("",iri(prefix));
		
		//define sparql variables
		Variable year = SparqlBuilder.var("year");
        Variable species = SparqlBuilder.var("species");
        Variable country = SparqlBuilder.var("country");
        Variable dataSource = SparqlBuilder.var("datasource");
        Variable animals = SparqlBuilder.var("animal");
       
        //set prefix for select query
        selectQuery.prefix(queryPrefix);
        ValueFactory x = SimpleValueFactory.getInstance();
        
        selectQuery.select(species, year, country, dataSource)
        .where(animals.isA(queryPrefix.iri(superClass))
                .and(animals.has(queryPrefix.iri("species"), species))
                .and(animals.has(queryPrefix.iri("year"), year))
                .and(animals.has(queryPrefix.iri("country"), country))
                .and(animals.has(queryPrefix.iri("datasource"), dataSource)));
                
		return selectQuery.getQueryString();
	}
	
	
}
