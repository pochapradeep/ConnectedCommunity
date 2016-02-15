package com.connComm.masters

class VehicleDetails {

	String vehicleRegistrationNo
	Integer maxOccupancy
	String vehicleNickName
	ResidentMaster residentMaster
	VehicleType vehicleType

	static hasMany = [tripSchedules: TripSchedule]
	static belongsTo = [ResidentMaster, VehicleType]

	static mapping = {
		id column: "VEHICLE_DETAILS_ID", generator: "increment"
		version false
	}

	static constraints = {
		vehicleRegistrationNo maxSize: 20
		vehicleNickName nullable: true, maxSize: 50
	}
}
