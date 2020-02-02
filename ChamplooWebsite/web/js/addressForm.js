$(document).ready(function()
{

	$.validator.addMethod("soloLettere", function(value, element) { 
		return this.optional(element) || /^[a-zA-Z ]+$/i.test(value); 
		}, "In questo campo non sono consentiti i numeri!");
	$.validator.addMethod("soloLettereNumeri", function(value, element) { 
		return this.optional(element) || /(?=[a-zA-Z0-9-])^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$/i.test(value); 
		}, "L'indirizzo puo' contenere solo lettere e numeri!");

$("#addressModify").validate(
	{
		rules:{
		'address':{
			required: true,
			minlength: 3,
			soloLettereNumeri: true
		},
		'city':{
			required: true,
			minlength: 3,
			soloLettere: true
		},
		'province':{
			required: true,
			minlength: 2,
			soloLettere: true
		},
		'cap':{
			required: true,
			minlength: 5,
			digits: true
		},
		'civic_number':{
			required: true,
			digits: true
		},
		},
		messages:{
		'address':{
			required: "Il campo indirizzo è obbligatorio",
			minlength: "L'indirizzo deve essere di almeno 3 caratteri!"
		},
		'city':{
			required: "Il campo citta' è obbligatorio",
			minlength: "La citta' deve essere di almeno 3 caratteri!"
		},
		'province':{
			required: "Il campo provincia è obbligatorio",
			minlength: "La provincia deve essere composta da 2 caratteri!"
		},
		'cap':{
			required: "Il campo CAP è obbligatorio",
			minlength: "Il CAP deve essere composto da 5 caratteri!",
			digits: "Questo campo puo' contenere solo numeri!"
		},
		'civic_number':{
			required: "Il campo del numero civico è obbligatorio",
			digits: "Questo campo puo' contenere solo numeri!"
		}
		}
		});
		});