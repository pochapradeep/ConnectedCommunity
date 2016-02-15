package com.connComm.masters

class ResidentStatus {

	String residentStatusName

	static hasMany = [residentMasters: ResidentMaster,
	                  userResidentMappings: UserResidentMapping]

	static mapping = {
		id column: "RESIDENT_STATUS_ID", generator: "increment"
		version false
	}

	static constraints = {
		residentStatusName maxSize: 50
	}
}
