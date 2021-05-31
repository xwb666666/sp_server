package cn.stylefeng.guns.cloud.member.mapper;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemIntegralDetail;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemIntegralDetailParam;
import cn.stylefeng.guns.cloud.api.member.model.result.GunsMemIntegralDetailResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
public interface GunsMemIntegralDetailMapper extends BaseMapper<GunsMemIntegralDetail> {

    List<GunsMemIntegralDetailResult> getSelectList(Page<GunsMemIntegralDetailResult> page, GunsMemIntegralDetailParam param);
}
