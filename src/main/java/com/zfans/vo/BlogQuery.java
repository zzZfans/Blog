package com.zfans.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zfans
 * @date 2020/05/11 3:02
 */
@Data
@NoArgsConstructor
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
