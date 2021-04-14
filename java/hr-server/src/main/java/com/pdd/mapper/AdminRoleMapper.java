package com.pdd.mapper;

import com.pdd.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pdd.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pdd
 * @since 2021-03-13
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
