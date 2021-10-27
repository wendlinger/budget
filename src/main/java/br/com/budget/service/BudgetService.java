package br.com.budget.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.budget.enums.EnumFolder;
import br.com.budget.exceptions.BusinessException;
import br.com.budget.model.Budget;
import br.com.budget.repository.BudgetRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BudgetService {

	private BudgetRepository budgetRepository;

	public Budget save(Budget budget) {
		return budgetRepository.save(budget);
	}

	public List<Budget> findAll() {
		return budgetRepository.findAll();
	}
	
	public List<Budget> findAllByPossibleDestinations(EnumFolder possibleDestinations) {
		return budgetRepository.findAllByPossibleDestinations(possibleDestinations);
	}

	public Budget updateExpense(Budget budget, Long id) throws BusinessException {
		Budget budgetDB = budgetRepository.getById(id);
		float spentTotal = budget.getSpentAmount() + budgetDB.getSpentAmount();

		if (!(budgetDB.getTotalAmount() >= spentTotal)) {
			throw new BusinessException("Can't add because the amount spent is greater than the total amount.");
		}
		budgetDB.setSpentAmount(spentTotal);
		return budgetRepository.save(budgetDB);
	}

	public boolean BudgetExistById(Long id) {
		return budgetRepository.existsById(id);
	}
}
