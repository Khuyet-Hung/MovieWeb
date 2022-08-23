package com.assm.utils;

import com.assm.model.Movie;
import com.assm.model.User;

public class ShareHelper {
	public static User USER = null;
	public static Movie Video = null;
	
    public static void logoff() {
        ShareHelper.USER = null;
    }

    public static boolean authenticated() {
        return ShareHelper.USER != null;
    }
} 
