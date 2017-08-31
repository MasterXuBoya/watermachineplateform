package com.terabits.mapper;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.TerminalOfflineRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TerminalOfflineRecordMapper {
    /**
     * 插入设备掉线记录，imei号即可，时间自动生成
     *
     * @param terminalOfflineRecordPO
     * @return
     * @throws Exception，受影响的行数
     */
    public int terminalofflinerecord(TerminalOfflineRecordPO terminalOfflineRecordPO) throws Exception;

    /**
     * 依据imei号码或者imei号码+时间来返回掉线记录列表
     *
     * @param imei
     * @param timeSpanBO
     * @return
     * @throws Exception
     */
    public List<TerminalOfflineRecordPO> selectTerminalOfflineRecordByImeiOrTime(@Param("imei") String imei, @Param("timeSpanBO") TimeSpanBO timeSpanBO) throws Exception;

    /**
     * 依据imei号码或者imei号码+时间来是删除掉线记录列表
     * @param imei
     * @param timeSpanBO
     * @return
     * @throws Exception，受影响的行数
     */
    public int deleteTerminalOfflineRecordByImeiOrTime(@Param("imei") String imei, @Param("timeSpanBO") TimeSpanBO timeSpanBO) throws Exception;
}
