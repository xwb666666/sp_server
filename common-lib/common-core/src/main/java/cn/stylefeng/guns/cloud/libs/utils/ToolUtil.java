package cn.stylefeng.guns.cloud.libs.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

public class ToolUtil {

    public static IdWorker WORKER = new IdWorker(1,1,1);
    public static final String SALT = "hc_mall";
    public static void copyProperties(Object source, Object target) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreNullValue(true).ignoreError());
    }

    /**
     * md5加密，带盐值
     *
     * @author fengshuonan
     * @Date 2019/7/20 17:36
     */
    public static String md5Encrypt(String password, String salt) {
        if (StrUtil.isBlank(password) || StrUtil.isBlank(salt)) {
            throw new IllegalArgumentException("密码或盐为空！");
        } else {
            return SecureUtil.md5(password + salt);
        }
    }

    public static String getRandomSalt() {
        return RandomUtil.randomString(5);
    }



    public static IdWorker setIdWorker(long workerId, long datacenterId, long sequence){
        WORKER = new IdWorker(workerId,datacenterId,sequence);
        return ToolUtil.WORKER;
    }
    public static String getManualIdGen(long workerId, long datacenterId, long sequence){
        long id = getManualIdGenLong(workerId,datacenterId,sequence);
        return String.valueOf(id);
    }
    public static Long getManualIdGenLong(long workerId, long datacenterId, long sequence){
        WORKER = setIdWorker(workerId,datacenterId,sequence);
        long id = WORKER.nextId();
        return id;
    }
    public static String getIdGen(){
        return String.valueOf(getIdGenLong());
    }
    public static Long getIdGenLong(){
        WORKER = setIdWorker(1,1,1);
        long id = WORKER.nextId();
        //System.out.println(id);
        return id;
    }
}
