package br.com.amorim.supermarket.controller.historicalgoodsissue;

import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import br.com.amorim.supermarket.service.historicalgoodsissue.HistoricalGoodsIssueCrudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("api/historical-goods-issue")
@Api("Historical Goods Issue")
public class HistoricalGoodsIssueController {

    private HistoricalGoodsIssueCrudService historicalGoodsIssueCrudService;

    @GetMapping
    @ApiOperation("Get all historicals goods issue")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Historicals goods issue returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the historicals goods issue")
    })
    public Page<HistoricalGoodsIssue> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Department list page") int page,
                                               @RequestParam(
                                                       value = "size",
                                                       required = false,
                                                       defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return historicalGoodsIssueCrudService.getAll(page, size);
    }
}
