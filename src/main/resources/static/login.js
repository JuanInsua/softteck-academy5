function submitForm() {
    var formData = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    fetch('/api/v1/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la solicitud');
        }
        return response.json();
    })
    .then(data => {
            // Almacenar el token en el almacenamiento local
            guardarTokenEnLocalStorage(data.token);

            // Redirigir a la página de tareas después de un inicio de sesión exitoso
            window.location.href = '/web/toDo';
        })
        .catch(error => {
            // Manejar el error
            alert('Error en el inicio de sesión. Verifica tus credenciales.');
        });
}
// Función para almacenar el token en el almacenamiento local
function guardarTokenEnLocalStorage(token) {
    localStorage.setItem('tuToken', token);
}