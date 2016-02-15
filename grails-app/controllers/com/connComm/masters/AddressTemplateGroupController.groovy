package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AddressTemplateGroupController extends RestfulController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    AddressTemplateGroupController(){
        super(AddressTemplateGroup)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AddressTemplateGroup.list(params), model:[addressTemplateGroupCount: AddressTemplateGroup.count()]
    }

    def show(AddressTemplateGroup addressTemplateGroup) {
        respond addressTemplateGroup
    }

    def create() {
        respond new AddressTemplateGroup(params)
    }

    @Transactional
    def save(AddressTemplateGroup addressTemplateGroup) {
        if (addressTemplateGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (addressTemplateGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond addressTemplateGroup.errors, view:'create'
            return
        }

        addressTemplateGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'addressTemplateGroup.label', default: 'AddressTemplateGroup'), addressTemplateGroup.id])
                redirect addressTemplateGroup
            }
            '*' { respond addressTemplateGroup, [status: CREATED] }
        }
    }

    def edit(AddressTemplateGroup addressTemplateGroup) {
        respond addressTemplateGroup
    }

    @Transactional
    def update(AddressTemplateGroup addressTemplateGroup) {
        if (addressTemplateGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (addressTemplateGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond addressTemplateGroup.errors, view:'edit'
            return
        }

        addressTemplateGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'addressTemplateGroup.label', default: 'AddressTemplateGroup'), addressTemplateGroup.id])
                redirect addressTemplateGroup
            }
            '*'{ respond addressTemplateGroup, [status: OK] }
        }
    }

    @Transactional
    def delete(AddressTemplateGroup addressTemplateGroup) {

        if (addressTemplateGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        addressTemplateGroup.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'addressTemplateGroup.label', default: 'AddressTemplateGroup'), addressTemplateGroup.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'addressTemplateGroup.label', default: 'AddressTemplateGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
