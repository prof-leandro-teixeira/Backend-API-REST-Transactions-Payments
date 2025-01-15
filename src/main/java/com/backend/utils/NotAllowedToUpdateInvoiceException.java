package com.backend.utils;

//Embora esta Exception seja igual CustomValidationException pode ser útil personalizar NotAllowedToUpdateInvoiceException 
//no futuro para atender a regulamentação ou parâmetro
public class NotAllowedToUpdateInvoiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotAllowedToUpdateInvoiceException(String message) {
        super(message);
    }
}