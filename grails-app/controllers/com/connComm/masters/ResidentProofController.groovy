package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentProofController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    ResidentProofController(){
        super(ResidentProof)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidentProof.list(params), model:[residentProofCount: ResidentProof.count()]
    }

    def show(ResidentProof residentProof) {
        respond residentProof
    }

    def create() {
        respond new ResidentProof(params)
    }

    @Transactional
    def save(ResidentProof residentProof) {
        if (residentProof == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentProof.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentProof.errors, view:'create'
            return
        }

        residentProof.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residentProof.label', default: 'ResidentProof'), residentProof.id])
                redirect residentProof
            }
            '*' { respond residentProof, [status: CREATED] }
        }
    }

    def edit(ResidentProof residentProof) {
        respond residentProof
    }

    @Transactional
    def update(ResidentProof residentProof) {
        if (residentProof == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residentProof.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residentProof.errors, view:'edit'
            return
        }

        residentProof.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residentProof.label', default: 'ResidentProof'), residentProof.id])
                redirect residentProof
            }
            '*'{ respond residentProof, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidentProof residentProof) {

        if (residentProof == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residentProof.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residentProof.label', default: 'ResidentProof'), residentProof.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residentProof.label', default: 'ResidentProof'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
