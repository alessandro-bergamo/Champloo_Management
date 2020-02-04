 function logout()
            {
                var value = ("logout");
                Swal.fire({
                    title: 'Sei sicuro di voler effettuare il Logout?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Logout',
                    cancelButtonText: 'Annulla',
                    width: '700px'
                }).then((result) => {
                    if (result.value) {
                        $.ajax({
                            type: "POST",
                            url: "UserControl",
                            data: {"operation" : value},
                            success: function(results){
                                Swal.fire({
                                    title: 'Logout Effettuato',
                                    timer: 1700,
                                    icon: 'success',
                                    showCancelButton: false,
                                    showConfirmButton: false,
                                    width: '400px',
                                })
                                setTimeout(function(){location.href="index.jsp"} , 1350);
                            }
                        })
                    }
                })
            };