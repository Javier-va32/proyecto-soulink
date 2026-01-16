document.addEventListener('DOMContentLoaded', async function() {
    console.log("🚀 Login con autenticación backend");

    // Verificar si ya hay sesión activa para actualizar el menú
    verificarSesionEnNavbar();

    // Configurar toggles de contraseña
    setupPasswordToggles();

    // Inicializar validaciones
    initLoginValidation();
    initRegisterValidation();
    initRecoverValidation();
    initTabHandling();
});

// ==================== NAVBAR ====================
function verificarSesionEnNavbar() {
    const userMenuContainer = document.getElementById('userMenuContainer');
    const userMenuText = document.getElementById('userMenuText');
    if (!userMenuContainer || !userMenuText) return;

    const usuarioActual = JSON.parse(localStorage.getItem('usuarioActual') || 'null');

    if (usuarioActual) {
        const primerNombre = usuarioActual.nombre.split(' ')[0];
        userMenuText.innerHTML = `<i class="fas fa-user-circle mr-1"></i>${primerNombre}`;
        userMenuContainer.classList.add('user-logged-in');
        userMenuText.classList.add('text-primary', 'font-weight-bold');
    }
}

// ==================== TOGGLE PASSWORD ====================
function setupPasswordToggles() {
    const togglePassword = document.getElementById('togglePassword');
    if (togglePassword) {
        togglePassword.addEventListener('click', function() {
            togglePasswordVisibility(document.getElementById('loginPassword'), this);
        });
    }
    const toggleRegPassword = document.getElementById('toggleRegPassword');
    if (toggleRegPassword) {
        toggleRegPassword.addEventListener('click', function() {
            togglePasswordVisibility(document.getElementById('regPassword'), this);
        });
    }
}

function togglePasswordVisibility(input, button) {
    const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
    input.setAttribute('type', type);
    const icon = button.querySelector('i');
    icon.className = type === 'text' ? 'fas fa-eye-slash' : 'fas fa-eye';
}

// ==================== VALIDACIONES ====================
function showError(input, message) {
    const formGroup = input.closest('.form-group');
    if (!formGroup) return;
    const existingError = formGroup.querySelector('.error-message');
    if (existingError) existingError.remove();
    input.classList.remove('is-invalid');
    if (message) {
        input.classList.add('is-invalid');
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message invalid-feedback d-block';
        errorDiv.textContent = message;
        formGroup.appendChild(errorDiv);
    }
}

function validateEmail(input) {
    const email = input.value.trim();
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email) { showError(input, 'El email es requerido'); return false; }
    if (!regex.test(email)) { showError(input, 'Email inválido'); return false; }
    showError(input, ''); return true;
}

function validatePassword(input) {
    const password = input.value;
    if (!password) { showError(input, 'La contraseña es requerida'); return false; }
    if (password.length < 8) { showError(input, 'Mínimo 8 caracteres'); return false; }
    showError(input, ''); return true;
}

// ==================== LOGIN ====================
function initLoginValidation() {
    const loginForm = document.getElementById('loginForm');
    if (!loginForm) return;

    const loginEmail = document.getElementById('loginEmail');
    const loginPassword = document.getElementById('loginPassword');

    loginForm.addEventListener('submit', async function(e) {
        e.preventDefault();

        if (!validateEmail(loginEmail) || !validatePassword(loginPassword)) return;

        const email = loginEmail.value.trim();
        const password = loginPassword.value;

        try {
            const response = await fetch('http://localhost:8080/usuarios/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (!response.ok) {
                mostrarErrorLogin("❌ Usuario o contraseña incorrectos");
                return;
            }

            const usuario = await response.json();

            // Guardar sesión
            localStorage.setItem('usuarioActual', JSON.stringify(usuario));
            localStorage.setItem('sesionActiva', 'true');

            mostrarExitoLogin(usuario.nombre);

            setTimeout(() => window.location.href = "../index.html", 2500);

        } catch (error) {
            console.error(error);
            mostrarErrorLogin("❌ Error al conectar con el servidor");
        }
    });
}

function mostrarErrorLogin(mensaje) {
    const erroresPrevios = document.querySelectorAll('.error-login-task10');
    erroresPrevios.forEach(e => e.remove());

    const loginForm = document.getElementById('loginForm');
    const errorDiv = document.createElement('div');
    errorDiv.className = 'alert alert-danger error-login-task10 mt-3';
    errorDiv.textContent = mensaje;
    loginForm.appendChild(errorDiv);
}

function mostrarExitoLogin(nombreUsuario) {
    const loginForm = document.getElementById('loginForm');
    const cardBody = loginForm.closest('.card-body');

    cardBody.innerHTML = `
        <div class="login-success-container d-flex flex-column align-items-center justify-content-center py-5">
            <i class="fas fa-check-circle text-success mb-3" style="font-size:3.5rem"></i>
            <h4 class="mb-2 text-center">¡Bienvenido/a, ${nombreUsuario}!</h4>
            <p class="text-muted mb-4 text-center">Sesión iniciada correctamente</p>
            <div class="spinner-border text-primary mb-3" style="width:2.5rem;height:2.5rem"></div>
            <p class="mb-0 text-center"><strong>Redirigiendo al inicio...</strong></p>
            <a href="../index.html" class="btn btn-primary btn-sm mt-3"><i class="fas fa-home"></i> Ir al inicio ahora</a>
        </div>
    `;
}


// ==================== REGISTER ====================
function initRegisterValidation() {
    const registerForm = document.getElementById('registerForm');
    if (!registerForm) return;

    const regNombre = document.getElementById('regNombre');
    const regApellido = document.getElementById('regApellido');
    const regEmail = document.getElementById('regEmail');
    const regPassword = document.getElementById('regPassword');
    const regConfirmPassword = document.getElementById('regConfirmPassword');
    const acceptTerms = document.getElementById('acceptTerms');

    registerForm.addEventListener('submit', async function(e) {
        e.preventDefault();
        if (!validateEmail(regEmail) || !validatePassword(regPassword) || regPassword.value !== regConfirmPassword.value) {
            alert("❌ Corrige los errores antes de enviar"); return;
        }
        if (!acceptTerms.checked) { alert("❌ Acepta los términos"); return; }

        const nuevoUsuario = {
            nombre: regNombre.value + " " + regApellido.value,
            email: regEmail.value,
            password: regPassword.value,
            id_rol: 2
        };

        try {
            const response = await fetch('http://localhost:8080/usuarios/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(nuevoUsuario)
            });

            if (!response.ok) {
                alert("❌ Error al registrar el usuario"); return;
            }

            const usuarioCreado = await response.json();
            alert(`✅ Usuario registrado: ${usuarioCreado.nombre}`);

            document.getElementById('loginEmail').value = usuarioCreado.email;
            document.querySelector('#register-tab').classList.remove('active');
            document.querySelector('#login-tab').classList.add('active');
            document.querySelector('#register').classList.remove('show','active');
            document.querySelector('#login').classList.add('show','active');

        } catch (error) {
            console.error(error);
            alert("❌ No se pudo conectar con el servidor");
        }
    });
}

// ==================== RECOVER ====================
function initRecoverValidation() {
    const recoverForm = document.getElementById('recoverForm');
    if (!recoverForm) return;
    const recoverEmail = document.getElementById('recoverEmail');
    const recoverSuccess = document.getElementById('recoverSuccess');

    recoverForm.addEventListener('submit', function(e) {
        e.preventDefault();
        if (!validateEmail(recoverEmail)) return;
        recoverSuccess.style.display = 'block';
        setTimeout(() => recoverSuccess.style.display = 'none', 5000);
    });
}

// ==================== UTIL ====================
function initTabHandling() {
    const authTabs = document.querySelectorAll('a[data-toggle="tab"]');
    authTabs.forEach(tab => {
        tab.addEventListener('shown.bs.tab', function(e) {
            const target = e.target.getAttribute('href');
            switch(target) {
                case '#login': clearFormValidation('loginForm'); break;
                case '#register': clearFormValidation('registerForm'); break;
                case '#recover': clearFormValidation('recoverForm'); break;
            }
        });
    });
}

function clearFormValidation(formId) {
    const form = document.getElementById(formId);
    if (!form) return;
    form.querySelectorAll('.is-invalid').forEach(i => i.classList.remove('is-invalid'));
    form.querySelectorAll('.error-message').forEach(e => e.remove());
}
