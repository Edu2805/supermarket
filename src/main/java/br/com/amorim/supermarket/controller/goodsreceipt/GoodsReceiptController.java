package br.com.amorim.supermarket.controller.goodsreceipt;

import br.com.amorim.supermarket.controller.goodsreceipt.dto.ConvertGoodsReceiptMapper;
import br.com.amorim.supermarket.controller.goodsreceipt.dto.GoodsReceiptDTO;
import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.service.goodsreceipt.GoodsReceiptCrudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api("Goods Receipt")
public class GoodsReceiptController {

    private GoodsReceiptCrudService goodsReceiptCrudService;
    private ConvertGoodsReceiptMapper convertGoodsReceiptMapper;

    @GetMapping
    @ApiOperation("Get all goods receipt")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Goods receipt returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the goods receipt")
    })
    public Page<GoodsReceipt> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Goods receipt list page") int page,
                                       @RequestParam(
                                               value = "size",
                                               required = false,
                                               defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return goodsReceiptCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific goods receipt")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Goods receipt returned successfully"),
            @ApiResponse(code = 404, message = "Goods receipt not found for given id")
    })
    public GoodsReceipt getById (@PathVariable @ApiParam("Goods receipt id") UUID id) {
        return goodsReceiptCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a goods receipt")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Goods receipt successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the goods receipt")
    })
    public GoodsReceipt save (@RequestBody @Valid @ApiParam("Parameters for saving the goods receipt")
                                  GoodsReceiptDTO goodsReceiptDTO) {
        var newGoodsReceipt = convertGoodsReceiptMapper.createOrUpdateGoodsReceiptMapper(goodsReceiptDTO);
        return goodsReceiptCrudService.save(newGoodsReceipt);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a goods receipt")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Goods receipt successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the goods receipt")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for saving the goods receipt")
                            GoodsReceiptDTO goodsReceiptDTO, @PathVariable @ApiParam("Goods receipt id") UUID id) {
        var newGoodsReceipt = convertGoodsReceiptMapper.createOrUpdateGoodsReceiptMapper(goodsReceiptDTO);
        goodsReceiptCrudService.update(newGoodsReceipt, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific goods receipt")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Goods receipt deleted successfully"),
            @ApiResponse(code = 404, message = "Goods receipt not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Goods receipt id") UUID id) {
        goodsReceiptCrudService.delete(id);
    }
}
