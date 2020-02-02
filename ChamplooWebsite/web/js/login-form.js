$(document).ready(function()
{

		
	$("#login-form").validate(
	{
        rules:{
		'email':{
			email: true,
			required: true
		},
		'password':{
			required: true
		}
		},
        messages:{
		'email':{
			email: "Inserisci un indirizzo email valido!",
			required: "Questo campo non puo' essere vuoto!"
		},
		'password':{
			required: "Questo campo non puo' essere vuoto!"
		}
        }
        
	});
});