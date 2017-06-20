//
//  BrowserViewController.h
//  test.leaking
//
//  Created by Ricky He on 20/6/17.
//  Copyright © 2017 noisyfox. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BrowserViewController : UIViewController

@property (weak, nonatomic) IBOutlet UIWebView *webView;

@end
