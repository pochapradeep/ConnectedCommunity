package com.connComm.masters

class ResidenceUnit {

	String residenceUnitName
	Integer parentResidenceUnitId
	Boolean uniqueResidenceIdentifier
	CommunityMaster communityMaster
	AddressTemplateGroup addressTemplateGroup

	static hasMany = [residentMasters: ResidentMaster]
	static belongsTo = [AddressTemplateGroup, CommunityMaster]

	static mapping = {
		id column: "RESIDENCE_UNIT_ID", generator: "increment"

		version false
		columns {
			communityMaster column:'COMMUNITY_ID'
		}
	}

	static constraints = {
		residenceUnitName maxSize: 50
		parentResidenceUnitId nullable: true
	}
}
