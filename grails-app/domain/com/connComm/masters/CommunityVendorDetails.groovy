package com.connComm.masters

class CommunityVendorDetails {

	Integer communityVendorTypeId
	String vendorName
	String vendorCompany
	String vendorAddress
	String vendorContactNo
	String vendorEmail

	static mapping = {
		id column: "COMMUNITY_VENDOR_DETAILS_ID", generator: "increment"
		version false
	}

	static constraints = {
		vendorName maxSize: 50
		vendorCompany nullable: true, maxSize: 50
		vendorAddress nullable: true
		vendorContactNo nullable: true, maxSize: 20
		vendorEmail nullable: true, maxSize: 50
	}
}
