package com.beloved.web.controller.system;

import com.beloved.common.converter.DictConverter;
import com.beloved.common.model.dto.system.SysDictDto;
import com.beloved.common.model.entity.system.SysDictType;
import com.beloved.common.model.request.system.DictRequest;
import com.beloved.system.service.SysDictService;
import com.beloved.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-10 19:39
 * @Description:
 */
@RestController
@RequestMapping("/system/dict")
public class DictTypeController extends BaseController {

    @Autowired
    private SysDictService dictService;
    
    @Autowired
    private DictConverter dictConverter;

    @GetMapping(value = "/queryTypeList")
    public List<SysDictType> queryTypeList(DictRequest dictRequest) {
        return dictService.queryTypeList(dictConverter.requestToDto(dictRequest));
    }
    
    @GetMapping(value = "/queryTypeDataList")
    public List<SysDictDto> queryTypeDataList(DictRequest dictRequest) {
        return dictService.queryDictDtoList(dictConverter.requestToDto(dictRequest));
    }
    
}
