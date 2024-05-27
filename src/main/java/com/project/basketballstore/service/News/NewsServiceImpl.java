package com.project.basketballstore.service.News;

import com.project.basketballstore.model.DTO.NewsDTO;
import com.project.basketballstore.model.image.ImageModel;
import com.project.basketballstore.model.news.News;
import com.project.basketballstore.model.user.User;
import com.project.basketballstore.repository.ImageRepository;
import com.project.basketballstore.repository.NewsRepository;
import com.project.basketballstore.service.News.NewsService;
import com.project.basketballstore.service.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final ImageRepository imageRepository;

    private final UserService userService;

    public NewsServiceImpl(NewsRepository newsRepository, ImageRepository imageRepository, UserService userService) {
        this.newsRepository = newsRepository;
        this.imageRepository = imageRepository;
        this.userService = userService;
    }

    @Override
    public Optional<News> getNewsDetail(int id) {
        return newsRepository.findById(id);
    }

    @Override
    public void addNews(NewsDTO newsDTO, Principal principal) throws IOException {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Set<ImageModel> images = uploadImage(newsDTO.getFile());
        News news = new  News();
        news.setUser(user);
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setDescribe(newsDTO.getDescribe());
        news.setCreateAt(newsDTO.getCreateAt());
        news.setNewsImage(images);

        newsRepository.save(news);
    }

    @Override
    public void updateNews(int id, NewsDTO newsDTO) throws IOException {
        Optional<News> optionalNews = newsRepository.findById(id);
        if(optionalNews.isEmpty()){
        System.out.println("Không tồn tại báo cáo !!!");
        }
        Set<ImageModel> images = uploadImage(newsDTO.getFile());
        News news = optionalNews.get();
        news.setContent(newsDTO.getContent());
        news.setTitle(newsDTO.getTitle());
        news.setDescribe(newsDTO.getDescribe());
        news.setCreateAt(newsDTO.getCreateAt());
        news.setNewsImage(images);
        newsRepository.save(news);
    }

    @Override
    public void deleteNewsDetail(int id) {
        newsRepository.deleteById(id);
    }

    public Set<ImageModel> uploadImage(Set<MultipartFile> multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageRepository.save(imageModel);
            imageModels.add(imageModel);

        }
        return imageModels;
    }
}
