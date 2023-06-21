//Necessita do bootstrap e jquery
//forca da senha

function forcaSenha(senha) {
    var forca = 0;

    var regLetrasMa = /[A-Z]/;
    var regLetrasMi = /[a-z]/;
    var regNumero = /[0-9]/;
    var regEspecial = /[!@#$%&*?]/;

    var tam = false;
    var tamM = false;
    var letrasMa = false;
    var letrasMi = false;
    var numero = false;
    var especial = false;

//    console.clear();
//    console.log('senha: '+senha);

    if (senha.length >= 8)
        tam = true;
    if (senha.length >= 30)
        tamM = true;
    if (regLetrasMa.exec(senha))
        letrasMa = true;
    if (regLetrasMi.exec(senha))
        letrasMi = true;
    if (regNumero.exec(senha))
        numero = true;
    if (regEspecial.exec(senha))
        especial = true;

    if (tam)
        forca += 10;
    if (tamM)
        forca += 10;
    if (letrasMa)
        forca += 10;
    if (letrasMi)
        forca += 10;
    if (letrasMa && letrasMi)
        forca += 20;
    if (numero)
        forca += 20;
    if (especial)
        forca += 20;

//    console.log('força: '+forca);

    return forca;
}


