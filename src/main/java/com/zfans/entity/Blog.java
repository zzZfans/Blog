package com.zfans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zfans
 * @date 2020/5/4 22:47
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Basic(fetch = FetchType.LAZY) //懒加载
    @Lob //大字段类型
    private String content;

    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentAbled;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient //不入库
    private String tagIds;

    @Transient
    private long createTimeL;

    private String description;

    @ManyToOne
//    @JsonIgnoreProperties(value = {"blogs"})
    private Type type;


    @ManyToMany(cascade = {CascadeType.PERSIST})
//    @JsonIgnoreProperties(value = {"blogs"})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
//    @JsonIgnoreProperties(value = {"blogs"})
    private User user;

    @OneToMany(mappedBy = "blog")
//    @JsonIgnoreProperties(value = {"blog"})
    private List<Comment> comments = new ArrayList<>();

    public void initTagIds() {
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(',');
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }

    @Override
    public String toString() {
        if (type != null) {
            type.getBlogs().clear();
        }
        if (user != null) {
            user.getBlogs().clear();
        }

        comments.forEach(data -> {
            data.setBlog(null);
        });

        tags.forEach(data -> {
            data.getBlogs().clear();
        });

        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentAbled=" + commentAbled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tagIds='" + tagIds + '\'' +
                ", createTimeL=" + createTimeL +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
