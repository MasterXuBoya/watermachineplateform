package com.terabits.mapper;

import com.terabits.meta.po.User.ConsumeOrderPO;
import com.terabits.meta.po.User.RechargeRecordPO;
import com.terabits.meta.po.User.UserPO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMapper {
    public List<ConsumeOrderPO> selectConsumeOrderByDisplayId(@Param("displayId") String displayId) throws Exception;

    /**
     * 查询某段时间消费记录
     */
    public List<ConsumeOrderPO> selectConsumeOrderByTime(@Param("displayId")String displayId,@Param("beginTime")String beginTime,@Param("endTime")String endTime) throws Exception;

}
