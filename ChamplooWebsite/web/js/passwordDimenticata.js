$(document).ready(function()
{
	$.validator.addMethod("username_regex", function(value, element) { 
		return this.optional(element) || /(?=[a-zA-Z0-9-])^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$/i.test(value); 
		}, "L'username puo' contenere solo lettere e numeri!");
		
	$("#passwordDimenticata").validate(
	{
        rules:{
		'username':{
			username_regex: true,
			required: true,
			minlength: 8
		},
		'email1':{
			required: true,
			email: true
		},
		'email2':{
			equalTo: '#email1'
		}
		},
        messages:{
		'username':{
			required: "Questo campo non puo' essere vuoto!",
			minlength: "L'username deve essere di almeno 3 caratteri!"
		},
		'email1':{
			required: "Questo campo non puo' essere vuoto!",
			email: "Devi inserire un formato email corretto"
		},
		'email2':{
			equalTo: "Le due email non combaciano"
		}
        }
        
	});
});