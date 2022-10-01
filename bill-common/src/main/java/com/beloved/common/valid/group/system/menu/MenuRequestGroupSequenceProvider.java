package com.beloved.common.valid.group.system.menu;

import com.beloved.common.enums.MenuTypeEnum;
import com.beloved.common.model.request.system.MenuRequest;
import com.beloved.common.service.BaseEnum;
import com.beloved.common.utils.ObjectUtils;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-28 13:22
 * @Description: 动态提供MenuRequest参数校验分组
 */
public class MenuRequestGroupSequenceProvider implements DefaultGroupSequenceProvider<MenuRequest> {
   
    @Override
    public List<Class<?>> getValidationGroups(MenuRequest object) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(MenuRequest.class);

        if (ObjectUtils.isEmpty(object)) {
            return defaultGroupSequence;  
        }
        
        if (ObjectUtils.isEmpty(object.getMenuId())) {
            if (Objects.equals(MenuTypeEnum.DIR, BaseEnum.getEnumByValue(object.getMenuType(), MenuTypeEnum.class))) {
                defaultGroupSequence.add(AddDir.class);
            }
            if (Objects.equals(MenuTypeEnum.MENU, BaseEnum.getEnumByValue(object.getMenuType(), MenuTypeEnum.class))) {
                defaultGroupSequence.add(AddMenu.class);
            }
        } else {
            if (Objects.equals(MenuTypeEnum.DIR, BaseEnum.getEnumByValue(object.getMenuType(), MenuTypeEnum.class))) {
                defaultGroupSequence.add(EditDir.class);
            }
            if (Objects.equals(MenuTypeEnum.MENU, BaseEnum.getEnumByValue(object.getMenuType(), MenuTypeEnum.class))) {
                defaultGroupSequence.add(EditMenu.class);
            }
        }

        return defaultGroupSequence;
    }
}
