package io.noisyfox.test.leaking.ui;

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
@ObjCClassName( "CycleRefViewController" )
@RegisterOnStartup
public class CycleRefViewController extends UIViewController
{
	@Owned
	@Selector( "alloc" )
	public static native AppViewController alloc();

	protected CycleRefViewController( Pointer peer )
	{
		super( peer );
	}

	private CycleRefView refView;

	@Override
	public void viewDidLoad()
	{
		super.viewDidLoad();

		refView = ( CycleRefView ) view();
		refView.mController = this;
	}
}
