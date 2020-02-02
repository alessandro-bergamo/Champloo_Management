$(document).ready(function()
{

	$.validator.addMethod("soloLettere", function(value, element) { 
		return this.optional(element) || /^[a-zA-Z ]+$/i.test(value); 
		}, "In questo campo non sono consentiti i numeri!");
	$.validator.addMethod("soloLettereNumeri", function(value, element) { 
		return this.optional(element) || /(?=[a-zA-Z0-9-])^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$/i.test(value); 
		}, "L'indirizzo puo' contenere solo lettere e numeri!");

$("#insertPaymentMethod").validate(
		{
			rules:{
				'bank':{
					required: true
				},
				'number':{
					required: true,
					minlength: 16,
					digits: true
				},
				'cvc':{
					required: true,
					digits: true,
					minlength: 3
				},
				'expiry':{
					required: true
				}
			},
			messages:{
				'bank':{
					required: "Devi selezionare il tipo di carta"
				},
				'number':{
					required: "Devi inserire il numero di carta!",
					minlength: "Il numero di carta deve essere di 16 numeri",
					digits: "Questo campo puo' essere di soli numeri!"
				},
				'cvc':{
					required: "Devi inserire il CVC!",
					digits: "Questo campo puo' essere di soli numeri!",
					minlength: "Il CVC deve essere di 3 numeri!"
				},
				'expiry':{
					required: "Devi inserire la data di scadenza!"
				}
			}
	    });   
});  


