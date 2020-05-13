package com.zfans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zfans
 * @date 2020/5/4 23:07
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名不能为空")
    private String name;

    @OneToMany(mappedBy = "type")
//    @JsonIgnoreProperties(value = {"type"})
    private List<Blog> blogs = new ArrayList<>();

    @Override
    public String toString() {
        blogs.forEach(data -> {
            data.setType(null);
        });

        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
