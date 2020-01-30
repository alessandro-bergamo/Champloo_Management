<%@page
		language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import="com.champloo.bean.*"
%>

<%
	UserBean utenteLoggato;
	CartBean cart;
	synchronized(session) 
        {
            utenteLoggato = (UserBean) request.getSession().getAttribute("utenteLoggato");
            cart = (CartBean) request.getSession().getAttribute("cart");
        }
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
                <nav class="main_nav">
                    <ul class="d-flex flex-row align-items-start justify-content-start">
                        <li class="active"><a href="category.jsp">Donna</a></li>
                        <li><a href="category.jsp">Uomo</a></li>
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="#">Contatti</a></li>
                    </ul>
                </nav>
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
                           if(utenteLoggato.getType()==2 || utenteLoggato.getType()==3 || utenteLoggato.getType()==4)
                           {
                   %>
                    <!-- User loggato -->
                    <div class="user"><a href="area_admin.jsp"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <div class="user"> <a href="UserControl?operation=logout"><div><img src="images/logout.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <% } else { %>
                    <div class="user"><a href="user_area.jsp"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <div class="user"> <a href="UserControl?operation=logout"><div><img src="images/logout.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <% }} else { %>
                    <!-- User che si deve loggare -->
                    <div class="user"><a href="user_log.jsp"><div><img src="images/user.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                  	<% } %>
                    <!-- Cart -->
                    <div class="cart"><a href="Cart?operation=retrieveProducts"><div><img class="svg" src="images/cart.svg" alt="https://www.flaticon.com/authors/freepik"></div></a></div>
                    <!-- Phone -->
                    <div class="header_phone d-flex flex-row align-items-center justify-content-start">
                    
                    </div>
                </div>
            </div>
        </header>

        <div class="super_container_inner">
            <div class="super_overlay"></div>