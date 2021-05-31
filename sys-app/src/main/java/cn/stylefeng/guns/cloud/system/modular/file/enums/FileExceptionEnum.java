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
package cn.stylefeng.guns.cloud.system.modular.file.enums;

import cn.stylefeng.guns.cloud.model.enums.exp.AbstractBaseException;

/**
 * 所有业务异常的枚举
 *
 * @author fengshuonan
 * @Date 2019年11月26日17:39:04
 */
public enum FileExceptionEnum implements AbstractBaseException {

    /**
     * 文件上传
     */
    FILE_UPLOAD_ERROR(701, "上传文件异常！"),

    /**
     * 创建文件客户端错误
     */
    FILE_CLIENT_ERROR(702, "创建文件客户端错误!"),

    /**
     * 获取文件流出错
     */
    FILE_IO_ERROR(703, "获取文件流出错!"),

    /**
     * 获取文件url下载地址出错
     */
    FILE_GET_URL_ERROR(704, "获取文件url出错!");

    FileExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
