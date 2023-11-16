// Función para cerrar sesión
function logout() {
    // Obtén el token de acceso
    var accessToken = localStorage.getItem('accessToken');

    // Realiza una petición Fetch para invalidar el token
    fetch('/api/v1/user/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al cerrar sesión');
        }
        limpiarTokenLocalStorage();
        // Si la petición es exitosa, redirige a la página de inicio de sesión
        window.location.href = '/web/login';  // Cambia '/login' con la URL real de tu página de inicio de sesión
    })
    .catch(error => {
        console.error('Error al cerrar sesión:', error);
    });
}

// Asocia la función de logout al evento click del botón
document.addEventListener('DOMContentLoaded', function () {
    var logoutButton = document.getElementById('logoutButton');
    if (logoutButton) {
        logoutButton.addEventListener('click', function () {
            logout();
        });
    }
});
// Función para limpiar el token del almacenamiento local
function limpiarTokenLocalStorage() {
    localStorage.removeItem('tuToken');
}