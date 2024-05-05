package br.com.amorim.supermarket.controller.saleinformation;

import br.com.amorim.supermarket.controller.saleinformation.dto.request.SaleInformationDTO;
import br.com.amorim.supermarket.controller.saleinformation.dto.response.SaleInformationSummaryDTO;
import br.com.amorim.supermarket.service.saleinformation.SaleInformationService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor

@RestController
@RequestMapping("api/saleinformation")
@Api("Sale Information")
public class SaleInformation {

    private SaleInformationService saleInformationService;

    @PostMapping()
    @ApiOperation("Get information about sales")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Information about sales returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the information about sales")
    })
    public SaleInformationSummaryDTO getSaleInformation (@RequestBody @Valid @ApiParam("User email login")SaleInformationDTO saleInformationDTO) {
        return saleInformationService.getSaleInformationSummary(saleInformationDTO);
    }
}
