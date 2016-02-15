package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VendorTypeController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    VendorTypeController(){
        super(VendorType)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond VendorType.list(params), model:[vendorTypeCount: VendorType.count()]
    }

    def show(VendorType vendorType) {
        respond vendorType
    }

    def create() {
        respond new VendorType(params)
    }

    @Transactional
    def save(VendorType vendorType) {
        if (vendorType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vendorType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vendorType.errors, view:'create'
            return
        }

        vendorType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vendorType.label', default: 'VendorType'), vendorType.id])
                redirect vendorType
            }
            '*' { respond vendorType, [status: CREATED] }
        }
    }

    def edit(VendorType vendorType) {
        respond vendorType
    }

    @Transactional
    def update(VendorType vendorType) {
        if (vendorType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vendorType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vendorType.errors, view:'edit'
            return
        }

        vendorType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vendorType.label', default: 'VendorType'), vendorType.id])
                redirect vendorType
            }
            '*'{ respond vendorType, [status: OK] }
        }
    }

    @Transactional
    def delete(VendorType vendorType) {

        if (vendorType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        vendorType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vendorType.label', default: 'VendorType'), vendorType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vendorType.label', default: 'VendorType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
