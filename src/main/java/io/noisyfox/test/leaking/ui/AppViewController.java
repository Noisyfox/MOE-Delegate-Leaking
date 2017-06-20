package io.noisyfox.test.leaking.ui;

import apple.foundation.NSArray;
import apple.foundation.NSFileManager;
import apple.foundation.NSURL;
import apple.foundation.enums.NSSearchPathDirectory;
import apple.foundation.enums.NSSearchPathDomainMask;
import dalvik.system.VMDebug;
import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Property;
import org.moe.natj.objc.ann.Selector;

import apple.NSObject;
import apple.uikit.UIButton;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;

import java.io.File;
import java.io.IOException;

@org.moe.natj.general.ann.Runtime( ObjCRuntime.class )
@ObjCClassName( "AppViewController" )
@RegisterOnStartup
public class AppViewController extends UIViewController
{

	@Owned
	@Selector( "alloc" )
	public static native AppViewController alloc();

	@Selector( "init" )
	public native AppViewController init();

	protected AppViewController( Pointer peer )
	{
		super( peer );
	}


	@Selector( "forceGCAction:" )
	public void forceGCAction()
	{
		Runtime.getRuntime().gc();
	}

	@Selector( "dumpHeapAction:" )
	public void dumpHeapAction()
	{
		String dumpName = String.valueOf( System.currentTimeMillis() );
		dumpName = "MOE-Dump-" + dumpName + ".hprof";

		NSArray nsa = NSFileManager.defaultManager().
				URLsForDirectoryInDomains(
						NSSearchPathDirectory.DocumentDirectory,
						NSSearchPathDomainMask.UserDomainMask );
		NSURL docDirURL = ( NSURL ) nsa.firstObject(); // Error handling is for cowards :)
		String fsPath = docDirURL.fileSystemRepresentation();
		try
		{
			VMDebug.dumpHprofData( new File( fsPath, dumpName ).getAbsolutePath() );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
}
