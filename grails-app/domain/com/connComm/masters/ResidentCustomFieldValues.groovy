package com.connComm.masters

class ResidentCustomFieldValues {

	String residentCustomFieldValue
	ResidentCustomField residentCustomField
	ResidentMaster residentMaster

	static belongsTo = [ResidentCustomField, ResidentMaster]

	static mapping = {
		id column: "RESIDENT_CUSTOM_FIELD_VALUES_ID", generator: "increment"
		version false
	}

	static constraints = {
		residentCustomFieldValue nullable: true, maxSize: 50
	}
}
