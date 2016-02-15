package com.connComm.masters

class TripRegistry {

	Integer additionalPassengerCount
	Date tripScheduleDate
	ResidentLocation residentLocationByDestinationLocationId
	ResidentLocation residentLocationBySourceLocationId
	ResidentMaster residentMaster
	RecurringTripType recurringTripType
	TripSchedule tripSchedule

	static belongsTo = [RecurringTripType, ResidentLocation, ResidentMaster, TripSchedule]

	static mapping = {
		id column: "TRIP_REGISTRY_ID", generator: "increment"
		version false
	}

	static constraints = {
		tripScheduleDate nullable: true
	}
}
