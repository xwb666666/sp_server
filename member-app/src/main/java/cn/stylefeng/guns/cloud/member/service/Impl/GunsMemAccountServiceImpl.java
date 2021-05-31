package cn.stylefeng.guns.cloud.member.service.Impl;
import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAccount;
import cn.stylefeng.guns.cloud.member.mapper.GunsMemAccountMapper;
import cn.stylefeng.guns.cloud.member.service.GunsMemAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
@Service
public class GunsMemAccountServiceImpl extends ServiceImpl<GunsMemAccountMapper, GunsMemAccount> implements GunsMemAccountService {


    @Override
    public GunsMemAccount findSelect(Long id) {
        QueryWrapper<GunsMemAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",id);
        queryWrapper.select();
        GunsMemAccount gunsMemAccount=  baseMapper.selectOne(queryWrapper);
        return gunsMemAccount;

    }
}
