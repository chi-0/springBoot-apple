package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController     {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

//    @Autowired
//    public ItemController(ItemRepository itemRepository, ItemService itemService) {
//        this.itemRepository = itemRepository;
//        this.itemService = itemService;
//    }

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items",result);

        return "list.html";
    }

    @GetMapping("/write")
    String write(){

        return "write.html";
    }

//    @PostMapping("/add")
//    String addPost(@ModelAttribute Item item){
//        itemRepository.save(item);
//
//        return "redirect:/list";
//    }

    @PostMapping("/add")
    String addPost(String title, Integer price){

        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){

            Optional<Item> result = itemRepository.findById(id);
            if(result.isPresent()){
                model.addAttribute("item",result.get());
                return "detail.html";
            }else {
                return "redirect:/list";
            }
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable Long id){
        Optional<Item> result = itemRepository.findById(id);

        if(result.isPresent()){
            model.addAttribute("data",result.get());
            return "edit.html";
        }else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String updatePost(String title, Integer price, Long id) throws Exception {
        itemService.updateItem(title,price,id);

        return "redirect:/list";

    }

    @DeleteMapping("/delete")
    ResponseEntity<String> deletePost(@RequestParam Long id) {
        itemRepository.deleteById(id);

        return ResponseEntity.status(200).body("삭제완료");
    }

}
