package com.sparta.testcodepractice.todo;

import com.sparta.testcodepractice.CommonResponseDTO;
import com.sparta.testcodepractice.user.UserDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TodoResponseDTO extends CommonResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Boolean isCompleted;
    private UserDTO user;
    private LocalDateTime createDate;

    public TodoResponseDTO(String msg, Integer statusCode) {
        super(msg, statusCode);
    }

    public TodoResponseDTO(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.user = new UserDTO(todo.getUser());
        this.createDate = todo.getCreateDate();
    }
}