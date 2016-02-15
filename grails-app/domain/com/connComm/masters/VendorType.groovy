package com.connComm.masters

class VendorType {

	String vendorTypeName
	Byte defaultType

	static hasMany = [residentMasters: ResidentMaster]

	static mapping = {
		id column: "VENDOR_TYPE_ID", generator: "increment"
		version false
	}

	static constraints = {
		vendorTypeName maxSize: 50
	}
}
