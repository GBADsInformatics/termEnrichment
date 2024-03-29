package org.gbadske.lenguyen.database;

import java.util.List;

import org.gbadske.lenguyen.beans.Term;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DatabaseRepository extends ListCrudRepository<Term, Long>{
	
	
	
	//public List<Term> findBySpeciesInAndCountryInAndTermYearIn(List<String> species, List<String> countries, List<String> years);
	
	@Query("select t from Term t where t.species in ?1 and t.country in ?2 and t.termYear in ?3")
	public List<Term> findTerm(List<String> species, List<String> countries, List<String> years);
	
	@Query("select distinct t.superClass from Term t where t.species in ?1 and t.country in ?2 and t.termYear in ?3")
	public List<String>  findSuperClassWithSpeciesCountryYear(List <String> species, List<String> countries, List<String> years);
	
	@Query("select t from Term t where t.country in ?1 and t.termYear in ?2 and t.superClass in ?3")
	public List<Term> findTermWithCountryYearSuperClass(List<String> countries, List<String> years, List<String> superClasses);
	
	@Query("select t.species from Term t where t.country in ?1 and t.termYear in ?2 and t.superClass in ?3")
	public List<String> findSpeciesWithCountryYearSuperClass(List<String> countries, List<String> years, List<String> superClasses);
	
	
	@Query("select distinct t.species from Term t") 
	public List<String> findAllUniqueSpecies();
	@Query("select distinct t.country from Term t") 
	public List<String> findAllUniqueCountry();
	@Query("select distinct t.termYear from Term t") 
	public List<String> findAllUniqueYear();
	

}
