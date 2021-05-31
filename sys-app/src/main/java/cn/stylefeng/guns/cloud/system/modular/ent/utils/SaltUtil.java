package cn.stylefeng.guns.cloud.system.modular.ent.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * 密码加盐的工具
 *
 * @author fengshuonan
 * @Date 2019/7/20 17:34
 */
public class SaltUtil {

    /**
     * 获取密码盐
     *
     * @author fengshuonan
     * @Date 2019/7/20 17:35
     */
    public static String getRandomSalt() {
        return RandomUtil.randomString(5);
    }

    /**
     * md5加密，带盐值
     *
     * @author fengshuonan
     * @Date 2019/7/20 17:36
     */
    public static String md5Encrypt(String password, String salt) {
        if (ObjectUtil.isEmpty(password) || ObjectUtil.isEmpty(salt)) {
            throw new IllegalArgumentException("密码或盐为空！");
        } else {
            return SecureUtil.md5(password + salt);
        }
    }

}
