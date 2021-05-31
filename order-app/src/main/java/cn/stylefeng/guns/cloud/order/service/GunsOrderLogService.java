package cn.stylefeng.guns.cloud.order.service;
import cn.stylefeng.guns.cloud.api.order.GunsOrderLog;
import cn.stylefeng.guns.cloud.order.model.result.OrderLogResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface GunsOrderLogService extends IService<GunsOrderLog> {


    Page<OrderLogResult> selectOrderLog();
}
