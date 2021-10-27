package br.com.budget.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.budget.enums.EnumFolder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Table(name = "budget")
public class Budget {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@NotBlank
	private	float totalAmount;
	
	@NotNull
	@NotBlank
	private float spentAmount;
	
	@NotNull
	@NotBlank
	private EnumFolder possibleDestinations;
	
	@NotNull
	@NotBlank
	private EnumFolder origin; 
}
