package cn.stylefeng.guns.cloud.member.mapper;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMember;
import cn.stylefeng.guns.cloud.api.member.model.params.GunsMemberQueryParam;
import cn.stylefeng.guns.cloud.member.model.result.GunsMemSelectResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import java.util.List;



public interface GunsMemMapper extends BaseMapper<GunsMember> {

    List<GunsMemSelectResult> getPageList(Page<GunsMemSelectResult>page, GunsMemberQueryParam param);

}
