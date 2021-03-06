
#include "J2ObjC_header.h"

#pragma push_macro("INCLUDE_ALL_LeGattStatus")
#ifdef RESTRICT_LeGattStatus
#define INCLUDE_ALL_LeGattStatus 0
#else
#define INCLUDE_ALL_LeGattStatus 1
#endif
#undef RESTRICT_LeGattStatus

#if __has_feature(nullability)
#pragma clang diagnostic push
#pragma GCC diagnostic ignored "-Wnullability-completeness"
#endif

#if !defined (LeGattStatus_) && (INCLUDE_ALL_LeGattStatus || defined(INCLUDE_LeGattStatus))
#define LeGattStatus_

#define RESTRICT_JavaLangEnum 1
#define INCLUDE_JavaLangEnum 1
#include "java/lang/Enum.h"

@class IOSObjectArray;

typedef NS_ENUM(NSUInteger, LeGattStatus_Enum) {
  LeGattStatus_Enum_SUCCESS = 0,
  LeGattStatus_Enum_READ_NOT_PERMITTED = 1,
  LeGattStatus_Enum_WRITE_NOT_PERMITTED = 2,
  LeGattStatus_Enum_INSUFFICIENT_AUTHENTICATION = 3,
  LeGattStatus_Enum_REQUEST_NOT_SUPPORTED = 4,
  LeGattStatus_Enum_INSUFFICIENT_ENCRYPTION = 5,
  LeGattStatus_Enum_INVALID_OFFSET = 6,
  LeGattStatus_Enum_INVALID_ATTRIBUTE_LENGTH = 7,
  LeGattStatus_Enum_FAILURE = 8,
};

@interface LeGattStatus : JavaLangEnum

+ (LeGattStatus * __nonnull)SUCCESS;

+ (LeGattStatus * __nonnull)READ_NOT_PERMITTED;

+ (LeGattStatus * __nonnull)WRITE_NOT_PERMITTED;

+ (LeGattStatus * __nonnull)INSUFFICIENT_AUTHENTICATION;

+ (LeGattStatus * __nonnull)REQUEST_NOT_SUPPORTED;

+ (LeGattStatus * __nonnull)INSUFFICIENT_ENCRYPTION;

+ (LeGattStatus * __nonnull)INVALID_OFFSET;

+ (LeGattStatus * __nonnull)INVALID_ATTRIBUTE_LENGTH;

+ (LeGattStatus * __nonnull)FAILURE;

#pragma mark Public

+ (LeGattStatus *)fromStringWithNSString:(NSString *)status;

+ (LeGattStatus *)valueOfWithNSString:(NSString *)name;

+ (IOSObjectArray *)values;

#pragma mark Package-Private

- (LeGattStatus_Enum)toNSEnum;

@end

J2OBJC_STATIC_INIT(LeGattStatus)

/*! INTERNAL ONLY - Use enum accessors declared below. */
FOUNDATION_EXPORT LeGattStatus *LeGattStatus_values_[];

inline LeGattStatus *LeGattStatus_get_SUCCESS();
J2OBJC_ENUM_CONSTANT(LeGattStatus, SUCCESS)

inline LeGattStatus *LeGattStatus_get_READ_NOT_PERMITTED();
J2OBJC_ENUM_CONSTANT(LeGattStatus, READ_NOT_PERMITTED)

inline LeGattStatus *LeGattStatus_get_WRITE_NOT_PERMITTED();
J2OBJC_ENUM_CONSTANT(LeGattStatus, WRITE_NOT_PERMITTED)

inline LeGattStatus *LeGattStatus_get_INSUFFICIENT_AUTHENTICATION();
J2OBJC_ENUM_CONSTANT(LeGattStatus, INSUFFICIENT_AUTHENTICATION)

inline LeGattStatus *LeGattStatus_get_REQUEST_NOT_SUPPORTED();
J2OBJC_ENUM_CONSTANT(LeGattStatus, REQUEST_NOT_SUPPORTED)

inline LeGattStatus *LeGattStatus_get_INSUFFICIENT_ENCRYPTION();
J2OBJC_ENUM_CONSTANT(LeGattStatus, INSUFFICIENT_ENCRYPTION)

inline LeGattStatus *LeGattStatus_get_INVALID_OFFSET();
J2OBJC_ENUM_CONSTANT(LeGattStatus, INVALID_OFFSET)

inline LeGattStatus *LeGattStatus_get_INVALID_ATTRIBUTE_LENGTH();
J2OBJC_ENUM_CONSTANT(LeGattStatus, INVALID_ATTRIBUTE_LENGTH)

inline LeGattStatus *LeGattStatus_get_FAILURE();
J2OBJC_ENUM_CONSTANT(LeGattStatus, FAILURE)

FOUNDATION_EXPORT LeGattStatus *LeGattStatus_fromStringWithNSString_(NSString *status);

FOUNDATION_EXPORT IOSObjectArray *LeGattStatus_values();

FOUNDATION_EXPORT LeGattStatus *LeGattStatus_valueOfWithNSString_(NSString *name);

FOUNDATION_EXPORT LeGattStatus *LeGattStatus_fromOrdinal(NSUInteger ordinal);

J2OBJC_TYPE_LITERAL_HEADER(LeGattStatus)

@compatibility_alias HoutbeckeRsLeLeGattStatus LeGattStatus;

#endif


#if __has_feature(nullability)
#pragma clang diagnostic pop
#endif
#pragma pop_macro("INCLUDE_ALL_LeGattStatus")
