'use strict';

$('.needs-validation').click(function (event) {
    event.target.closest('form').classList.add('was-validated');
});
