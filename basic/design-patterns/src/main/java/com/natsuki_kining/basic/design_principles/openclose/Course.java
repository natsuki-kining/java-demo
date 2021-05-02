package com.natsuki_kining.basic.design_principles.openclose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO
 *
 * @author natsuki_kining
 * @date 2021/5/2 23:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {

    private String id;

    private String name;
    
    private Double price;
}
