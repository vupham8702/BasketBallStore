package com.project.basketballstore.controller;


import com.project.basketballstore.model.DTO.NewsDTO;
import com.project.basketballstore.model.news.News;
import com.project.basketballstore.service.News.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<?> addNews (@ModelAttribute NewsDTO newsDTO, Principal principal){
        try {
            newsService.addNews(newsDTO,principal);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("tạo mới thất bại");
        }

        return ResponseEntity.ok("Tạo mới thành công");
    }

    @GetMapping("/{id}")
    public Optional<News> newsDetail(@PathVariable int id)
    {
        return newsService.getNewsDetail(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable int id, @ModelAttribute NewsDTO newsDTO){
        try {
            newsService.updateNews(id,newsDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Chỉnh sửa không thành công !!!");
        }
        return ResponseEntity.ok("Chỉnh sửa thành công !!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable int id){
        newsService.deleteNewsDetail(id);
        return ResponseEntity.ok("Xóa bài viết thành công !!!");
    }
}
