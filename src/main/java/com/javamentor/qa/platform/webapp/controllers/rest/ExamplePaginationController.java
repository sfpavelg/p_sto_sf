package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExampleDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/example")
@Api(value = "Example pagination controller")
public class ExamplePaginationController {

    private final ExampleDtoService exampleDtoService;

    @GetMapping("/pagination")
    @ApiOperation(value = "Get example of paginated Dto",
            notes = "currentPageNumber is a number of page with dto's.", response = ExampleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. ExampleDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Page doesn't exist")})
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "1") int currentPageNumber,
                                         @RequestParam(defaultValue = "10") int itemsOnPage) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("itemsOnPage", itemsOnPage);

        return ResponseEntity.ok(exampleDtoService.getListingUsers(param));
    }

    @GetMapping("/anotherPagination")
    @ApiOperation(value = "Get example of another paginated Dto",
            notes = "currentPageNumber is a number of page with dto's.", response = ExampleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. ExampleDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Page doesn't exist")})
    public ResponseEntity<?> getAnotherUsers(@RequestParam(defaultValue = "1") int currentPageNumber,
                                             @RequestParam(defaultValue = "5") int itemsOnPage) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("itemsOnPage", itemsOnPage);

        return ResponseEntity.ok(exampleDtoService.getAnotherListingUsers(param));
    }
}
