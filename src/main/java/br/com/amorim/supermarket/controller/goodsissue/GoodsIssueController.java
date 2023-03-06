package br.com.amorim.supermarket.controller.goodsissue;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.service.goodsissue.GoodsIssueCrudService;
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
@RequestMapping("api/goods-issue")
public class GoodsIssueController {

    private GoodsIssueCrudService goodsIssueCrudService;

    @GetMapping
    public Page<GoodsIssue> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                     @RequestParam(
                                                     value = "size",
                                                     required = false,
                                                     defaultValue = "20") int size) {
        return goodsIssueCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public GoodsIssue getById (@PathVariable UUID id) {
        return goodsIssueCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public GoodsIssue save (@RequestBody @Valid GoodsIssue goodsIssue) {
        return goodsIssueCrudService.save(goodsIssue);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid GoodsIssue goodsIssue, @PathVariable UUID id) {
        goodsIssueCrudService.update(goodsIssue, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        goodsIssueCrudService.delete(id);
    }
}
