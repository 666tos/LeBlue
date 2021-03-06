
#include "J2ObjC_header.h"

#pragma push_macro("INCLUDE_ALL_EventSinkTest")
#ifdef RESTRICT_EventSinkTest
#define INCLUDE_ALL_EventSinkTest 0
#else
#define INCLUDE_ALL_EventSinkTest 1
#endif
#undef RESTRICT_EventSinkTest

#if !defined (EventSinkTest_) && (INCLUDE_ALL_EventSinkTest || defined(INCLUDE_EventSinkTest))
#define EventSinkTest_

@interface EventSinkTest : NSObject

#pragma mark Public

- (instancetype)init;

- (void)setUp;

- (void)testLimit;

@end

J2OBJC_EMPTY_STATIC_INIT(EventSinkTest)

FOUNDATION_EXPORT void EventSinkTest_init(EventSinkTest *self);

FOUNDATION_EXPORT EventSinkTest *new_EventSinkTest_init() NS_RETURNS_RETAINED;

FOUNDATION_EXPORT EventSinkTest *create_EventSinkTest_init();

J2OBJC_TYPE_LITERAL_HEADER(EventSinkTest)

@compatibility_alias HoutbeckeRsLeEventSinkTest EventSinkTest;

#endif

#pragma pop_macro("INCLUDE_ALL_EventSinkTest")
