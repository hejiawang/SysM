package com.wang.sysm.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("customer", "id", Customer.class);
		arp.addMapping("sysm_code_log", "id", CodeLog.class);
		arp.addMapping("test", "id", Test.class);
		arp.addMapping("test2", "id", Test2.class);
	}
}

