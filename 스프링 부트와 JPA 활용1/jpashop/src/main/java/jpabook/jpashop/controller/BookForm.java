package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@Slf4j
public class BookForm {

    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private Long id;
    private String isbn;
}
