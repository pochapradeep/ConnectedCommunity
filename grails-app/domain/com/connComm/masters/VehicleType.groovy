package com.connComm.masters

class VehicleType {

	String vehicleTypeName

	static hasMany = [vehicleDetailses: VehicleDetails]

	static mapping = {
		id column: "VEHICLE_TYPE_ID", generator: "increment"
		version false
	}

	static constraints = {
		vehicleTypeName maxSize: 50
	}
}
