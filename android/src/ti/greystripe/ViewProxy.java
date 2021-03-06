/**
 * Ti.Greystripe Module
 * Copyright (c) 2010-2011 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */
package ti.greystripe;

import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.TiMessenger;
import org.appcelerator.kroll.common.AsyncResult;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

@Kroll.proxy(creatableInModule = GreystripeModule.class)
public class ViewProxy extends TiViewProxy {

	private View _view;

	public ViewProxy() {
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		_view = new View(this);
		_view.getLayoutParams().autoFillsHeight = true;
		_view.getLayoutParams().autoFillsWidth = true;
		return _view;
	}

    private static final int MSG_REFRESH = 50000;

	private final Handler handler = new Handler(TiMessenger.getMainMessenger().getLooper(), new Handler.Callback ()
	{
    	public boolean handleMessage(Message msg)
        {
            switch (msg.what) {
                case MSG_REFRESH: {
                    AsyncResult result = (AsyncResult) msg.obj;
                    handleRefresh();
                    result.setResult(null);
                    return true;
                }
            }
            return false;
        }
	});

	private void handleRefresh()
	{
        _view.refresh();
	}

	@Kroll.method
	public void refresh() {
	    if (_view != null) {
	    	if (!TiApplication.isUIThread()) {
	            TiMessenger.sendBlockingMainMessage(handler.obtainMessage(MSG_REFRESH));
        	} else {
        	    handleRefresh();
        	}
        }
	}
}