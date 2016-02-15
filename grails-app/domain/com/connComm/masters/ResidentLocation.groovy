package com.connComm.masters

class ResidentLocation {

	String locationNickName
	String locationName
	String locationLatitude
	String locationLongitude
	ResidentMaster residentMaster

	static hasMany = [tripRegistriesForDestinationLocationId: TripRegistry,
	                  tripRegistriesForSourceLocationId: TripRegistry,
	                  tripSchedulesForDestinationLocationId: TripSchedule,
	                  tripSchedulesForSourceLocationId: TripSchedule]
	static belongsTo = [ResidentMaster]

	// TODO you have multiple hasMany references for class(es) [TripRegistry, TripSchedule] 
	//      so you'll need to disambiguate them with the 'mappedBy' property:
	
	static mappedBy = [tripRegistriesForDestinationLocationId: "residentLocationByDestinationLocationId",
		tripRegistriesForSourceLocationId: "residentLocationBySourceLocationId",
		tripSchedulesForDestinationLocationId: "residentLocationByDestinationLocationId",
		tripSchedulesForSourceLocationId: "residentLocationBySourceLocationId"]


	static mapping = {
		id column: "LOCATION_ID", generator: "increment"
		version false
	}

	static constraints = {
		locationNickName maxSize: 50
		locationName maxSize: 50
		locationLatitude nullable: true, maxSize: 20
		locationLongitude nullable: true, maxSize: 20
	}
}
