package com.project.basketballstore.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {

    private int id;
    private String title;
    private String describe;
    private String content;
    private LocalDateTime createAt;
    private Set<MultipartFile> file;
}
