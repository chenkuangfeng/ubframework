package com.ubsoft.framework.core.web.listener;

import com.ubsoft.framework.core.dal.support.EntityHelper;
import com.ubsoft.framework.core.util.DateUtil;
import com.ubsoft.framework.system.license.LicenseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;


public class BaseListener implements ServletContextListener {
	private   static Logger logger = LoggerFactory.getLogger(EntityHelper.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/** 版本信息 */
		String sysPath = System.getProperty("user.dir");
		try {
			// license=主板+cupid+mac
			String[] license = LicenseManager.decryptLicense(sysPath + "/lic.lic").split("\\|");
			Date expireDate = DateUtil.string2Date(license[license.length - 1], "yyyy-MM-dd");
			Date today = new Date(System.currentTimeMillis());
			if (expireDate.getTime() < today.getTime()) {
				// 有效期
				logger.error("License 已过期!");
				System.exit(0);

			} else {
				System.out.println("-license有效期截止到"+license[license.length - 1]+"-");
			}

		} catch (Exception e) {
			logger.error("找不到license信息.");
			System.exit(0);

		}
		// 设置缓存配置文件路径
		String cachePath = event.getServletContext().getRealPath("/WEB-INF/conf/ehcache.xml");
		String configPath = event.getServletContext().getRealPath("/WEB-INF/conf/config.xml");
		String fdmPath = event.getServletContext().getRealPath("/WEB-INF/fdm/");
//		// 加载系统全局配置信息
//		AppConfig.initConfig(configPath);
//		// 加载系统服务器端缓存
//		AppConfig.initCache(cachePath);
//		AppConfig.sprintContext = ContextLoader.getCurrentWebApplicationContext();
//		// 加载fdm
//		AppConfig.initFdm(fdmPath);
//		AppConfig.webRootPath = event.getServletContext().getRealPath("/");
//		//IDbTableMetaService ds = (IDbTableMetaService) AppContext.getBean("dbTableMetaService");
//		//ds.initDbTableMeta("DefaultDS", null, null);
		

	}

}
