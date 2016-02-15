package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AddressTemplateController extends RestfulController {

    static responseFormats = ['html', 'json']


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    AddressTemplateController(){
        super(AddressTemplate)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AddressTemplate.list(params), model:[addressTemplateCount: AddressTemplate.count()]
    }

    def show(AddressTemplate addressTemplate) {
        respond addressTemplate
    }

    def create() {
        respond new AddressTemplate(params)
    }

    @Transactional
    def save(AddressTemplate addressTemplate) {
        if (addressTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (addressTemplate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond addressTemplate.errors, view:'create'
            return
        }

        addressTemplate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'addressTemplate.label', default: 'AddressTemplate'), addressTemplate.id])
                redirect addressTemplate
            }
            '*' { respond addressTemplate, [status: CREATED] }
        }
    }

    def edit(AddressTemplate addressTemplate) {
        respond addressTemplate
    }

    @Transactional
    def update(AddressTemplate addressTemplate) {
        if (addressTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (addressTemplate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond addressTemplate.errors, view:'edit'
            return
        }

        addressTemplate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'addressTemplate.label', default: 'AddressTemplate'), addressTemplate.id])
                redirect addressTemplate
            }
            '*'{ respond addressTemplate, [status: OK] }
        }
    }

    @Transactional
    def delete(AddressTemplate addressTemplate) {

        if (addressTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        addressTemplate.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'addressTemplate.label', default: 'AddressTemplate'), addressTemplate.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'addressTemplate.label', default: 'AddressTemplate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
