package com.vini.dev.twapi.api.posts.domain;

import com.vini.dev.twapi.api.users.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id")
    String id;

    // @Column(name = "author_id")
    @ManyToOne
    @JoinColumn(name = "author_id")
    User author;

    @Lob
    @Column(name = "post_body", nullable = false, columnDefinition = "CLOB")
    String body; // length 280 for normal users; 2000 form premium users
}
