package com.terabits.service.impl;

import com.terabits.mapper.FeedbackMapper;
import com.terabits.meta.bo.TimeSpanBO;
import com.terabits.meta.po.FeedbackPO;
import com.terabits.utils.DBTools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl {
    //查询操作，session.close()
    public List<FeedbackPO> selectFeedbackByPhone(String phone) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        List<FeedbackPO> feedbackPOS = new ArrayList<FeedbackPO>();
        try {
            feedbackPOS = feedbackMapper.selectFeedbackByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return feedbackPOS;
    }

    public List<FeedbackPO> selectFeedbackByTime(TimeSpanBO timeSpanBO) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        List<FeedbackPO> feedbackPOS = new ArrayList<FeedbackPO>();
        try {
            feedbackPOS = feedbackMapper.selectFeedbackByTime(timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return feedbackPOS;
    }

    public int deleteFeedbackByPhone(String phone) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.deleteFeedbackByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return affectedLines;
    }

    public int deleteFeedbackByTime(TimeSpanBO timeSpanBO) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.deleteFeedbackByTime(timeSpanBO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return affectedLines;
    }
}
