package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentLocationController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    ResidentLocationController(){
        super(ResidentLocation)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidentLocation.list(params), model:[residentLocationCount: ResidentLocation.count()]
    }

    def show(ResidentLocation residentLocation) {
        respond residentLocation
    }

    def create() {
        respond new ResidentLocation(params)
    }

    @Transactional
    def save(ResidentLocation residentLocation) {
        if (residentLocation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentLocation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentLocation.errors, view:'create'
            return
        }

        residentLocation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residentLocation.label', default: 'ResidentLocation'), residentLocation.id])
                redirect residentLocation
            }
            '*' { respond residentLocation, [status: CREATED] }
        }
    }

    def edit(ResidentLocation residentLocation) {
        respond residentLocation
    }

    @Transactional
    def update(ResidentLocation residentLocation) {
        if (residentLocation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentLocation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentLocation.errors, view:'edit'
            return
        }

        residentLocation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residentLocation.label', default: 'ResidentLocation'), residentLocation.id])
                redirect residentLocation
            }
            '*'{ respond residentLocation, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidentLocation residentLocation) {

        if (residentLocation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residentLocation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residentLocation.label', default: 'ResidentLocation'), residentLocation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residentLocation.label', default: 'ResidentLocation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
