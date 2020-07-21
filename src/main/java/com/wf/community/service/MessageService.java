package com.wf.community.service;

import com.wf.community.entity.Message;

import java.util.List;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/18 14:43
 */
public interface MessageService {

    List<Message> findConversations(int userId, int offset, int limit);

    int findConversationCount(int userId);

    List<Message> findLetters(String conversationId, int offset, int limit);

    int findLetterCount(String conversationId);

    int findLetterUnreadCount(int userId, String conversationId);

    int addMessage(Message message);

    int readMessage(List<Integer> ids);

    Message findLatestNotice(int userId, String topic);

    int findNoticeCount(int userId, String topic);

    int findNoticeUnreadCount(int userId, String topic);

    List<Message> findNotices(int userId, String topic, int offset, int limit);
}
