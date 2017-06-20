package io.noisyfox.test.leaking.ui;

import apple.foundation.NSError;
import apple.foundation.NSURL;
import apple.foundation.NSURLRequest;
import apple.uikit.UIViewController;
import apple.uikit.UIWebView;
import apple.uikit.protocol.UIWebViewDelegate;
import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Selector;

/**
 * Created by noisyfox on 20/6/17.
 */
@org.moe.natj.general.ann.Runtime( ObjCRuntime.class )
@ObjCClassName( "BrowserUnsafeViewController" )
@RegisterOnStartup
public class BrowserUnsafeViewController extends UIViewController implements UIWebViewDelegate
{
	@Owned
	@Selector( "alloc" )
	public static native AppViewController alloc();

	@Selector( "init" )
	public native AppViewController init();


	@Selector( "webView" )
	public native UIWebView webView();

	protected BrowserUnsafeViewController( Pointer peer )
	{
		super( peer );
	}

	@Override
	public void viewDidLoad()
	{
		super.viewDidLoad();

		webView().setDelegate_unsafe( this );
	}

	@Override
	public void viewDidAppear( boolean animated )
	{
		super.viewDidAppear( animated );
		webView().loadRequest( NSURLRequest.requestWithURL( NSURL.URLWithString( "https://www.google.com" ) ) );
	}

	@Override
	public void webViewDidFailLoadWithError( UIWebView webView, NSError error )
	{

	}

	@Override
	public boolean webViewShouldStartLoadWithRequestNavigationType( UIWebView webView, NSURLRequest request, long navigationType )
	{
		return true;
	}

	@Override
	public void webViewDidFinishLoad( UIWebView webView )
	{

	}

	@Override
	public void webViewDidStartLoad( UIWebView webView )
	{

	}
}
