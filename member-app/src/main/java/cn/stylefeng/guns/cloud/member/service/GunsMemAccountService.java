package cn.stylefeng.guns.cloud.member.service;

import cn.stylefeng.guns.cloud.api.member.entity.GunsMemAccount;

import com.baomidou.mybatisplus.extension.service.IService;


public interface GunsMemAccountService extends IService<GunsMemAccount> {


    GunsMemAccount findSelect(Long id);
}
