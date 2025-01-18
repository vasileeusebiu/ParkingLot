(function () {
    'use strict';

    // Obține toate formularurile cu clasa "needs-validation"
    var forms = document.querySelectorAll('.needs-validation');

    // Parcurge fiecare formular și adaugă un eveniment de submit
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                // Dacă formularul nu este valid, previne trimiterea
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                // Adaugă sau îndepărtează clasa 'was-validated' pentru a arăta stilurile de validare
                form.classList.add('was-validated');
            }, false);
        });

    // Adaugă stiluri pentru câmpurile invalide
    var inputs = document.querySelectorAll('.form-control');
    inputs.forEach(function(input) {
        input.addEventListener('input', function () {
            if (input.validity.valid) {
                input.classList.remove('is-invalid');
            } else {
                input.classList.add('is-invalid');
            }
        });
    });
})();