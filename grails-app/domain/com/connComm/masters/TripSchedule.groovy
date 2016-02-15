package com.connComm.masters

class TripSchedule {

	Integer communityId
	Date tripDateTime
	Integer emptySeatCount
	ResidentLocation residentLocationByDestinationLocationId
	ResidentLocation residentLocationBySourceLocationId
	RecurringTripType recurringTripType
	ResidentMaster residentMaster
	VehicleDetails vehicleDetails

	static hasMany = [tripRegistries: TripRegistry]
	static belongsTo = [RecurringTripType, ResidentLocation, ResidentMaster, VehicleDetails]

	static mapping = {
		id column: "TRIP_SCHEDULE_ID", generator: "increment"
		version false
	}

	static constraints = {
		tripDateTime nullable: true
	}
}
