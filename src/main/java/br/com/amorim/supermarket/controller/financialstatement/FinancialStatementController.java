package br.com.amorim.supermarket.controller.financialstatement;

import br.com.amorim.supermarket.model.financialstatement.FinancialStatement;
import br.com.amorim.supermarket.service.financialstatement.FinancialStatementCrudService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor

@RestController
@RequestMapping("api/financial-statement")
public class FinancialStatementController {

    private FinancialStatementCrudService financialStatementCrudService;

    @GetMapping
    public Page<FinancialStatement> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                             @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") int size) {
        return financialStatementCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public FinancialStatement getById (@PathVariable UUID id) {
        return financialStatementCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public FinancialStatement save (@RequestBody @Valid FinancialStatement financialStatement) {
        return financialStatementCrudService.save(financialStatement);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid FinancialStatement financialStatement, @PathVariable UUID id) {
        financialStatementCrudService.update(financialStatement, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        financialStatementCrudService.delete(id);
    }
}
