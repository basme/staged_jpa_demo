package com.netcracker.training.hibernate.demo.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageBean {

    private Integer number;
    private boolean isTechnical;

}
