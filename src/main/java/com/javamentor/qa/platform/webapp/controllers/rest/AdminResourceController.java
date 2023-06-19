package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.dto.answer.AnswerDtoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/admin")
public class AdminResourceController {

    private AnswerDtoService answerDtoService;

    @GetMapping("/answer/delete")
    public ResponseEntity<?> getAllDeletedAnswersByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(answerDtoService.getAllDeletedAnswersByUserId(userId));
    }
}
