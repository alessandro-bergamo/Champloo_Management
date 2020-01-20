<!DOCTYPE html>
<html>
<body onload="submittaForm()">

<script>
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
</script>

<script>
function submittaForm() {
	var username = getUrlVars()["username"];
	var email = getUrlVars()["email"];
	var firstname = getUrlVars()["firstname"];
	var lastname = getUrlVars()["lastname"];
	var password = getUrlVars()["password"];
	
	var inputUsername = document.getElementById("username");
	var inputEmail = document.getElementById("email");
	var inputName = document.getElementById("firstname");
	var inputLastname = document.getElementById("lastname");
	var inputPassword = document.getElementById("password");
	
	inputUsername.setAttribute('value', username);
	inputEmail.setAttribute('value', email);
	inputName.setAttribute('value', firstname);
	inputLastname.setAttribute('value', lastname);
	inputPassword.setAttribute('value', password);

    document.getElementById("hiddenform").submit();

}
</script>
<form action="UserControl" method="post" id="hiddenform">
<input name="operation" value="validateUser" type="hidden" >
<input name="username" type="hidden" id="username">
<input name="email" type="hidden" id="email">
    <br>
    <br>
<input name="firstname" type="hidden" id="firstname">
<input name="lastname" type="hidden" id="lastname">
<input name="password" type="hidden" id="password">
</form>
</body>
</html>