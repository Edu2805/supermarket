package br.com.amorim.supermarket.controller.goodsissue;

import br.com.amorim.supermarket.controller.goodsissue.dto.ConvertGoodsIssueMapper;
import br.com.amorim.supermarket.controller.goodsissue.dto.GoodsIssueDTO;
import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.service.goodsissue.GoodsIssueCrudService;
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
@RequestMapping("api/goods-issue")
@Api("Goods Issue")
public class GoodsIssueController {

    private GoodsIssueCrudService goodsIssueCrudService;
    private ConvertGoodsIssueMapper convertGoodsIssueMapper;

    @GetMapping
    @ApiOperation("Get all goods issue")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Goods issue returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the goods issue")
    })
    public Page<GoodsIssue> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Goods issue list page") int page,
                                     @RequestParam(
                                             value = "size",
                                             required = false,
                                             defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return goodsIssueCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific goods issue")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Goods issue returned successfully"),
            @ApiResponse(code = 404, message = "Goods issue not found for given id")
    })
    public GoodsIssue getById (@PathVariable @ApiParam("Goods issue id") UUID id) {
        return goodsIssueCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a goods issue")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Goods issue successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the goods issue")
    })
    public GoodsIssue save (@RequestBody @Valid @ApiParam("Parameters for saving the goods issue") GoodsIssueDTO goodsIssueDTO) {
        var newGoodsIssue = convertGoodsIssueMapper.createOrUpdateGoodsIssueMapper(goodsIssueDTO);
        return goodsIssueCrudService.save(newGoodsIssue);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a goods issue")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Goods issue successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the goods issue")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the goods issue")
                            GoodsIssueDTO goodsIssueDTO, @PathVariable @ApiParam("Goods issue id") UUID id) {
        var updateGoodsIssue = convertGoodsIssueMapper.createOrUpdateGoodsIssueMapper(goodsIssueDTO);
        goodsIssueCrudService.update(updateGoodsIssue, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific goods issue")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Goods issue deleted successfully"),
            @ApiResponse(code = 404, message = "Goods issue not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Goods issue id") UUID id) {
        goodsIssueCrudService.delete(id);
    }
}
