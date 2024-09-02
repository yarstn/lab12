package com.example.users.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title cannot be null or empty")
    @Size(min = 5, message = "title must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null ")
    private String title;
    @NotEmpty(message = "body cannot be null or empty")
//    @Size(min = 5, message = "body must be longer than 4 characters")
    @Column(columnDefinition = "varchar(20) not null ")
    private String body;

    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;
}
