



$(document).ready(function()  
{  
    $("#aggiuntaProdotto").validate(  
    {  
        rules:  
        {
        'type_product':{
        	required: true
        },
        'model_product':{  
            required: true,  
            minlength: 3  
            },   
        'brand_product':{
        	required: true
        },
        'name_product':{
        	required: true,
        	minlength: 3
        },
        'description_product':{
        	required: true,
        	minlength: 3
        },
        'size_product':{
        	required: true
        },
        'color_product':{
        	required: true,
        	minlength: 3
        },
        'price_product':{
        	required: true,
        	number: true
        },
        'discount_percent_product':{
        	required: true,
        	digits: true,
        	rangelength: [0, 99]
        },
        'discounted_price_product':{
        	required: true,
        	number: true
        },
        'qnt_stock_product':{
        	required: true,
        	digits: true,
        	rangelength: [0, 99]
        },
        'status_product':{
        	required: true
        },  
        'img_path_folder_product':{
        	required: true
        }
      },
        messages:  
        {  
        'type_product':{
        	required: "Devi selezionare il tipo di prodotto interessato!"
        },	
        'model_product':{  
            required: "Il campo modello &egrave; obbligatorio!",  
            minlength: "Il nome del modello deve essere di almeno 3 lettere!"  
            },
        'brand_product':{
        	required: "Devi selezionare il brand del prodotto interessato!"
        },
        'name_product':{
        	required: "Il campo nome &egrave; obbligatorio!",
            minlength: "Il nome del prodotto deve essere di almeno 3 lettere!"  

        },
        'description_product':{
        	required: "Il campo descrizione &egrave; obbligatorio!",
        	minlength: "La descrizione del prodotto deve essere di almeno 3 lettere"
        },
        'size_product':{
        	required: "Devi selezionare la taglia del prodotto interessato!"
        },
        'color_product':{
        	required: "Il campo colore &egrave; obbligatorio!",
        	minlength: "Il colore del prodotto deve essere di almeno 3 lettere!"
        },
        'price_product':{
        	required: "Il campo prezzo &egrave; obbligatorio!",
        	number: "Questo campo puo' contenere solo numeri decimali!"
        },
        'discount_percent_product':{
        	required: "Il campo % prodotto &egrave; obbligatorio!",
        	digits: "Questo campo puo' contenere solo numeri interi!",
        	rangelength: "Questo campo puo' contenere numeri da 0 a 99!"
        },
        'discounted_price_product':{
        	required: "Il campo prezzo prodotto in sconto &egrave; obbligatorio!",
        	number: "Questo campo puo' contenere solo numeri decimali!"
        },
        'qnt_stock_product':{
        	required: "Il campo quantita' &egrave; obbligatorio!",
        	digits: "Questo campo puo' contenere solo numeri interi!",
        	rangelength: "Questo campo puo' contenere numeri da 0 a 99!"
        },
        'status_product':{
        	required: "Devi selezionare la tipologia del prodotto interessato!"
        },  
        'img_path_folder_product':{
        	required: "Il campo path folder dell'immagine &egrave; obbligatorio!"
        }
        }
    });   
});  