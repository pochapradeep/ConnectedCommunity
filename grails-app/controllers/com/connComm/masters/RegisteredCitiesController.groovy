package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RegisteredCitiesController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['html', 'json']


    RegisteredCitiesController(){
        super(RegisteredCities)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RegisteredCities.list(params), model:[registeredCitiesCount: RegisteredCities.count()]
    }

    def show(RegisteredCities registeredCities) {
        respond registeredCities
    }

    def create() {
        respond new RegisteredCities(params)
    }

    @Transactional
    def save(RegisteredCities registeredCities) {
        if (registeredCities == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (registeredCities.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond registeredCities.errors, view:'create'
            return
        }

        registeredCities.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'registeredCities.label', default: 'RegisteredCities'), registeredCities.id])
                redirect registeredCities
            }
            '*' { respond registeredCities, [status: CREATED] }
        }
    }

    def edit(RegisteredCities registeredCities) {
        respond registeredCities
    }

    @Transactional
    def update(RegisteredCities registeredCities) {
        if (registeredCities == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (registeredCities.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond registeredCities.errors, view:'edit'
            return
        }

        registeredCities.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'registeredCities.label', default: 'RegisteredCities'), registeredCities.id])
                redirect registeredCities
            }
            '*'{ respond registeredCities, [status: OK] }
        }
    }

    @Transactional
    def delete(RegisteredCities registeredCities) {

        if (registeredCities == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        registeredCities.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'registeredCities.label', default: 'RegisteredCities'), registeredCities.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'registeredCities.label', default: 'RegisteredCities'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
