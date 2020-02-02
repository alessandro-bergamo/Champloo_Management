$(document).ready(function()
{

	$.validator.addMethod("soloLettere", function(value, element) { 
		return this.optional(element) || /^[a-zA-Z ]+$/i.test(value); 
		}, "In questo campo non sono consentiti i numeri!");
	$.validator.addMethod("soloLettereNumeri", function(value, element) { 
		return this.optional(element) || /(?=[a-zA-Z0-9-])^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$/i.test(value); 
		}, "L'indirizzo puo' contenere solo lettere e numeri!");

		
	$("#userInfo").validate(
	{
        rules:{
		'firstname':{
			required: true,
			minlength: 3,
			soloLettere: true
		},
		'lastname':{
			required: true,
			minlength: 3,
			soloLettere: true
		},
		},
        messages:{
		'firstname':{
			required: "Il campo nome è obbligatorio!",
			minlength: "Il nome deve essere di almeno 3 lettere!"
		},
		'lastname':{
			required: "Il campo cognome è obbligatorio!",
			minlength: "Il cognome deve essere di almeno 3 lettere!"
		}
		}
	});

		});