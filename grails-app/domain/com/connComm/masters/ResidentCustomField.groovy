package com.connComm.masters

class ResidentCustomField {

	String residentCustomFieldName
	Boolean mandatoryIndicator
	Boolean postalAddressFlag
	CommunityMaster communityMaster

	static hasMany = [residentCustomFieldValueses: ResidentCustomFieldValues]
	static belongsTo = [CommunityMaster]

	static mapping = {
		id column: "RESIDENT_CUSTOM_FIELD_ID", generator: "increment"
		version false
	}

	static constraints = {
		residentCustomFieldName maxSize: 50
	}
}
