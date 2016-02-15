package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentMasterController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    ResidentMasterController(){
        super(ResidentMaster)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidentMaster.list(params), model:[residentMasterCount: ResidentMaster.count()]
    }

    def show(ResidentMaster residentMaster) {
        respond residentMaster
    }

    def create() {
        respond new ResidentMaster(params)
    }

    @Transactional
    def save(ResidentMaster residentMaster) {
        if (residentMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentMaster.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentMaster.errors, view:'create'
            return
        }

        residentMaster.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residentMaster.label', default: 'ResidentMaster'), residentMaster.id])
                redirect residentMaster
            }
            '*' { respond residentMaster, [status: CREATED] }
        }
    }

    def edit(ResidentMaster residentMaster) {
        respond residentMaster
    }

    @Transactional
    def update(ResidentMaster residentMaster) {
        if (residentMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentMaster.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentMaster.errors, view:'edit'
            return
        }

        residentMaster.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residentMaster.label', default: 'ResidentMaster'), residentMaster.id])
                redirect residentMaster
            }
            '*'{ respond residentMaster, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidentMaster residentMaster) {

        if (residentMaster == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residentMaster.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residentMaster.label', default: 'ResidentMaster'), residentMaster.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residentMaster.label', default: 'ResidentMaster'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
