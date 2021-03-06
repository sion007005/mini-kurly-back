package com.sion.minikurlyback.service;

import com.sion.minikurlyback.dto.ItemDto;
import com.sion.minikurlyback.entity.Category;
import com.sion.minikurlyback.entity.Item;
import com.sion.minikurlyback.enums.SaleStatus;
import com.sion.minikurlyback.exception.IllegalRequestException;
import com.sion.minikurlyback.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryService categoryService;

    public ItemDto create(ItemDto itemDto, Long categoryId) {
        Category category = categoryService.findById(categoryId);

        Item item = Item.builder()
                .name(itemDto.getName())
                .brand(itemDto.getBrand())
                .description(itemDto.getDescription())
                .salePrice(itemDto.getSalePrice())
                .originalPrice(itemDto.getOriginalPrice())
                .stock(itemDto.getStock())
                .kurlyOnly(itemDto.getKurlyOnly())
                .imagePath(itemDto.getImagePath())
                .category(category)
                .saleStatus(SaleStatus.ACTIVE)
                .build();

        itemRepository.save(item);
        return ItemDto.from(item);
    }

    public ItemDto findOneById(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        if (!item.isPresent()) {
            new IllegalRequestException("등록된 상품이 없습니다.");
        }

        return ItemDto.from(item.get());
    }

    public List<ItemDto> findAllByCategoryId(Long categoryId, Pageable pageable) {
        Category category = categoryService.findById(categoryId);

        if (category.getLevel() == 1) {
            Category firstChild = categoryService.findAllByParentId(categoryId).get(0);
            categoryId = firstChild.getId();
        }

        List<Item> itemList = itemRepository.findByCategoryIdOrderByCreatedAtDesc(categoryId, pageable);
        return itemList.stream().map(item -> ItemDto.from(item)).collect(Collectors.toList());
    }
}
