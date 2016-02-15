package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentStatusController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    ResidentStatusController(){
        super(ResidentStatus)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidentStatus.list(params), model:[residentStatusCount: ResidentStatus.count()]
    }

    def show(ResidentStatus residentStatus) {
        respond residentStatus
    }

    def create() {
        respond new ResidentStatus(params)
    }

    @Transactional
    def save(ResidentStatus residentStatus) {
        if (residentStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentStatus.errors, view:'create'
            return
        }

        residentStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residentStatus.label', default: 'ResidentStatus'), residentStatus.id])
                redirect residentStatus
            }
            '*' { respond residentStatus, [status: CREATED] }
        }
    }

    def edit(ResidentStatus residentStatus) {
        respond residentStatus
    }

    @Transactional
    def update(ResidentStatus residentStatus) {
        if (residentStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentStatus.errors, view:'edit'
            return
        }

        residentStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residentStatus.label', default: 'ResidentStatus'), residentStatus.id])
                redirect residentStatus
            }
            '*'{ respond residentStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidentStatus residentStatus) {

        if (residentStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residentStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residentStatus.label', default: 'ResidentStatus'), residentStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residentStatus.label', default: 'ResidentStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
