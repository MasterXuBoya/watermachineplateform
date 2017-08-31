package com.terabits.service;

import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.FeedbackPO;

import java.util.List;

public interface FeedbackService {
    /**
     * 插入反馈记录
     *
     * @param feedbackPO
     * @return 受影响的行数
     */
    public int insertFeedback(FeedbackPO feedbackPO);

    /**
     * 通过电话号码来查询反馈
     *
     * @param phone
     * @return该电话号码相关的所有反馈
     */
    public List<FeedbackPO> selectFeedbackByPhone(String phone);

    /**
     * 通过时间段查询反馈信息
     *
     * @param timeSpanBO
     * @return该时间段的所有反馈
     */
    public List<FeedbackPO> selectFeedbackByTime(TimeSpanBO timeSpanBO);

    /**
     * 通过电话号码删除反馈
     *
     * @param phone
     * @return 受影响的行数
     */
    public int deleteFeedbackByPhone(String phone);

    /**
     * 通过时间段删除反馈
     *
     * @param timeSpanBO
     * @return 受影响的行数
     */
    public int deleteFeedbackByTime(TimeSpanBO timeSpanBO);

}