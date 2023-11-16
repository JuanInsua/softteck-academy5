document.addEventListener('DOMContentLoaded', function () {
    var tuToken = localStorage.getItem('tuToken');
    var headers = new Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('Authorization', 'Bearer ' + tuToken);
//---------------------CARGA TAREAS-------------------------------------------------------------------------------------
    function loadTasks(page) {
        fetch('http://localhost:8080/api/v1/user/getTasks', {
            method: 'GET',
            headers: headers
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar tareas');
            }
            return response.json();
        })
        .then(data => {
            updateTable(data);
        })
        .catch(error => {
            console.error('Error al cargar tareas: ' + error);
        });
    }
//-----------------------TABLA UPDATE-----------------------------------------------------------------------------------
    function updateTable(data) {
        var tasksTableBody = document.getElementById('tasksTable').getElementsByTagName('tbody')[0];

        if (tasksTableBody != null) {
            // Limpiar la tabla antes de insertar las nuevas filas
            tasksTableBody.innerHTML = "";

            data.tasks.forEach(function (task) {
                var editLink = '<a href="#" class="edit-link" data-task-id="' + task.id + '">Editar</a> ';
                var deleteButton = '<button type="button" class="btn btn-danger eliminar-task" data-task-id="' + task.id + '">Eliminar</button>';
                var row = '<tr>' +
                    '<td>' + task.id + '</td>' +
                    '<td>' + task.description + '</td>' +
                    '<td>' + task.taskName + '</td>' +
                    '<td>' + task.active + '</td>' +
                    '<td>' +
                    editLink +
                    deleteButton +
                    '</td>' +
                    '</tr>';
                tasksTableBody.insertAdjacentHTML('beforeend', row);
            });

            var editLinks = document.querySelectorAll('.edit-link');
            editLinks.forEach(function (editLink) {
                editLink.addEventListener('click', function (event) {
                    event.preventDefault();
                    var taskId = this.getAttribute('data-task-id');
                    openEditModal(taskId, data);
                });
            });

            var deleteButtons = document.querySelectorAll('.eliminar-task');
            deleteButtons.forEach(function (deleteButton) {
                deleteButton.addEventListener('click', function (event) {
                event.preventDefault();
                var taskId = this.getAttribute('data-task-id');
                deleteTask(taskId);
                });
            });
        } else {
            console.error('No se encontró el elemento con ID "tasksTable" en el documento.');
        }
    }
//----------------------------------------------------------------------------------------------------------------------
//-------------------------------EDIT MODAL-----------------------------------------------------------------------------
    function openEditModal(taskId, data) {
            var taskToEdit = data.tasks.find(task => task.id.toString() === taskId);
                document.getElementById('editTaskId').value = taskToEdit.id;
                document.getElementById('editTaskDescription').value = "";
                document.getElementById('editTaskName').value = "";
                $('#editTaskModal').modal('show');
    }

        // Selecciona el botón por su clase
        var saveButton = document.querySelector('.guardar-btn');
        // Agrega un escuchador de eventos al botón
        saveButton.addEventListener('click', function () {
            // Llama a la función saveEditedTask cuando se hace clic en el botón
            saveEditedTask();
        });

    function saveEditedTask() {
        var editedTask = {
            id: document.getElementById('editTaskId').value,
            taskName: document.getElementById('editTaskName').value,
            description: document.getElementById('editTaskDescription').value,
        };

        var requestOptions = {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(editedTask)
        };

        fetch('http://localhost:8080/api/v1/user/editTask', requestOptions)
            .then(response => {
            console.log('Respuesta del servidor:', response);
                if (!response.ok) {
                      throw new Error('Error al actualizar la tarea');
                }
                return response.text();
            })
            .then(data => {
                            // Actualizar solo la fila modificada en la tabla
                            var updatedRow = document.querySelector('tr[data-task-id="' + data.id + '"]');
                            if (updatedRow) {
                                updatedRow.innerHTML = '<td>' + data.id + '</td>' +
                                    '<td>' + data.description + '</td>' +
                                    '<td>' + data.taskName + '</td>' +
                                    '<td>' + data.active + '</td>' +
                                    '<td>' +
                                    '<a href="#" class="edit-link" data-task-id="' + data.id + '">Editar</a> ' +
                                    '<form action="/tasks/delete/' + data.id + '" method="post">' +
                                    '<button type="submit" class="btn btn-danger">Eliminar</button>' +
                                    '</form>' +
                                    '</td>';
                            }

                            $('#editTaskModal').modal('hide');
                            loadTasks(0);
                        })
            .catch(error => {
                console.error('Error al actualizar la tarea:', error);
            });
    }
//----------------------------------------------------------------------------------------------------------------------
//------------------------------CREATE TASK MODAL-----------------------------------------------------------------------
    // Selecciona el botón para agregar tarea por su clase en el modal
            var saveAddButton = document.querySelector('.agregar-btn');
            // Agrega un escuchador de eventos al botón para guardar la tarea agregada
            saveAddButton.addEventListener('click', function () {
                saveAddedTask();
            });
    var addButton = document.querySelector('.crear-btn');
        // Agrega un escuchador de eventos al botón para mostrar el modal de agregar tarea
        addButton.addEventListener('click', function () {
            openAddModal();
        });

        function openAddModal() {
            // Limpiar los campos del formulario de agregar tarea
            document.getElementById('addTaskDescription').value = '';
            document.getElementById('addTaskName').value = '';
            // Mostrar el modal de agregar tarea
            $('#addTaskModal').modal('show');
        }

        function saveAddedTask() {
            var addedTask = {
                description: document.getElementById('addTaskDescription').value,
                taskName: document.getElementById('addTaskName').value,
            };

            var requestOptions = {
                method: 'POST',
                headers: headers,
                body: JSON.stringify(addedTask)
            };

            fetch('http://localhost:8080/api/v1/user/newTask', requestOptions)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error al agregar la tarea');
                    }
                    return response.text();
                })
                .then(data => {
                    $('#addTaskModal').modal('hide');
                    loadTasks(0);
                    showAlert('Tarea agregada exitosamente');
                })
                .catch(error => {
                    console.error('Error al agregar la tarea:', error);
                });
        }
//----------------------------------------------------------------------------------------------------------------------
//------------------------------DELETE TASK-----------------------------------------------------------------------------

            // Función para eliminar la tarea
            function deleteTask(taskId) {
                // Realiza la solicitud al endpoint correspondiente para eliminar la tarea
                var requestOptions = {
                    method: 'POST',  // Usa el método HTTP correcto para eliminar
                    headers: headers
                };

                fetch('http://localhost:8080/api/v1/user/bajaTask/' + taskId, requestOptions)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Error al eliminar la tarea');
                        }
                        return response.text();
                    })
                    .then(data => {
                        // Después de eliminar la tarea, puedes actualizar la tabla si es necesario
                        loadTasks(0);

                        // Muestra la alerta después de eliminar la tarea
                        showAlert('Tarea eliminada exitosamente', 'danger'); // 'danger' para un estilo de alerta de peligro
                    })
                    .catch(error => {
                        console.error('Error al eliminar la tarea:', error);
                    });
            }
//----------------------------------------------------------------------------------------------------------------------
//-----------------------ALERTA DE RESULTADO----------------------------------------------------------------------------
    function showAlert(message, type = 'success') {
        // Crear un elemento de alerta con las clases de Bootstrap
        var alertDiv = document.createElement('div');
        alertDiv.className = 'alert alert-' + type + ' alert-dismissible fade show';
        // Añadir el mensaje a la alerta
        alertDiv.innerHTML = '<strong>' + message + '</strong>' +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
            '<span aria-hidden="true">&times;</span>' +
            '</button>';
        // Obtener una referencia al elemento después del cual deseas agregar la alerta (por ejemplo, el header)
            var referenceElement = document.getElementById('headerToDoApp');
            // Verificar si el elemento de referencia existe
            if (referenceElement) {
                // Insertar la alerta después del elemento de referencia
                referenceElement.parentNode.insertBefore(alertDiv, referenceElement.nextSibling);
                // Configurar un temporizador para cerrar automáticamente la alerta después de 3 segundos
                setTimeout(function () {
                    alertDiv.remove();
                }, 6000);
        }
    }
    window.addEventListener('load', function () {
        loadTasks(0);
    });
});

