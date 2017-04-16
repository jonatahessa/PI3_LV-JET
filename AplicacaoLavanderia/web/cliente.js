function main() {
  var btn = document.querySelector('#salvar');
  btn.addEventListener("click", validar);
}

function validar() {
  var camposinvalidos = [];
  var camposvalidos = [];
  var i = 0;
  var j = 0;
  var nome = document.querySelector('#camponome');
  var cpf = document.querySelector('#campocpf');
  var email = document.querySelector('#campoemail');
  var telefone = document.querySelector('#campotelefone');

  if (nome.value == '') {
    camposinvalidos[i] = "#camponome";
    i++;
  } else {
    camposvalidos[j] = "#camponome";
    j++;
  }
  if (cpf.value == '') {
    camposinvalidos[i] = "#campocpf";
    i++;
  } else {
    camposvalidos[j] = "#campocpf";
    j++;
  }
  if (validaemail(email.value) == false) {
    camposinvalidos[i] = "#campoemail";
    i++;
  } else {
    camposvalidos[j] = "#campoemail";
    j++;
  }
  if (telefone.value == '') {
    camposinvalidos[i] = "#campotelefone";
    i++;
  } else {
    camposvalidos[j] = "#campotelefone";
    j++;
  }
  if (i != 0) {
    alert("Verifique os campos destacados!");
    destacacampos(camposinvalidos);
    if (j != 0) {
      retornacampos(camposvalidos);
    }
  } else {
    retornacampos(camposvalidos);
  }
}

function validaemail(email) {
  var valida = false;
  var usuario = email.substring(0, email.indexOf("@"));
  var dominio = email.substring(email.indexOf("@")+ 1, email.length);

if ((usuario.length >=1) &&
    (dominio.length >=3) &&
    (usuario.search("@")==-1) &&
    (dominio.search("@")==-1) &&
    (usuario.search(" ")==-1) &&
    (dominio.search(" ")==-1) &&
    (dominio.search(".")!=-1) &&
    (dominio.indexOf(".") >=1)&&
    (dominio.lastIndexOf(".") < dominio.length - 1)) {
      valida = true;
    } else {
      valida = false;
  }
  return valida;
}

function destacacampos(camposinvalidos) {
  for (var i = 0; i < camposinvalidos.length; i++) {
      document.querySelector(camposinvalidos[i]).className = 'camposdestacados';
    }
}

function retornacampos(camposvalidos) {
  for (var i = 0; i < camposvalidos.length; i++) {
      document.querySelector(camposvalidos[i]).className = 'campos';
    }
}

window.addEventListener("load", main);
