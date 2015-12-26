package com.myfood.common.util;

import java.util.Iterator;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MediaUtil {
	private static Logger logger = LoggerFactory.getLogger(MediaUtil.class);
	@SuppressWarnings("unchecked")
	public static String getDefaultExtension(byte[] fileContent) {
		MagicMatch match;
		String suffix = "";
		try {
			
			match = Magic.getMagicMatch(fileContent, false);
			if (match.getSubMatches().size() > 0) {
				// grab the first sub match and use its extension.
				Iterator it = match.getSubMatches().iterator();
				MagicMatch m = (MagicMatch) it.next();
				if (m != null)
					suffix = m.getExtension();
			} else {
				suffix = match.getExtension();
			}
		} catch (Exception ex) {
			logger.error("getting default getDefaultExtension: "+ex.getMessage(), ex);
			suffix = "raw";
		}
		return suffix;
	}
}
