package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidenceUnitController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    ResidenceUnitController(){
        super(ResidenceUnit)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ResidenceUnit.list(params), model:[residenceUnitCount: ResidenceUnit.count()]
    }

    def show(ResidenceUnit residenceUnit) {
        respond residenceUnit
    }

    def create() {
        respond new ResidenceUnit(params)
    }

    @Transactional
    def save(ResidenceUnit residenceUnit) {
        if (residenceUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residenceUnit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residenceUnit.errors, view:'create'
            return
        }

        residenceUnit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'residenceUnit.label', default: 'ResidenceUnit'), residenceUnit.id])
                redirect residenceUnit
            }
            '*' { respond residenceUnit, [status: CREATED] }
        }
    }

    def edit(ResidenceUnit residenceUnit) {
        respond residenceUnit
    }

    @Transactional
    def update(ResidenceUnit residenceUnit) {
        if (residenceUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (residenceUnit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond residenceUnit.errors, view:'edit'
            return
        }

        residenceUnit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'residenceUnit.label', default: 'ResidenceUnit'), residenceUnit.id])
                redirect residenceUnit
            }
            '*'{ respond residenceUnit, [status: OK] }
        }
    }

    @Transactional
    def delete(ResidenceUnit residenceUnit) {

        if (residenceUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        residenceUnit.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'residenceUnit.label', default: 'ResidenceUnit'), residenceUnit.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'residenceUnit.label', default: 'ResidenceUnit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
