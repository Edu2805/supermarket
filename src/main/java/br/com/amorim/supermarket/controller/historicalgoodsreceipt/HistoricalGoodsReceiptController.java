package br.com.amorim.supermarket.controller.historicalgoodsreceipt;

import br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto.ConverterHistoricalGoodsReceiptMapper;
import br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto.request.HistoricalGoodsReceiptInput;
import br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto.response.HistoricalGoodsReceiptOutput;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import br.com.amorim.supermarket.service.historicalgoodsreceipt.HistoricalGoodsReceiptCrudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@RestController
@RequestMapping("api/historical-goods-receipt")
@Api("Historical Goods receipt")
public class HistoricalGoodsReceiptController {

    private HistoricalGoodsReceiptCrudService historicalGoodsReceiptCrudService;
    private ConverterHistoricalGoodsReceiptMapper converterHistoricalGoodsReceiptMapper;

    @GetMapping
    @ApiOperation("Get all historicals Goods receipt")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Historicals goods receipt returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the historicals goods receipt")
    })
    public Page<HistoricalGoodsReceipt> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Historicals goods receipt list page") int page,
                                                 @RequestParam(
                                                         value = "size",
                                                         required = false,
                                                         defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return historicalGoodsReceiptCrudService.getAll(page, size);
    }

    @PostMapping("/invoice")
    @ApiOperation("Get all purchases from Goods receipt")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Historicals goods receipt returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the historicals goods receipt")
    })
    public List<HistoricalGoodsReceiptOutput> findByPurchaseInvoice(@RequestBody @ApiParam("Invoice code") HistoricalGoodsReceiptInput invoice) {
        List<HistoricalGoodsReceipt> finByInvoice = historicalGoodsReceiptCrudService.findByInvoice(invoice.getInvoice());
        return converterHistoricalGoodsReceiptMapper.historicalGoodsReceiptOutputMapper(finByInvoice);
    }

    @GetMapping("/{sourceId}")
    @ApiOperation("Get a specific product into historical goods receipt")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product returned successfully"),
            @ApiResponse(code = 404, message = "Product not found for given id")
    })
    public List<HistoricalGoodsReceipt> findBySourceId(@PathVariable @ApiParam("Product Data id by Historical Goods Receipt id") UUID sourceId) {
        return historicalGoodsReceiptCrudService.findBySourceId(sourceId);
    }
}
