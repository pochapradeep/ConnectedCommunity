package com.connComm.masters

import grails.test.mixin.*
import spock.lang.*

@TestFor(RecurringTripTypeController)
@Mock(RecurringTripType)
class RecurringTripTypeControllerSpec extends Specification {

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
            !model.recurringTripTypeList
            model.recurringTripTypeCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.recurringTripType!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def recurringTripType = new RecurringTripType()
            recurringTripType.validate()
            controller.save(recurringTripType)

        then:"The create view is rendered again with the correct model"
            model.recurringTripType!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            recurringTripType = new RecurringTripType(params)

            controller.save(recurringTripType)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/recurringTripType/show/1'
            controller.flash.message != null
            RecurringTripType.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def recurringTripType = new RecurringTripType(params)
            controller.show(recurringTripType)

        then:"A model is populated containing the domain instance"
            model.recurringTripType == recurringTripType
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def recurringTripType = new RecurringTripType(params)
            controller.edit(recurringTripType)

        then:"A model is populated containing the domain instance"
            model.recurringTripType == recurringTripType
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/recurringTripType/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def recurringTripType = new RecurringTripType()
            recurringTripType.validate()
            controller.update(recurringTripType)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.recurringTripType == recurringTripType

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            recurringTripType = new RecurringTripType(params).save(flush: true)
            controller.update(recurringTripType)

        then:"A redirect is issued to the show action"
            recurringTripType != null
            response.redirectedUrl == "/recurringTripType/show/$recurringTripType.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/recurringTripType/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def recurringTripType = new RecurringTripType(params).save(flush: true)

        then:"It exists"
            RecurringTripType.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(recurringTripType)

        then:"The instance is deleted"
            RecurringTripType.count() == 0
            response.redirectedUrl == '/recurringTripType/index'
            flash.message != null
    }
}
