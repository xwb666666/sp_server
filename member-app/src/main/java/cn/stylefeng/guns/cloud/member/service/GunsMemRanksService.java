package cn.stylefeng.guns.cloud.member.service;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemRanks;
import cn.stylefeng.guns.cloud.member.model.result.GunsMemRanksResult;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface GunsMemRanksService extends IService<GunsMemRanks> {
    List<GunsMemRanksResult> selectList(Long sort);

    boolean removeId(Long id);

    List<GunsMemRanksResult> nameSelect(String name);
}
