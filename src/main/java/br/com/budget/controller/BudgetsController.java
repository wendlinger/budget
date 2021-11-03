package br.com.budget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.budget.enums.EnumFolder;
import br.com.budget.exceptions.BusinessException;
import br.com.budget.exceptions.ResourceNotFoundException;
import br.com.budget.model.Budget;
import br.com.budget.service.BudgetService;

@RestController
@RequestMapping("budgets")
public class BudgetsController {

	private BudgetService budgetService;

	@Autowired
	public BudgetsController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@PostMapping
	public ResponseEntity<Budget> save(@RequestBody Budget budget) {
		return ResponseEntity.ok(budgetService.save(budget));
	}

	@GetMapping
	public ResponseEntity<List<Budget>> findAll(
			@RequestParam(value = "possibleDestinations", required = false) EnumFolder possibleDestinations) {
		if (possibleDestinations != null) {
			return ResponseEntity.ok(budgetService.findAllByPossibleDestinations(possibleDestinations));
		}
		return ResponseEntity.ok(budgetService.findAll());
	}

	@PutMapping("{id}/expense")
	public ResponseEntity<Budget> updateExpenseValue(@PathVariable Long id, @RequestBody Budget budget)
			throws ResourceNotFoundException, BusinessException {
		if (!budgetService.BudgetExistById(id)) {
			throw new ResourceNotFoundException("Budget not found on: " + id);
		} else {
			return ResponseEntity.ok(budgetService.updateExpense(budget, id));
		}
	}

	@GetMapping("{id}/has-available-resource")
	public boolean hasAvailableResource(@PathVariable Long id,
			@RequestParam(value = "value", required = false) float value) throws ResourceNotFoundException {
		if (!budgetService.BudgetExistById(id)) {
			throw new ResourceNotFoundException("Budget not found on: " + id);
		} else {
			return budgetService.hasAvailableResource(id, value);
		}
	}

	@GetMapping("has-available-resource-by-possible-destinations")
	public ResponseEntity<Budget> findByPossibleDestinationsWithAvailableResource(
			@RequestParam(value = "possibleDestinations") EnumFolder possibleDestinations,
			@RequestParam(value = "cost") Float cost) {
		
		return ResponseEntity.ok(budgetService.findByPossibleDestinationsWithAvailableResource(possibleDestinations, cost));
	}

}
