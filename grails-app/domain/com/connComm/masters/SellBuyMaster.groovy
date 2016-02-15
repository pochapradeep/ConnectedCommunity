package com.connComm.masters

class SellBuyMaster {

	String sellText
	String productDescription
	String productImageUrl
	String productImageName
	Double sellingPrice
	Boolean negotiable
	Integer quantity
	Double buyingPrice
	String productName
	ResidentMaster residentMasterByBuyerId
	ResidentMaster residentMasterBySellerId
	CommunityMaster communityMaster
	ProductCategory productCategory
	SellingStatus sellingStatus

	static belongsTo = [CommunityMaster, ProductCategory, ResidentMaster, SellingStatus]

	static mapping = {
		id column: "SELL_BUY_MASTER_ID", generator: "increment"
		version false
	}

	static constraints = {
		sellText maxSize: 100
		productDescription nullable: true, maxSize: 100
		productImageUrl nullable: true
		productImageName nullable: true
		negotiable nullable: true
		productName nullable: true, maxSize: 50
	}
}
