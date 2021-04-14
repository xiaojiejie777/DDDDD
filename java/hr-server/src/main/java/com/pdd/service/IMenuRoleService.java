package com.pdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pdd.pojo.MenuRole;
import com.pdd.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pdd
 * @since 2021-03-13
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
