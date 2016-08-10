
#include "J2ObjC_source.h"
#include "LeRemoteDevice.h"

@interface LeRemoteDevice : NSObject

@end

@implementation LeRemoteDevice

+ (const J2ObjcClassInfo *)__metadata {
  static const J2ObjcMethodInfo methods[] = {
    { "addListenerWithLeRemoteDeviceListener:", "addListener", "V", 0x401, NULL, NULL },
    { "removeListenerWithLeRemoteDeviceListener:", "removeListener", "V", 0x401, NULL, NULL },
    { "getAddress", NULL, "Ljava.lang.String;", 0x401, NULL, NULL },
    { "connect", NULL, "V", 0x401, NULL, NULL },
    { "disconnect", NULL, "V", 0x401, NULL, NULL },
    { "close", NULL, "V", 0x401, NULL, NULL },
    { "startServicesDiscovery", NULL, "V", 0x401, NULL, NULL },
    { "startServicesDiscoveryWithJavaUtilUUIDArray:", "startServicesDiscovery", "V", 0x481, NULL, NULL },
    { "setCharacteristicWriteListenerWithLeCharacteristicWriteListener:withJavaUtilUUIDArray:", "setCharacteristicWriteListener", "V", 0x481, NULL, NULL },
    { "setCharacteristicListenerWithLeCharacteristicListener:withJavaUtilUUIDArray:", "setCharacteristicListener", "V", 0x481, NULL, NULL },
    { "getName", NULL, "Ljava.lang.String;", 0x401, NULL, NULL },
    { "readRssi", NULL, "V", 0x401, NULL, NULL },
  };
  static const J2ObjcClassInfo _LeRemoteDevice = { 2, "LeRemoteDevice", "houtbecke.rs.le", NULL, 0x609, 12, methods, 0, NULL, 0, NULL, 0, NULL, NULL, NULL };
  return &_LeRemoteDevice;
}

@end

J2OBJC_INTERFACE_TYPE_LITERAL_SOURCE(LeRemoteDevice)