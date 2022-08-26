package com.beloved.system.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.core.codec.Base64;
import com.beloved.common.constant.CaptchaConstants;
import com.beloved.common.constant.RedisConstants;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.MyAuthenticationException;
import com.beloved.common.model.vo.auth.CaptchaVo;
import com.beloved.common.utils.ObjectUtils;
import com.beloved.common.utils.RedisUtils;
import com.beloved.common.utils.StringUtils;
import com.beloved.common.utils.UUIDUtils;
import com.beloved.core.config.BillConfig;
import com.beloved.system.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-28 09:46
 * @Description:
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private BillConfig billConfig;
    
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public CaptchaVo createCaptcha() {
        CaptchaVo captchaDto = new CaptchaVo();
        
        Boolean captchaOnOff = billConfig.getCaptchaOnOff();
        captchaDto.setOnOff(captchaOnOff);
        if (!captchaOnOff) {
            return captchaDto;
        }

        String type = billConfig.getCaptchaType();
        
        AbstractCaptcha captcha = null;
        String imagePrefix = null;
        switch (type) {
            case CaptchaConstants.TYPE_CIRCLE:
                imagePrefix = CaptchaConstants.BASE64_PNG_TYPE;
                captcha = CaptchaUtil.createCircleCaptcha(CaptchaConstants.DEFAULT_WIDTH ,CaptchaConstants.DEFAULT_HEIGHT);
                break;
            case CaptchaConstants.TYPE_SHEAR:
                imagePrefix = CaptchaConstants.BASE64_PNG_TYPE;
                captcha = CaptchaUtil.createShearCaptcha(CaptchaConstants.DEFAULT_WIDTH ,CaptchaConstants.DEFAULT_HEIGHT);
                break;
            case CaptchaConstants.TYPE_GIF:
                imagePrefix = CaptchaConstants.BASE64_GIF_TYPE;
                captcha = CaptchaUtil.createGifCaptcha(CaptchaConstants.DEFAULT_WIDTH ,CaptchaConstants.DEFAULT_HEIGHT);
                break;
            case CaptchaConstants.TYPE_LINE:
            default:
                imagePrefix = CaptchaConstants.BASE64_PNG_TYPE;
                captcha = CaptchaUtil.createLineCaptcha(CaptchaConstants.DEFAULT_WIDTH ,CaptchaConstants.DEFAULT_HEIGHT);
        }

        String uuid = UUIDUtils.getUUID();

        String key = this.getRedisKey(uuid);

        redisUtils.set(key, captcha.getCode(), billConfig.getCaptchaExpiration(), TimeUnit.MINUTES);

        FastByteArrayOutputStream os = null;
        
        try {
            os = new FastByteArrayOutputStream();
            captcha.write(os);
            
            captchaDto.setUuid(uuid);
            String image = imagePrefix + Base64.encode(os.toByteArray());
            captchaDto.setImage(image);

            return captchaDto;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ObjectUtils.isNotEmpty(os)) os.close();
        }
    }

    @Override
    public void verify(String uuid, String code) {
        
        String key = this.getRedisKey(uuid);

        String captcha = (String) redisUtils.get(key);
        redisUtils.del(key);
        
        if (StringUtils.isEmpty(captcha)) {
            throw new MyAuthenticationException(ErrorCode.CAPTCHA_EXPIRED);
        }
        
        if (!StringUtils.equalsIgnoreCase(code, captcha)) {
            throw new MyAuthenticationException(ErrorCode.CAPTCHA_FAIL);
        }
    }
    
    private String getRedisKey(String uuid) {
        return RedisConstants.AUTH_CAPTCHA_KEY + uuid;
    }
}
