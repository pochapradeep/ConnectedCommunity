package com.connComm.masters

class ProductCategory {

	String productCategoryName

	static hasMany = [sellBuyMasters: SellBuyMaster]

	static mapping = {
		id column: "PRODUCT_CATEGORY_ID", generator: "increment"
		version false
	}

	static constraints = {
		productCategoryName maxSize: 50
	}
}
