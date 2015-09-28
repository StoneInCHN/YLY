package com.yly.service.impl;

import java.awt.image.BufferedImage;
import javax.annotation.Resource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.yly.beans.Setting;
import com.yly.beans.Setting.CaptchaType;
import com.yly.service.CaptchaService;
import com.yly.utils.SettingUtils;

/**
 * Service - 验证码
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements CaptchaService {

    @Resource(name = "imageCaptchaService")
    private com.octo.captcha.service.CaptchaService imageCaptchaService;

    public BufferedImage buildImage(String captchaId) {
        return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
    }

    public boolean isValid(CaptchaType captchaType, String captchaId, String captcha) {
        Setting setting = SettingUtils.get();
        if (captchaType == null || ArrayUtils.contains(setting.getCaptchaTypes(), captchaType)) {
            if (StringUtils.isNotEmpty(captchaId) && StringUtils.isNotEmpty(captcha)) {
                try {
                    return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

}