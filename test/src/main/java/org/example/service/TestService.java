package org.example.service;

import cn.swunlp.backend.base.base.exception.BusinessException;
import org.springframework.stereotype.Service;

/**
 * Description: (Your class description here)
 *
 * @author TangXi
 * @createDate 2023/12/5
 */

@Service
public class TestService {
    // Your class implementation goes here

    public String test() {
        throw new BusinessException("test");
    }
}
