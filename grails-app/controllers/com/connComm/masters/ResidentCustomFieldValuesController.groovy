package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentCustomFieldValuesController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    ResidentCustomFieldValuesController(){
        super(ResidentCustomFieldValues)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidentCustomFieldValues.list(params), model:[residentCustomFieldValuesCount: ResidentCustomFieldValues.count()]
    }

    def show(ResidentCustomFieldValues residentCustomFieldValues) {
        respond residentCustomFieldValues
    }

    def create() {
        respond new ResidentCustomFieldValues(params)
    }

    @Transactional
    def save(ResidentCustomFieldValues residentCustomFieldValues) {
        if (residentCustomFieldValues == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentCustomFieldValues.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentCustomFieldValues.errors, view:'create'
            return
        }

        residentCustomFieldValues.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residentCustomFieldValues.label', default: 'ResidentCustomFieldValues'), residentCustomFieldValues.id])
                redirect residentCustomFieldValues
            }
            '*' { respond residentCustomFieldValues, [status: CREATED] }
        }
    }

    def edit(ResidentCustomFieldValues residentCustomFieldValues) {
        respond residentCustomFieldValues
    }

    @Transactional
    def update(ResidentCustomFieldValues residentCustomFieldValues) {
        if (residentCustomFieldValues == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentCustomFieldValues.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentCustomFieldValues.errors, view:'edit'
            return
        }

        residentCustomFieldValues.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residentCustomFieldValues.label', default: 'ResidentCustomFieldValues'), residentCustomFieldValues.id])
                redirect residentCustomFieldValues
            }
            '*'{ respond residentCustomFieldValues, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidentCustomFieldValues residentCustomFieldValues) {

        if (residentCustomFieldValues == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residentCustomFieldValues.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residentCustomFieldValues.label', default: 'ResidentCustomFieldValues'), residentCustomFieldValues.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residentCustomFieldValues.label', default: 'ResidentCustomFieldValues'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
