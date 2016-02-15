package com.connComm.masters

class SellingStatus {

	String sellingStatusName

	static hasMany = [sellBuyMasters: SellBuyMaster]

	static mapping = {
		id column: "SELLING_STATUS_ID", generator: "increment"
		version false
	}

	static constraints = {
		sellingStatusName maxSize: 50
	}
}
