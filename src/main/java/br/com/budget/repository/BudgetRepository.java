package br.com.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.budget.enums.EnumFolder;
import br.com.budget.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long>{
	
	public List<Budget> findAllByPossibleDestinations(EnumFolder possibleDestinations);
}
