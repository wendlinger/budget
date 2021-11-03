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

		if (!hasAvailableResource(id, budget.getSpentAmount())) {
			throw new BusinessException("Can't add because the amount spent is greater than the total amount.");
		}
		budgetDB.setSpentAmount(budget.getSpentAmount() + budgetDB.getSpentAmount());
		return budgetRepository.save(budgetDB);
	}

	public boolean BudgetExistById(Long id) {
		return budgetRepository.existsById(id);
	}

	public boolean hasAvailableResource(Long id, float value) {
		Budget budgetDB = budgetRepository.getById(id);
		float spentTotal = value + budgetDB.getSpentAmount();

		if (!(budgetDB.getTotalAmount() >= spentTotal)) {
			return false;
		}

		return true;
	}
	
	public Budget findByPossibleDestinationsWithAvailableResource(EnumFolder possibleDestinations, float cost) {
		Budget responseBudget = new Budget();
		
		List<Budget> listBudget = findAllByPossibleDestinations(possibleDestinations);
		for (Budget budget : listBudget) {
			if (hasAvailableResource(budget.getId(), cost)) {
				responseBudget = budget;
			}
		}
		return responseBudget;
	}
		
}
