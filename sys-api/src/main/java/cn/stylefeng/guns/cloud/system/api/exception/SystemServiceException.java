/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
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
package cn.stylefeng.guns.cloud.system.api.exception;

import cn.stylefeng.guns.cloud.model.enums.exp.AbstractBaseException;
import cn.stylefeng.guns.cloud.model.exp.ApiServiceException;

/**
 * 系统管理服务抛出的异常
 *
 * @author fengshuonan
 * @date 2018-08-08-上午10:04
 */
public class SystemServiceException extends ApiServiceException {

    public SystemServiceException() {
    }

    public SystemServiceException(AbstractBaseException abstractBaseException) {
        super(abstractBaseException);
    }
}
