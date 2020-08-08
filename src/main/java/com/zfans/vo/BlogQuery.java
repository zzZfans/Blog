package com.zfans.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zfans
 */
@Data
@NoArgsConstructor
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
