package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SellingStatusController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    SellingStatusController(){
        super(SellingStatus)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SellingStatus.list(params), model:[sellingStatusCount: SellingStatus.count()]
    }

    def show(SellingStatus sellingStatus) {
        respond sellingStatus
    }

    def create() {
        respond new SellingStatus(params)
    }

    @Transactional
    def save(SellingStatus sellingStatus) {
        if (sellingStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sellingStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sellingStatus.errors, view:'create'
            return
        }

        sellingStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sellingStatus.label', default: 'SellingStatus'), sellingStatus.id])
                redirect sellingStatus
            }
            '*' { respond sellingStatus, [status: CREATED] }
        }
    }

    def edit(SellingStatus sellingStatus) {
        respond sellingStatus
    }

    @Transactional
    def update(SellingStatus sellingStatus) {
        if (sellingStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sellingStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sellingStatus.errors, view:'edit'
            return
        }

        sellingStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sellingStatus.label', default: 'SellingStatus'), sellingStatus.id])
                redirect sellingStatus
            }
            '*'{ respond sellingStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(SellingStatus sellingStatus) {

        if (sellingStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sellingStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sellingStatus.label', default: 'SellingStatus'), sellingStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sellingStatus.label', default: 'SellingStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
