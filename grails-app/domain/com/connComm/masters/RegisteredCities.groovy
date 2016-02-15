package com.connComm.masters

class RegisteredCities {

	String registeredCityName
	String state

	static mapping = {
		id column: "REGISTERED_CITY_ID", generator: "increment"
		version false
	}

	static constraints = {
		registeredCityName maxSize: 50
		state maxSize: 50
	}
}
