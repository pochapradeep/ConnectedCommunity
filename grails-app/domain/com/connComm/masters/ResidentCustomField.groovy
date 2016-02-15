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
		
		columns {
			communityMaster column:'community_id'
		}
	}

	static constraints = {
		residentCustomFieldName maxSize: 50
	}
}
