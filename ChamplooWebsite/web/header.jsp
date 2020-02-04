<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
		import="com.champloo.util.*"
%>
    <!-- CDN SWEETALERT2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<%
	UserBean utenteLoggato;
	CartBean cart;
	ActiveCart activeCart;
	synchronized(session) 
        {
            utenteLoggato = (UserBean) request.getSession().getAttribute("utenteLoggato");
            cart = (CartBean) request.getSession().getAttribute("cart");
            activeCart = (ActiveCart) request.getSession().getAttribute("activeCart");
        }
	
		System.out.println("header.jsp -> activeCart: "+activeCart);
%>
        <header class="header">
            <div class="header_overlay"></div>
            <div class="header_content d-flex flex-row align-items-center justify-content-start">
                <div class="logo">
                    <a href="index.jsp">
                        <div class="logo_cpl d-flex flex-row align-items-center justify-content-start">
                            <div><img src="images/logo_cpl.png" alt=""></div>
                            <div></div>
                        </div>
                    </a>
                </div>
                <div class="hamburger"><i class="fa fa-bars" aria-hidden="true"></i></div>
                <div class="header_right d-flex flex-row align-items-center justify-content-start ml-auto">
                    <!-- Search -->
                    <div class="header_search">
                        <form action="#" id="header_search_form">
                            <input type="text" class="search_input" placeholder="Cerca" required="required">
                            <button class="header_search_button"><img src="images/search.png" alt=""></button>
                        </form>
                    </div>
                   <%
                        if(utenteLoggato != null && cart != null)
                        {
                            if(utenteLoggato.getType()==2)
                            {
                   %>
                    <!-- User loggato -->
                    <div class="user"><a href="UserControl?operation=userManager"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <div class="user"><a herf=""><div><img src="images/logout.svg" style="cursor: pointer; !important;" onclick="logout()" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <%      } else if(utenteLoggato.getType()==3) { %>
                    <div class="user"><a href="Order?operation=ordersManager"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <div class="user"><a herf=""><div><img src="images/logout.svg" style="cursor: pointer; !important;" onclick="logout()" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <%      } else if(utenteLoggato.getType()==4) { %>
                    <div class="user"><a href="Product?operation=productManager"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <div class="user"><a herf=""><div><img src="images/logout.svg" style="cursor: pointer; !important;" onclick="logout()" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <%      } else { %>
                    <div class="user"><a href="user_area.jsp"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <div class="user"><a herf=""><div><img src="images/logout.svg" style="cursor: pointer; !important;" onclick="logout()"alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <!-- Cart -->
                    <div class="cart"><a href="Cart?operation=retrieveProducts"><div><img class="svg" src="images/cart.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <% }} else{ %>
                    <!-- User che si deve loggare -->
                    <div class="user"><a href="user_log.jsp"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                  	<!-- Cart -->
                    <div class="cart"><a href="Cart?operation=retrieveProducts"><div><img class="svg" src="images/cart.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                  	<% } %>
                    <!-- Phone -->
                    <div class="header_phone d-flex flex-row align-items-center justify-content-start">
                    </div>
                </div>
            </div>
        </header>

        <div class="super_container_inner">
            <div class="super_overlay"></div>

        <script>
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
        </script>