package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VehicleTypeController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    VehicleTypeController(){
        super(VehicleType)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond VehicleType.list(params), model:[vehicleTypeCount: VehicleType.count()]
    }

    def show(VehicleType vehicleType) {
        respond vehicleType
    }

    def create() {
        respond new VehicleType(params)
    }

    @Transactional
    def save(VehicleType vehicleType) {
        if (vehicleType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vehicleType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vehicleType.errors, view:'create'
            return
        }

        vehicleType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vehicleType.label', default: 'VehicleType'), vehicleType.id])
                redirect vehicleType
            }
            '*' { respond vehicleType, [status: CREATED] }
        }
    }

    def edit(VehicleType vehicleType) {
        respond vehicleType
    }

    @Transactional
    def update(VehicleType vehicleType) {
        if (vehicleType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vehicleType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vehicleType.errors, view:'edit'
            return
        }

        vehicleType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vehicleType.label', default: 'VehicleType'), vehicleType.id])
                redirect vehicleType
            }
            '*'{ respond vehicleType, [status: OK] }
        }
    }

    @Transactional
    def delete(VehicleType vehicleType) {

        if (vehicleType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        vehicleType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vehicleType.label', default: 'VehicleType'), vehicleType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehicleType.label', default: 'VehicleType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
