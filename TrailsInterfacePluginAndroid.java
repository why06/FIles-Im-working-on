package com.designsensory.old_tn_trail;

import java.io.File;
import java.util.Calendar;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

public class TrailsInterfacePluginAndroid {

	public void updateAppData()
	{
		if(Configuration.kUpdateApp)
		{
			//Let's get all the info we need about our app /WARN/
			long lastUpdated = getAppLastUpdateTime(OldTennessee.context);
			Calendar currentDate = Calendar.getInstance();
			Calendar timeToUpdate = Calendar.getInstance();
			long updateInterval = Configuration.kUpdateInterval * 1000; //convert to milliseconds
			timeToUpdate.setTimeInMillis(lastUpdated + updateInterval);
			
			boolean skipupdate = false; //toggles based on date since last update.
			
			//if Time to update
			if (currentDate.after(timeToUpdate))
			{
				OldTNDB database = new OldTNDB(OldTennessee.context); //pass context
		        database.fetchData();
				//Do it
			}else{
				//Don't
			}
		}
	}
	
	/**
	 * The time at which the app was last updated. Units are as per currentTimeMillis(). 
	 * @param context
	 * @return
	 */
	public static long getAppLastUpdateTime(Context context){
	    try {
	    if(Build.VERSION.SDK_INT>8/*Build.VERSION_CODES.FROYO*/ ){
	        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.lastUpdateTime;
	    }else{
	        ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
	        String sAppFile = appInfo.sourceDir;
	        return new File(sAppFile).lastModified();
	    }
	    } catch (NameNotFoundException e) {
	    //should never happen
	    return 0;
	    }
	}
}
