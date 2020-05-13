package com.zfans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zfans
 * @date 2020/5/4 23:13
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;


    @ManyToOne
//    @JsonIgnoreProperties(value = {"comments"})
    private Blog blog;


    @OneToMany(mappedBy = "parentComment")
//    @JsonIgnoreProperties(value = {"parentComment"})
    private List<Comment> replyComments = new ArrayList<>();


    @ManyToOne
//    @JsonIgnoreProperties(value = {"replyComments"})
    private Comment parentComment;

    @Override
    public String toString() {
        if (blog != null) {
            blog.getComments().clear();
        }

        replyComments.forEach(data -> {
            data.setParentComment(null);
            data.getReplyComments().clear();
        });

        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blog=" + blog +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                '}';
    }
}
