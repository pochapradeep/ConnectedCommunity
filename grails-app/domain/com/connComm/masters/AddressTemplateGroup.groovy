package com.connComm.masters

import grails.rest.Resource

//@Resource(uri='/addressTemplateGroup',formats=['json', 'xml'])
class AddressTemplateGroup {

	Integer addressTemplateOrder
	String addressTypeName
	Boolean commonAddressFlag
	Boolean postalAddressFlag
	AddressTemplate addressTemplate

	static hasMany = [residenceUnits: ResidenceUnit]
	static belongsTo = [AddressTemplate]

	static mapping = {
		id column: "ADDRESS_TEMPLATE_GROUP_ID", generator: "increment"
		version false
	}

	static constraints = {
		addressTypeName maxSize: 50
	}
}
