package com.project.basketballstore.service.News;

import com.project.basketballstore.model.DTO.NewsDTO;
import com.project.basketballstore.model.news.News;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

public interface NewsService {
    Optional<News> getNewsDetail(int id);

    void addNews(NewsDTO newsDTO, Principal principal) throws IOException;

    void updateNews(int id, NewsDTO newsDTO) throws IOException;

    void deleteNewsDetail(int id);
}
