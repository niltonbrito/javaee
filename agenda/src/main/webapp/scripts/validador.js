/**
 * Validação de campos obrigatorios mo formulario
 * 
 * @author Nilton Brito
 */

function validar() {
	let nome = frmContato.name.value
	let phone = frmContato.phone.value
	if (nome === "") {
		alert('Preencha o campo Nome')
		frmContato.name.focus()
		return false
	} else if (phone === "") {
		alert('Preencha o campo Celular')
		frmContato.phone.focus()
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}