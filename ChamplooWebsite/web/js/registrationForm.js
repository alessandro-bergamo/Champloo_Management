$(document).ready(function()
{
	$.validator.addMethod("username_regex", function(value, element) { 
		return this.optional(element) || /(?=[a-zA-Z0-9-])^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$/i.test(value); 
		}, "L'username puo' contenere solo lettere e numeri!");
	
	$.validator.addMethod("nomecognome", function(value, element) { 
		return this.optional(element) || /^[a-zA-Z ]+$/i.test(value); 
		}, "In questo campo non sono consentiti i numeri!");

		
	$("#registrationForm").validate(
	{
        rules:{
		'username':{
			required: true,
			username_regex: true,
			minlength: 5
			},
		'email':{
			required: true,
			email: true
			},
		'firstname':{
			required: true,
			minlength: 3,
			nomecognome: true
		},
		'lastname':{
			required: true,
			minlength: 3,
			nomecognome: true
		},
		'password':{
			required: true,
			minlength: 8
			},
		'password2':{
			equalTo: '#password'
			}
		},
        messages:{
		'username':{
			required: "Il campo username è obbligatorio!",
			minlength: "Scegli un username di almeno 5 lettere!"
			},
		'email':{
			required: "Il campo email è obbligatorio!",
			email: "Inserisci un indirizzo email valido!"
			},
		'firstname':{
			required: "Il campo nome è obbligatorio!",
			minlength: "Il nome deve essere di almeno 3 lettere!"
		},
		'lastname':{
			required: "Il campo cognome è obbligatorio!",
			minlength: "Il cognome deve essere di almeno 3 lettere!"
		},
		'password':{
			required: "Il campo password è obbligatorio!",
			minlength: "Inserisci una password di almeno 8 caratteri!"
			},
		'password2':{
			equalTo: "Le due password non coincidono!"
			}
		}
	});
});
