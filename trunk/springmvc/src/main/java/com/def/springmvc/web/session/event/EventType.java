package com.def.springmvc.web.session.event;

/**
 * session事件类型的标识类
 * @author sunhaibo
 *
 */
public final class EventType {
	
    /**
     * session 被创建时的事件类型.
     */
    public static final EventType SESSION_CREATEED = new EventType();

    /**
     * session 销毁时的事件类型.
     */
    public static final EventType SESSION_DESTROYED = new EventType();
    
    /**
     * 当向session中保存内容时的事件类型 .
     */ 
    public static final EventType SESSION_SET_ATTRIBUTE = new EventType();
    /**
     * 当从session中移除内容时的事件类型 .
     */ 
    public static final EventType SESSION_REMOVE_ATTRIBUTE = new EventType();
    
    
    private EventType(){}
}
