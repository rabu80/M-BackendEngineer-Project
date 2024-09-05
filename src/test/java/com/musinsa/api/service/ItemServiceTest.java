package com.musinsa.api.service;

import com.musinsa.api.item.repository.ItemRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

}