package br.com.amorim.supermarket.controller.getsalenumber;

import br.com.amorim.supermarket.service.getsalenumber.GetSaleNumber;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@AllArgsConstructor

@RestController
@RequestMapping("api/salenumber")
@Api("Sale Number")
public class GetSaleNumberController {

    private GetSaleNumber getSaleNumberService;

    @GetMapping
    @ApiOperation("Get sale number")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sale number returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the sale number")
    })
    public BigInteger getSaleNumber() {
        return getSaleNumberService.generateSaleNumber();
    }
}
