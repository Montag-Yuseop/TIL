package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;
    
//    @Test
//    public void updateItem() throws Exception {
//        // given
//        Book book = em.find(Book.class, 1L);
//
//        // TX
//        book.setName("새로운 이름");
//
//        // 변경 감지 == dirty checking
//
//    }
}
