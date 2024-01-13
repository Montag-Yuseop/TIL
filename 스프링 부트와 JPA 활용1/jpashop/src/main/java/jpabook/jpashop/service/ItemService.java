package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto updateItemDto) {
        Item findItem = itemRepository.findOne(itemId); // 실제 DB에 있는 영속성 Entity 가져오기
        findItem.setName(updateItemDto.getName());
        findItem.setPrice(updateItemDto.getPrice());
        findItem.setStockQuantity(updateItemDto.getStockQuantity());
        // 변경이 감지되면 업데이트 시킨다(트랜젝션 커밋 시점)
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}
