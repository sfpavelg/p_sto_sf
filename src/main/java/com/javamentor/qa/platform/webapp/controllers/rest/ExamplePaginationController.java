package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExamplePaginationDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/example")
@Api(value = "Example pagination controller")
public class ExamplePaginationController {

    private final ExamplePaginationDtoService examplePaginationDtoService;

    public ExamplePaginationController(ExamplePaginationDtoService examplePaginationDtoService) {
        this.examplePaginationDtoService = examplePaginationDtoService;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get example of paginated Dto",
            notes = "Available parameters and their values:" +
                    "1. currentPageNumber is a number of page with dto's." +
                    "2. itemsOnPage is a number that means the number of elements on the page")
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int currentPageNumber,
                                         @RequestParam(defaultValue = "10") int itemsOnPage) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("itemsOnPage", itemsOnPage);
        // For sortBy default value is "id" sorting/
        param.put("sortBy", "id");

        List<ExampleDto> userPage = examplePaginationDtoService.getListingUsers(param);

        if (!userPage.isEmpty()) {
            return ResponseEntity.ok(userPage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Page with param " + param + " not found");
        }
    }

    @GetMapping("/anotherPagination")
    @ApiOperation(value = "Get example of another paginated Dto",
            notes = "Available parameters and their values:" +
                    "1. currentPageNumber is a number of page with dto's." +
                    "2. sortBy is the value of a string which in this example" +
                    " can be id, email, fullName, city, imageLink, or reputation")
    public ResponseEntity<?> getAnotherUsers(@RequestParam(defaultValue = "0") int currentPageNumber,
                                             @RequestParam(defaultValue = "id") String sortBy) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("sortBy", sortBy);

        List<ExampleDto> userPage = examplePaginationDtoService.getAnotherListingUsers(param);

        if (!userPage.isEmpty()) {
            return ResponseEntity.ok(userPage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Page with param " + param + " not found");
        }
    }
}
