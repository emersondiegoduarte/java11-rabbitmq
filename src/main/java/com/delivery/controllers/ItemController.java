package com.delivery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.entities.Item;
import com.delivery.services.repositories.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;

	@PostMapping("/store")
	public ResponseEntity<Item> store(@RequestBody Item item) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(item));
	}
}
