package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RecurringTripTypeController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    RecurringTripTypeController(){
        super(RecurringTripType)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RecurringTripType.list(params), model:[recurringTripTypeCount: RecurringTripType.count()]
    }

    def show(RecurringTripType recurringTripType) {
        respond recurringTripType
    }

    def create() {
        respond new RecurringTripType(params)
    }

    @Transactional
    def save(RecurringTripType recurringTripType) {
        if (recurringTripType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (recurringTripType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond recurringTripType.errors, view:'create'
            return
        }

        recurringTripType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'recurringTripType.label', default: 'RecurringTripType'), recurringTripType.id])
                redirect recurringTripType
            }
            '*' { respond recurringTripType, [status: CREATED] }
        }
    }

    def edit(RecurringTripType recurringTripType) {
        respond recurringTripType
    }

    @Transactional
    def update(RecurringTripType recurringTripType) {
        if (recurringTripType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (recurringTripType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond recurringTripType.errors, view:'edit'
            return
        }

        recurringTripType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'recurringTripType.label', default: 'RecurringTripType'), recurringTripType.id])
                redirect recurringTripType
            }
            '*'{ respond recurringTripType, [status: OK] }
        }
    }

    @Transactional
    def delete(RecurringTripType recurringTripType) {

        if (recurringTripType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        recurringTripType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'recurringTripType.label', default: 'RecurringTripType'), recurringTripType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurringTripType.label', default: 'RecurringTripType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
