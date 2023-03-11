package br.com.amorim.supermarket.controller.goodsreceipt;

import br.com.amorim.supermarket.controller.goodsreceipt.dto.ConvertGoodsReceiptMapper;
import br.com.amorim.supermarket.controller.goodsreceipt.dto.GoodsReceiptDTO;
import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.service.goodsreceipt.GoodsReceiptCrudService;
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
@RequestMapping("api/goods-receipt")
public class GoodsReceiptController {

    private GoodsReceiptCrudService goodsReceiptCrudService;
    private ConvertGoodsReceiptMapper convertGoodsReceiptMapper;

    @GetMapping
    public Page<GoodsReceipt> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                       @RequestParam(
                                             value = "size",
                                             required = false,
                                             defaultValue = "20") int size) {
        return goodsReceiptCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public GoodsReceipt getById (@PathVariable UUID id) {
        return goodsReceiptCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public GoodsReceipt save (@RequestBody @Valid GoodsReceiptDTO goodsReceiptDTO) {
        var newGoodsReceipt = convertGoodsReceiptMapper.createOrUpdateGoodsReceiptMapper(goodsReceiptDTO);
        return goodsReceiptCrudService.save(newGoodsReceipt);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid GoodsReceiptDTO goodsReceiptDTO, @PathVariable UUID id) {
        var newGoodsReceipt = convertGoodsReceiptMapper.createOrUpdateGoodsReceiptMapper(goodsReceiptDTO);
        goodsReceiptCrudService.update(newGoodsReceipt, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        goodsReceiptCrudService.delete(id);
    }
}
