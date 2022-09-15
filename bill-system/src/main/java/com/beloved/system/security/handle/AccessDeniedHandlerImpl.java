package com.beloved.system.security.handle;

import com.beloved.common.enums.ErrorCode;
import com.beloved.common.model.vo.ResultVo;
import com.beloved.common.utils.JsonUtils;
import com.beloved.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证用户访问无权限资源自定义异常处理器
 *
 * @author beloved
 */
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    
    @Autowired
    private JsonUtils jsonUtils;
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.error("权限不足：{}", e.getMessage(), e);
        ServletUtils.writeJson(response, jsonUtils.toJSONString(new ResultVo<>(ErrorCode.FORBIDDEN)));
    }
}
