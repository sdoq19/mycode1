package com.resource.jms.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.utils.LoggerUtil;

/**
 * 消息监听接口
 * @author zhuzh
 *
 */
public class MyListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		try {
			LoggerUtil.info(getClass(), ">>>接受消息：" + ((TextMessage)arg0).getText() + "");
		} catch (JMSException e) {
			LoggerUtil.info(getClass(), e.toString());
		}
	}
}
