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

    public int updateFeedbackStatusById(int status, int id) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        List<FeedbackPO> feedbackPOS = new ArrayList<FeedbackPO>();
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.updateFeedbackStatusById(status, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return affectedLines;
    }

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

    public int deleteFeedbackById(int id) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.deleteFeedbackById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return affectedLines;
    }

    public int deleteFeedbackByTimeOnlySolved(TimeSpanBO timeSpanBO, int status) throws Exception {
        SqlSession session = DBTools.getSession();
        FeedbackMapper feedbackMapper = session.getMapper(FeedbackMapper.class);
        int affectedLines = 0;
        try {
            affectedLines = feedbackMapper.deleteFeedbackByTimeOnlySolved(timeSpanBO, status);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return affectedLines;
    }
}
