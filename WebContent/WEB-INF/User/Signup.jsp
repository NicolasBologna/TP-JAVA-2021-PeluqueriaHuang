<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Registro - SALÓN LEGEND</title>
  <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="style/login.css">
</head>

<body>
  <main>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-6 login-section-wrapper">
          <div class="brand-wrapper">
            <img src="images/logo.svg" alt="logo" class="logo">
          </div>
          <div class="login-wrapper my-auto">
            <h1 class="login-title">Registrarse</h1>
            <form  action="Signup" method="post">
              <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" class="form-control" placeholder="email@ejemplo.com" required>
              </div>
              <div class="form-group mb-4">
                <label for="password">Contraseña</label>
                <input type="password" name="password" id="password" minlength="8" class="form-control"
                  placeholder="Ingrese su contraseña" required onkeyup='check();'>
              </div>
              <div class="form-group mb-4">
                <label for="repeat-password">Repita la contraseña</label>
                <input type="password" name="repeat-password" id="repeat-password" minlength="8" class="form-control"
                  placeholder="Ingrese otra vez su contraseña" required onkeyup='check();' >
                  <span id='message'></span>
              </div>
              <div class="form-group mb-4">
                <label for="first_name">Nombre</label>
                <input type="text" name="first_name" id="first_name" class="form-control"
                  placeholder="Ingrese su nombre" required>
              </div>
              <div class="form-group mb-4">
                <label for="last_name">Apellido</label>
                <input type="text" name="last_name" id="last_name" class="form-control"
                  placeholder="Ingrese su apellido" required>
              </div>
              <div class="form-group mb-4">
                <label for="dni">Documento Nacional de Identidad</label>
                <input type="text" name="dni" id="dni" class="form-control"
                  placeholder="(Opcional)Ingrese su dni">
              </div>
              <div class="form-group mb-4">
                <label for="phone">Teléfono</label>
                <input type="text" name="phone" id="phone" class="form-control"
                  placeholder="Ingrese su teléfono" required>
              </div>
              <input name="register" id="register" class="btn btn-block login-btn" type="submit">
            </form>
          </div>
        </div>
        <div class="col-sm-6 px-0 d-none d-sm-block">
          <img src="images/signup.jpg" alt="login image" class="login-img">
        </div>
      </div>
    </div>
  </main>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  var check = function() {
	  if (document.getElementById('password').value ==
	    document.getElementById('repeat-password').value) {
	    document.getElementById('message').style.color = 'green';
	    document.getElementById('message').innerHTML = 'Las contraseñas coinciden';
	  } else {
	    document.getElementById('message').style.color = 'red';
	    document.getElementById('message').innerHTML = 'Las contraseñas no coinciden';
	  }
	}
  </script>
</body>

</html>