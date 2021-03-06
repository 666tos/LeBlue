
#include "J2ObjC_header.h"

#pragma push_macro("INCLUDE_ALL_MockBluetoothTest")
#ifdef RESTRICT_MockBluetoothTest
#define INCLUDE_ALL_MockBluetoothTest 0
#else
#define INCLUDE_ALL_MockBluetoothTest 1
#endif
#undef RESTRICT_MockBluetoothTest

#if !defined (MockBluetoothTest_) && (INCLUDE_ALL_MockBluetoothTest || defined(INCLUDE_MockBluetoothTest))
#define MockBluetoothTest_

@class LeSessionController;
@class ListEventSinkSource;
@protocol LeDevice;
@protocol LeRemoteDevice;

@interface MockBluetoothTest : NSObject

#pragma mark Public

- (instancetype)init;

- (ListEventSinkSource *)createSource;

- (id<LeDevice>)getDevice;

- (jint)getLE_CHARACTERISTIC_1_1;

- (jint)getLE_CHARACTERISTIC_1_2;

- (jint)getLE_CHARACTERISTIC_LISTENER;

- (jint)getLE_DEVICE;

- (jint)getLE_DEVICE_LISTENER;

- (jint)getLE_REMOTE_DEVICE;

- (jint)getLE_REMOTE_DEVICE_LISTENER;

- (jint)getLE_SERVICE_1;

- (id<LeRemoteDevice>)getRemoteDevice;

- (LeSessionController *)getSessionController;

- (void)setDeviceWithLeDevice:(id<LeDevice>)device;

- (void)setRemoteDeviceWithLeRemoteDevice:(id<LeRemoteDevice>)remoteDevice;

- (void)setSessionControllerWithLeSessionController:(LeSessionController *)sessionController;

- (void)setUp;

- (void)testController;

@end

J2OBJC_EMPTY_STATIC_INIT(MockBluetoothTest)

FOUNDATION_EXPORT void MockBluetoothTest_init(MockBluetoothTest *self);

FOUNDATION_EXPORT MockBluetoothTest *new_MockBluetoothTest_init() NS_RETURNS_RETAINED;

FOUNDATION_EXPORT MockBluetoothTest *create_MockBluetoothTest_init();

J2OBJC_TYPE_LITERAL_HEADER(MockBluetoothTest)

@compatibility_alias HoutbeckeRsLeMockBluetoothTest MockBluetoothTest;

#endif

#pragma pop_macro("INCLUDE_ALL_MockBluetoothTest")
