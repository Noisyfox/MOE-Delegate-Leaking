//
//  AppViewController.h
//  test.leaking
//
//  Created by Ricky He on 20/6/17.
//  Copyright © 2017 noisyfox. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AppViewController : UIViewController

- (IBAction)forceGCAction:(UIButton *)sender;

- (IBAction)dumpHeapAction:(UIButton *)sender;

@end
