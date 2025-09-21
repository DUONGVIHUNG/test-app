package com.example.docker_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping ("/add")
    public ResponseEntity<Item> create(@RequestBody Item item){

        Item savedItem = itemRepository.save(new Item(item.getName()));
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public String getItemById(@PathVariable Long id){

        Item getItem = itemRepository.findById(id).orElse(new Item("Not found"));

        return getItem.getName();
    }
}
