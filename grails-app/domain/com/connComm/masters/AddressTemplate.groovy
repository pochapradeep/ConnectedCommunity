package com.connComm.masters

import grails.rest.Resource

//@Resource(uri='/addressTemplate',formats=['json', 'xml'])

class AddressTemplate {

	String addressTemplateName

	static hasMany = [addressTemplateGroups: AddressTemplateGroup,
	                  communityMasters: CommunityMaster]

	static mapping = {
		id column: "ADDRESS_TEMPLATE_ID", generator: "increment"
		version false
	}

	static constraints = {
		addressTemplateName maxSize: 50
	}
}
