package com.terabits.mapper;

import com.terabits.meta.po.Statistic.AuxcalPO;
import com.terabits.meta.po.Statistic.TotalPO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticMapper {

    public AuxcalPO selectTodayAuxcal(@Param("day") String day) throws Exception;
    public TotalPO selectTotal() throws Exception;

}
