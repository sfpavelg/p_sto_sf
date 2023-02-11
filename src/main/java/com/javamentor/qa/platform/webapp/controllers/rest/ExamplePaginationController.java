package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.dto.ExamplePaginationDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
                    "1. currentPageNumber is a number of page with dto's.")
//    TODO: Write correct Swagger docs
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int currentPageNumber) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);

        return ResponseEntity.ok(examplePaginationDtoService.getListingUsers(param).getItems());
    }

    @GetMapping("/anotherPagination")
    @ApiOperation(value = "Get example of another paginated Dto",
            notes = "Available parameters and their values:" +
                    "1. currentPageNumber is a number of page with dto's.")
    public ResponseEntity<?> getAnotherUsers(@RequestParam(defaultValue = "0") int currentPageNumber) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);

        return ResponseEntity.ok(examplePaginationDtoService.getAnotherListingUsers(param).getItems());
    }
}
