package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
