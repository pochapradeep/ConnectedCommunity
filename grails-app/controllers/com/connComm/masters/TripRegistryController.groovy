package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TripRegistryController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    TripRegistryController(){
        super(TripRegistry)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TripRegistry.list(params), model:[tripRegistryCount: TripRegistry.count()]
    }

    def show(TripRegistry tripRegistry) {
        respond tripRegistry
    }

    def create() {
        respond new TripRegistry(params)
    }

    @Transactional
    def save(TripRegistry tripRegistry) {
        if (tripRegistry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tripRegistry.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tripRegistry.errors, view:'create'
            return
        }

        tripRegistry.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tripRegistry.label', default: 'TripRegistry'), tripRegistry.id])
                redirect tripRegistry
            }
            '*' { respond tripRegistry, [status: CREATED] }
        }
    }

    def edit(TripRegistry tripRegistry) {
        respond tripRegistry
    }

    @Transactional
    def update(TripRegistry tripRegistry) {
        if (tripRegistry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tripRegistry.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tripRegistry.errors, view:'edit'
            return
        }

        tripRegistry.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tripRegistry.label', default: 'TripRegistry'), tripRegistry.id])
                redirect tripRegistry
            }
            '*'{ respond tripRegistry, [status: OK] }
        }
    }

    @Transactional
    def delete(TripRegistry tripRegistry) {

        if (tripRegistry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tripRegistry.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tripRegistry.label', default: 'TripRegistry'), tripRegistry.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tripRegistry.label', default: 'TripRegistry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
