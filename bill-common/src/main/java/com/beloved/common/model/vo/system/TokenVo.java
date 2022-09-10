package com.beloved.common.model.vo.system;

import lombok.Data;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-29 11:19
 * @Description:
 */
@Data
public class TokenVo {
    
    private String header;
    private String tokenPrefix;
    private String token;
}
