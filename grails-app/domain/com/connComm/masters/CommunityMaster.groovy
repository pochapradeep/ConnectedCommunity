package com.connComm.masters

class CommunityMaster {

	String communityName
	String communityNickName
	String address
	String city
	String state
	String latitude
	String longitude
	String country
	String zipcode
	String communityUrl
	String communityImageFileName
	AddressTemplate addressTemplate
	CommunityLicenseType communityLicenseType
	CommunityType communityType
	CommunityStatus communityStatus

	static hasMany = [communityAdminGroups: CommunityAdminGroup,
	                  communityPendingRegistrations: CommunityPendingRegistration,
	                  residenceUnits: ResidenceUnit,
	                  residentCustomFields: ResidentCustomField,
	                  residentMasters: ResidentMaster,
	                  roles: Role,
	                  sellBuyMasters: SellBuyMaster]
	static belongsTo = [AddressTemplate, CommunityLicenseType, CommunityStatus, CommunityType]

	static mapping = {
		id column: "COMMUNITY_ID", generator: "increment"
		version false
	}

	static constraints = {
		communityName maxSize: 50
		communityNickName maxSize: 50
		address nullable: true, maxSize: 100
		city nullable: true, maxSize: 50
		state nullable: true, maxSize: 50
		latitude nullable: true, maxSize: 50
		longitude nullable: true, maxSize: 50
		country nullable: true, maxSize: 50
		zipcode nullable: true, maxSize: 20
		communityUrl nullable: true
		communityImageFileName nullable: true
	}
}
