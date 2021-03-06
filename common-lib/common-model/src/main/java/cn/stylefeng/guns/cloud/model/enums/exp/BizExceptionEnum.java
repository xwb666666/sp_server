/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.cloud.model.enums.exp;

/**
 * 所有业务异常的枚举
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum implements AbstractBaseException {

    /**
     * 服务器异常
     */
    SERVER_ERROR(41600, "服务器异常"),

    /**
     * token异常
     */
    TOKEN_EXPIRED(41601, "token过期"),
    TOKEN_ERROR(41602, "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(41603, "签名验证失败"),

    /**
     * session
     */
    NO_CURRENT_USER(41604, "没有登录用户");

    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final Integer code;

    private final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
