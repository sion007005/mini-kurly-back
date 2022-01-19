package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.ItemDto;
import com.sion.minikurlyback.entity.Item;
import com.sion.minikurlyback.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemDto create(ItemDto itemDto) {
        Item item = Item.builder()
                .name(itemDto.getName())
                .brand(itemDto.getBrand())
                .description(itemDto.getDescription())
                .salePrice(itemDto.getSalePrice())
                .originalPrice(itemDto.getOriginalPrice())
                .discountRate(itemDto.getDiscountRate())
                .stock(itemDto.getStock())
                .isKurlyOnly(itemDto.isKurlyOnly())
                .build();

        itemRepository.save(item);

        return ItemDto.from(item);
    }

    public ItemDto findOneById(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        if (!item.isPresent()) {
            new RuntimeException("등록된 상품이 없습니다.");
        }

        return ItemDto.from(item.get());
    }
}
