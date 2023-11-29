package org.example.controller;

import cn.swunlp.backend.base.web.annotation.JsonResult;
import org.example.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: (Your class description here)
 *
 * @author TangXi
 * @createDate 2023/11/27
 */

@RestController
@JsonResult
public class TestController {
    // Your class implementation goes here

    @GetMapping("/")
    public Student test(){
        return new Student().setAge(18).setName("TangXi").setDesc("Hello World!");
    }
}
