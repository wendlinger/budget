package br.com.budget.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private	float totalAmount;
	
	@NotNull
	private float spentAmount;
	
	@NotNull
	private EnumFolder possibleDestinations;
	
	@NotNull
	private EnumFolder origin; 
}
