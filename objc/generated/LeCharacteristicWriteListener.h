
#include "J2ObjC_header.h"

#pragma push_macro("INCLUDE_ALL_LeCharacteristicWriteListener")
#ifdef RESTRICT_LeCharacteristicWriteListener
#define INCLUDE_ALL_LeCharacteristicWriteListener 0
#else
#define INCLUDE_ALL_LeCharacteristicWriteListener 1
#endif
#undef RESTRICT_LeCharacteristicWriteListener

#if !defined (LeCharacteristicWriteListener_) && (INCLUDE_ALL_LeCharacteristicWriteListener || defined(INCLUDE_LeCharacteristicWriteListener))
#define LeCharacteristicWriteListener_

@class JavaUtilUUID;
@protocol LeGattCharacteristic;
@protocol LeRemoteDevice;

@protocol LeCharacteristicWriteListener < JavaObject >

- (void)leCharacteristicWrittenWithJavaUtilUUID:(JavaUtilUUID *)uuid
                             withLeRemoteDevice:(id<LeRemoteDevice>)remoteDevice
                       withLeGattCharacteristic:(id<LeGattCharacteristic>)characteristic
                                    withBoolean:(jboolean)succes;

@end

J2OBJC_EMPTY_STATIC_INIT(LeCharacteristicWriteListener)

J2OBJC_TYPE_LITERAL_HEADER(LeCharacteristicWriteListener)

#define HoutbeckeRsLeLeCharacteristicWriteListener LeCharacteristicWriteListener

#endif

#pragma pop_macro("INCLUDE_ALL_LeCharacteristicWriteListener")
