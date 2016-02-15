package com.connComm.masters

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VehicleDetailsController extends RestfulController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    static responseFormats = ['html', 'json']


    VehicleDetailsController(){
        super(VehicleDetails)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond VehicleDetails.list(params), model:[vehicleDetailsCount: VehicleDetails.count()]
    }

    def show(VehicleDetails vehicleDetails) {
        respond vehicleDetails
    }

    def create() {
        respond new VehicleDetails(params)
    }

    @Transactional
    def save(VehicleDetails vehicleDetails) {
        if (vehicleDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vehicleDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vehicleDetails.errors, view:'create'
            return
        }

        vehicleDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vehicleDetails.label', default: 'VehicleDetails'), vehicleDetails.id])
                redirect vehicleDetails
            }
            '*' { respond vehicleDetails, [status: CREATED] }
        }
    }

    def edit(VehicleDetails vehicleDetails) {
        respond vehicleDetails
    }

    @Transactional
    def update(VehicleDetails vehicleDetails) {
        if (vehicleDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vehicleDetails.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vehicleDetails.errors, view:'edit'
            return
        }

        vehicleDetails.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vehicleDetails.label', default: 'VehicleDetails'), vehicleDetails.id])
                redirect vehicleDetails
            }
            '*'{ respond vehicleDetails, [status: OK] }
        }
    }

    @Transactional
    def delete(VehicleDetails vehicleDetails) {

        if (vehicleDetails == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        vehicleDetails.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vehicleDetails.label', default: 'VehicleDetails'), vehicleDetails.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehicleDetails.label', default: 'VehicleDetails'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
