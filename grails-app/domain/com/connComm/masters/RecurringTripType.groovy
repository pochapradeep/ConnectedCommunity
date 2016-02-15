package com.connComm.masters

class RecurringTripType {

	String recurringTripTypeName

	static hasMany = [tripRegistries: TripRegistry,
	                  tripSchedules: TripSchedule]

	static mapping = {
		id column: "RECURRING_TRIP_TYPE_ID", generator: "increment"
		version false
	}

	static constraints = {
		recurringTripTypeName maxSize: 50
	}
}
