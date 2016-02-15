package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentCustomFieldController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    ResidentCustomFieldController(){
        super(ResidentCustomField)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidentCustomField.list(params), model:[residentCustomFieldCount: ResidentCustomField.count()]
    }

    def show(ResidentCustomField residentCustomField) {
        respond residentCustomField
    }

    def create() {
        respond new ResidentCustomField(params)
    }

    @Transactional
    def save(ResidentCustomField residentCustomField) {
        if (residentCustomField == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentCustomField.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentCustomField.errors, view:'create'
            return
        }

        residentCustomField.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residentCustomField.label', default: 'ResidentCustomField'), residentCustomField.id])
                redirect residentCustomField
            }
            '*' { respond residentCustomField, [status: CREATED] }
        }
    }

    def edit(ResidentCustomField residentCustomField) {
        respond residentCustomField
    }

    @Transactional
    def update(ResidentCustomField residentCustomField) {
        if (residentCustomField == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentCustomField.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentCustomField.errors, view:'edit'
            return
        }

        residentCustomField.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residentCustomField.label', default: 'ResidentCustomField'), residentCustomField.id])
                redirect residentCustomField
            }
            '*'{ respond residentCustomField, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidentCustomField residentCustomField) {

        if (residentCustomField == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residentCustomField.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residentCustomField.label', default: 'ResidentCustomField'), residentCustomField.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residentCustomField.label', default: 'ResidentCustomField'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
