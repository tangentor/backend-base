package org.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Description: (Your class description here)
 *
 * @author TangXi
 * @createDate 2023/11/27
 */

@Data
@Accessors(chain = true)
public class Student {
    // Your class implementation goes here

    private String name;

    private Integer age;

    private String desc;
}
