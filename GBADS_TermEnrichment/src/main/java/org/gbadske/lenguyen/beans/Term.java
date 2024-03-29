package org.gbadske.lenguyen.beans;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * This bean class is to capture information in Term table.
 * `term_id` BIGINT NOT NULL AUTO_INCREMENT,
   `species` VARCHAR(45) NOT NULL,
   `superClass`  VARCHAR(45) NOT NULL,
   `country` VARCHAR(45) NULL,
   `t_year` VARCHAR(45) NULL,
   `description` VARCHAR(400) NULL,
   `ontologyVersion` VARCHAR(200) NOT NULL,
 */

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="term_id")
	private Long termId;
	@NonNull
	@Column(name="species")
	private String species;
	@NonNull
	@Column(name="super_class")
	private String superClass;
	@Column(name="country")
	private String country;
	@Column(name="term_year")
	private String termYear;
	@Column(name="data_source")
	private String dataSource;
	@Column(name="description")
	private String description;
	@Column(name="ontology_version")
	private String ontologyVersion;
}
