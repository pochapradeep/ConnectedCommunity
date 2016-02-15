package com.connComm.masters

import grails.test.mixin.*
import spock.lang.*

@TestFor(UserResidentMappingController)
@Mock(UserResidentMapping)
class UserResidentMappingControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.userResidentMappingList
            model.userResidentMappingCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.userResidentMapping!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def userResidentMapping = new UserResidentMapping()
            userResidentMapping.validate()
            controller.save(userResidentMapping)

        then:"The create view is rendered again with the correct model"
            model.userResidentMapping!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            userResidentMapping = new UserResidentMapping(params)

            controller.save(userResidentMapping)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/userResidentMapping/show/1'
            controller.flash.message != null
            UserResidentMapping.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def userResidentMapping = new UserResidentMapping(params)
            controller.show(userResidentMapping)

        then:"A model is populated containing the domain instance"
            model.userResidentMapping == userResidentMapping
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def userResidentMapping = new UserResidentMapping(params)
            controller.edit(userResidentMapping)

        then:"A model is populated containing the domain instance"
            model.userResidentMapping == userResidentMapping
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/userResidentMapping/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def userResidentMapping = new UserResidentMapping()
            userResidentMapping.validate()
            controller.update(userResidentMapping)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.userResidentMapping == userResidentMapping

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            userResidentMapping = new UserResidentMapping(params).save(flush: true)
            controller.update(userResidentMapping)

        then:"A redirect is issued to the show action"
            userResidentMapping != null
            response.redirectedUrl == "/userResidentMapping/show/$userResidentMapping.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/userResidentMapping/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def userResidentMapping = new UserResidentMapping(params).save(flush: true)

        then:"It exists"
            UserResidentMapping.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(userResidentMapping)

        then:"The instance is deleted"
            UserResidentMapping.count() == 0
            response.redirectedUrl == '/userResidentMapping/index'
            flash.message != null
    }
}
