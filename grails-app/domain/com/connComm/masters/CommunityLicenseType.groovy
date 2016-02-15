package com.connComm.masters
import grails.rest.*

//@Resource(uri='/communityLicenseType',formats=['json', 'xml'])
class CommunityLicenseType {

	String communityLicenseTypeName

	static hasMany = [communityMasters: CommunityMaster]

	static mapping = {
		id column: "COMMUNITY_LICENSE_TYPE_ID", generator: "increment"
		version false
	}

	static constraints = {
		communityLicenseTypeName maxSize: 50
	}
}
