package org.example.controller;

import cn.swunlp.backend.base.base.exception.BusinessException;
import cn.swunlp.backend.base.web.annotation.JsonResult;
import lombok.RequiredArgsConstructor;
import org.example.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: (Your class description here)
 *
 * @author TangXi
 * @createDate 2023/12/5
 */

@RestController
@JsonResult
@RequiredArgsConstructor
public class TestController {
    // Your class implementation goes here

    private final TestService testService;

    @GetMapping("/")
    public String test() {
        return testService.test();
    }
}
