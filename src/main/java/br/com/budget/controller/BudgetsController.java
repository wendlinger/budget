package br.com.budget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PatchMapping("{id}/expense")
	public ResponseEntity<Budget> update(@PathVariable Long id, @RequestBody Budget budget)
			throws ResourceNotFoundException, BusinessException {
		if (!budgetService.BudgetExistById(id)) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			throw new ResourceNotFoundException("User not found on: " + id);
		} else {
			return ResponseEntity.ok(budgetService.updateExpense(budget, id));
		}
	}
}
