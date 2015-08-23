package com.def.springmvc.web.session.store;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.def.springmvc.web.session.StandardSession;
import com.def.springmvc.web.session.Config;
import com.def.springmvc.web.session.UserSessionException;
import com.def.springmvc.util.JsonBinder;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemCacheSessionStore implements SessionStore {
	private MemCachedClient client;
	private static JsonBinder jsonBinder =JsonBinder.buildNormalBinder();
	public MemCacheSessionStore() {
		client = MemCachedClientSingletonFactory.getMemcacheClient();
	}

	
	public StandardSession loadSession(String id) throws UserSessionException {
		StandardSession session = null;
		if(id==null){
			return null;
		}
		try {
			String jsonString = (String) client.get(getKey(id));
			session=jsonBinder.fromJson(jsonString, StandardSession.class);
		} catch (Exception e) {
			throw new UserSessionException(e.getMessage());
		}
		if (session != null && !session.isValidate()) {
			session.invalidate();
			session = null;
		}
		return session;
	}

	
	public void saveOrUpdateSession(StandardSession session) throws UserSessionException {
		Date expiry = new Date((new Date()).getTime() + Config.getRefreshPeriod());
		if (session.isValidate()) {
			try {
				String jsonStr=jsonBinder.toJson(session);
				if(client.keyExists(getKey(session.getId()))){
					
				   client.replace(getKey(session.getId()), jsonStr, expiry);
				}else{
					client.add(getKey(session.getId()), jsonStr, expiry);	
				}
			} catch (Exception e) {
				throw new UserSessionException(e.getMessage());
			}
		} else {
			session.invalidate();
		}

	}

	
	public void removeSession(String id) throws UserSessionException {
		try {
			client.delete(getKey(id));
		} catch (Exception e) {
			throw new UserSessionException(e.getMessage());
		}

	}

	/**
	 * 根据memcache的region标示+session的id合成真实存在于memcache服务器上的key，防止和别的项目key冲突
	 * 
	 * @param key  session的id
	 * 
	 * @return 真实存在于memcache服务器上的key
	 * 
	 * create date: 2009-11-03
	 * author: liujia
	 */
	private String getKey(Object key) {
		if (key == null)
			throw new IllegalArgumentException("Cache key not be null");
		byte digest[];
		String region = Config.getRegion();
		MessageDigest alga = null;
		try {
			alga = MessageDigest.getInstance("MD5");
			alga.update(region.getBytes());
			alga.update(String.valueOf(key).getBytes());
			digest = alga.digest();
			return new String(Hex.encodeHex(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static class MemCachedClientSingletonFactory extends MemCachedClient {
		private static final SockIOPool pool = SockIOPool.getInstance();
		private static final MemCachedClientSingletonFactory client = new MemCachedClientSingletonFactory();
		protected final Log logger = LogFactory.getLog(getClass());

		private MemCachedClientSingletonFactory() {
          try{
			String[] servers = { Config.getCacheServers() };
			if (Config.getCacheServers().indexOf(",") != -1) {
				servers = Config.getCacheServers().split(",");

			}
			pool.setServers(servers);
			pool.setInitConn(0);
			pool.setMinConn(2);
			pool.setMaxConn(50);
			pool.setMaxIdle(0x1499700);
			pool.setMaintSleep(0x493e0L);
			pool.setNagle(false);
			pool.setSocketTO(3000);
			pool.setSocketConnectTO(0);
			pool.initialize();

			//client = new MemCachedClient("session-store");
			//this.setCompressEnable(true);
			//this.setCompressThreshold(1024L);
          }catch(Exception e){
        	  pool.shutDown();
        	  
        	  logger.error("#####"+e.getMessage());
        	  
          }
			logger.info("#####Memcahe client be createed !!!!!!");
		}

		public static MemCachedClient getMemcacheClient() {
			return client;
		}
	}

}
