package io.noisyfox.test.leaking.ui;

import apple.uikit.UIView;
import apple.uikit.UIViewController;
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
@ObjCClassName( "CycleRefView" )
@RegisterOnStartup
public class CycleRefView extends UIView
{
	@Owned
	@Selector( "alloc" )
	public static native CycleRefView alloc();

	protected CycleRefView( Pointer peer )
	{
		super( peer );
	}

	public UIViewController mController;
}
