package com.beloved.web.controller.system;

import com.beloved.common.model.vo.common.OptionVo;
import com.beloved.web.controller.common.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:39
 * @Description:
 */
@RestController
@RequestMapping("/system/dict/data")
public class DictDataController extends BaseController {

    /**
     * 根据字典类型获取字典数据
     * @param dictType
     * @return
     */
    @GetMapping(value = "/options/{dictType}")
    public List<OptionVo> dictType(@PathVariable String dictType) {
        
        return null;
    }
    
}
