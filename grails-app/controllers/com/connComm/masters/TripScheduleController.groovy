package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TripScheduleController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    TripScheduleController(){
        super(TripSchedule)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TripSchedule.list(params), model:[tripScheduleCount: TripSchedule.count()]
    }

    def show(TripSchedule tripSchedule) {
        respond tripSchedule
    }

    def create() {
        respond new TripSchedule(params)
    }

    @Transactional
    def save(TripSchedule tripSchedule) {
        if (tripSchedule == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tripSchedule.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tripSchedule.errors, view:'create'
            return
        }

        tripSchedule.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tripSchedule.label', default: 'TripSchedule'), tripSchedule.id])
                redirect tripSchedule
            }
            '*' { respond tripSchedule, [status: CREATED] }
        }
    }

    def edit(TripSchedule tripSchedule) {
        respond tripSchedule
    }

    @Transactional
    def update(TripSchedule tripSchedule) {
        if (tripSchedule == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tripSchedule.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tripSchedule.errors, view:'edit'
            return
        }

        tripSchedule.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tripSchedule.label', default: 'TripSchedule'), tripSchedule.id])
                redirect tripSchedule
            }
            '*'{ respond tripSchedule, [status: OK] }
        }
    }

    @Transactional
    def delete(TripSchedule tripSchedule) {

        if (tripSchedule == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tripSchedule.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tripSchedule.label', default: 'TripSchedule'), tripSchedule.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tripSchedule.label', default: 'TripSchedule'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
