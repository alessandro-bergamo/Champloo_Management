$(document).ready(function()
{
	$.validator.addMethod("username_regex", function(value, element) { 
		return this.optional(element) || /(?=[a-zA-Z0-9-])^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$/i.test(value); 
		}, "L'username puo' contenere solo lettere e numeri!");
	
	$.validator.addMethod("nomecognome", function(value, element) { 
		return this.optional(element) || /^[a-zA-Z ]+$/i.test(value); 
		}, "In questo campo non sono consentiti i numeri!");
	$.validator.addMethod("password_regex", function(value, element) { 
		return this.optional(element) || /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/i.test(value); 
		}, "La password deve contenere almeno una lettera maiuscola e un numero!");

		
	$("#registrationForm").validate(
	{
        rules:{
		'username':{
			required: true,
			username_regex: true,
			rangelength: [0, 15],
			minlength: 5
			},
		'email':{
			required: true,
			minlength: 5,
			email: true,
			rangelength: [0, 40]
			},
		'firstname':{
			required: true,
			minlength: 3,
			rangelength: [0, 20],
			nomecognome: true
		},
		'lastname':{
			required: true,
			minlength: 3,
			rangelength: [0, 20],
			nomecognome: true
		},
		'password':{
			required: true,
			minlength: 8,
			rangelength: [0, 20],
			password_regex: true
			},
		'password2':{
			equalTo: '#password'
			}
		},
        messages:{
		'username':{
			required: "Il campo username è obbligatorio!",
			minlength: "Scegli un username di almeno 5 lettere!",
			rangelength: "L'username non puo' essere maggiore di 15 lettere!"
			},
		'email':{
			required: "Il campo email è obbligatorio!",
			email: "Inserisci un indirizzo email valido!",
			minlength: "L'email deve essere di almeno 5 caratteri!",
			rangelength: "L'username non puo' essere maggiore di 40 lettere!"
			},
		'firstname':{
			required: "Il campo nome è obbligatorio!",
			minlength: "Il nome deve essere di almeno 3 lettere!",
			rangelength: "Il nome non puo' essere maggiore di 20 lettere!"
		},
		'lastname':{
			required: "Il campo cognome è obbligatorio!",
			minlength: "Il cognome deve essere di almeno 3 lettere!",
			rangelength: "Il cognome non puo' essere maggiore di 20 lettere!"
		},
		'password':{
			required: "Il campo password è obbligatorio!",
			minlength: "Inserisci una password di almeno 8 caratteri!",
			rangelength: "La password non puo' superare i 20 caratteri!"
			},
		'password2':{
			equalTo: "Le due password non coincidono!"
			}
		}
	});
});
