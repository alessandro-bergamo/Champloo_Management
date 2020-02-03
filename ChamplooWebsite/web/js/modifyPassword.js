$(document).ready(function()
{
	$.validator.addMethod("regex", function(value, element) { 
		return this.optional(element) || /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/i.test(value); 
		}, "La password deve contenere almeno una lettera maiuscola, un numero e un carattere speciale!");
		
	$("#modifyPassword").validate(
	{
        rules:{
		'password':{
			required: true,
			minlength: 8
		},
		'nuovapass1':{
			required: true,
			minlength: 8
		},
		'nuovapass2':{
			equalTo: '#nuovapass1'
		}
		},
        messages:{
		'password':{
			required: "Questo campo non puo' essere vuoto!",
			minlength: "La password deve essere di almeno 8 caratteri"
		},
		'nuovapass1':{
			required: "Questo campo non puo' essere vuoto!",
			minlength: "La password deve essere di almeno 8 caratteri"
		},
		'nuovapass2':{
			equalTo: "Le due password non combaciano"
		}
        }
        
	});
});